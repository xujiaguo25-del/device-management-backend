-- dictテーブルをクリア
TRUNCATE TABLE dict CASCADE;

-- OSタイプ
INSERT INTO dict (dict_id, dict_type_code, dict_type_name, dict_item_name, sort, is_enabled, create_time) VALUES
(1, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows 7', 1, 1, CURRENT_TIMESTAMP),
(2, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows 10', 2, 1, CURRENT_TIMESTAMP),
(3, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows 11', 3, 1, CURRENT_TIMESTAMP),
(4, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows Server 2008', 4, 1, CURRENT_TIMESTAMP),
(5, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows Server 2016', 5, 1, CURRENT_TIMESTAMP),
(6, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows Server 2019', 6, 1, CURRENT_TIMESTAMP),
(7, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Windows Server 2022', 7, 1, CURRENT_TIMESTAMP),
(8, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Mac OS', 8, 1, CURRENT_TIMESTAMP),
(9, 'OS_TYPE', 'オペレーティングシステムタイプ', 'Ubuntu', 9, 1, CURRENT_TIMESTAMP),
(10, 'OS_TYPE', 'オペレーティングシステムタイプ', 'CentOS', 10, 1, CURRENT_TIMESTAMP);

-- メモリサイズ
INSERT INTO dict (dict_id, dict_type_code, dict_type_name, dict_item_name, sort, is_enabled, create_time) VALUES
(11, 'MEMORY_SIZE', 'メモリサイズ', '4GB', 1, 1, CURRENT_TIMESTAMP),
(12, 'MEMORY_SIZE', 'メモリサイズ', '8GB', 2, 1, CURRENT_TIMESTAMP),
(13, 'MEMORY_SIZE', 'メモリサイズ', '16GB', 3, 1, CURRENT_TIMESTAMP),
(14, 'MEMORY_SIZE', 'メモリサイズ', '24GB', 4, 1, CURRENT_TIMESTAMP),
(15, 'MEMORY_SIZE', 'メモリサイズ', '32GB', 5, 1, CURRENT_TIMESTAMP),
(16, 'MEMORY_SIZE', 'メモリサイズ', '128GB', 6, 1, CURRENT_TIMESTAMP);

-- ソリッドステートドライブ容量
INSERT INTO dict (dict_id, dict_type_code, dict_type_name, dict_item_name, sort, is_enabled, create_time) VALUES
(17, 'SSD_SIZE', 'ソリッドステートドライブ容量', '256GB', 1, 1, CURRENT_TIMESTAMP),
(18, 'SSD_SIZE', 'ソリッドステートドライブ容量', '500GB', 2, 1, CURRENT_TIMESTAMP),
(19, 'SSD_SIZE', 'ソリッドステートドライブ容量', '512GB', 3, 1, CURRENT_TIMESTAMP),
(20, 'SSD_SIZE', 'ソリッドステートドライブ容量', '1TB', 4, 1, CURRENT_TIMESTAMP);

-- ハードディスクドライブ容量
INSERT INTO dict (dict_id, dict_type_code, dict_type_name, dict_item_name, sort, is_enabled, create_time) VALUES
(21, 'HDD_SIZE', 'ハードディスクドライブ容量', '500GB', 1, 1, CURRENT_TIMESTAMP),
(22, 'HDD_SIZE', 'ハードディスクドライブ容量', '1TB', 2, 1, CURRENT_TIMESTAMP),
(23, 'HDD_SIZE', 'ハードディスクドライブ容量', '2TB', 3, 1, CURRENT_TIMESTAMP);

-- 確認ステータス
INSERT INTO dict (dict_id, dict_type_code, dict_type_name, dict_item_name, sort, is_enabled, create_time) VALUES
(24, 'CONFIRM_STATUS', '確認ステータス', '未確認', 1, 1, CURRENT_TIMESTAMP),
(25, 'CONFIRM_STATUS', '確認ステータス', '確認済み', 2, 1, CURRENT_TIMESTAMP);

-- ユーザータイプ
INSERT INTO dict (dict_id, dict_type_code, dict_type_name, dict_item_name, sort, is_enabled, create_time) VALUES
(26, 'USER_TYPE', 'ユーザータイプ', '一般ユーザー', 1, 1, CURRENT_TIMESTAMP),
(27, 'USER_TYPE', 'ユーザータイプ', '管理者', 2, 1, CURRENT_TIMESTAMP);