package com.device.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 機器オブジェクト
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "device_info")
public class Device {
    @Id
    @Size(max = 50)
    @Column(name = "device_id", unique = true, nullable = false, length = 50)
    private String deviceId; //機器番号

    @Size(max = 100)
    @Column(name = "device_model", length = 100)
    private String deviceModel; //ホストモデル

    @Size(max = 100)
    @Column(name = "computer_name", length = 100)
    private String computerName; //コンピュータ名

    @Size(max = 100)
    @Column(name = "login_username", length = 100)
    private String loginUsername; //ログインユーザ名

    @Size(max = 100)
    @Column(name = "project", length = 100)
    private String project; //所属プロジェクト

    @Size(max = 100)
    @Column(name = "dev_room", length = 100)
    private String devRoom; //所属開発室

    @Column(name = "user_id")
    private String userId; //従業員番号

    @Column(name = "remark", columnDefinition = "text", length = Integer.MAX_VALUE)
    private String remark; //備考

    @Column(name = "self_confirm_id", columnDefinition = "bigint")
    private Long selfConfirmId; //本人確認ID

    @Column(name = "os_id", columnDefinition = "bigint")
    private Long osId; //OSID

    @Column(name = "memory_id", columnDefinition = "bigint")
    private Long memoryId; //メモリID

    @Column(name = "ssd_id", columnDefinition = "bigint")
    private Long ssdId; //SSDID

    @Column(name = "hdd_id", columnDefinition = "bigint")
    private Long hddId; //HDDID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "self_confirm_id", referencedColumnName = "dict_id", insertable = false, updatable = false)
    private Dict selfConfirmDict; // 本人確認状態辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "os_id", referencedColumnName = "dict_id", insertable = false, updatable = false)
    private Dict osDict; // os 辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memory_id", referencedColumnName = "dict_id", insertable = false, updatable = false)
    private Dict memoryDict; //memory 辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssd_id", referencedColumnName = "dict_id", insertable = false, updatable = false)
    private Dict ssdDict; // SSD 辞書

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hdd_id", referencedColumnName = "dict_id", insertable = false, updatable = false)
    private Dict hddDict; // HDD 辞書

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createTime; //作成日時

    @Size(max = 100)
    @Column(name = "creater", length = 100)
    private String creater; //作成者

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_time", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime updateTime; //更新日時

    @Size(max = 100)
    @Column(name = "updater", length = 100)
    private String updater; //更新者

    // ============= 関連関係 =============

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * デバイスIPアドレスリスト（1対多）
     */
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<DeviceIp> deviceIps;

    /**
     * モニターリスト（1対多）
     */
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Monitor> monitorInfos;

    /**
     * デバイス権限（1対1）
     */
    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DevicePermission devicePermission;
}
