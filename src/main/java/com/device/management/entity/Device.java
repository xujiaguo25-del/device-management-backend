package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 機器オブジェクト
 */
@Data
@Entity
@Table(name = "device_info") // table name
public class Device {
    @Id
    @Column(name = "device_id", unique = true, nullable = false)
    private String deviceId; //機器番号

    @Column(name = "device_model")
    private String deviceModel; //ホストモデル

    @Column(name = "computer_name")
    private String computerName; //コンピュータ名

    @Column(name = "login_username")
    private String loginUsername; //ログインユーザ名

    @Column(name = "project")
    private String project; //所属プロジェクト

    @Column(name = "dev_room")
    private String devRoom; //所属開発室

    @Column(name = "user_id")
    private String userId; //従業員番号

    @Column(name = "remark", columnDefinition = "text")
    private String remark; //備考

    @Column(name = "self_confirm_id", columnDefinition = "bigint")
    private String selfConfirmId; //本人確認ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "os_id", referencedColumnName = "dict_id")
    private Dict osDict; // os辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id", referencedColumnName = "dict_id")
    private Dict memoryDict; //memory辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssd_id", referencedColumnName = "dict_id")
    private Dict ssdDict; // SSD辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hdd_id", referencedColumnName = "dict_id")
    private Dict hddDict; // HDD辞書

    @Column(name = "create_time", columnDefinition = "timestamp")
    private LocalDateTime createTime; //作成日時

    @Column(name = "creater")
    private String creater; //作成者

    @Column(name = "update_time", columnDefinition = "timestamp")
    private LocalDateTime updateTime; //更新日時

    @Column(name = "updater")
    private String updater; //更新者

    // ========== 関連関係（モニター、IPアドレス）==========

    @OneToMany(mappedBy = "device",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OrderBy("createTime ASC")
    private List<Monitor> monitors = new ArrayList<>();

    @OneToMany(mappedBy = "device",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OrderBy("createTime ASC")
    private List<DeviceIp> deviceIps = new ArrayList<>();

    // ========== 業務メソッド ==========
    //モニター追加

    public void addMonitor(Monitor monitor) {
        if (monitor != null) {
            monitor.setDevice(this);
            this.monitors.add(monitor);
        }
    }
    //モニター削除
    public void removeMonitor(Monitor monitor) {
        if (monitor != null) {
            monitor.setDevice(null);
            this.monitors.remove(monitor);
        }
    }
    //IPアドレス追加
    public void addDeviceIp(DeviceIp deviceIp) {
        if (deviceIp != null) {
            deviceIp.setDevice(this);
            this.deviceIps.add(deviceIp);
        }
    }
    //IPアドレス削除
    public void removeDeviceIp(DeviceIp deviceIp) {
        if (deviceIp != null) {
            deviceIp.setDevice(null);
            this.deviceIps.remove(deviceIp);
        }
    }

    /**
     * ハードウェア設定の概要情報を取得する
     */
    public String getHardwareSummary() {
        StringBuilder sb = new StringBuilder();

        if (osDict != null) {
            sb.append("OS: ").append(osDict.getDictItemName()).append(" | ");
        }

        if (memoryDict != null) {
            sb.append("メモリ: ").append(memoryDict.getDictItemName()).append(" | ");
        }

        if (ssdDict != null) {
            sb.append("SSD: ").append(ssdDict.getDictItemName()).append(" | ");
        }

        if (hddDict != null) {
            sb.append("HDD: ").append(hddDict.getDictItemName());
        }

        // 末尾の「 | 」を削除
        String result = sb.toString();
        if (result.endsWith(" | ")) {
            result = result.substring(0, result.length() - 3);
        }

        return result;
    }

    /**
     * ディクショナリ項目のタイプが正しいか検証する
     */
    public void validateDictTypes() {
        if (osDict != null && !osDict.isType(Dict.DictType.OS)) {
            throw new IllegalArgumentException("OS字典项类型不正确");
        }
        if (memoryDict != null && !memoryDict.isType(Dict.DictType.MEMORY)) {
            throw new IllegalArgumentException("内存字典项类型不正确");
        }
        if (ssdDict != null && !ssdDict.isType(Dict.DictType.SSD)) {
            throw new IllegalArgumentException("SSD字典项类型不正确");
        }
        if (hddDict != null && !hddDict.isType(Dict.DictType.HDD)) {
            throw new IllegalArgumentException("HDD字典项类型不正确");
        }
    }
}