package com.device.management.service;


import com.device.management.dto.*;
import com.device.management.entity.Device;
import com.device.management.entity.DeviceIp;
import com.device.management.entity.Monitor;
import com.device.management.entity.User;
import com.device.management.repository.DeviceIpRepository;
import com.device.management.repository.DeviceRepository;
import com.device.management.repository.MonitorRepository;
import com.device.management.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private DeviceIpRepository deviceIpRepository;

    @Transactional
    public DeviceFullDTO insertDevice(DeviceFullDTO deviceFullDTO){

        DeviceDTO deviceDTO = DeviceDTO.builder()
                .deviceId(deviceFullDTO.getDeviceId()).deviceModel(deviceFullDTO.getDeviceModel())
                .computerName(deviceFullDTO.getComputerName()).loginUsername(deviceFullDTO.getLoginUsername())
                .project(deviceFullDTO.getProject()).devRoom(deviceFullDTO.getDevRoom()).userId(deviceFullDTO.getUserId())
                .remark(deviceFullDTO.getRemark()).selfConfirmId(deviceFullDTO.getSelfConfirmId()).osId(deviceFullDTO.getOsId())
                .memoryId(deviceFullDTO.getMemoryId()).ssdId(deviceFullDTO.getSsdId()).hddId(deviceFullDTO.getHddId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();

        MonitorDTO monitorDTO = MonitorDTO.builder()
                .monitorName(deviceFullDTO.getMonitorName()).deviceId(deviceFullDTO.getDeviceId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();

        DeviceIpDTO deviceIpDTO = DeviceIpDTO.builder()
                .ipAddress(deviceFullDTO.getIpAddress()).deviceId(deviceFullDTO.getDeviceId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();

        UserDTO userDTO = UserDTO.builder()
                .userId(deviceFullDTO.getUserId()).name(deviceFullDTO.getName())
                .deptId(deviceFullDTO.getDeptId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();


        // user insert
        User userReturn = convertUserToEntity(userDTO);
        if (!userRepository.existsByUserId(userDTO.getUserId())) {

            User user = convertUserToEntity(userDTO);
            user.setPassword("123");
            user.setUserTypeId(1);

            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());

            userReturn = userRepository.save(user);
        }


        // device insert
        List<Device> deviceList = deviceRepository.findByDeviceId(deviceDTO.getDeviceId());

        if(!CollectionUtils.isEmpty(deviceList)) { // 設備の存在
            throw new IllegalStateException("device:" + deviceDTO.getDeviceId() + "exist.");
        }

        Device device = convertDeviceToEntity(deviceDTO);
        device.setCreateTime(LocalDateTime.now()); // 作成時刻の設定
        device.setUpdateTime(LocalDateTime.now()); // 更新日時の設定

        Device deviceReturn = deviceRepository.save(device); // 保存された設備に戻る


        // monitor insert
        Monitor monitorReturn = convertMonitorToEntity(monitorDTO);
        if (!monitorRepository.existsByMonitorName(monitorDTO.getMonitorName())) {

            Monitor monitor = convertMonitorToEntity(monitorDTO);

            monitor.setCreateTime(LocalDateTime.now());
            monitor.setUpdateTime(LocalDateTime.now());

            monitorReturn = monitorRepository.save(monitor);
        }

        // ip insert

        // ipチェック
        String ipAddress = deviceIpDTO.getIpAddress();
        ipAddress = ipAddress.trim();

        try {
            InetAddress.getByName(ipAddress);
        } catch (Exception e) {
            throw new IllegalArgumentException("IPアドレスの形式が無効です: " + ipAddress);
        }

        // insert
        DeviceIp deviceIpReturn = convertDeviceIpToEntity(deviceIpDTO);
        if (!deviceIpRepository.existsByIpAddress(deviceIpDTO.getIpAddress())) {

            DeviceIp deviceIp = convertDeviceIpToEntity(deviceIpDTO);


            deviceIp.setCreateTime(LocalDateTime.now());
            deviceIp.setUpdateTime(LocalDateTime.now());

            deviceIpReturn = deviceIpRepository.save(deviceIp);
        }

        DeviceFullDTO deviceFullDtoReturn = DeviceFullDTO.builder()
                .deviceId(deviceReturn.getDeviceId()).deviceModel(deviceReturn.getDeviceModel())
                .computerName(deviceReturn.getComputerName()).loginUsername(deviceReturn.getLoginUsername())
                .project(deviceReturn.getProject()).devRoom(deviceReturn.getDevRoom()).userId(deviceReturn.getUserId())
                .remark(deviceReturn.getRemark()).selfConfirmId(deviceReturn.getSelfConfirmId()).osId(deviceReturn.getOsId())
                .memoryId(deviceReturn.getMemoryId()).ssdId(deviceReturn.getSsdId()).hddId(deviceReturn.getHddId())
                .creater(deviceReturn.getCreater()).updater(deviceReturn.getUpdater())
                .monitorName(monitorReturn.getMonitorName()).ipAddress(deviceIpReturn.getIpAddress())
                .name(userReturn.getName()).deptId(userReturn.getDeptId())
                .build();

        return deviceFullDtoReturn; //設備FullDTOを返す
    }




//    public DeviceDTO insertDevice(DeviceDTO deviceDTO){
//
//        List<Device> deviceList = deviceRepository.findByDeviceId(deviceDTO.getDeviceId());
//
//        if(!CollectionUtils.isEmpty(deviceList)) { // 設備の存在
//            throw new IllegalStateException("device:" + deviceDTO.getDeviceId() + "exist.");
//        }
//
//        Device device = convertDeviceToEntity(deviceDTO);
//        device.setCreateTime(LocalDateTime.now()); // 作成時刻の設定
//        device.setUpdateTime(LocalDateTime.now()); // 更新日時の設定
//
//
//        Device device1 = deviceRepository.save(device); // 保存された設備に戻る
//
//        return convertDeviceToDTO(device1); //設備DTOを返す
//    }


    @Transactional
    public DeviceFullDTO updateDeviceById(String deviceId, DeviceFullDTO deviceFullDTO) {

//        System.out.println("!!!!!!!!!!!!" + deviceFullDTO + "!!!!!!!!!!!!");

        deviceFullDTO.setDeviceId(deviceId); // 渡されたパラメータのdeviceIdの後ろに使用されます

        DeviceDTO deviceDTO = DeviceDTO.builder()
                .deviceId(deviceFullDTO.getDeviceId()).deviceModel(deviceFullDTO.getDeviceModel())
                .computerName(deviceFullDTO.getComputerName()).loginUsername(deviceFullDTO.getLoginUsername())
                .project(deviceFullDTO.getProject()).devRoom(deviceFullDTO.getDevRoom()).userId(deviceFullDTO.getUserId())
                .remark(deviceFullDTO.getRemark()).selfConfirmId(deviceFullDTO.getSelfConfirmId()).osId(deviceFullDTO.getOsId())
                .memoryId(deviceFullDTO.getMemoryId()).ssdId(deviceFullDTO.getSsdId()).hddId(deviceFullDTO.getHddId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();

        MonitorDTO monitorDTO = MonitorDTO.builder()
                .monitorId(deviceFullDTO.getMonitorId())
                .monitorName(deviceFullDTO.getMonitorName()).deviceId(deviceFullDTO.getDeviceId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();


        DeviceIpDTO deviceIpDTO = DeviceIpDTO.builder()
                .ipId(deviceFullDTO.getIpId())
                .ipAddress(deviceFullDTO.getIpAddress()).deviceId(deviceFullDTO.getDeviceId())
                .creater(deviceFullDTO.getCreater()).updater(deviceFullDTO.getUpdater())
                .build();


        // device update
        // 機器が存在しない場合、異常を投げる
        Device deviceDB = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalArgumentException("device:" + deviceId + "not exist."));

        deviceDTO.setUpdateTime(LocalDateTime.now()); // 更新日時の設定

//        System.out.println(deviceDTO);

        // 更新アクション
        deviceRepository.updateDevice(
                deviceId,
                deviceDTO.getDeviceModel(),
                deviceDTO.getComputerName(),
                deviceDTO.getLoginUsername(),
                deviceDTO.getProject(),
                deviceDTO.getDevRoom(),
                deviceDTO.getUserId(),
                deviceDTO.getRemark(),
                deviceDTO.getSelfConfirmId(),
                deviceDTO.getOsId(),
                deviceDTO.getMemoryId(),
                deviceDTO.getSsdId(),
                deviceDTO.getHddId(),
                deviceDTO.getUpdateTime(),
                deviceDTO.getUpdater()
        );

        /***
         * JPAのL 1キャッシュの問題で、ここで返されるデータはキャッシュから取得した上のdeviceDBのデータであり、
         * repositoryの@Modifying注記に@Modifying（clearAutomatically=true、flushAutomatically=true）を追加する必要があります。
         * キャッシュをクリアして、新しいデータを取得できます
         */
        // 更新されたデータを返す
        Device deviceReturn = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalStateException("device:" + deviceId + "not exist."));


        // monitor update
        Monitor monitorReturn = convertMonitorToEntity(monitorDTO);
        if(monitorRepository.existsByMonitorName(monitorDTO.getMonitorName())) {

            Monitor monitorDB = monitorRepository.findByMonitorName(monitorDTO.getMonitorName());

            if(!monitorDB.getMonitorId().equals(monitorDTO.getMonitorId())) {
//                System.out.println(monitorDB.getMonitorId() + "++++++++++" + monitorDTO.getMonitorId()); // 1  null
                throw new IllegalStateException("This monitor is used by " + monitorDB.getDeviceId() + "!");
            }
            // else updateがない

        } else {
            Monitor monitor = convertMonitorToEntity(monitorDTO);

            // データベースクエリ、カバー範囲、データ整合性維持
            Monitor monitorDB = monitorRepository.findByMonitorId(monitorDTO.getMonitorId());
            monitor.setCreateTime(monitorDB.getCreateTime());
            monitor.setCreater(monitorDB.getCreater());

            monitor.setUpdateTime(LocalDateTime.now());
            monitor.setMonitorId(monitorDTO.getMonitorId());

            monitorReturn = monitorRepository.save(monitor); // update
        }

        // ip update

        // ipチェック
        String ipAddress = deviceIpDTO.getIpAddress();
        ipAddress = ipAddress.trim();

        try {
            InetAddress.getByName(ipAddress);
        } catch (Exception e) {
            throw new IllegalArgumentException("IPアドレスの形式が無効です: " + ipAddress);
        }


        DeviceIp deviceIpReturn = convertDeviceIpToEntity(deviceIpDTO);
        if(deviceIpRepository.existsByIpAddress(deviceIpDTO.getIpAddress())) {

            DeviceIp deviceIpDB = deviceIpRepository.findByIpAddress(deviceIpDTO.getIpAddress());

            if(!deviceIpDB.getIpId().equals(deviceIpDTO.getIpId())) {
                throw new IllegalStateException("This ip is used by " + deviceIpDB.getDeviceId() + "!");
            }
            // else updateがない

        } else {


            DeviceIp deviceIp = convertDeviceIpToEntity(deviceIpDTO);


            // データベースクエリ、カバー範囲、データ整合性維持
            DeviceIp deviceIpDB = deviceIpRepository.findByIpId(deviceIpDTO.getIpId());

            deviceIp.setCreateTime(deviceIpDB.getCreateTime());
            deviceIp.setCreater(deviceIpDB.getCreater());


            deviceIp.setUpdateTime(LocalDateTime.now());
            deviceIp.setIpId(deviceIpDTO.getIpId());

            deviceIpReturn = deviceIpRepository.save(deviceIp); // update
        }



        DeviceFullDTO deviceFullDtoReturn = DeviceFullDTO.builder()
                .deviceId(deviceReturn.getDeviceId()).deviceModel(deviceReturn.getDeviceModel())
                .computerName(deviceReturn.getComputerName()).loginUsername(deviceReturn.getLoginUsername())
                .project(deviceReturn.getProject()).devRoom(deviceReturn.getDevRoom()).userId(deviceReturn.getUserId())
                .remark(deviceReturn.getRemark()).selfConfirmId(deviceReturn.getSelfConfirmId()).osId(deviceReturn.getOsId())
                .memoryId(deviceReturn.getMemoryId()).ssdId(deviceReturn.getSsdId()).hddId(deviceReturn.getHddId())
                .creater(deviceReturn.getCreater()).updater(deviceReturn.getUpdater())
                .monitorName(monitorReturn.getMonitorName()).monitorId(monitorReturn.getMonitorId())
                .ipAddress(deviceIpReturn.getIpAddress()).ipId(deviceIpReturn.getIpId())
                .build();

        return deviceFullDtoReturn;
    }





//    @Transactional
//    public DeviceDTO updateDeviceById(String deviceId, DeviceDTO deviceDTO) {
//
//        // 機器が存在しない場合、異常を投げる
//        Device deviceDB = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalArgumentException("device:" + deviceId + "not exist."));
//
////        if(StringUtils.hasLength(deviceDTO.getComputerName()) && !deviceDB.getComputerName().equals(deviceDTO.getComputerName())){
////            deviceDB.setComputerName(deviceDTO.getComputerName());
////        }
//
//        deviceDTO.setUpdateTime(LocalDateTime.now()); // 更新日時の設定
//
    ////        System.out.println(deviceDTO);
//
//        // 更新アクション
//        deviceRepository.updateDevice(
//                deviceId,
//                deviceDTO.getDeviceModel(),
//                deviceDTO.getComputerName(),
//                deviceDTO.getLoginUsername(),
//                deviceDTO.getProject(),
//                deviceDTO.getDevRoom(),
//                deviceDTO.getUserId(),
//                deviceDTO.getRemark(),
//                deviceDTO.getSelfConfirmId(),
//                deviceDTO.getOsId(),
//                deviceDTO.getMemoryId(),
//                deviceDTO.getSsdId(),
//                deviceDTO.getHddId(),
//                deviceDTO.getUpdateTime(),
//                deviceDTO.getUpdater()
//        );
//
//        /***
//         * JPAのL 1キャッシュの問題で、ここで返されるデータはキャッシュから取得した上のdeviceDBのデータであり、
//         * repositoryの@Modifying注記に@Modifying（clearAutomatically=true、flushAutomatically=true）を追加する必要があります。
//         * キャッシュをクリアして、新しいデータを取得できます
//         */
//
//        // 更新されたデータを返す
//        Device updatedDevice = deviceRepository.findById(deviceId).orElseThrow(() -> new IllegalStateException("device:" + deviceId + "not exist."));
//
//        return convertDeviceToDTO(updatedDevice);
//    }




    // UserDTO 回転 User
    private User convertUserToEntity(UserDTO dto) {
        User user = new User();

        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setDeptId(dto.getDeptId());
        user.setCreateTime(dto.getCreateTime());
        user.setCreater(dto.getCreater());
        user.setUpdateTime(dto.getUpdateTime());
        user.setUpdater(dto.getUpdater());

        return user;
    }

    // User 回転 UserDTO
    private UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .deptId(user.getDeptId())
                .createTime(user.getCreateTime())
                .creater(user.getCreater())
                .updateTime(user.getUpdateTime())
                .updater(user.getUpdater())
                .build();
    }


    // MonitorDTO 回転 Monitor
    private Monitor convertMonitorToEntity(MonitorDTO dto) {
        Monitor monitor = new Monitor();

//        monitor.setMonitorId(dto.getMonitorId());
        monitor.setMonitorName(dto.getMonitorName());
        monitor.setDeviceId(dto.getDeviceId());
        monitor.setCreateTime(dto.getCreateTime());
        monitor.setCreater(dto.getCreater());
        monitor.setUpdateTime(dto.getUpdateTime());
        monitor.setUpdater(dto.getUpdater());

        return monitor;
    }

    // Monitor 回転 MonitorDTO
    private MonitorDTO convertMonitorToDTO(Monitor monitor) {
        return MonitorDTO.builder()
                .monitorId(monitor.getMonitorId())
                .monitorName(monitor.getMonitorName())
                .deviceId(monitor.getDeviceId())
                .createTime(monitor.getCreateTime())
                .creater(monitor.getCreater())
                .updateTime(monitor.getUpdateTime())
                .updater(monitor.getUpdater())
                .build();
    }


    // DeviceIpDTO 回転 DeviceIp
    private DeviceIp convertDeviceIpToEntity(DeviceIpDTO dto) {
        DeviceIp deviceIp = new DeviceIp();

//        deviceIp.setIpId(dto.getIpId());
        deviceIp.setIpAddress(dto.getIpAddress());
        deviceIp.setDeviceId(dto.getDeviceId());
        deviceIp.setCreateTime(dto.getCreateTime());
        deviceIp.setCreater(dto.getCreater());
        deviceIp.setUpdateTime(dto.getUpdateTime());
        deviceIp.setUpdater(dto.getUpdater());

        return deviceIp;
    }

    // DeviceIp 回転 DeviceIpDTO
    private DeviceIpDTO convertDeviceIpToDTO(DeviceIp deviceIp) {

        return DeviceIpDTO.builder()
                .ipId(deviceIp.getIpId())
                .ipAddress(deviceIp.getIpAddress())
                .deviceId(deviceIp.getDeviceId())
                .createTime(deviceIp.getCreateTime())
                .creater(deviceIp.getCreater())
                .updateTime(deviceIp.getUpdateTime())
                .updater(deviceIp.getUpdater())
                .build();
    }


    // DeviceDTO 回転 Device
    private Device convertDeviceToEntity(DeviceDTO dto) {
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
        device.setCreateTime(dto.getCreateTime());
        device.setCreater(dto.getCreater());
        device.setUpdateTime(dto.getUpdateTime());
        device.setUpdater(dto.getUpdater());

        return device;
    }

    // Device 回転 DeviceDTO
    private DeviceDTO convertDeviceToDTO(Device device) {
        return DeviceDTO.builder()
                .deviceId(device.getDeviceId()).deviceModel(device.getDeviceModel())
                .computerName(device.getComputerName()).loginUsername(device.getLoginUsername())
                .project(device.getProject()).devRoom(device.getDevRoom()).userId(device.getUserId())
                .remark(device.getRemark()).selfConfirmId(device.getSelfConfirmId()).osId(device.getOsId())
                .memoryId(device.getMemoryId()).ssdId(device.getSsdId()).hddId(device.getHddId())
                .createTime(device.getCreateTime()).creater(device.getCreater()).updateTime(device.getUpdateTime())
                .updater(device.getUpdater())
                .build();
    }

}
