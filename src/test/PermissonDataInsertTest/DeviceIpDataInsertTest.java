package PermissonDataInsertTest;

import com.device.DeviceManagementApplication;
import com.device.management.entity.DeviceInfo;
import com.device.management.repository.DeviceIpRepository;
import com.device.management.entity.DeviceIp;
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
public class DeviceIpDataInsertTest {

    @Autowired
    private DeviceIpRepository deviceIpRepository;

    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    @Test
    public void insertAllDeviceIpData() {
        List<DeviceIp> deviceIpList = new ArrayList<>();

        deviceIpList.add(buildDeviceIp(1, "HYRON-221129 PC-DC-055", "172.28.100.50",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(2, "hyron-220914 pc-dc-053", "10.6.1.36",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(3, "hyron-220914 pc-dc-053", "10.6.1.244",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(4, "hyron-220914 pc-dc-053", "10.6.3.232",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(5, "HYRON-211029 MAC-001", "10.6.1.48",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(6, "HYRON-200902 PC-DC-001", "172.28.101.10",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(7, "HYRON-210125 PC-DC-005", "172.28.100.251",
                "2026-01-05 08:54:29.877451", "admin",
                "2026-01-05 08:54:29.877451", "admin"));
        deviceIpList.add(buildDeviceIp(8, "zsdeviceid", "1.1.1.1",
                "2026-01-06 07:53:21.580521", null,
                "2026-01-06 07:53:21.580521", null));
        deviceIpList.add(buildDeviceIp(9, "zsdeviceid", "2.2.2.2",
                "2026-01-06 07:53:21.580521", null,
                "2026-01-06 07:53:21.580521", null));

        // 执行批量插入
        deviceIpRepository.saveAll(deviceIpList);
    }

    /**
     * 构建DeviceIp对象（确保device_id与device_info表的device_id完全匹配，大小写一致）
     */
    private DeviceIp buildDeviceIp(int ipId, String deviceId, String ipAddress,
                                   String createTimeStr, String creater,
                                   String updateTimeStr, String updater) {
        DeviceIp deviceIp = new DeviceIp();
        deviceIp.setId(ipId);
        deviceIp.setDevice(DeviceInfo.builder().deviceId(deviceId).build());
        deviceIp.setIpAddress(ipAddress);

        // 时间转换（IP表时间格式与其他表一致）
        deviceIp.setCreateTime(LocalDateTime.parse(createTimeStr, dtFormatter)
                .atZone(ZoneId.systemDefault()).toInstant());
        deviceIp.setCreater(creater);
        deviceIp.setUpdateTime(LocalDateTime.parse(updateTimeStr, dtFormatter)
                .atZone(ZoneId.systemDefault()).toInstant());
        deviceIp.setUpdater(updater);
        return deviceIp;
    }
}
