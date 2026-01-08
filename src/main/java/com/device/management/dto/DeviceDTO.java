package com.device.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//デバイスDTO（統一辞書を使用）

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//デバイス情報DTO
public class DeviceDTO {

    //基本情報
    private String deviceId;//ホストデバイス番号", example = "HYRON-xxx PC-DC-001", required = true
    private String deviceModel;//ホストモデル, example = "dell-5000"
    private String computerName;//コンピューター名, example = "DA04-xx-pc"
    private String loginUsername;//ログインユーザー名, example = "zhangsan"
    private String project;//所属プロジェクト, example = "MS"
    private String devRoom;//所属開発室, example = "M2-A-01"
    private String userId;//ユーザー社員番号, example = "JS0001"
    private UserDTO userInfo;
    private String remark;//備考, example = "開発機"

    //辞書
    @JsonProperty("selfConfirm")
    private DictDTO selfConfirmDict;//本人確認辞書項目
    @JsonProperty("os")
    private DictDTO osDict;//オペレーティングシステム辞書項目
    @JsonProperty("memory")
    private DictDTO memoryDict;//メモリ辞書項目
    @JsonProperty("ssd")
    private DictDTO ssdDict;//SSD辞書項目
    @JsonProperty("hdd")
    private DictDTO hddDict;//HDD辞書項目

    //辞書ID
    private Long selfConfirmId;//本人確認ID, example = "1"
    private Long osId;//オペレーティングシステム辞書ID, example = "1"
    private Long memoryId;//メモリ辞書ID, example = "2"
    private Long ssdId;//SSD辞書ID, example = "3"
    private Long hddId;//HDD辞書ID, example = "4"

    //作成・更新情報
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//作成日時, example = "2023-10-01 09:00:00"
    private String creater;//作成者, example = "admin"
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新日時, example = "2023-10-02 10:30:00"
    private String updater;//更新者, example = "admin"

    //関連情報
    @JsonProperty("monitors")
    @Builder.Default
    private List<MonitorDTO> monitors = new ArrayList<>();//モニターリスト
    @JsonProperty("deviceIps")
    @Builder.Default
    private List<DeviceIpDTO> deviceIps = new ArrayList<>();//IPアドレスリスト

    //集計情報
    private String hardwareSummary;//ハードウェア構成サマリー, example = "OS: Windows 11 Pro | メモリ: 16GB | SSD: 512GB | HDD: 1TB"
    private String ipAddresses;//IPアドレス一覧, example = "192.168.1.100, 192.168.1.101"
    private Integer monitorCount;//モニター数, example = "2"
    private Integer ipCount;//IPアドレス数, example = "1"
    private String status;//デバイス状態, example = "ACTIVE"

}