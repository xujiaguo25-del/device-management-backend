package PermissonDataInsertTest;

import com.device.DeviceManagementApplication;
import com.device.management.entity.DeviceInfo;
import com.device.management.entity.MonitorInfo;
import com.device.management.repository.MonitorRepository;
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
public class MonitorInfoDataInsertTest {

    @Autowired
    private MonitorRepository monitorInfoRepository;

    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    @Test
    public void insertAllMonitorInfoData() {
        List<MonitorInfo> monitorInfoList = new ArrayList<>();

        // 1. 原始SQL中monitor_info表的7条数据（含一台设备绑定多显示器场景，如HYRON-210125 PC-DC-005）
        monitorInfoList.add(buildMonitorInfo(1, "HYRON-221129 PC-DC-055", "HYRON-241118 Minitor-075",
                "2026-01-05 08:55:00.942059", "admin",
                "2026-01-05 08:55:00.942059", "admin"));
        monitorInfoList.add(buildMonitorInfo(2, "hyron-220914 pc-dc-053", "HYRON-241118 Minitor-076",
                "2026-01-05 08:55:00.942059", "admin",
                "2026-01-05 08:55:00.942059", "admin"));
        monitorInfoList.add(buildMonitorInfo(3, "HYRON-211029 MAC-001", "HYRON-171017 PC-DC-006JD",
                "2026-01-05 08:55:00.942059", "admin",
                "2026-01-05 08:55:00.942059", "admin"));
        monitorInfoList.add(buildMonitorInfo(4, "HYRON-210125 PC-DC-005", "ttt",
                "2026-01-05 08:55:00.942059", "admin",
                "2026-01-06 01:18:48.577086", "admin"));
        monitorInfoList.add(buildMonitorInfo(5, "HYRON-210125 PC-DC-005", "210125 PC-DC-005",
                "2026-01-05 08:55:00.942059", "admin",
                "2026-01-05 08:55:00.942059", "admin"));
        monitorInfoList.add(buildMonitorInfo(6, "zsdeviceid", "显示器1",
                "2026-01-06 07:51:48.373132", null,
                "2026-01-06 07:51:48.373132", null));
        monitorInfoList.add(buildMonitorInfo(7, "zsdeviceid", "显示器2",
                "2026-01-06 07:51:48.373132", null,
                "2026-01-06 07:51:48.373132", null));

        // 执行批量插入
        monitorInfoRepository.saveAll(monitorInfoList);
    }

    /**
     * 构建MonitorInfo对象（显示器名称按SQL原样保留，如"ttt"）
     */
    private MonitorInfo buildMonitorInfo(Integer monitorId, String deviceId, String monitorName,
                                         String createTimeStr, String creater,
                                         String updateTimeStr, String updater) {
        MonitorInfo monitorInfo = new MonitorInfo();
        monitorInfo.setId(monitorId);
        monitorInfo.setDevice(DeviceInfo.builder().deviceId(deviceId).build());
        monitorInfo.setMonitorName(monitorName);

        monitorInfo.setCreateTime(LocalDateTime.parse(createTimeStr, dtFormatter)
                .atZone(ZoneId.systemDefault()).toInstant());
        monitorInfo.setCreater(creater);
        monitorInfo.setUpdateTime(LocalDateTime.parse(updateTimeStr, dtFormatter)
                .atZone(ZoneId.systemDefault()).toInstant());
        monitorInfo.setUpdater(updater);
        return monitorInfo;
    }
}