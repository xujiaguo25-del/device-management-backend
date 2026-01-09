package PermissonDataInsertTest;

import com.device.DeviceManagementApplication;
import com.device.management.entity.Dict;
import com.device.management.entity.User;
import com.device.management.repository.DeviceRepository;
import com.device.management.entity.DeviceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = DeviceManagementApplication.class)
public class DeviceInfoDataInsertTest {

    @Autowired
    private DeviceRepository deviceInfoRepository;

    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    @Test
    public void insertAllDeviceInfoData() {
        List<DeviceInfo> deviceInfoList = new ArrayList<>();

        // 1. 原始SQL中device_info表的7条数据（null值保持与SQL一致）
        deviceInfoList.add(buildDeviceInfo("123123", null, null, null, null, null, "123123", null,
                null, null, null, null, null,
                "2026-01-05 07:16:24.051994", null,
                "2026-01-05 07:16:24.051994", null));
        deviceInfoList.add(buildDeviceInfo("HYRON-200902 PC-DC-001", "lenvo 刃7000", "stj", "chengb", "D204-TRSW", "M2-A-02", "USER_004", "三菱食品AI测试用",
                36L, 24L, 32L, 33L, null,
                "2026-01-05 08:54:12.024281", "admin",
                "2026-01-05 08:54:12.024281", "admin"));
        deviceInfoList.add(buildDeviceInfo("HYRON-210125 PC-DC-005", "dell-5080", "DA02-XURUI-PC1", "xurui", "R&D+SBI", "M5-A-09", "USER_005", "大显示器DA02申请",
                36L, 21L, 33L, 42L, 35L,
                "2026-01-05 08:54:12.024281", "admin",
                "2026-01-05 08:54:12.024281", "admin"));
        deviceInfoList.add(buildDeviceInfo("HYRON-211029 MAC-001", "macmini2020", "Mac", "mac", "MTI-TECH-SOL", "M2-A-01", "USER_003", "潘小琴保管",
                37L, 23L, 31L, 35L, null,
                "2026-01-05 08:54:12.024281", "admin",
                "2026-01-05 08:54:12.024281", "admin"));
        deviceInfoList.add(buildDeviceInfo("hyron-220914 pc-dc-053", "dell-5000", "DA04-LUNA-PC09", "zhudt", "MTI-TECH-LUNA", "M2-A-03", "USER_002", "项目专用机",
                36L, 21L, 32L, 41L, 33L,
                "2026-01-05 08:54:12.024281", "admin",
                "2026-01-05 08:54:12.024281", "admin"));
        deviceInfoList.add(buildDeviceInfo("HYRON-221129 PC-DC-055", "dell-5000", "da04-liuwfnri", "lfeng", "R&D+SBI", "M5-A-09", "USER_001", "开发机",
                36L, 21L, 32L, 33L, null,
                "2026-01-05 08:54:12.024281", "admin",
                "2026-01-05 08:54:12.024281", "admin"));
        deviceInfoList.add(buildDeviceInfo("zsdeviceid", "4090", "zhangsandevice2026", "zhangs", "项目", "二楼", "TESTUSER", "api测试用",
                36L, 24L, 32L, 33L, 35L,
                "2026-01-05 07:16:24.051994", "admin",
                "2026-01-06 07:43:41.679715", null));

        // 执行批量插入
        deviceInfoRepository.saveAll(deviceInfoList);
    }

    /**
     * 构建DeviceInfo对象（处理null值与外键关联ID，如self_confirm_id关联dict表）
     */
    private DeviceInfo buildDeviceInfo(String deviceId, String deviceModel, String computerName, String loginUsername, String project, String devRoom, String userId, String remark,
                                       Long selfConfirmId, Long osId, Long memoryId, Long ssdId, Long hddId,
                                       String createTimeStr, String creater,
                                       String updateTimeStr, String updater) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceModel(deviceModel);
        deviceInfo.setComputerName(computerName);
        deviceInfo.setLoginUsername(loginUsername);
        deviceInfo.setProject(project);
        deviceInfo.setDevRoom(devRoom);
        deviceInfo.setUser(User.builder().userId(userId).build());

        deviceInfo.setRemark(remark);
        deviceInfo.setSelfConfirm(Dict.builder().id(selfConfirmId).build());
        deviceInfo.setOs(Dict.builder().id(osId).build());
        deviceInfo.setMemory(Dict.builder().id(memoryId).build());
        deviceInfo.setSsd(Dict.builder().id(ssdId).build());
        deviceInfo.setHdd(Dict.builder().id(hddId).build());

        // 时间转换（兼容null场景，若SQL中无时间值可直接传Instant.now()）
        if (createTimeStr != null) {
            deviceInfo.setCreateTime(LocalDateTime.parse(createTimeStr, dtFormatter)
                    .atZone(ZoneId.systemDefault()).toInstant());
        } else {
            deviceInfo.setCreateTime(Instant.now());
        }
        deviceInfo.setCreater(creater);

        if (updateTimeStr != null) {
            deviceInfo.setUpdateTime(LocalDateTime.parse(updateTimeStr, dtFormatter)
                    .atZone(ZoneId.systemDefault()).toInstant());
        } else {
            deviceInfo.setUpdateTime(Instant.now());
        }
        deviceInfo.setUpdater(updater);
        return deviceInfo;
    }
}