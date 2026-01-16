package com.device.management.service.impl;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.DeviceFullDTO;
import com.device.management.dto.DictDTO;
import com.device.management.entity.Device;
import com.device.management.dto.DeviceExcelDto;
import com.device.management.entity.DeviceIp;
import com.device.management.entity.Monitor;
import com.device.management.entity.User;
import com.device.management.exception.InvalidIpAddressException;
import com.device.management.exception.ParameterException;
import com.device.management.exception.ResourceConflictException;
import com.device.management.exception.ResourceNotFoundException;
import com.device.management.enums.DictEnum;
import com.device.management.repository.DeviceIpRepository;
import com.device.management.repository.DeviceRepository;
import com.device.management.repository.MonitorRepository;
import com.device.management.repository.SamplingCheckRepository;
import com.device.management.repository.UserRepository;
import com.device.management.service.DevicePermissionService;
import com.device.management.service.DeviceService;
import com.device.management.service.SamplingCheckService;
import jakarta.transaction.Transactional;
import com.device.management.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.PermissionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * デバイス管理サービス実装クラス
 * デバイスのCRUD操作、リスト照会機能を提供
 *
 * @author device-management
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private DeviceIpRepository deviceIpRepository;

    @Autowired
    private SamplingCheckRepository samplingCheckRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DevicePermissionService permissionService;

    @Autowired
    private SamplingCheckService samplingCheckService;

    private static final InetAddressValidator IP_VALIDATOR = InetAddressValidator.getInstance();
    private static final int MAX_PAGE_SIZE = 100;

    // ==================== 公共業務メソッド ====================

    @Override
    @Transactional
    public ApiResponse<DeviceFullDTO> insertDevice(DeviceFullDTO dto) {
        log.info("デバイス追加: {}", dto.getDeviceId());

        // 基本パラメータ検証
        validateBasicParams(dto.getDeviceId(), dto.getCreater());

        // 業務競合チェック
        validateDeviceNotExists(dto.getDeviceId());
        validateUserId(dto.getUserId());
        validateMonitors(dto.getMonitorsInput(), null);
        validateDeviceIps(dto.getDeviceIpsInput(), null);

        // デバイス及び関連データ保存
        Device entity = convertToDeviceEntity(dto);
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        Device saved = deviceRepository.save(entity);

        List<Monitor> monitors = saveMonitors(dto.getMonitorsInput(), saved.getDeviceId(), dto.getCreater(), dto.getUpdater());
        List<DeviceIp> ips = saveDeviceIps(dto.getDeviceIpsInput(), saved.getDeviceId(), dto.getCreater(), dto.getUpdater());

        DeviceFullDTO result = buildDeviceFullDTO(saved, monitors, ips, dto);
        return ApiResponse.success("デバイス追加成功", result);
    }

    @Override
    @Transactional
    public ApiResponse<DeviceFullDTO> updateDeviceById(String deviceId, DeviceFullDTO dto) {
        log.info("デバイス更新: {}", deviceId);

        // パラメータ検証
        if (!StringUtils.hasText(deviceId)) {
            throw new ParameterException("デバイスIDは空にできません");
        }
        if (dto == null) {
            throw new ParameterException("リクエストボディは空にできません");
        }

        // デバイス存在チェック
        Device existDevice = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("デバイスが存在しません: " + deviceId));

        // 業務競合チェック
        validateUserId(dto.getUserId());

        // 古いモニターとIPを先に削除し、その後新しいものを検証（既存データの競合回避）
        deleteMonitorsByDeviceId(deviceId);
        deleteDeviceIpsByDeviceId(deviceId);

        // 新しいモニターとIPを検証（古いデータが削除されたため、他のデバイスによる占有を正しく検証可能）
        validateMonitors(dto.getMonitorsInput(), null);
        validateDeviceIps(dto.getDeviceIpsInput(), null);

        // デバイス及び関連データ更新
        Device entity = convertToDeviceEntity(dto);
        entity.setCreateTime(existDevice.getCreateTime());
        entity.setUpdateTime(LocalDateTime.now());
        Device saved = deviceRepository.save(entity);

        List<Monitor> monitors = saveMonitors(dto.getMonitorsInput(), saved.getDeviceId(), dto.getUpdater(), dto.getUpdater());
        List<DeviceIp> ips = saveDeviceIps(dto.getDeviceIpsInput(), saved.getDeviceId(), dto.getUpdater(), dto.getUpdater());

        DeviceFullDTO result = buildDeviceFullDTO(saved, monitors, ips, dto);
        return ApiResponse.success("デバイス更新成功", result);
    }

    @Override
    @Transactional
    public ApiResponse<String> deleteDevice(String deviceId) {
        log.info("デバイス削除: {}", deviceId);

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("デバイスが存在しません: " + deviceId));

        // 関連データをカスケード削除
        samplingCheckRepository.deleteByDeviceId(deviceId);
        deleteMonitorsByDeviceId(deviceId);
        deleteDeviceIpsByDeviceId(deviceId);
        deviceRepository.delete(device);

        return ApiResponse.success("デバイス削除成功", deviceId);
    }

    @Override
    public Page<DeviceFullDTO> list(String userId, int page, int size) {
        log.info("機器一覧をクエリ: userId={}, page={}, size={}", userId, page, size);

        // パラメータ検証
        validatePageParams(page, size);

        // userIdが提供された場合、ユーザーが存在するかを検証
        if (StringUtils.hasText(userId)) {
            validateUserId(userId);
        }

        // ページングパラメータを構築
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("deviceId").ascending());

        // JPAページングクエリを使用
        Page<Device> devicePage = deviceRepository.findByConditionsWithPagination(
                StringUtils.hasText(userId) ? userId : null,
                pageable
        );

        // 現在ページの機器一覧を取得
        List<Device> devices = devicePage.getContent();

        // 関連データを一括取得（IPとモニター）
        List<String> deviceIds = devices.stream()
                .map(d -> d.getDeviceId().trim())
                .collect(Collectors.toList());
        Map<String, List<DeviceIp>> ipMap = buildDeviceIpMap(deviceIds);
        Map<String, List<Monitor>> monitorMap = buildDeviceMonitorMap(deviceIds);

        // DTOに変換
        List<DeviceFullDTO> dtoList = devices.stream()
                .map(d -> toFullDTOWithRelations(d, ipMap, monitorMap))
                .collect(Collectors.toList());

        // ページング結果を返却
        return new PageImpl<>(dtoList, pageable, devicePage.getTotalElements());
    }

    @Override
    public ApiResponse<DeviceFullDTO> detail(String deviceId) {
        log.info("デバイス詳細照会: {}", deviceId);

        if (!StringUtils.hasText(deviceId)) {
            throw new ParameterException("デバイスIDは空にできません");
        }

        Device device = deviceRepository.findByDeviceIdWithDicts(deviceId.trim());
        if (device == null) {
            throw new ResourceNotFoundException("デバイスが存在しません: " + deviceId.trim());
        }

        List<String> ids = Collections.singletonList(device.getDeviceId().trim());
        DeviceFullDTO dto = toFullDTOWithRelations(device,
                buildDeviceIpMap(ids),
                buildDeviceMonitorMap(ids));
        return ApiResponse.success("照会成功", dto);
    }

    // ==================== プライベート検証メソッド ====================

    /**
     * 基本パラメータ検証
     */
    private void validateBasicParams(String deviceId, String creater) {
        if (!StringUtils.hasText(deviceId)) {
            throw new ParameterException("デバイスIDは空にできません");
        }
        if (!StringUtils.hasText(creater)) {
            throw new ParameterException("作成者は空にできません");
        }
    }

    /**
     * ページングパラメータ検証
     */
    private void validatePageParams(int page, int size) {
        if (page < 1) {
            throw new IllegalArgumentException("ページ番号は1以上でなければなりません");
        }
        if (size < 1 || size > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("1ページのサイズは1から" + MAX_PAGE_SIZE + "の間でなければなりません");
        }
    }

    /**
     * デバイスが存在しないことを検証
     */
    private void validateDeviceNotExists(String deviceId) {
        if (deviceRepository.existsByDeviceId(deviceId)) {
            throw new ResourceConflictException("デバイスが既に存在します: " + deviceId);
        }
    }

    /**
     * ユーザーIDが存在することを検証（user_idが提供された場合）
     */
    private void validateUserId(String userId) {
        if (StringUtils.hasText(userId)) {
            if (!userRepository.findByUserId(userId).isPresent()) {
                throw new ResourceNotFoundException("ユーザーIDが存在しません: " + userId);
            }
        }
    }

    /**
     * モニター検証（統一メソッド、追加・更新シナリオ対応）
     *
     * @param monitors モニターリスト
     * @param deviceId デバイスID（更新シナリオで渡す、追加シナリオではnull）
     */
    private void validateMonitors(List<Monitor> monitors, String deviceId) {
        if (CollectionUtils.isEmpty(monitors)) {
            return;
        }

        Set<String> names = new HashSet<>();
        for (Monitor monitor : monitors) {
            String monitorName = monitor.getMonitorName();
            if (!StringUtils.hasText(monitorName)) {
                throw new ParameterException("モニター名は空にできません");
            }

            // 检查重复
            if (names.contains(monitorName)) {
                throw new ParameterException("モニター名が重複しています: " + monitorName);
            }
            names.add(monitorName);

            // 检查是否被其他设备占用
            if (monitorRepository.existsByMonitorName(monitorName)) {
                Monitor exist = monitorRepository.findByMonitorName(monitorName);
                // 更新场景：如果是当前设备的监视器，允许；否则报错
                if (deviceId == null || !exist.getDeviceId().equals(deviceId)) {
                    throw new ResourceConflictException("モニター名が他のデバイスで使用されています: " + monitorName + ", 设备ID: " + exist.getDeviceId());
                }
            }
        }
    }

    /**
     * IPアドレス検証（統一メソッド、追加・更新シナリオ対応）
     *
     * @param deviceIps IPアドレスリスト
     * @param deviceId  デバイスID（更新シナリオで渡す、追加シナリオではnull）
     */
    private void validateDeviceIps(List<DeviceIp> deviceIps, String deviceId) {
        if (CollectionUtils.isEmpty(deviceIps)) {
            return;
        }

        Set<String> ipSet = new HashSet<>();
        for (DeviceIp ip : deviceIps) {
            String addr = ip.getIpAddress() != null ? ip.getIpAddress().trim() : null;
            if (!StringUtils.hasText(addr)) {
                throw new ParameterException("IPアドレスは空にできません");
            }

            // 重複チェック
            if (ipSet.contains(addr)) {
                throw new ParameterException("IPアドレスが重複しています: " + addr);
            }
            ipSet.add(addr);

            // 形式検証
            validateIpv4(addr);

            // 他のデバイスで占有されているかチェック
            if (deviceIpRepository.existsByIpAddress(addr)) {
                DeviceIp exist = deviceIpRepository.findByIpAddress(addr);
                // 更新シナリオ：現在のデバイスのIPであれば許可；それ以外はエラー
                if (deviceId == null || !exist.getDeviceId().equals(deviceId)) {
                    throw new ResourceConflictException("IPアドレスが他のデバイスで使用されています: " + addr + ", デバイスID: " + exist.getDeviceId());
                }
            }
        }
    }

    /**
     * IPv4アドレス形式検証
     */
    private void validateIpv4(String addr) {
        if (!IP_VALIDATOR.isValidInet4Address(addr)) {
            log.warn("IPアドレス形式が無効: {}", addr);
            throw new InvalidIpAddressException("IPアドレス形式が無効: " + addr);
        }
    }

    // ==================== プライベート変換メソッド ====================

    /**
     * DTOをエンティティに変換
     */
    private Device convertToDeviceEntity(DeviceFullDTO dto) {
        Device device = new Device();
        device.setDeviceId(dto.getDeviceId());
        device.setDeviceModel(dto.getDeviceModel());
        device.setComputerName(dto.getComputerName());
        device.setLoginUsername(dto.getLoginUsername());
        device.setProject(dto.getProject());
        device.setDevRoom(dto.getDevRoom());
        device.setUserId(dto.getUserId());
        device.setRemark(dto.getRemark());
        device.setSelfConfirmId(dto.getSelfConfirmId());
        device.setOsId(dto.getOsId());
        device.setMemoryId(dto.getMemoryId());
        device.setSsdId(dto.getSsdId());
        device.setHddId(dto.getHddId());
        device.setCreater(dto.getCreater());
        device.setUpdater(dto.getUpdater());
        return device;
    }

    /**
     * 完全なデバイスDTO構築
     */
    private DeviceFullDTO buildDeviceFullDTO(Device device, List<Monitor> monitors, List<DeviceIp> ips, DeviceFullDTO orig) {
        DeviceFullDTO dto = new DeviceFullDTO();
        dto.setDeviceId(device.getDeviceId());
        dto.setDeviceModel(device.getDeviceModel());
        dto.setComputerName(device.getComputerName());
        dto.setLoginUsername(device.getLoginUsername());
        dto.setProject(device.getProject());
        dto.setDevRoom(device.getDevRoom());
        dto.setUserId(device.getUserId());
        dto.setRemark(device.getRemark());

        dto.setSelfConfirmId(device.getSelfConfirmId());
        dto.setOsId(device.getOsId());
        dto.setMemoryId(device.getMemoryId());
        dto.setSsdId(device.getSsdId());
        dto.setHddId(device.getHddId());
        dto.setCreater(device.getCreater());
        dto.setUpdater(device.getUpdater());
        dto.setMonitorsInput(monitors != null ? monitors : Collections.emptyList());
        dto.setDeviceIpsInput(ips != null ? ips : Collections.emptyList());
        dto.setName(orig != null ? orig.getName() : null);
        dto.setDeptId(orig != null ? orig.getDeptId() : null);
        return dto;
    }

    /**
     * 関連データを含む完全なDTOに変換
     */
    private DeviceFullDTO toFullDTOWithRelations(Device device,
                                                  Map<String, List<DeviceIp>> ipMap,
                                                  Map<String, List<Monitor>> monitorMap) {
        DeviceFullDTO dto = toFullBasicDTO(device);
        String key = device.getDeviceId().trim();
        List<DeviceIp> ips = ipMap.getOrDefault(key, Collections.emptyList());
        List<Monitor> monitors = monitorMap.getOrDefault(key, Collections.emptyList());
        dto.setDeviceIpsInput(ips);
        dto.setMonitorsInput(monitors);
        return dto;
    }

    /**
     * 基本DTOに変換（関連データを含まない）
     */
    private DeviceFullDTO toFullBasicDTO(Device device) {
        User user = device.getUser();
        DeviceFullDTO dto = DeviceFullDTO.builder()
                .deviceId(device.getDeviceId().trim())
                .userId(device.getUserId())
                .name(user != null ? user.getName() : null)
                .deptId(user != null ? user.getDeptId() : null)
                .deviceModel(device.getDeviceModel())
                .computerName(device.getComputerName())
                .loginUsername(device.getLoginUsername())
                .project(device.getProject())
                .devRoom(device.getDevRoom())
                .remark(device.getRemark())
                .selfConfirmId(device.getSelfConfirmId())
                .osId(device.getOsId())
                .memoryId(device.getMemoryId())
                .ssdId(device.getSsdId())
                .hddId(device.getHddId())
                .createTime(device.getCreateTime())
                .creater(device.getCreater())
                .updateTime(device.getUpdateTime())
                .updater(device.getUpdater())
                .monitorsInput(Collections.emptyList())
                .deviceIpsInput(Collections.emptyList())
                .build();

        // 辞書データ設定
        dto.setSelfConfirmDict(DictDTO.fromEntity(device.getSelfConfirmDict()));
        dto.setOsDict(DictDTO.fromEntity(device.getOsDict()));
        dto.setMemoryDict(DictDTO.fromEntity(device.getMemoryDict()));
        dto.setSsdDict(DictDTO.fromEntity(device.getSsdDict()));
        dto.setHddDict(DictDTO.fromEntity(device.getHddDict()));

        return dto;
    }

    // ==================== プライベートデータ操作メソッド ====================

    /**
     * モニターリスト保存
     */
    private List<Monitor> saveMonitors(List<Monitor> list, String deviceId, String creater, String updater) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        LocalDateTime now = LocalDateTime.now();
        return list.stream()
                .map(m -> {
                    // 新しいMonitorオブジェクトを作成し、主キーが正しく生成されることを確認
                    Monitor monitor = new Monitor();
                    monitor.setMonitorId(null); // 明示的にnullを設定し、主キー自動生成を確実にする
                    monitor.setMonitorName(m.getMonitorName());
                    monitor.setDeviceId(deviceId);
                    monitor.setCreater(creater);
                    monitor.setUpdater(updater);
                    monitor.setCreateTime(now);
                    monitor.setUpdateTime(now);
                    return monitorRepository.saveAndFlush(monitor);
                })
                .collect(Collectors.toList());
    }

    /**
     * IPアドレスリスト保存
     */
    private List<DeviceIp> saveDeviceIps(List<DeviceIp> list, String deviceId, String creater, String updater) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        LocalDateTime now = LocalDateTime.now();
        return list.stream()
                .map(ip -> {
                    // 新しいDeviceIpオブジェクトを作成し、主キーが正しく生成されることを確認
                    DeviceIp deviceIp = new DeviceIp();
                    deviceIp.setIpId(null); // 明示的にnullを設定し、主キー自動生成を確実にする
                    deviceIp.setIpAddress(ip.getIpAddress());
                    deviceIp.setDeviceId(deviceId);
                    deviceIp.setCreater(creater);
                    deviceIp.setUpdater(updater);
                    deviceIp.setCreateTime(now);
                    deviceIp.setUpdateTime(now);
                    return deviceIpRepository.saveAndFlush(deviceIp);
                })
                .collect(Collectors.toList());
    }

    /**
     * デバイスのすべてのモニターを削除
     */
    private void deleteMonitorsByDeviceId(String deviceId) {
        List<Monitor> monitors = monitorRepository.findByDeviceId(deviceId);
        if (!CollectionUtils.isEmpty(monitors)) {
            monitorRepository.deleteAll(monitors);
        }
    }

    /**
     * デバイスのすべてのIPアドレスを削除
     */
    private void deleteDeviceIpsByDeviceId(String deviceId) {
        List<DeviceIp> deviceIps = deviceIpRepository.findByDeviceId(deviceId);
        if (!CollectionUtils.isEmpty(deviceIps)) {
            deviceIpRepository.deleteAll(deviceIps);
        }
    }

    /**
     * デバイスIPアドレスマッピング構築（統一メソッド）
     */
    private Map<String, List<DeviceIp>> buildDeviceIpMap(List<String> deviceIds) {
        if (CollectionUtils.isEmpty(deviceIds)) {
            return Collections.emptyMap();
        }
        return buildEntityMap(deviceRepository.findDeviceIpsByDeviceIds(deviceIds), DeviceIp.class);
    }

    /**
     * デバイスモニターマッピング構築（統一メソッド）
     */
    private Map<String, List<Monitor>> buildDeviceMonitorMap(List<String> deviceIds) {
        if (CollectionUtils.isEmpty(deviceIds)) {
            return Collections.emptyMap();
        }
        return buildEntityMap(deviceRepository.findMonitorsByDeviceIds(deviceIds), Monitor.class);
    }

    /**
     * エンティティマッピングの汎用メソッド構築
     */
    @SuppressWarnings("unchecked")
    private <T> Map<String, List<T>> buildEntityMap(List<Object[]> rows, Class<T> entityClass) {
        Map<String, List<T>> map = new HashMap<>();
        for (Object[] row : rows) {
            if (row.length >= 2 && row[0] != null && row[1] != null) {
                String deviceId = ((String) row[0]).trim();
                T entity = (T) row[1];
                map.computeIfAbsent(deviceId, k -> new ArrayList<>()).add(entity);
            }
        }
        return map;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public ApiResponse<String> importDeviceExcel(MultipartFile file, int startRow) {
        if (file.isEmpty()) {
            return new ApiResponse<>(400, "文件为空", null);
        }

               try {
            // 使用通用工具类解析Excel
            List<DeviceExcelDto> excelDataList = ExcelUtil.importExcel(file, DeviceExcelDto.class,startRow);
            List<DeviceExcelDto> normalizedList = preprocessExcelDtos(excelDataList);

            log.info("Excel数据解析完成:"+normalizedList);
            saveDeviceData(normalizedList);

            return new ApiResponse<>(200, "导入成功，共处理 " + normalizedList.size() + " 条数据", null);
        } catch (Exception e) {
            log.error("Excel导入失败", e);
            return new ApiResponse<>(500, "Excel导入失败: " + e.getMessage(), null);
        }
    }

private List<DeviceExcelDto> preprocessExcelDtos(List<DeviceExcelDto> input) {
    if (input == null || input.isEmpty()) return Collections.emptyList();
    List<DeviceExcelDto> out = new ArrayList<>();
    String lastUserId = null;
    String lastDeviceId = null;

    for (DeviceExcelDto dto : input) {
        if (dto == null) continue;

        // 处理 "-" 值并 trim
        dto.setUserId(processStringField(dto.getUserId()));
        dto.setUserName(processStringField(dto.getUserName()));
        dto.setDepartment(processStringField(dto.getDepartment()));
        dto.setDeviceId(processStringField(dto.getDeviceId()));
        dto.setMonitorDeviceId(processStringField(dto.getMonitorDeviceId()));
        dto.setDeviceModel(processStringField(dto.getDeviceModel()));
        dto.setComputerName(processStringField(dto.getComputerName()));
        dto.setIpAddress(processStringField(dto.getIpAddress()));
        dto.setOs(processStringField(dto.getOs()));
        dto.setMemory(processStringField(dto.getMemory()));
        dto.setSsd(processStringField(dto.getSsd()));
        dto.setHdd(processStringField(dto.getHdd()));
        dto.setLoginUsername(processStringField(dto.getLoginUsername()));
        dto.setProject(processStringField(dto.getProject()));
        dto.setDevRoom(processStringField(dto.getDevRoom()));
        dto.setRemark(processStringField(dto.getRemark()));
        dto.setSelfConfirm(processStringField(dto.getSelfConfirm()));

        boolean hasUser = StringUtils.hasText(dto.getUserId()) || StringUtils.hasText(dto.getUserName()) || StringUtils.hasText(dto.getDepartment());
        boolean hasDevice = StringUtils.hasText(dto.getDeviceId()) || StringUtils.hasText(dto.getMonitorDeviceId()) ||
                StringUtils.hasText(dto.getDeviceModel()) || StringUtils.hasText(dto.getComputerName()) ||
                StringUtils.hasText(dto.getIpAddress()) || StringUtils.hasText(dto.getLoginUsername()) ||
                StringUtils.hasText(dto.getProject()) || StringUtils.hasText(dto.getDevRoom()) ||
                StringUtils.hasText(dto.getRemark()) || StringUtils.hasText(dto.getOs()) ||
                StringUtils.hasText(dto.getMemory()) || StringUtils.hasText(dto.getSsd()) ||
                StringUtils.hasText(dto.getHdd());

        if (!hasUser && !hasDevice) continue;

        // 如果当前行有用户信息，则更新最后的用户信息
        if (hasUser) {
            lastUserId = dto.getUserId();
        } else {
            // 如果当前行没有用户信息，但之前有用户信息，则继承用户信息
            if (StringUtils.hasText(lastUserId)) {
                if (!StringUtils.hasText(dto.getUserId())) dto.setUserId(lastUserId);
                if (!StringUtils.hasText(dto.getUserName())) dto.setUserName(dto.getUserId()); // 使用用户ID作为用户名
            } else {
                continue;
            }
        }

        // 设备ID继承逻辑：如果当前行没有设备ID但用户ID与上一行相同，则继承上一行的设备ID
        if (!StringUtils.hasText(dto.getDeviceId()) &&
            StringUtils.hasText(lastUserId) &&
            lastUserId.equals(dto.getUserId()) &&
            StringUtils.hasText(lastDeviceId)) {
            dto.setDeviceId(lastDeviceId);
        } else if (StringUtils.hasText(dto.getDeviceId())) {
            // 如果当前行有设备ID，则更新lastDeviceId
            lastDeviceId = dto.getDeviceId();
        }

        out.add(dto);
    }
    return out;
}



    /**
     * 处理字符串字段：去除首尾空白并将"-"转换为空值
     */
    private String processStringField(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        String trimmedValue = value.trim();
        return "-".equals(trimmedValue) ? null : trimmedValue;
    }

    /**
     * 导入数据库
     */

   /* private void saveDeviceData(List<DeviceExcelDto> list) {
        for (DeviceExcelDto dto : list) {
            String deviceId = dto.getDeviceId() != null ? dto.getDeviceId().trim() : null;
            String userId = dto.getUserId() != null ? dto.getUserId().trim() : null;
            if (!StringUtils.hasText(userId) || !userRepository.findByUserId(userId).isPresent()) continue;

            Device device = new Device();
            device.setDeviceId(deviceId);
            device.setDeviceModel(dto.getDeviceModel());
            device.setComputerName(dto.getComputerName());

            if (dto.getLoginUsername() != null && !dto.getLoginUsername().isEmpty()) {
                device.setLoginUsername(dto.getLoginUsername());
            } else {
                device.setLoginUsername(dto.getUserName());
            }

            device.setUserId(userId);
            device.setProject(dto.getProject());
            device.setDevRoom(dto.getDevRoom());
            device.setRemark(dto.getRemark());

            device.setOsId(mapOsToId(dto.getOs()));
            device.setMemoryId(mapMemoryToId(dto.getMemory()));
            device.setSsdId(mapSsdToId(dto.getSsd()));
            device.setHddId(mapHddToId(dto.getHdd()));
            device.setSelfConfirmId(mapSelfConfirmToId(dto.getSelfConfirm()));

            device.setCreateTime(LocalDateTime.now());
            device.setUpdateTime(LocalDateTime.now());
            device.setCreater("SYSTEM");
            device.setUpdater("SYSTEM");

            deviceRepository.save(device);

            if (dto.getIpAddress() != null && !dto.getIpAddress().isEmpty()) {
                String[] ips = dto.getIpAddress().split("[\\n\\r,;]+");
                for (String ip : ips) {
                    if (ip.trim().isEmpty()) continue;
                    DeviceIp deviceIp = new DeviceIp();
                    deviceIp.setDeviceId(deviceId);
                    deviceIp.setIpAddress(ip.trim());
                    deviceIp.setCreateTime(LocalDateTime.now());
                    deviceIp.setUpdateTime(LocalDateTime.now());
                    deviceIp.setCreater("SYSTEM");
                    deviceIp.setUpdater("SYSTEM");
                    deviceIpRepository.save(deviceIp);
                }
            }
        }
    }*/

    private void saveDeviceData(List<DeviceExcelDto> list) {
        for (DeviceExcelDto dto : list) {
            String deviceId = dto.getDeviceId() != null ? dto.getDeviceId().trim() : null;
            if (!StringUtils.hasText(deviceId)) continue;

            String userId = dto.getUserId() != null ? dto.getUserId().trim() : null;
            if (!StringUtils.hasText(userId)) continue;

            // 检查用户是否存在，如果不存在则创建新用户
            User user = userRepository.findByUserId(userId).orElse(null);
            if (user == null) {
                // 创建新用户
                user = new User();
                user.setUserId(userId);

                // 设置用户名，优先使用userName，如果没有则使用loginUsername
                if (StringUtils.hasText(dto.getUserName())) {
                    user.setName(dto.getUserName().trim());
                } else if (StringUtils.hasText(dto.getLoginUsername())) {
                    // 如果没有提供用户名，尝试从登录用户名中提取
                    user.setName(dto.getLoginUsername().trim());
                } else {
                    // 如果都没有，使用默认名称或设备ID相关信息
                    user.setName("Imported User"); // 或者可以设置为其他默认值
                }

                // 设置部门信息
                if (StringUtils.hasText(dto.getDepartment())) {
                    user.setDeptId(dto.getDepartment().trim());
                } else {
                    user.setDeptId("UNKNOWN"); // 默认部门
                }

                // 设置为普通用户类型（根据您的业务需求调整）
                user.setUserTypeId(DictEnum.USER_TYPE_USER.getDictId()); // 假设这是普通用户类型

                // 设置默认密码或其他必要字段
                // 注意：实际应用中应该设置一个临时密码并强制用户更改
                user.setPassword("5npjufQ0AT0RkEDe6Rcnsw=="); // 默认密码，实际应用中需要更安全的处理

                user.setCreateTime(LocalDateTime.now());
                user.setUpdateTime(LocalDateTime.now());
                user.setCreater("SYSTEM");
                user.setUpdater("SYSTEM");

                // 保存新用户
                user = userRepository.save(user);
            }

            Device device = new Device();
            device.setDeviceId(deviceId);
            device.setDeviceModel(dto.getDeviceModel());
            device.setComputerName(dto.getComputerName());

            if (dto.getLoginUsername() != null && !dto.getLoginUsername().isEmpty()) {
                device.setLoginUsername(dto.getLoginUsername());
            } else {
                device.setLoginUsername(dto.getUserName());
            }

            device.setUserId(userId);
            device.setProject(dto.getProject());
            device.setDevRoom(dto.getDevRoom());
            device.setRemark(dto.getRemark());

            device.setOsId(mapOsToId(dto.getOs()));
            device.setMemoryId(mapMemoryToId(dto.getMemory()));
            device.setSsdId(mapSsdToId(dto.getSsd()));
            device.setHddId(mapHddToId(dto.getHdd()));
            device.setSelfConfirmId(mapSelfConfirmToId(dto.getSelfConfirm()));

            device.setCreateTime(LocalDateTime.now());
            device.setUpdateTime(LocalDateTime.now());
            device.setCreater("SYSTEM");
            device.setUpdater("SYSTEM");

            deviceRepository.save(device);
            // 保存显示器信息（如果存在）
            if (StringUtils.hasText(dto.getMonitorDeviceId())) {
                // 处理可能的多个显示器（如果有分隔符的话）
                String[] monitorNames = dto.getMonitorDeviceId().split("[\\n\\r,;]+");
                for (String monitorName : monitorNames) {
                    if (monitorName.trim().isEmpty()) continue;

                    // 检查显示器是否已存在
                    if (!monitorRepository.existsByMonitorName(monitorName.trim())) {
                        Monitor monitor = new Monitor();
                        monitor.setMonitorName(monitorName.trim());
                        monitor.setDeviceId(deviceId);
                        monitor.setCreateTime(LocalDateTime.now());
                        monitor.setUpdateTime(LocalDateTime.now());
                        monitor.setCreater("SYSTEM");
                        monitor.setUpdater("SYSTEM");

                        monitorRepository.save(monitor);
                    }
                }
            }

            // 保存IP地址信息
            if (dto.getIpAddress() != null && !dto.getIpAddress().isEmpty()) {
                String[] ips = dto.getIpAddress().split("[\\n\\r,;]+");
                for (String ip : ips) {
                    if (ip.trim().isEmpty()) continue;
                    DeviceIp deviceIp = new DeviceIp();
                    deviceIp.setDeviceId(deviceId);
                    deviceIp.setIpAddress(ip.trim());
                    deviceIp.setCreateTime(LocalDateTime.now());
                    deviceIp.setUpdateTime(LocalDateTime.now());
                    deviceIp.setCreater("SYSTEM");
                    deviceIp.setUpdater("SYSTEM");
                    deviceIpRepository.save(deviceIp);
                }
            }

        }
        permissionService.batchImportPermissionFromExcel(list);
        samplingCheckService.batchImport( list);
    }



    // 辅助映射方法 - 根据 DictEnum 定义
    private Long mapOsToId(String osStr) {
        if (osStr == null) return null;
        String lower = osStr.trim().toLowerCase().replace(" ", "");
        if (lower.contains("win") && lower.contains("10")) return DictEnum.OS_TYPE_WINDOWS_10.getDictId();
        if (lower.contains("win") && lower.contains("11")) return DictEnum.OS_TYPE_WINDOWS_11.getDictId();
        if (lower.contains("mac") || lower.contains("macos")) return DictEnum.OS_TYPE_MACOS_VENTURA.getDictId();
        if (lower.contains("linux") || lower.contains("ubuntu")) return DictEnum.OS_TYPE_LINUX_UBUNTU_22_04.getDictId();
        return null;
    }

    private Long mapMemoryToId(String memStr) {
        if (memStr == null) return null;
        String val = memStr.replaceAll("[^0-9]", "");
        if ("8".equals(val)) return DictEnum.MEMORY_SIZE_8GB.getDictId();
        if ("16".equals(val)) return DictEnum.MEMORY_SIZE_16GB.getDictId();
        if ("32".equals(val)) return DictEnum.MEMORY_SIZE_32GB.getDictId();
        if ("64".equals(val)) return DictEnum.MEMORY_SIZE_64GB.getDictId();
        return null;
    }

    private Long mapSsdToId(String ssdStr) {
        if (ssdStr == null) return null;
        Integer max = extractMaxNumber(ssdStr);
        if (max == null) return null;
        if (max == 256) return DictEnum.SSD_SIZE_256GB.getDictId();
        if (max == 500) return DictEnum.SSD_SIZE_512GB.getDictId();
        if (max == 512) return DictEnum.SSD_SIZE_512GB.getDictId();
        if (max == 1000 || max == 1024 || max == 1) return DictEnum.SSD_SIZE_1TB.getDictId();
        if (max == 2000 || max == 2048 || max == 2) return DictEnum.SSD_SIZE_2TB.getDictId();
        return null;
    }

    private Long mapHddToId(String hddStr) {
        if (hddStr == null) return null;
        Integer max = extractMaxNumber(hddStr);
        if (max == null) return null;
        if (max == 1000 || max == 1024 || max == 1) return DictEnum.HDD_SIZE_1TB.getDictId();
        if (max == 2000 || max == 2048 || max == 2) return DictEnum.HDD_SIZE_2TB.getDictId();
        if (max == 4000 || max == 4096 || max == 4) return DictEnum.HDD_SIZE_4TB.getDictId();
        return null;
    }

    private Long mapSelfConfirmToId(String confirmStr) {
        if (confirmStr == null) return null;
        String s = confirmStr.trim().toLowerCase();
        if ("1".equals(s) || "true".equals(s) || "yes".equals(s) || "y".equals(s) || s.contains("确认") || s.contains("已确认") || s.contains("確認") || s.contains("是")) {
            return DictEnum.CONFIRM_STATUS_CONFIRMED.getDictId();
        }
        return DictEnum.CONFIRM_STATUS_UNCONFIRMED.getDictId();
    }

    private Integer extractMaxNumber(String text) {
        String[] parts = text.trim().split("[^0-9]+");
        Integer max = null;
        for (String p : parts) {
            if (p.isEmpty()) continue;
            int n;
            try {
                n = Integer.parseInt(p);
            } catch (NumberFormatException e) {
                continue;
            }
            if (max == null || n > max) max = n;
        }
        return max;
    }
}
