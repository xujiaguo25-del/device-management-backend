package com.device.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MonitorDTO {
    private Integer monitorId; // モニター番号
    private String monitorName; // モニター名
    private String deviceId; // 关联的设备ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //作成日時
    private String creater; //作成者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //更新日時
    private String updater; //更新者
}