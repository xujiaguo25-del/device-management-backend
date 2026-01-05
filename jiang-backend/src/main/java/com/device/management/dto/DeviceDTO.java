package com.device.management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

/**
 * 設備データ転送オブジェクト
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {

    // ========== 基本情報 ==========
    private String deviceId;          // 機器番号
    private String deviceModel;       // ホストモデル
    private String computerName;      // コンピュータ名
    private String loginUsername;     // ログインユーザ名

    // ========== 所属情報 ==========
    private String project;           // 所属プロジェクト
    private String devRoom;           // 所属開発室
    private String userId;            // 従業員番号

    // ========== 備考 ==========
    private String remark;            // 備考

    // ========== 確認情報 ==========
    private String selfConfirmId;     // 本人確認ID

    // ========== ハードウェア情報 ==========
    private Long osId;                // OSID
    private Long memoryId;            // メモリID
    private Long ssdId;               // SSDID
    private Long hddId;               // HDDID

    // ========== 作成情報 ==========
    private LocalDateTime createTime; // 作成日時
    private String creater;           // 作成者

    // ========== 更新情報 ==========
    private LocalDateTime updateTime; // 更新日時
    private String updater;           // 更新者
}