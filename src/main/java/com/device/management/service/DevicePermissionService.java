package com.device.management.service;

import com.device.management.dto.ApiResponse;
import com.device.management.dto.PermissionInsertDTO;
import com.device.management.dto.PermissionUpdateDTO;
import com.device.management.dto.PermissionsListDTO;
import com.device.management.entity.Device;
import com.device.management.entity.DevicePermission;
import com.device.management.entity.User;
import com.device.management.exception.ResourceNotFoundException;
import com.device.management.exception.UnauthorizedException;
import com.device.management.repository.DevicePermissionRepository;
import com.device.management.repository.DeviceRepository;
import com.device.management.repository.DictRepository;
import com.device.management.security.JwtTokenProvider;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.device.management.dto.DeviceExcelDto;
import java.util.Set;
import java.util.HashSet;


@Slf4j
@Service
public class DevicePermissionService {
    @Resource
    DevicePermissionRepository devicePermissionRepository;
    @Resource
    DeviceRepository deviceRepository;
    @Resource
    HttpServletRequest httpServletRequest;
    @Resource
    private JwtTokenProvider jwtTokenProvider;
    @Resource
    private DictRepository dictRepository;

    public ApiResponse<?> addPermissions(PermissionInsertDTO permissionInsertDTO) {
        Device deviceInfo = deviceRepository.findDeviceByDeviceId(permissionInsertDTO.getDeviceId());
        if (deviceInfo == null) {
            return ApiResponse.error(30001, "デバイスが存在しません");
        }

        DevicePermission devicePermissions = devicePermissionRepository.findDevicePermissionsByDevice(deviceInfo);

        if (devicePermissions != null) {
            return ApiResponse.error(30002, "デバイスにはすでに権限情報があります");
        }

        String permissionId = UUID.randomUUID().toString();

        DevicePermission devicePermission = DevicePermission.builder()
                .permissionId(permissionId)
                .device(deviceInfo)
                .domainStatusId(permissionInsertDTO.getDomainStatus())
                .domainGroup(permissionInsertDTO.getDomainGroup())
                .noDomainReason(permissionInsertDTO.getNoDomainReason())
                .smartitStatusId(permissionInsertDTO.getSmartitStatus())
                .noSmartitReason(permissionInsertDTO.getNoSmartitReason())
                .usbStatusId(permissionInsertDTO.getUsbStatus())
                .usbReason(permissionInsertDTO.getUsbReason())
                .usbExpireDate(permissionInsertDTO.getUsbExpireDate())
                .antivirusStatusId(permissionInsertDTO.getAntivirusStatus())
                .noSymantecReason(permissionInsertDTO.getNoSymantecReason())
                .remark(permissionInsertDTO.getRemark())
                .createTime(LocalDateTime.now())
                .creater(jwtTokenProvider.getUserIdFromToken(extractTokenFromRequest()))
                .updateTime(LocalDateTime.now())
                .updater(jwtTokenProvider.getUserIdFromToken(extractTokenFromRequest()))
                .build();
        devicePermissionRepository.save(devicePermission);
        permissionInsertDTO.setPermissionId(permissionId);
        return ApiResponse.success("権限追加成功", permissionInsertDTO);
    }

    public ApiResponse<List<PermissionsListDTO>> getPermissions(Integer page, Integer size, User user, Device deviceInfo) {
        if (jwtTokenProvider.getUserTypeIdFromToken(extractTokenFromRequest()) != 11) {
            if (user == null) {
                user = new User();
            }
            //管理者ではないため、自分のuserIdに設定する
            user.setUserId(jwtTokenProvider.getUserIdFromToken(extractTokenFromRequest()));
        }

        // ページネーションパラメータを検証する
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        } else if (size > 20) {
            size = 20;
        }

        // ページ分割オブジェクトを構築し、権限番号で並べる
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "permissionId"));

        // クエリ条件を構築する
        Specification<DevicePermission> spec = buildQuerySpecification(user, deviceInfo);

        // ページネーションクエリを実行する
        Page<DevicePermission> permissionPage = devicePermissionRepository.findAll(spec, pageable);

        // DTOリストに変換する
        List<PermissionsListDTO> dtoList = convertToDTOList(permissionPage.getContent());

        // 応答を構築する
        return ApiResponse.page(dtoList, permissionPage.getTotalElements(), permissionPage.getNumber() + 1, permissionPage.getSize());
    }

    // クエリ条件を構築する
    private Specification<DevicePermission> buildQuerySpecification(User user, Device deviceInfo) {
        return (Root<DevicePermission> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // DISTINCT を使用して、1対多の関係によるデータの重複を回避する
            if (query.getResultType().equals(DevicePermission.class)) {
                query.distinct(true);
            }

            // ユーザーIDで検索
            if (user != null && StringUtils.hasText(user.getUserId())) {
                // Deviceに関連付けられたユーザーを検索する
                Join<DevicePermission, Device> deviceJoin = root.join("device", JoinType.INNER);
                Join<Device, User> userJoin = deviceJoin.join("user", JoinType.INNER);
                predicates.add(cb.equal(userJoin.get("userId"), user.getUserId()));
            }

            // デバイスIDで検索
            if (deviceInfo != null && StringUtils.hasText(deviceInfo.getDeviceId())) {
                Join<DevicePermission, Device> deviceJoin = root.join("device", JoinType.INNER);
                predicates.add(cb.equal(deviceJoin.get("deviceId"), deviceInfo.getDeviceId()));
            }

            // もし条件がなければ、すべての記録を返します
            if (predicates.isEmpty()) {
                return cb.conjunction(); // true を返し、すべてを検索します
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    // エンティティのリストをDTOのリストに変換する
    private List<PermissionsListDTO> convertToDTOList(List<DevicePermission> permissions) {
        return permissions.stream().map(this::convertToDTO1).collect(Collectors.toList());
    }

    // 単一のエンティティをDTOに変換する
    private PermissionsListDTO convertToDTO1(DevicePermission permission) {
        if (permission == null) {
            return null;
        }

        PermissionsListDTO dto = new PermissionsListDTO();
        dto.setPermissionId(permission.getPermissionId());

        // デバイスとユーザー情報
        if (permission.getDevice() != null) {
            Device device = permission.getDevice();
            dto.setDeviceId(device.getDeviceId());
            dto.setComputerName(device.getComputerName());
            dto.setLoginUsername(device.getLoginUsername());
            dto.setUserId(device.getUser().getUserId());
            dto.setName(device.getUser().getName());
            dto.setDeptId(device.getUser().getDeptId());

            // 複数のIPアドレスを処理する - deviceIpsリストからすべてのIPアドレスを抽出する
            if (device.getDeviceIp() != null && !device.getDeviceIp().isEmpty()) {
                List<String> ipAddresses = device.getDeviceIp().stream().map(deviceIp -> deviceIp.getIpAddress()).filter(ip -> ip != null && !ip.trim().isEmpty()).collect(Collectors.toList());
                dto.setIpAddress(ipAddresses);
            } else {
                dto.setIpAddress(new ArrayList<>()); // IPアドレスがない場合は、空のリストを返します
            }

            // モニター ID の処理 - monitors リストからすべてのモニター ID を抽出
            if (device.getMonitor() != null && !device.getMonitor().isEmpty()) {
                List<@Size(max = 100) String> monitorNames = device.getMonitor().stream().map(monitorInfo -> monitorInfo.getMonitorName()).filter(id -> id != null).collect(Collectors.toList());
                dto.setMonitorNames(monitorNames);
            } else {
                dto.setMonitorNames(new ArrayList<>()); // モニターがない場合は、空のリストを返します
            }
        }

        // 域状態
        if (permission.getDomainStatusId() != null) {
            dto.setDomainStatus(permission.getDomainStatusId());
        }
        dto.setDomainGroup(permission.getDomainGroup());
        dto.setNoDomainReason(permission.getNoDomainReason());

        // Smartitの状態
        if (permission.getSmartitStatusId() != null) {
            dto.setSmartitStatus(permission.getSmartitStatusId());
        }
        dto.setNoSmartitReason(permission.getNoSmartitReason());

        // USBの状態
        if (permission.getUsbStatusId() != null) {
            dto.setUsbStatus(permission.getUsbStatusId());
        }
        dto.setUsbReason(permission.getUsbReason());
        dto.setUsbExpireDate(permission.getUsbExpireDate());

        // ウイルス対策の状態
        if (permission.getAntivirusStatusId() != null) {
            dto.setAntivirusStatus(permission.getAntivirusStatusId());
        }
        dto.setNoSymantecReason(permission.getNoSymantecReason());
        dto.setRemark(permission.getRemark());
        dto.setCreateTime(permission.getCreateTime());
        dto.setCreater(permission.getCreater());
        dto.setUpdateTime(permission.getUpdateTime());
        dto.setUpdater(permission.getUpdater());

        return dto;
    }


    @Transactional
    /**
     * IDに基づいてデバイス使用権限を削除
     * @param id 権限ID（APIレイヤーのLong型）
     */ public void deletePermissionById(String permissionId) {
        // 2. 権限の存在チェック
        DevicePermission permission = devicePermissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("権限が存在しません: " + permissionId));

        // 4. TODO: 関連リソースのチェック（他のテーブルをクエリする必要あり）
        // 模擬：権限IDに"TEST"が含まれている場合、関連があるものとみなす
//        if (permissionId.contains("TEST")) {
//            throw new ConflictException("権限は既にリソースに紐づいているため、削除できません: " + permissionId);
//        }

        // 5. 物理削除の実行
        devicePermissionRepository.delete(permission);
    }


    /**
     * IDに基づき使用権限の詳細情報を取得します。
     * 1. 権限IDをパラメータとして受け取ります。
     * 2. リポジトリ層を介して対応する権限エンティティを検索します。
     * 3. 存在しない場合はリソース未検出例外をスローします。
     * 4. エンティティオブジェクトをDTOオブジェクトに変換します。
     * 5. DTOオブジェクトを呼び出し元に返します。
     *
     * @param permissionId 権限ID
     * @return 権限詳細情報DTOオブジェクト
     */
    @Transactional(readOnly = true)
    public ApiResponse<?> findPermissionDetail(String permissionId) {

        Long userType = jwtTokenProvider.getUserTypeIdFromToken(extractTokenFromRequest());
        String userId = jwtTokenProvider.getUserIdFromToken(extractTokenFromRequest());
        DevicePermission permission;
        if (userType != 11) {
            permission = devicePermissionRepository.findDevicePermissionByPermissionIdAndUserId(permissionId, userId).orElseThrow(() -> {
                return new UnauthorizedException("この情報を見る権限がありません");
            });
        } else {
            //IDに基づいてデータベースから権限オブジェクトを取得する
            permission = devicePermissionRepository.findById(permissionId).orElseThrow(() -> {
                log.error("権限情報が存在しません，permissionId: {}", permissionId);
                // 存在しない場合は例外をスローします。
                return new ResourceNotFoundException("権限情報が存在しません，permissionId: " + permissionId);
            });
        }


        log.info("権限の詳細を確認する，permissionId: {}", permissionId);


        // エンティティオブジェクトをDTOオブジェクトに変換して返却する;
        return ApiResponse.success("検索に成功しました", convertToDTO(permission));
    }

    /**
     * フィールド単位で権限情報を更新します。
     * 1. IDに基づき既存の権限エンティティを検索します。
     * 2. 各パラメータに値があるか確認します（StringUtilsを使用して文字列判定を行います）。
     * 3. 値があるパラメータを既存のエンティティに更新します。
     * 4. 更新後のエンティティを保存します。
     */
    @Transactional
    public ApiResponse<?> updatePermissionByFields(String permissionId, PermissionUpdateDTO updateDTO) {
        if (jwtTokenProvider.getUserTypeIdFromToken(extractTokenFromRequest()) != 11) {
            return ApiResponse.error(30403, "あなたは管理者ではありません");
        }


        log.info("フィールドに基づいて権限情報を更新する，ID: {}", permissionId);

        //既存の権限エンティティを取得します。
        DevicePermission existing = devicePermissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("権限情報が存在しません"));

        //パラメータを一つずつ確認して更新する
        // SmartITステータス更新
        if (updateDTO.getSmartitStatus() != null) {
            dictRepository.findById(updateDTO.getSmartitStatus()).orElseThrow(() -> new ResourceNotFoundException("SmartITステータスが存在しません"));
            existing.setSmartitStatusId(updateDTO.getSmartitStatus());
        }

        if (StringUtils.hasText(updateDTO.getNoSmartitReason())) {
            existing.setNoSmartitReason(updateDTO.getNoSmartitReason());
        }

        // USBステータス更新
        if (updateDTO.getUsbStatus() != null) {
            dictRepository.findById(updateDTO.getUsbStatus()).orElseThrow(() -> new ResourceNotFoundException("USBステータスが存在しません"));
            existing.setUsbStatusId(updateDTO.getUsbStatus());
        }

        if (StringUtils.hasText(updateDTO.getUsbReason())) {
            existing.setUsbReason(updateDTO.getUsbReason());
        }

        // アンチウイルスステータス更新
        if (updateDTO.getAntivirusStatus() != null) {
            dictRepository.findById(updateDTO.getAntivirusStatus()).orElseThrow(() -> new ResourceNotFoundException("アンチウイルスステータスが存在しません"));
            existing.setAntivirusStatusId(updateDTO.getAntivirusStatus());
        }

        if (StringUtils.hasText(updateDTO.getNoSymantecReason())) {
            existing.setNoSymantecReason(updateDTO.getNoSymantecReason());
        }

        // ドメインステータス更新
        if (updateDTO.getDomainStatus() != null) {
            dictRepository.findById(updateDTO.getDomainStatus()).orElseThrow(() -> new ResourceNotFoundException("ドメインステータスが存在しません"));
            existing.setDomainStatusId(updateDTO.getDomainStatus());
        }

        if (StringUtils.hasText(updateDTO.getDomainGroup())) {
            existing.setDomainGroup(updateDTO.getDomainGroup());
        }

        if (StringUtils.hasText(updateDTO.getNoDomainReason())) {
            existing.setNoDomainReason(updateDTO.getNoDomainReason());
        }

        if (StringUtils.hasText(updateDTO.getRemark())) {
            existing.setRemark(updateDTO.getRemark());
        }

        //更新者を設定する（現在のユーザーを取得）
        existing.setUpdater(jwtTokenProvider.getUserIdFromToken(extractTokenFromRequest()));

        if (updateDTO.getUsbExpireDate() != null) {
            existing.setUsbExpireDate(updateDTO.getUsbExpireDate());
        }

        //保存して更新
        devicePermissionRepository.save(existing);
        log.info("権限情報の更新が完了しました，permissionId: {}", permissionId);

        return ApiResponse.success("更新に成功しました");
    }

    /**
     * DTOへの変換メソッド
     * エンティティオブジェクトをDTOオブジェクトに変換する方法
     *
     * @param permission 権限エンティティオブジェクト
     * @return 変換後のDTOオブジェクト
     */
    private PermissionUpdateDTO convertToDTO(DevicePermission permission) {
        PermissionUpdateDTO dto = new PermissionUpdateDTO();

        // 権限基本IDを設定
        dto.setPermissionId(permission.getPermissionId());

        // デバイス情報マッピング
        if (permission.getDevice() != null) {
            //デバイス情報
            dto.setDeviceId(permission.getDevice().getDeviceId());
            dto.setComputerName(permission.getDevice().getComputerName());
            dto.setDeviceModel(permission.getDevice().getDeviceModel());
            dto.setProject(permission.getDevice().getProject());
            dto.setDevRoom(permission.getDevice().getDevRoom());

            // ユーザー情報
            if (permission.getDevice().getUser() != null) {
                dto.setUserId(permission.getDevice().getUser().getUserId());
                dto.setUserName(permission.getDevice().getUser().getName());
                dto.setDeptId(permission.getDevice().getUser().getDeptId());
            }
        }

        // ドメインステータス情報
        if (permission.getDomainStatusId() != null) {
            dto.setDomainStatus(permission.getDomainStatusId());
        }
        dto.setDomainGroup(permission.getDomainGroup());
        dto.setNoDomainReason(permission.getNoDomainReason());

        // SmartITステータス情報
        if (permission.getSmartitStatusId() != null) {
            dto.setSmartitStatus(permission.getSmartitStatusId());
        }
        dto.setNoSmartitReason(permission.getNoSmartitReason());

        // USBステータス情報
        if (permission.getUsbStatusId() != null) {
            dto.setUsbStatus(permission.getUsbStatusId());
        }
        dto.setUsbReason(permission.getUsbReason());
        dto.setUsbExpireDate(permission.getUsbExpireDate());

        // アンチウイルスソフトのステータス情報
        if (permission.getAntivirusStatusId() != null) {
            dto.setAntivirusStatus(permission.getAntivirusStatusId());
        }
        dto.setNoSymantecReason(permission.getNoSymantecReason());

        //情報の作成と更新
        dto.setRemark(permission.getRemark());
        dto.setCreateTime(permission.getCreateTime());
        dto.setCreater(permission.getCreater());
        dto.setUpdateTime(permission.getUpdateTime());
        dto.setUpdater(permission.getUpdater());

        return dto;
    }

    /**
     * リクエストヘッダーからトークンを抽出
     */
    private String extractTokenFromRequest() {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        log.debug("元のAuthorizationヘッダー: {}", bearerToken);

        if (StringUtils.hasText(bearerToken)) {
            // bearerTokenの長さと内容を表示（不可視文字を含む可能性があることに注意）
            log.debug("Authorizationヘッダー長さ: {}", bearerToken.length());
            for (int i = 0; i < bearerToken.length(); i++) {
                char c = bearerToken.charAt(i);
                log.trace("文字[{}]: {} (ASCII: {})", i, c, (int) c);
            }

            // Bearerで始まるかチェック（大文字小文字を区別しない）
            if (bearerToken.length() > 7 && bearerToken.substring(0, 7).equalsIgnoreCase("Bearer ")) {
                String token = bearerToken.substring(7).trim();
                log.debug("抽出されたトークン: {}... (長さ: {})", token.substring(0, Math.min(30, token.length())), token.length());
                return token;
            } else {
                log.warn("AuthorizationヘッダーがBearerで始まらない、または長さが不足");
            }
        } else {
            log.warn("Authorizationヘッダーが空または存在しない");
        }
        return null;
    }


    // 在 DevicePermissionService 类中添加以下方法

    /**
     * 批量从DeviceExcelDto导入设备权限信息
     * 只使用deviceId，其他字段设为默认值
     * @param excelDataList Excel数据列表
     * @return API响应结果
     */
    @Transactional
    public ApiResponse<String> batchImportPermissionFromExcel(List<DeviceExcelDto> excelDataList) {
        log.info("批量导入设备权限信息，总数: {}", excelDataList.size());

        if (excelDataList == null || excelDataList.isEmpty()) {
            return ApiResponse.error(400, "Excel数据不能为空");
        }

        int successCount = 0;
        int failCount = 0;
        Set<String> processedDeviceIds = new HashSet<>(); // 防止重复处理
        StringBuilder errorMessages = new StringBuilder();

        for (int i = 0; i < excelDataList.size(); i++) {
            DeviceExcelDto dto = excelDataList.get(i);

            try {
                // 验证设备ID是否存在
                if (!StringUtils.hasText(dto.getDeviceId())) {
                    failCount++;
                    errorMessages.append("第").append(i + 1).append("行: 设备ID为空\n");
                    continue;
                }

                // 检查是否已处理过此设备ID
                if (processedDeviceIds.contains(dto.getDeviceId())) {
                    continue; // 跳过重复的设备ID
                }

                // 验证设备是否存在
                Device device = deviceRepository.findDeviceByDeviceId(dto.getDeviceId());
                if (device == null) {
                    failCount++;
                    errorMessages.append("第").append(i + 1).append("行，设备ID: ")
                            .append(dto.getDeviceId()).append(" - 设备不存在\n");
                    continue;
                }

                // 检查设备是否已存在权限信息
                DevicePermission existingPermission = devicePermissionRepository.findDevicePermissionsByDevice(device);
                if (existingPermission != null) {
                    failCount++;
                    errorMessages.append("第").append(i + 1).append("行，设备ID: ")
                            .append(dto.getDeviceId()).append(" - 设备已存在权限信息\n");
                    continue;
                }

                // 创建权限记录
                String permissionId = UUID.randomUUID().toString();

                DevicePermission devicePermission = DevicePermission.builder()
                        .permissionId(permissionId)
                        .device(device)
                        // 所有其他字段设为默认值或null
                        .domainStatusId(null) // 域状态ID默认为null
                        .domainGroup(null) // 域组默认为null
                        .noDomainReason(null) // 非域原因默认为null
                        .smartitStatusId(null) // SmartIT状态ID默认为null
                        .noSmartitReason(null) // SmartIT原因默认为null
                        .usbStatusId(null) // USB状态ID默认为null
                        .usbReason(null) // USB原因默认为null
                        .usbExpireDate(null) // USB过期日期默认为null
                        .antivirusStatusId(null) // 防病毒状态ID默认为null
                        .noSymantecReason(null) // 防病毒原因默认为null
                        .remark(null) // 备注默认为null
                        .createTime(LocalDateTime.now())
                        .creater("SYSTEM") // 系统导入
                        .updateTime(LocalDateTime.now())
                        .updater("SYSTEM") // 系统导入
                        .build();

                // 保存权限信息
                devicePermissionRepository.save(devicePermission);

                // 添加到已处理集合
                processedDeviceIds.add(dto.getDeviceId());
                successCount++;

            } catch (Exception e) {
                failCount++;
                errorMessages.append("第").append(i + 1).append("行，设备ID: ")
                        .append(dto != null ? dto.getDeviceId() : "未知")
                        .append(" - 异常: ").append(e.getMessage()).append("\n");
                log.error("导入权限信息时发生异常", e);
            }
        }

        String message = String.format("批量导入完成，成功: %d, 失败: %d", successCount, failCount);
        if (failCount > 0) {
            message += "\n错误详情:\n" + errorMessages.toString();
            return ApiResponse.error(200, message); // 使用200状态码，但在消息中包含错误信息
        }

        return ApiResponse.success(message);
    }

}
