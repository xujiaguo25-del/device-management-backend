package com.device.management.enums;

import lombok.Getter;

/**
 * 辞書列挙
 * すべての辞書タイプと対応する辞書項目IDを含む
 */
@Getter
public enum DictEnum {
    // ------------------------------
    // USER_TYPE（ユーザータイプ）
    // ------------------------------
    USER_TYPE_ADMIN(11L, "USER_TYPE", "admin"),
    USER_TYPE_USER(12L, "USER_TYPE", "user"),

    // ------------------------------
    // OS_TYPE（オペレーティングシステムタイプ）
    // ------------------------------
    OS_TYPE_WINDOWS_7(201L, "OS_TYPE", "Windows 7"),
    OS_TYPE_WINDOWS_10(202L, "OS_TYPE", "Windows 10"),
    OS_TYPE_WINDOWS_11(203L, "OS_TYPE", "Windows 11"),
    OS_TYPE_WINDOWS_SERVER_2008(204L, "OS_TYPE", "Windows Server 2008"),
    OS_TYPE_WINDOWS_SERVER_2016(205L, "OS_TYPE", "Windows Server 2016"),
    OS_TYPE_WINDOWS_SERVER_2019(206L, "OS_TYPE", "Windows Server 2019"),
    OS_TYPE_WINDOWS_SERVER_2022(207L, "OS_TYPE", "Windows Server 2022"),
    OS_TYPE_MAC_OS(208L, "OS_TYPE", "Mac OS"),
    OS_TYPE_UBUNTU(209L, "OS_TYPE", "Ubuntu"),
    OS_TYPE_CENTOS(210L, "OS_TYPE", "CentOS"),

    // ------------------------------
    // MEMORY_SIZE（メモリサイズ）
    // ------------------------------
    MEMORY_SIZE_4GB(31L, "MEMORY_SIZE", "4GB"),
    MEMORY_SIZE_8GB(32L, "MEMORY_SIZE", "8GB"),
    MEMORY_SIZE_16GB(33L, "MEMORY_SIZE", "16GB"),
    MEMORY_SIZE_24GB(34L, "MEMORY_SIZE", "24GB"),
    MEMORY_SIZE_32GB(35L, "MEMORY_SIZE", "32GB"),
    MEMORY_SIZE_64GB(36L, "MEMORY_SIZE", "64GB"),
    MEMORY_SIZE_128GB(37L, "MEMORY_SIZE", "128GB"),

    // ------------------------------
    // SSD_SIZE（ソリッドステートドライブ容量）
    // ------------------------------
    SSD_SIZE_256GB(41L, "SSD_SIZE", "256GB"),
    SSD_SIZE_500GB(42L, "SSD_SIZE", "500GB"),
    SSD_SIZE_512GB(43L, "SSD_SIZE", "512GB"),
    SSD_SIZE_1TB(44L, "SSD_SIZE", "1TB"),

    // ------------------------------
    // HDD_SIZE（ハードディスクドライブ容量）
    // ------------------------------
    HDD_SIZE_500GB(51L, "HDD_SIZE", "500GB"),
    HDD_SIZE_1TB(52L, "HDD_SIZE", "1TB"),
    HDD_SIZE_2TB(53L, "HDD_SIZE", "2TB"),

    // ------------------------------
    // CONFIRM_STATUS（確認ステータス）
    // ------------------------------
    CONFIRM_STATUS_CONFIRMED(61L, "CONFIRM_STATUS", "確認済み"),
    CONFIRM_STATUS_UNCONFIRMED(62L, "CONFIRM_STATUS", "未確認"),

    // ------------------------------
    // DOMAIN_STATUS（ドメインステータス）
    // ------------------------------
    DOMAIN_STATUS_D1(3001L, "DOMAIN_STATUS", "D1"),
    DOMAIN_STATUS_D2(3002L, "DOMAIN_STATUS", "D2"),
    DOMAIN_STATUS_D3(3003L, "DOMAIN_STATUS", "D3"),
    DOMAIN_STATUS_D4(3004L, "DOMAIN_STATUS", "D4"),
    DOMAIN_STATUS_D5(3005L, "DOMAIN_STATUS", "D5"),
    DOMAIN_STATUS_D6(3006L, "DOMAIN_STATUS", "D6"),
    DOMAIN_STATUS_D7(3007L, "DOMAIN_STATUS", "D7"),
    DOMAIN_STATUS_EU(3008L, "DOMAIN_STATUS", "EU"),
    DOMAIN_STATUS_MG(3009L, "DOMAIN_STATUS", "MG"),
    DOMAIN_STATUS_NRI_01(3010L, "DOMAIN_STATUS", "NRI-01"),
    DOMAIN_STATUS_MS(3011L, "DOMAIN_STATUS", "MS"),
    DOMAIN_STATUS_EQU(3012L, "DOMAIN_STATUS", "EQU"),
    DOMAIN_STATUS_NOT_JOINED(3099L, "DOMAIN_STATUS", "未参加"),

    // ------------------------------
    // SMARTIT_STATUS（デバイスのSmartITインストールステータス）
    // ------------------------------
    SMARTIT_STATUS_LOCAL(3101L, "SMARTIT_STATUS", "本地"),
    SMARTIT_STATUS_REMOTE(3102L, "SMARTIT_STATUS", "远程"),
    SMARTIT_STATUS_NOT_INSTALLED(3103L, "SMARTIT_STATUS", "未安装"),

    // ------------------------------
    // USB_STATUS（デバイスのUSB使用許可ステータス）
    // ------------------------------
    USB_STATUS_CLOSED(3201L, "USB_STATUS", "关闭"),
    USB_STATUS_DATA(3202L, "USB_STATUS", "数据"),
    USB_STATUS_3G_NETWORK_CARD(3203L, "USB_STATUS", "3G网卡"),
    USB_STATUS_OTHER(3204L, "USB_STATUS", "其他"),

    // ------------------------------
    // ANTIVIRUS_STATUS（デバイスのアンチウイルスソフトインストールステータス）
    // ------------------------------
    ANTIVIRUS_STATUS_AUTO(3301L, "ANTIVIRUS_STATUS", "自动"),
    ANTIVIRUS_STATUS_MANUAL(3302L, "ANTIVIRUS_STATUS", "手动"),
    ANTIVIRUS_STATUS_NOT_INSTALLED(3399L, "ANTIVIRUS_STATUS", "未安装"),

    // ------------------------------
    // DEVICE_INFO_NULL（デバイス情報空値）
    // ------------------------------
    DEVICE_INFO_NULL(10000L, "DEVICE_INFO_NULL", "NULL");

    private final Long dictId;
    private final String typeCode;
    private final String itemName;

    DictEnum(Long dictId, String typeCode, String itemName) {
        this.dictId = dictId;
        this.typeCode = typeCode;
        this.itemName = itemName;
    }

    /**
     * 辞書IDで辞書列挙を検索
     */
    public static DictEnum findByDictId(Long dictId) {
        for (DictEnum dictEnum : values()) {
            if (dictEnum.getDictId().equals(dictId)) {
                return dictEnum;
            }
        }
        return null;
    }

    /**
     * 辞書タイプコードで辞書列挙を検索
     */
    public static DictEnum[] findByTypeCode(String typeCode) {
        return java.util.Arrays.stream(values())
                .filter(dictEnum -> dictEnum.getTypeCode().equals(typeCode))
                .toArray(DictEnum[]::new);
    }

    // 补充getter方法，确保外部可访问枚举属性
    public Long getDictId() {
        return dictId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getItemName() {
        return itemName;
    }
}