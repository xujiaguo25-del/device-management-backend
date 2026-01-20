package com.device;

import com.device.management.DeviceManagementApplication;
import com.device.management.repository.DictRepository;
import com.device.management.entity.Dict;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = DeviceManagementApplication.class)
public class DictDataInsertTest {

    @Autowired
    private DictRepository dictRepository;

    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    @Test
    public void insertAllDictData() {
        List<Dict> dictList = new ArrayList<>();

        // ------------------------------
        // USER_TYPE（ユーザータイプ）
        // ------------------------------
        dictList.add(buildDict(11L, "USER_TYPE", "ユーザータイプ", "システムユーザーのタイプ",
                "admin", 2, (short) 1,
                "2026-01-04 22:23:01.290470", "admin",
                "2026-01-04 23:45:02.154890", "admin"));
        dictList.add(buildDict(12L, "USER_TYPE", "ユーザータイプ", "システムユーザーのタイプ",
                "user", 1, (short) 1,
                "2026-01-04 22:23:01.290470", "admin",
                "2026-01-04 23:45:00.038927", "admin"));


        // ------------------------------
        // OS_TYPE（オペレーティングシステムタイプ）
        // ------------------------------
        dictList.add(buildDict(201L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows 7", 1, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:07.113395", "admin"));
        dictList.add(buildDict(202L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows 10", 2, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:08.975521", "admin"));
        dictList.add(buildDict(203L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows 11", 3, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:11.710822", "admin"));
        dictList.add(buildDict(204L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows Server 2008", 4, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:13.661055", "admin"));
        dictList.add(buildDict(205L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows Server 2016", 5, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:15.123456", "admin"));
        dictList.add(buildDict(206L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows Server 2019", 6, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:16.654321", "admin"));
        dictList.add(buildDict(207L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Windows Server 2022", 7, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:18.987654", "admin"));
        dictList.add(buildDict(208L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Mac OS", 8, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:20.123789", "admin"));
        dictList.add(buildDict(209L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "Ubuntu", 9, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:22.456123", "admin"));
        dictList.add(buildDict(210L, "OS_TYPE", "オペレーティングシステムタイプ", "デバイスで使用されるOSのタイプ",
                "CentOS", 10, (short) 1,
                "2026-01-04 23:43:32.499654", "admin",
                "2026-01-04 23:45:24.789123", "admin"));

        // ------------------------------
        // MEMORY_SIZE（メモリサイズ）
        // ------------------------------
        dictList.add(buildDict(31L, "MEMORY_SIZE", "メモリサイズ", "デバイスの物理メモリ容量",
                "4GB", 1, (short) 1,
                "2026-01-04 23:43:32.500961", "admin",
                "2026-01-04 23:46:53.865544", "admin"));
        dictList.add(buildDict(32L, "MEMORY_SIZE", "メモリサイズ", "デバイスの物理メモリ容量",
                "8GB", 2, (short) 1,
                "2026-01-04 23:43:32.500961", "admin",
                "2026-01-04 23:47:01.488466", "admin"));
        dictList.add(buildDict(33L, "MEMORY_SIZE", "メモリサイズ", "デバイスの物理メモリ容量",
                "16GB", 3, (short) 1,
                "2026-01-04 23:43:32.500961", "admin",
                "2026-01-04 23:46:52.712702", "admin"));
        dictList.add(buildDict(34L, "MEMORY_SIZE", "メモリサイズ", "デバイスの物理メモリ容量",
                "24GB", 4, (short) 1,
                "2026-01-04 23:43:32.500961", "admin",
                "2026-01-04 23:46:54.987654", "admin"));
        dictList.add(buildDict(35L, "MEMORY_SIZE", "メモリサイズ", "デバイスの物理メモリ容量",
                "32GB", 5, (short) 1,
                "2026-01-04 23:43:32.500961", "admin",
                "2026-01-04 23:46:55.123456", "admin"));
        dictList.add(buildDict(36L, "MEMORY_SIZE", "メモリサイズ", "デバイスの物理メモリ容量",
                "128GB", 6, (short) 1,
                "2026-01-04 23:43:32.500961", "admin",
                "2026-01-04 23:46:56.654321", "admin"));

        // ------------------------------
        // SSD_SIZE（ソリッドステートドライブ容量）
        // ------------------------------
        dictList.add(buildDict(41L, "SSD_SIZE", "ソリッドステートドライブ容量", "デバイスのSSDストレージ容量",
                "256GB", 1, (short) 1,
                "2026-01-04 23:43:32.501325", "admin",
                "2026-01-04 23:46:48.431263", "admin"));
        dictList.add(buildDict(42L, "SSD_SIZE", "ソリッドステートドライブ容量", "デバイスのSSDストレージ容量",
                "500GB", 2, (short) 1,
                "2026-01-04 23:43:32.501325", "admin",
                "2026-01-04 23:46:49.123456", "admin"));
        dictList.add(buildDict(43L, "SSD_SIZE", "ソリッドステートドライブ容量", "デバイスのSSDストレージ容量",
                "512GB", 3, (short) 1,
                "2026-01-04 23:43:32.501325", "admin",
                "2026-01-04 23:46:45.355822", "admin"));
        dictList.add(buildDict(44L, "SSD_SIZE", "ソリッドステートドライブ容量", "デバイスのSSDストレージ容量",
                "1TB", 4, (short) 1,
                "2026-01-04 23:43:32.501325", "admin",
                "2026-01-04 23:46:44.038207", "admin"));

        // ------------------------------
        // HDD_SIZE（ハードディスクドライブ容量）
        // ------------------------------
        dictList.add(buildDict(51L, "HDD_SIZE", "ハードディスクドライブ容量", "デバイスのHDDストレージ容量",
                "500GB", 1, (short) 1,
                "2026-01-04 23:43:32.501787", "admin",
                "2026-01-04 23:46:40.224721", "admin"));
        dictList.add(buildDict(52L, "HDD_SIZE", "ハードディスクドライブ容量", "デバイスのHDDストレージ容量",
                "1TB", 2, (short) 1,
                "2026-01-04 23:43:32.501787", "admin",
                "2026-01-04 23:46:41.123456", "admin"));
        dictList.add(buildDict(53L, "HDD_SIZE", "ハードディスクドライブ容量", "デバイスのHDDストレージ容量",
                "2TB", 3, (short) 1,
                "2026-01-04 23:43:32.501787", "admin",
                "2026-01-04 23:46:38.270224", "admin"));

        // ------------------------------
        // CONFIRM_STATUS（確認ステータス）
        // ------------------------------
        dictList.add(buildDict(62L, "CONFIRM_STATUS", "確認ステータス", "デバイスの確認ステータス",
                "未確認", 1, (short) 1,
                "2026-01-04 23:43:32.502066", "admin",
                "2026-01-04 23:46:32.930814", "admin"));
        dictList.add(buildDict(61L, "CONFIRM_STATUS", "確認ステータス", "デバイスの確認ステータス",
                "確認済み", 2, (short) 1,
                "2026-01-04 23:43:32.502066", "admin",
                "2026-01-04 23:46:35.355428", "admin"));



       
        dictRepository.saveAll(dictList);
    }
    /**
     * Dictオブジェクトを構築（重複ロジックをカプセル化）
     */
    private Dict buildDict(Long dictId, String typeCode, String typeName, String typeDesc,
                           String itemName, Integer sort, Short enabled,
                           String createTime, String creater,
                           String updateTime, String updater) {
        Dict dict = new Dict();
        dict.setDictId(dictId);
        dict.setDictTypeCode(typeCode);
        dict.setDictTypeName(typeName);
        dict.setDictTypeDescription(typeDesc);
        dict.setDictItemName(itemName);
        dict.setSort(sort);
        dict.setIsEnabled(enabled);
        dict.setCreateTime(LocalDateTime.parse(createTime, dtFormatter));
        dict.setCreater(creater);
        dict.setUpdateTime(LocalDateTime.parse(updateTime, dtFormatter));
        dict.setUpdater(updater);
        return dict;
    }
}
