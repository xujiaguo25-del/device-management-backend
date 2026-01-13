package com.device.management.service;


import com.device.management.dto.*;
import com.device.management.entity.Device;
import com.device.management.entity.DeviceIp;
import com.device.management.entity.Monitor;
import com.device.management.entity.User;
import com.device.management.repository.DeviceIpRepository;
import com.device.management.repository.DeviceRepository;
import com.device.management.repository.MonitorRepository;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;



/**
 * デバイスサービスクラス
 * デバイス関連のビジネスロジックを処理
 */
@Slf4j
@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private DeviceIpRepository deviceIpRepository;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    @Transactional
    public DeviceFullDTO insertDevice(DeviceFullDTO deviceFullDTO){

        Device device = new Device();
        device.setDeviceId(deviceFullDTO.getDeviceId());
        device.setDeviceModel(deviceFullDTO.getDeviceModel());
        device.setComputerName(deviceFullDTO.getComputerName());
        device.setLoginUsername(deviceFullDTO.getLoginUsername());
        device.setProject(deviceFullDTO.getProject());
        device.setDevRoom(deviceFullDTO.getDevRoom());
        device.setUserId(deviceFullDTO.getUserId());
        device.setRemark(deviceFullDTO.getRemark());
        device.setSelfConfirmId(deviceFullDTO.getSelfConfirmId());
        device.setOsId(deviceFullDTO.getOsId());
        device.setMemoryId(deviceFullDTO.getMemoryId());
        device.setSsdId(deviceFullDTO.getSsdId());
        device.setHddId(deviceFullDTO.getHddId());
        device.setCreater(deviceFullDTO.getCreater());
        device.setUpdater(deviceFullDTO.getUpdater());



        // device insert
        List<Device> deviceList = deviceRepository.findByDeviceId(device.getDeviceId());

        if(!CollectionUtils.isEmpty(deviceList)) { // 設備の存在
            throw new IllegalStateException("device:" + device.getDeviceId() + "exist.");
        }

//        Device device = convertDeviceToEntity(deviceDTO);
        device.setCreateTime(LocalDateTime.now()); // 作成時刻の設定
        device.setUpdateTime(LocalDateTime.now()); // 更新日時の設定

        Device deviceReturn = deviceRepository.save(device); // 保存された設備に戻る


        // monitor insert
        List<Monitor> monitorReturns = new ArrayList<>();
        if (!CollectionUtils.isEmpty(deviceFullDTO.getMonitors())) {
            for (Monitor monitor : deviceFullDTO.getMonitors()) {

                monitor.setDeviceId(deviceFullDTO.getDeviceId());
                monitor.setCreater(deviceFullDTO.getCreater());
                monitor.setUpdater(deviceFullDTO.getUpdater());

//                Monitor monitor = convertMonitorToEntity(monitorDTO);

                if (!monitorRepository.existsByMonitorName(monitor.getMonitorName())) {

                    monitor.setCreateTime(LocalDateTime.now());
                    monitor.setUpdateTime(LocalDateTime.now());

                    Monitor monitorReturn = monitorRepository.save(monitor);
                    monitorReturns.add(monitorReturn);
                } else { // exsit

                    Monitor monitorDB = monitorRepository.findByMonitorName(monitor.getMonitorName());

                    if(!monitorDB.getDeviceId().equals(monitor.getDeviceId())) {
                        //                System.out.println(monitorDB.getMonitorId() + "++++++++++" + monitorDTO.getMonitorId()); // 1  null
                        throw new IllegalStateException("Monitor " + monitor.getMonitorName() + " is used by " + monitorDB.getDeviceId() + "!");
                    }

//                    monitorReturns.add(monitorDB);
                }
            }
        }

        // ip insert
        List<DeviceIp> ipReturns = new ArrayList<>();
        if (!CollectionUtils.isEmpty(deviceFullDTO.getIpAddresses())) {
            for (DeviceIp deviceIp : deviceFullDTO.getIpAddresses()) {

                deviceIp.setDeviceId(deviceFullDTO.getDeviceId());
                deviceIp.setCreater(deviceFullDTO.getCreater());
                deviceIp.setUpdater(deviceFullDTO.getUpdater());

                // ipチェック
                String ipAddress = deviceIp.getIpAddress();
                ipAddress = ipAddress.trim();

                try {
                    InetAddress.getByName(ipAddress);
                } catch (Exception e) {
                    throw new IllegalArgumentException("IPアドレスの形式が無効です: " + ipAddress);
                }

                // insert
                if (!deviceIpRepository.existsByIpAddress(deviceIp.getIpAddress())) {

//                    DeviceIp deviceIp = convertDeviceIpToEntity(deviceIpDTO);

                    deviceIp.setCreateTime(LocalDateTime.now());
                    deviceIp.setUpdateTime(LocalDateTime.now());

                    DeviceIp deviceIpReturn = deviceIpRepository.save(deviceIp);
                    ipReturns.add(deviceIpReturn);
                } else {

                    DeviceIp deviceIpDB = deviceIpRepository.findByIpAddress(deviceIp.getIpAddress());

                    if(!deviceIpDB.getDeviceId().equals(deviceIp.getDeviceId())) {
                        throw new IllegalStateException("Ip " + deviceIp.getIpAddress() + " is used by " + deviceIpDB.getDeviceId() + "!");
                    }

//                    ipReturns.add(deviceIpDB);
                }
            }
        }

        DeviceFullDTO deviceFullDtoReturn = new DeviceFullDTO();

        deviceFullDtoReturn.setDeviceId(deviceReturn.getDeviceId());
        deviceFullDtoReturn.setDeviceModel(deviceReturn.getDeviceModel());
        deviceFullDtoReturn.setComputerName(deviceReturn.getComputerName());
        deviceFullDtoReturn.setLoginUsername(deviceReturn.getLoginUsername());
        deviceFullDtoReturn.setProject(deviceReturn.getProject());
        deviceFullDtoReturn.setDevRoom(deviceReturn.getDevRoom());
        deviceFullDtoReturn.setUserId(deviceReturn.getUserId());
        deviceFullDtoReturn.setRemark(deviceReturn.getRemark());
        deviceFullDtoReturn.setSelfConfirmId(deviceReturn.getSelfConfirmId());
        deviceFullDtoReturn.setOsId(deviceReturn.getOsId());
        deviceFullDtoReturn.setMemoryId(deviceReturn.getMemoryId());
        deviceFullDtoReturn.setSsdId(deviceReturn.getSsdId());
        deviceFullDtoReturn.setHddId(deviceReturn.getHddId());
        deviceFullDtoReturn.setCreater(deviceReturn.getCreater());
        deviceFullDtoReturn.setUpdater(deviceReturn.getUpdater());
        deviceFullDtoReturn.setMonitors(monitorReturns);
        deviceFullDtoReturn.setIpAddresses(ipReturns);
        deviceFullDtoReturn.setName(deviceFullDTO.getName());
        deviceFullDtoReturn.setDeptId(deviceFullDTO.getDeptId());


        return deviceFullDtoReturn; //設備FullDTOを返す
    }



    @Transactional
    public DeviceFullDTO updateDeviceById(String deviceId, DeviceFullDTO deviceFullDTO) {

//        System.out.println("!!!!!!!!!!!!" + deviceFullDTO + "!!!!!!!!!!!!");

        deviceFullDTO.setDeviceId(deviceId); // 渡されたパラメータのdeviceIdの後ろに使用されます

        Device device = new Device();
        device.setDeviceId(deviceFullDTO.getDeviceId());
        device.setDeviceModel(deviceFullDTO.getDeviceModel());
        device.setComputerName(deviceFullDTO.getComputerName());
        device.setLoginUsername(deviceFullDTO.getLoginUsername());
        device.setProject(deviceFullDTO.getProject());
        device.setDevRoom(deviceFullDTO.getDevRoom());
        device.setUserId(deviceFullDTO.getUserId());
        device.setRemark(deviceFullDTO.getRemark());
        device.setSelfConfirmId(deviceFullDTO.getSelfConfirmId());
        device.setOsId(deviceFullDTO.getOsId());
        device.setMemoryId(deviceFullDTO.getMemoryId());
        device.setSsdId(deviceFullDTO.getSsdId());
        device.setHddId(deviceFullDTO.getHddId());
        device.setCreater(deviceFullDTO.getCreater());
        device.setUpdater(deviceFullDTO.getUpdater());


        // device update
        // 機器が存在しない場合、異常を投げる
        Device deviceDB = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalArgumentException("device:" + deviceId + "not exist."));

        device.setCreateTime(deviceDB.getCreateTime());
        device.setUpdateTime(LocalDateTime.now()); // 更新日時の設定

        // 更新アクション
        deviceRepository.save(device);

        // 更新されたデータを返す
        Device deviceReturn = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalStateException("device:" + deviceId + "not exist."));


        // monitor update
        List<Monitor> monitorReturns = new ArrayList<>();
        if (CollectionUtils.isEmpty(deviceFullDTO.getMonitors())) {

            List<Monitor> existingMonitors = monitorRepository.findByDeviceId(deviceId);
            if (!CollectionUtils.isEmpty(existingMonitors)) {
                monitorRepository.deleteAll(existingMonitors);
            }

            monitorReturns = new ArrayList<>();
        }else { // データがある
            for (Monitor monitor : deviceFullDTO.getMonitors()) {

                monitor.setDeviceId(deviceFullDTO.getDeviceId());
                monitor.setCreater(deviceFullDTO.getCreater());
                monitor.setUpdater(deviceFullDTO.getUpdater());

                // exist
                if(monitorRepository.existsByMonitorName(monitor.getMonitorName())) {

                    Monitor monitorDB = monitorRepository.findByMonitorName(monitor.getMonitorName());

                    // be used
                    if (!monitorDB.getDeviceId().equals(deviceId)) {
                        throw new IllegalStateException("Monitor " + monitor.getMonitorName() + " is used by " + monitorDB.getDeviceId() + "!");
                    }

                    // モニタは変更されていません
                    monitorDB.setMonitorName(monitor.getMonitorName());
                    monitorDB.setUpdater(monitor.getUpdater());
                    monitorDB.setUpdateTime(LocalDateTime.now());

                    Monitor monitorReturn = monitorRepository.save(monitorDB);
                    monitorReturns.add(monitorReturn);

                } else { // not exist
//                    Monitor monitor = convertMonitorToEntity(monitorDTO);

                    monitor.setCreateTime(LocalDateTime.now());
                    monitor.setUpdateTime(LocalDateTime.now());


                    Monitor monitorReturn = monitorRepository.save(monitor); // update
                    monitorReturns.add(monitorReturn);
                }
            }

            // 新しいリストに含まれていない古いレコードを削除する
            List<Monitor> existingMonitors = monitorRepository.findByDeviceId(deviceId);
            Set<String> newMonitorNames = deviceFullDTO.getMonitors().stream()
                    .map(Monitor::getMonitorName)
                    .collect(Collectors.toSet());

            for (Monitor existing : existingMonitors) {
                if (!newMonitorNames.contains(existing.getMonitorName())) {
                    monitorRepository.delete(existing);
                }
            }
        }


        // ip update
        List<DeviceIp> ipReturns = new ArrayList<>();
        if (CollectionUtils.isEmpty(deviceFullDTO.getIpAddresses())) {

            List<DeviceIp> existingIps = deviceIpRepository.findByDeviceId(deviceId);
            if (!CollectionUtils.isEmpty(existingIps)) {
                deviceIpRepository.deleteAll(existingIps);
            }

            ipReturns = new ArrayList<>();
        }else {
            for (DeviceIp deviceIp : deviceFullDTO.getIpAddresses()) {

                deviceIp.setDeviceId(deviceFullDTO.getDeviceId());
                deviceIp.setCreater(deviceFullDTO.getCreater());
                deviceIp.setUpdater(deviceFullDTO.getUpdater());

                // ipチェック
                String ipAddress = deviceIp.getIpAddress();
                ipAddress = ipAddress.trim();

                try {
                    InetAddress.getByName(ipAddress);
                } catch (Exception e) {
                    throw new IllegalArgumentException("IPアドレスの形式が無効です: " + ipAddress);
                }

                // update
                if (deviceIpRepository.existsByIpAddress(deviceIp.getIpAddress())) {
                    DeviceIp deviceIpDB = deviceIpRepository.findByIpAddress(deviceIp.getIpAddress());

                    // be used
                    if (!deviceIpDB.getDeviceId().equals(deviceId)) {
                        throw new IllegalStateException("IP " + deviceIp.getIpAddress() + " is used by " + deviceIpDB.getDeviceId() + "!");
                    }

                    // ipは変更されていません
                    deviceIpDB.setIpAddress(deviceIp.getIpAddress());
                    deviceIpDB.setUpdater(deviceIp.getUpdater());
                    deviceIpDB.setUpdateTime(LocalDateTime.now());

                    DeviceIp deviceIpReturn = deviceIpRepository.save(deviceIpDB);
                    ipReturns.add(deviceIpReturn);

                } else { // not exist
//                    DeviceIp deviceIp = convertDeviceIpToEntity(deviceIpDTO);
                    deviceIp.setCreateTime(LocalDateTime.now());
                    deviceIp.setUpdateTime(LocalDateTime.now());

                    DeviceIp deviceIpReturn = deviceIpRepository.save(deviceIp);
                    ipReturns.add(deviceIpReturn);
                }
            }

            // 新しいリストに含まれていない古いレコードを削除する
            List<DeviceIp> existingIps = deviceIpRepository.findByDeviceId(deviceId);
            Set<String> newIpAddresses = deviceFullDTO.getIpAddresses().stream()
                    .map(DeviceIp::getIpAddress)
                    .collect(Collectors.toSet());

            for (DeviceIp existing : existingIps) {
                if (!newIpAddresses.contains(existing.getIpAddress())) {
                    deviceIpRepository.delete(existing);
                }
            }
        }

        DeviceFullDTO deviceFullDtoReturn = new DeviceFullDTO();

        deviceFullDtoReturn.setDeviceId(deviceReturn.getDeviceId());
        deviceFullDtoReturn.setDeviceModel(deviceReturn.getDeviceModel());
        deviceFullDtoReturn.setComputerName(deviceReturn.getComputerName());
        deviceFullDtoReturn.setLoginUsername(deviceReturn.getLoginUsername());
        deviceFullDtoReturn.setProject(deviceReturn.getProject());
        deviceFullDtoReturn.setDevRoom(deviceReturn.getDevRoom());
        deviceFullDtoReturn.setUserId(deviceReturn.getUserId());
        deviceFullDtoReturn.setRemark(deviceReturn.getRemark());
        deviceFullDtoReturn.setSelfConfirmId(deviceReturn.getSelfConfirmId());
        deviceFullDtoReturn.setOsId(deviceReturn.getOsId());
        deviceFullDtoReturn.setMemoryId(deviceReturn.getMemoryId());
        deviceFullDtoReturn.setSsdId(deviceReturn.getSsdId());
        deviceFullDtoReturn.setHddId(deviceReturn.getHddId());
        deviceFullDtoReturn.setCreater(deviceReturn.getCreater());
        deviceFullDtoReturn.setUpdater(deviceReturn.getUpdater());
        deviceFullDtoReturn.setMonitors(monitorReturns);
        deviceFullDtoReturn.setIpAddresses(ipReturns);


        return deviceFullDtoReturn;
    }


    /**
     * デバイス削除
     * @param deviceId デバイスID
     */
    public void deleteDevice(String deviceId) {
        log.info("Delete device with id: {}", deviceId);

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found, DeviceId: " + deviceId));

        deviceRepository.delete(device);
        log.info("Device deleted successfully: {}", deviceId);
    }


    /**
     * デバイス情報をExcel形式でエクスポート
     * @param response HTTPレスポンス
     */
    public void exportDevicesToExcel(HttpServletResponse response) {

        List<Device> devices = deviceRepository.findAll();
        log.info("Exporting {} devices to Excel", devices.size());

        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String fileName = "devices_export_" + timestamp + ".xlsx";

        // ファイル名をURLエンコードして、中文の文字化けなどの問題を防ぐ
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            log.warn("文件名编码失败，使用原始文件名", e);
        }

        // レスポンスヘッダーを設定する
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);

        // try-with-resourcesを使用してワークブックが閉じられることを確認する
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("设备清单");

            // ヘッダーを作成する
            createExcelHeader(sheet);

            // データを入力する
            if (!devices.isEmpty()) {
                fillExcelData(sheet, devices);
            } else {
                Row row = sheet.createRow(1);
                row.createCell(0).setCellValue("暂无数据");
            }

            // 列の幅を自動調整
            autoSizeColumns(sheet);

            // レスポンスストリームに書き込む
            workbook.write(response.getOutputStream());

            log.info("Excel导出成功，共导出 {} 条记录", devices.size());
        } catch (IOException e) {
            log.error("导出 Excel 失败", e);
            throw new RuntimeException("导出Excel失败: " + e.getMessage(), e);
        }
    }

    /**
     * Excelヘッダーを作成
     * @param sheet シートオブジェクト
     */
    private void createExcelHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {
                "工号", "姓名", "部门",
                "主机设备编号", "显示器设备编号","显示器设备名", "主机型号", "电脑名",
                "IP　地址", "操作系统", "内存单位", "固态硬盘",
                "机械硬盘", "登录用户名", "所在项目", "所在开发室","备注","本人确认"
        };

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    /**
     * Excelデータを埋め込む
     * @param sheet シートオブジェクト
     * @param devices デバイスリスト
     */
    private void fillExcelData(Sheet sheet, List<Device> devices) {
        int rowNum = 1;
        for (Device device : devices) {
            Row row = sheet.createRow(rowNum++);

            User user = device.getUser();
            safeSetCellValue(row, 0, user != null ? user.getUserId() : "");
            safeSetCellValue(row, 1, user != null ? user.getName() : "");
            safeSetCellValue(row, 2, user != null ? user.getDeptId() : "");
            // 安全地填充每个单元格
            safeSetCellValue(row, 3, device.getDeviceId());
            safeSetCellValue(row, 4, getAllMonitorIds(device));
            safeSetCellValue(row, 5, getAllMonitorNames(device));
            safeSetCellValue(row, 6, device.getDeviceModel());
            safeSetCellValue(row, 7, device.getComputerName());
            safeSetCellValue(row, 8, getAllIpAddresses(device));
            safeSetCellValue(row, 9, getDictItemName(device.getOsDict()));
            safeSetCellValue(row, 10, getDictItemName(device.getMemoryDict()));
            safeSetCellValue(row, 11, getDictItemName(device.getSsdDict()));
            safeSetCellValue(row, 12, getDictItemName(device.getHddDict()));
            safeSetCellValue(row, 13, device.getLoginUsername());
            safeSetCellValue(row, 14, device.getProject());
            safeSetCellValue(row, 15, device.getDevRoom());
            safeSetCellValue(row, 16, device.getRemark());
            safeSetCellValue(row, 17, getDictItemName(device.getSelfConfirmDict()));
        }
    }

    /**
     * セルに安全に値を設定
     * @param row 行オブジェクト
     * @param cellNum セル番号
     * @param value 設定値
     */
    private void safeSetCellValue(Row row, int cellNum, String value) {
        Cell cell = row.createCell(cellNum);
        cell.setCellValue(value != null ? value : "");
    }

    /**
     * すべてのモニター名を取得（カンマ区切り）
     * @param device デバイスエンティティ
     * @return モニター名文字列
     */
    private String getAllMonitorNames(Device device) {
        if (device.getMonitorInfos() != null && !device.getMonitorInfos().isEmpty()) {
            StringBuilder monitorNames = new StringBuilder();
            for (Monitor monitor : device.getMonitorInfos()) {
                if (monitor.getMonitorName() != null && !monitor.getMonitorName().isEmpty()) {
                    if (monitorNames.length() > 0) {
                        monitorNames.append(", ");
                    }
                    monitorNames.append(monitor.getMonitorName());
                }
            }
            return monitorNames.toString();
        }
        return "";
    }

    /**
     * すべてのモニターIDを取得（カンマ区切り）
     * @param device デバイスエンティティ
     * @return モニターID文字列
     */
    private String getAllMonitorIds(Device device) {
        if (device.getMonitorInfos() != null && !device.getMonitorInfos().isEmpty()) {
            StringBuilder monitorIds = new StringBuilder();
            for (Monitor monitor : device.getMonitorInfos()) {
                if (monitor.getMonitorId() != null) {
                    if (monitorIds.length() > 0) {
                        monitorIds.append(", ");
                    }
                    monitorIds.append(monitor.getMonitorId());
                }
            }
            return monitorIds.toString();
        }
        return "";
    }

    /**
     * すべてのIPアドレスを取得（カンマ区切り）
     * @param device デバイスエンティティ
     * @return IPアドレス文字列
     */
    private String getAllIpAddresses(Device device) {
        if (device.getDeviceIps() != null && !device.getDeviceIps().isEmpty()) {
            StringBuilder ipAddresses = new StringBuilder();
            for (DeviceIp ip : device.getDeviceIps()) {
                if (ip.getIpAddress() != null && !ip.getIpAddress().isEmpty()) {
                    if (ipAddresses.length() > 0) {
                        ipAddresses.append(", ");
                    }
                    ipAddresses.append(ip.getIpAddress());
                }
            }
            return ipAddresses.toString();
        }
        return "";
    }

    /**
     * 辞書項目名を取得
     * @param dict 辞書エンティティ
     * @return 辞書項目名
     */
    private String getDictItemName(com.device.management.entity.Dict dict) {
        return dict != null && dict.getDictItemName() != null ?
                dict.getDictItemName() : "";
    }

    /**
     * 列幅を自動調整
     * @param sheet シートオブジェクト
     */
    private void autoSizeColumns(Sheet sheet) {
        if (sheet.getRow(0) != null) {
            int columnCount = sheet.getRow(0).getLastCellNum();
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
                // 防止列宽过大
                int maxWidth = 50 * 256; // 50个字符宽度
                if (sheet.getColumnWidth(i) > maxWidth) {
                    sheet.setColumnWidth(i, maxWidth);
                }
            }
        }
    }

}