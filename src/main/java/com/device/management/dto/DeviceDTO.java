package com.device.management.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDTO {

    private String deviceId; //機器番号
    private String deviceModel; //ホストモデル
    private String computerName; //コンピュータ名
    private String loginUsername; //ログインユーザ名
    private String project; //所属プロジェクト
    private String devRoom; //所属開発室
    private String userId; //従業員番号
    private String remark; //備考
    private Integer selfConfirmId; //本人確認ID
    private Long osId; //OSID
    private Long memoryId; //メモリID
    private Long ssdId; //SSDID
    private Long hddId; //HDDID
    private LocalDateTime createTime; //作成日時
    private String creater; //作成者
    private LocalDateTime updateTime; //更新日時
    private String updater; //更新者


}
