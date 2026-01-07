package com.device.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    @Column(name = "user_id", insertable = false, updatable = false)
    private String userId;//従業員番号

    @Column(name = "remark", columnDefinition = "text")
    private String remark; //備考

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "self_confirm_id", referencedColumnName = "dict_id")
    private Dict selfConfirmDict; //本人確認辞書

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

    //関連関係（モニター、IPアドレス）

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    private Set<DeviceIp> ipList = new HashSet<>();

    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    private Set<Monitor> monitorList = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}