package com.device.management.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceIpDTO {
    private Integer ipId; //IP番号
    private String ipAddress; //IPアドレス
    private String deviceId; // 关联的设备ID
    private LocalDateTime createTime; //作成日時
    private String creater; //作成者
    private LocalDateTime updateTime; //更新日時
    private String updater; //更新者
}