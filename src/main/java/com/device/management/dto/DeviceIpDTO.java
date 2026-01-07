package com.device.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceIpDTO {
    private Integer ipId; //IP番号
    private String ipAddress; //IPアドレス
    private String deviceId;    // 機器番号（外部キー）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //作成日時
    private String creater; //作成者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //更新日時
    private String updater; //更新者
}