-- すべてのテーブルのデータを一括でクリアし、外部キーの依存関係を自動的に処理する
-- 注意：自動増分シーケンスはリセットされず、元の値から続行されます
-- 重要：実行前に必ずデータをバックアップしてください！
TRUNCATE TABLE
    device_info,
    device_ip,
    device_permission,
    dict,
    monitor_info,
    sampling_check,
    users
CASCADE;

-- DA04部門のすべてのデバイス情報をdevice_infoテーブルに挿入
-- 注意：空の値（"_"、"-"、""）はすべてNULLとして処理
-- 確認ステータスはすべて「未確認」（dict_id=24）に設定
INSERT INTO device_info (device_id, device_model, computer_name, login_username, project, dev_room, user_id, remark, self_confirm_id, os_id, memory_id, ssd_id, hdd_id, create_time, creater, update_time, updater) VALUES
-- 林飞のデバイス（JS0014）
('HYRON-221129 PC-DC-089', 'dell-5000', 'DA04-LINF-PC', 'linf-nrtu', 'D204-NRTU', 'M10', 'JS0014', 'D1005开发室', 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211217 PC-DC-058', 'dell-5090', 'HYRON-JS-LINFEI', 'linfei', 'DA05支援', 'M10', 'JS0014', 'D1005开发室', 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘文丰のデバイス（JS0020）
('HYRON-221129 PC-DC-055', 'dell-5000', 'da04-liuwfnri', 'lfeng', 'R&D+SBI', 'M5-A-09', 'JS0020', '开发机', 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 朱德涛のデバイス（JS0054）
('hyron-220914 pc-dc-053', 'dell-5000', 'DA04-LUNA-PC09', 'zhudt', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0054', NULL, 62, 202, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 潘小琴のデバイス（JS0105）
('HYRON-211029 MAC-001', 'macmini2020', 'Mac', 'mac', 'MTI-TECH-SOL', 'M2-A-01', 'JS0105', '潘小琴保管', 62, 208, 32, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240828 PC-DC-002', 'dell-5090', 'DA04-PANXQ', 'panxq-ms', 'MS-API基盤', 'M2-A-01', 'JS0105', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-241118 PC-DC-020', 'Optiplex', 'DA04-LUNA-PC22', 'panxq', 'MTI-TECH-LUNA', 'M2-A-01', 'JS0105', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈国宝のデバイス（JS0108）
('HYRON-211217 PC-DC-046', 'dell-5090', 'S2D-chengb-PC4', 'chengb', 'D204-TRSW', 'M2-A-02', 'JS0108', '权限不足，无法修改PC名称', 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-200902 PC-DC-001', 'lenvo 刃7000', 'stj', 'chengb', 'D204-TRSW', 'M2-A-02', 'JS0108', NULL, 62, 209, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-220622 PC-DC-001', 'DELL 3650 GPU', 'stj', 'chengb', 'D204-TRSW', NULL, 'JS0108', 'DA02借用，李庆', 62, 209, 36, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-220622 PC-DC-002', 'DELL 3650', 'stj', 'chengb', 'D204-TRSW', 'M2-A-01', 'JS0108', '三菱食品AI测试用', 62, 209, 36, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-230828 nobo-001', 'ThinkBook14+', 'DA04-CHENGB-note', NULL, 'D204-TRSW', 'M2-A-02', 'JS0108', '未加域，需要加域', 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-221018 PC-DC-022', '康佳DT31', 'DA04-chengb-01', 'DA04-NRI-TRSW', 'D204-TRSW', 'M2-A-02', 'JS0108', '上网机', 62, 202, 32, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-120412 pc-dc-007', 'optiplex-790', 'MS', 'administrator', 'MS', 'M2-A-01', 'JS0108', '服务器，已安装杀毒软件', 62, 205, 32, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240416 PC-DC-020', 'dell-5090', 'DA04-CHENGB-M', 'chengb-ms', 'MS', 'M2-A-01', 'JS0108', '开发机', 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 武志鹏のデバイス（JS0129）
('HYRON-211217 PC-DC-041', 'dell-5090', 'D4-WUZP-PC', 'wuzp', 'MTI-SC-AMUSE', 'M2-A-03', 'JS0129', '无修改PC名权限', 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曹向阳のデバイス（JS0177）
('HYRON-211217 PC-DC-045', 'dell-5090', 'DA04-CAOXY-PC', 'caoxy', 'TEC', 'M2-A-03', 'JS0177', NULL, 62, 202, 33, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-191107 PC-DC-025', 'dell-5070', 'DA04-CAOXY-PC2', 'caoxy', 'MTI-健診-DX', 'M2-A-03', 'JS0177', NULL, 62, 203, 35, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 欧金龙のデバイス（JS0199）
('HYRON-220914 PC-DC-056', 'dell-5000', 'DA04-OUJL-PC', 'oujl', 'MTI-SC-AMUSE', 'M2-A-02', 'JS0199', NULL, 62, 202, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-2020703 PC-iMac-001', 'iMac', 'iMac', 'Amuse-iMAC', 'MTI-SC-AMUSE', 'M2-A-02', 'JS0199', NULL, 62, 208, 32, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-210118 PC-DC-034', 'dell-5080', 'ubuntukaoqin', 'hyron-kaoqin', 'MTI-SC-AMUSE', 'M2-A-05', 'JS0199', '考勤系统服务器', 62, 209, 32, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王燕飞のデバイス（JS0263）
('HYRON-231127 PC-DC-008', 'Optiplex', 'DA04-WANGYANFEI-PC', 'wangyf', 'MTI-TECH-DC', 'M2-A-03', 'JS0263', NULL, 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 周仲山のデバイス（JS0266）
('HYRON-230705 nobo-001', 'Thinkpad X1', 'DA04-ZHOUZS-X1', 'zhouzs', NULL, 'M2-A-02', 'JS0266', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 徐冬琴のデバイス（JS0291）
('HYRON-211217 PC-DC-043', 'dell-5090', 'DA04-XUDQ-PC', 'xudq', 'MTI-SC-MEDIC', 'M2-A-05', 'JS0291', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 聂建军のデバイス（JS0298）
('HYRON-131216 PC-DC-028', 'dell-7010', 'DA04-MTI-FILE', 'niejj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '文件服务器', 62, 207, 33, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211217 PC-DC-040', 'dell-5090', 'DA04-NIEJJ-PC01', 'niejj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '开发机', 62, 202, 34, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-210305 PC-DC-011', 'DELL-5080', 'DA04-LUNA-PC21', 'biancr', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '空头武志鹏开发机', 62, 203, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-130408 nobo-001', 'Thinkpad X230i', 'DA04-LUNA-PC16', 'niejj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '笔记本', 62, 202, 31, NULL, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曹天扬のデバイス（JS0300）
('HYRON-181210 PC-DC-016', 'dell-5050', 'DA04-LUNA-PC13', 'caoty', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0300', '开发机', 62, 202, 33, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 卢淑美のデバイス（JS0356）
('HYRON-160818 PC-DC-011', 'dell-5040', 'hyron-pism-dev', 'root', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器', 62, 210, 32, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-150813 PC-DC-009', 'dell-7020', 'D4-PIT-NTASVR1', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器', 62, 205, 32, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-120412 pc-dc-018', 'dell-790', 'ADSERVER-PC', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器(PiSM LB用、MTI-PIT)', 62, 201, 32, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-191107 PC-DC-012', 'dell-5070', 'DA04-LUSHM-PC', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '开发机', 62, 202, 35, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-120412 pc-dc-013', 'dell-790', 'D4-PIT-PISMCS', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器', 62, 206, 33, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 袁晓东のデバイス（JS0426）
('HYRON-200106 MACMINI-001', 'macmini2018', 'MACMINI-001', 'life-ranger', 'R&D+SBI', 'M5-A-09', 'JS0426', 'NCTS测试机，门口', 62, 208, 32, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211217 PC-DC-050', 'dell-5090', 'DA04-YUANXD-PC', 'yuanxd', 'MTI-TECH-LIFE', 'M2-A-03', 'JS0426', 'Life开发机', 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-151127 PC-DC-006', 'dell-7020', 'DA04-NCTS-SRV-1', 'administrator', 'R&D+SBI', 'M5-A-09', 'JS0426', 'NCTS服务器，许加国处', 62, 205, 33, 41, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240828 PC-DC-005', 'dell-7010', 'HYRON-JS-YUANXD', 'yuanxd', 'DA05支援', 'M10', 'JS0426', 'A05支援', 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-220414 PC-DC-047', 'dell-5090', 'MS-SRV-NET', 'yuanxd', 'MS-生成AI', 'M2-A-01', 'JS0426', 'MS上网机', 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李琪のデバイス（JS0449）
('HYRON-221129 PC-DC-042', 'dell-5000', 'DA04-liqi', 'liqi', 'MTI-TECH-DC', 'M2-A-03', 'JS0449', '开发机', 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-161205 PC-DC-001', 'dell-5040', 'DA04-DC-DBSVR', 'wangjun2011', 'MTI-TECH-DC', 'M2-A-03', 'JS0449', '服务器', 62, 202, 33, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李行のデバイス（JS0572）
('HYRON-181112 PC-DC-025', 'dell-5050', 'DA04-LIXING', 'lixing', 'CAS-经费', 'M2-A-05', 'JS0572', '外借', 62, 202, 33, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王晓舟のデバイス（JS0596）
('HYRON-240416 PC-DC-017', 'dell-7010', 'DA04-WANGXZ-PC2', 'wangxz-ms', 'MS-リフト', 'M2-A-01', 'JS0596', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张娟のデバイス（JS0609）
('HYRON-240828 PC-DC-011', 'dell-7010', 'DA04-ZHANGJUAN-PC', 'zhangjuan-ms', 'MS-GCP', 'M2-A-01', 'JS0609', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-241118 PC-DC-009', 'Optiplex', 'DA04-ZHANGJUAN1-PC', 'x_zhangjuan', 'イーグル', 'M2-A-05', 'JS0609', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 袁娟のデバイス（JS0611）
('HYRON-211217 PC-DC-038', 'dell-5090', 'DA04-YUANJUAN-PC', 'yuanjuan', 'MTI-SC-MW', 'M2-A-02', 'JS0611', NULL, 62, 203, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵莉のデバイス（JS0619）
('HYRON-221129 PC-DC-041', 'dell-5000', 'DA04-LUNA-PC05', 'zhaoli', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0619', '开发机', 62, 202, 33, 43, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-171221 PC-DC-014', 'dell-5050', 'DA04-LUNA-PC18', 'lirunzhe', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0619', '空头刘皖豫开发机', 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('Hyron-191107 nobo-001', 'dell Inspiron5493', 'DA04-MTG-PC', 'zhaoli', NULL, 'M2-A-03', 'JS0619', '会议室笔记本', 62, 202, 32, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-131216 PC-DC-014', 'OPTIPLEX', 'DA04', 'zhaoli', NULL, 'M2-A-03', 'JS0619', '部门服务器', 62, 204, 32, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈卫东のデバイス（JS0627）
('HYRON-190605 PD-DC-017', 'dell-5050', 'DA04-CHENWD-PC5', 'chenwd', 'イーグル', 'M2-A-02', 'JS0627', NULL, 62, 203, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 莫尚勇のデバイス（JS0720）
('HYRON-221129 PC-DC-043', 'dell-5000', 'DA04-MOSY-PC', 'mosy', 'D204-TRSW', 'M2-A-02', 'JS0720', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('Longtoo-131219 Nobo-004', '笔记本mac pro', NULL, 'mosy', 'D204-TRSW', 'M2-A-02', 'JS0720', '基本不用', 62, 208, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张炎のデバイス（JS0746）
('HYRON-221129 PC-DC-050', 'dell-5000', 'DA04-zhangy-PC3', 'zhangyan', 'D204-TRSW', 'M2-A-02', 'JS0746', NULL, 62, 203, 34, 43, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙博のデバイス（JS0751）
('HYRON-240828 PC-DC-010', 'optiplex tower 7010', 'DA04-SUNBO-PC3', 'sunbo', 'MS-GCP', 'M2-A-02', 'JS0751', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈辰のデバイス（JS0798）
('HYRON-241118 PC-DC-002', 'dell-7010', 'DA04-CHENC-PC', 'chenchen', 'MS', 'M2-A-01', 'JS0798', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 董鸣辉のデバイス（JS0800）
('HYRON-211217 PC-DC-027', 'dell-5090', 'DA04-DONGMH-PC02', 'dongmh-nrtu', 'D204-NRTU', 'M5-A-09', 'JS0800', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-170620 PC-DC-008', 'dell-5050', 'DA04-DONGMH-PC01', 'dmh', 'D204-NRTU', 'M5-A-09', 'JS0800', '上网机', 62, 202, 33, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-130920 PC-DC-013', 'dell-7020', 'DA04-DONGMH-SE', 'nrtuser01', 'D204-NRTU', 'M5-A-09', 'JS0800', '文件服务器', 62, 202, 32, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-231127 PC-DC-007', 'optiplex', 'DESKTOP-A2A123', 'ISSM', 'D204-NRTU', 'M5-A-09', 'JS0800', 'NRTU测试机', 62, 202, 33, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨爱玉のデバイス（JS0833）
('HYRON-240416 PC-DC-015', 'dell-7010', 'HYRON-YANGAIYU', 'yangay-ms', 'MS-リフト', 'M2-A-01', 'JS0833', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240416 PC-DC-016', 'dell-7010', 'DA02-MAJW-PC2', 'majw-ms', 'MS-リフト', 'M2-A-01', 'JS0833', '原马敬文使用，显示器在陆跃跃处，主机在陈国宝座位处', 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张卫华のデバイス（JS0842）
('HYRON-240828 PC-DC-006', 'dell-7010', 'DA04-ZHANGWEIHUA-PC', 'zhangweihua', 'D204-TRSW', 'M2-A-02', 'JS0842', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 许加国のデバイス（JS0942）
('HYRON-220914 PC-DC-049', 'dell-5000', 'DA04-SUNYI-PC', 'xujg', 'R&D+SBI', 'M5-A-09', 'JS0942', NULL, 62, 202, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨皓伟のデバイス（JS0958）
('HYRON-220914 PC-DC-055', 'dell-5000', 'S2D-yanghw-PC4', 'yanghw', 'D204-TRSW', 'M2-A-02', 'JS0958', '电脑名无修改权限', 62, 203, 33, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵康2のデバイス（JS1061）
('230621-029(暂)', 'dell-5070', 'DA04-zhaokang2-PC', 'zhaokang2', 'MTI-SC-MW', 'M2-A-02', 'JS1061', 'IP设置了加速，换IP后部分软件不能用，所以暂时还用2段的这个IP', 62, 202, 35, 44, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 汪海燕のデバイス（JS1075）
('HYRON-211217 PC-DC-047', 'dell-5090', 'DA04-WANGHY-PC', 'wanghaiyan', 'MTI-SC-MW', 'M2-A-02', 'JS1075', '9/2~南京出勤', 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 蔡翔宇のデバイス（JS1148）
('HYRON-210621 PC-DC-010', 'dell-5080', 'DA04-CAIXY-PC', 'caixy', 'MTI-PIT', 'M2-A-05', 'JS1148', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈殊熠のデバイス（JS1150）
('HYRON-211217 PC-DC-020', 'dell-5090', 'DA04-CHENSY-PC', 'chensy-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1150', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 罗佳のデバイス（JS1174）
('HYRON-240828 PC-DC-012', 'dell-7010', 'DA04-LUOJIA-PC', 'luojia-ms', 'MS-GCP', 'M2-A-01', 'JS1174', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙鹏のデバイス（JS1180）
('HYRON-190605 PC-DC-054', 'dell-5050', 'DA04-sunpeng-pc', 'sunpeng', 'イーグル', 'M2-A-05', 'JS1180', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 潘宇のデバイス（JS1244）
('HYRON-211217 PC-DC-044', 'dell-5090', 'S2D-panyu-PC', 'panyu', 'イーグル', 'M2-A-05', 'JS1244', '无修改PC名权限', 62, 202, 35, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王邵赟のデバイス（JS1246）
('HYRON-210805 PC-DC-006', 'dell-5080', 'DA04-WANGSY-PC', 'wangshaoyun', 'MTI-PIT', 'M2-A-05', 'JS1246', NULL, 62, 202, 35, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211220 MAC-001', 'MAC mini', NULL, NULL, NULL, 'M2-A-03', 'JS1246', '部门纸质单据存放处的白色柜子里', 62, NULL, 32, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-221018 PC-DC-021', '康佳mini2022', 'ubuntu001', NULL, NULL, 'M2-A-05', 'JS1246', NULL, 62, 209, 32, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王冠杰のデバイス（JS1285）
('HYRON-210118 PC-DC-024', 'dell-5080', 'DA04-WANGGJ2-PC', 'wanggj2', 'R&D+SBI', 'M5-A-09', 'JS1285', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 邓怿泽のデバイス（JS1307）
('HYRON-220414 PC-DC-040', 'dell-5090', 'DA04-DENGYZ-PC4', 'dengyz-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1307', '小显示器？', 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王子晗のデバイス（JS1339）
('HYRON-220926 PC-DC-021', 'dell-5000', 'DA04-WANGZH-PC3', 'wangzihan', 'MTI-SC-MEDIC', 'M2-A-05', 'JS1339', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张欢のデバイス（JS1347）
('HYRON-211217 PC-DC-019', 'dell-5090', 'DA04-ZHANGHUAN3-PC', 'zhanghuan2', 'イーグル', 'M2-A-05', 'JS1347', NULL, 62, 203, 35, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 罗翔のデバイス（JS1359）
('HYRON-220926 PC-DC-008', 'dell-5000', 'DA04-luoxiang', 'luoxiang', 'MTI-TECH-LIFE', 'M2-A-03', 'JS1359', NULL, 62, 203, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-190805 macmini-001', 'macmini2018', 'MAC-mini-001', 'wangyu2', 'MTI-TECH-LIFE', 'M2-A-03', 'JS1359', '512是外接', 62, 208, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 应加俊のデバイス（JS1363）
('HYRON-231127 PC-DC-004', 'Optiplex', 'DA04-LUNA-PC03', 'yingjj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1363', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王孟洛のデバイス（JS1402）
('HYRON-210621 PD-DC-018', 'dell-5080', 'DA04-WANGML-PC', 'wangml', 'イーグル', 'M2-A-05', 'JS1402', NULL, 62, 202, 35, 42, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 贾乃康のデバイス（JS1435）
('HYRON-171017 PC-DC-006', 'dell-5050', 'DA04-JIANK-PC', 'jiank', 'MTI-TECH-SOL', 'M2-A-02', 'JS1435', '潘小琴保管', 62, 202, 33, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240828 PC-DC-018', 'Optiplex', 'HYRON-JS-JIANK', 'jiank', 'DA05支援', 'M10', 'JS1435', 'A05支援', 62, 202, 32, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 姜榕のデバイス（JS1436）
('HYRON-240828 PC-DC-001', 'Optiplex Tower', 'HYRON-JS-JIANGRONG', 'jiangrong', 'DA05支援', 'M10', 'JS1436', 'DA05支援', 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 章捷のデバイス（JS1510）
('HYRON-251201 PC-DC-016', 'Pro Tower QCT1250', 'DA04-ZHANGJIE-PC2', 'zhangjie-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1510', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 潘箬婷のデバイス（JS1519）
('HYRON-191107 PC-DC-010', 'dell-5070', 'DA04-PANRT-PC', 'panrt', 'MTI-TECH-DC', 'M2-A-03', 'JS1519', NULL, 62, 202, 33, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 丁楠のデバイス（JS1527）
('HYRON-221129 PC-DC-071', 'Optiplex 5000', 'DA04-DINGNAN-PC', 'dingnan', 'イーグル', 'M2-A-05', 'JS1527', NULL, 62, 202, 35, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 华丰のデバイス（JS1555）
('HYRON-240416 PC-DC-019', 'dell-7010', 'DA04-HUAFENG-PC', 'huaf-ms', 'MS-リフト', 'M2-A-01', 'JS1555', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 丁飞鸿のデバイス（JS1564）
('HYRON-221129 PC-DC-070', 'dell-5000', 'DESKTOP-EU3U356', 'dingfh', 'CAS-经费', 'M2-A-05', 'JS1564', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杜记航のデバイス（JS1565）
('HYRON-220914 PC-DC-051', 'dell-5000', 'DA04-LUNA-PC10', 'dujh', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1565', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曲家志のデバイス（JS1599）
('HYRON-210118 PC-DC-001', 'dell-5080', 'DA04-qujz-PC', 'qujz', 'MTI-SC-MW', 'M2-A-02', 'JS1599', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-171017 PC-DC-020', 'dell-5050', 'DA04-qujz-PC2', 'qujz', 'MTI-SC-MW', 'M2-A-02', 'JS1599', NULL, 62, 202, 32, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 施炜玮のデバイス（JS1607）
('HYRON-210118 PC-DC-012', 'dell-5080', 'DA04-SHIWW-PC', 'shiww', 'MTI-健診-DX', 'M2-A-03', 'JS1607', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨杨のデバイス（JS1613）
('HYRON-220926 PC-DC-009', 'dell-5000', 'HYRON-JS-YANGYANG', 'yangyang', 'MTI-TECH-LIFE', 'M2-A-03', 'JS1613', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 朱国威のデバイス（JS1640）
('HYRON-231127 PC-DC-002', 'Optiplex', 'DA04-ZHUGW-PC01', 'zhugw', 'MTI-SC-AMUSE', 'M2-A-02', 'JS1640', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 庄子泓のデバイス（JS1663）
('HYRON-191107 PC-DC-031', 'dell-5070', 'DA04-ZZH-PC', 'zhuangzh-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1663', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘乃伟のデバイス（JS1683）
('HYRON-220914 PC-DC-054', 'dell-5000', 'DA04-LIUNW-PC6', 'liunw', 'D204-NRTU', 'M5-A-09', 'JS1683', '10F', 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 卞春荣のデバイス（JS1697）
('HYRON-220914 PC-DC-052', 'dell-5000', 'DA04-LUNA-PC08', 'biancr', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1697', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵祥富のデバイス（JS1715）
('HYRON-221129 PC-DC-044', 'dell-5000', 'DA04-ZHAOXINAGFU-PC2', 'zhaoxiangfu', 'MTI-SC-MEDIC', 'M2-A-05', 'JS1715', NULL, 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘皖豫のデバイス（JS1719）
('HYRON-240416 PC-DC-014', 'OptiPlex Tower', 'HEJUNJIE-PC', 'hejj-ms', 'MS-リフト', 'M2-A-01', 'JS1719', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何江龙のデバイス（JS1720）
('HYRON-220926 PC-DC-023', 'dell-5000', 'DA04-LUNA-PC07', 'hejl', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1720', '显示器在卞春荣处', 62, 202, 34, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李润哲のデバイス（JS1736）
('HYRON-220125 PC-DC-017', 'dell-5090', 'DA04-LUNA-PC17', 'lirunzhe', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1736', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李纪飞のデバイス（JS1757）
('HYRON-241118 PC-DC-001', 'dell-7010', 'DA04-LIJF-PC2', 'lijf', 'MTI-TECH-DC', 'M2-A-03', 'JS1757', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李智勇のデバイス（JS1809）
('HYRON-240828 PC-DC-007', 'dell-7010', 'DA04-lizy2-PC2', 'lizy2', 'MTI-健診-DX', 'M2-A-03', 'JS1809', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 马泽原のデバイス（JS1840）
('HYRON-221129 PC-DC-069', 'dell-5000', 'DESKTOP-B08J4HA', 'mazy', NULL, 'M5', 'JS1840', '外借', 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-181112 PC-DC-002', NULL, NULL, NULL, NULL, '6F', 'JS1840', '已归还', 62, 203, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 宋柳叶のデバイス（JS1873）
('HYRON-220414 PC-DC-049', 'dell-5090', 'DA04-SONGLY-PC', 'songly', 'MTI-SC-AMUSE', 'M2-A-02', 'JS1873', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李宝林のデバイス（JS1882）
('HYRON-170425 PC-DC-006', 'dell-5040', 'S2D-LIBL-PC1', 'libl', 'MTI-TECH-DC', 'M2-A-03', 'JS1882', '无修改PC名权限', 62, 202, 33, NULL, 51, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨祥坤のデバイス（JS2045）
('230621-027(暂)', 'dell-5090', 'DA04-YANGXK-PC2', 'yangxk2-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2045', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李芷栅のデバイス（JS2050）
('HYRON-210125 PC-DC-015', 'dell-5080', 'DA04-LIZS-PC', 'lizs', 'MTI-SC-MW', 'M2-A-02', 'JS2050', NULL, 62, 202, 33, 43, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王天昊のデバイス（JS2053）
('HYRON-221129 PC-DC-007', 'dell-5000', 'DA04-WANGTH-PC1', 'wangth-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2053', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 唐凯2のデバイス（JS2060）
('HYRON-220414 PC-DC-043', 'dell-5090', 'DA04-TANGKAI-PC', 'tangkai2', 'MTI-PIT', 'M2-A-05', 'JS2060', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李勇のデバイス（JSXP406）
('HYRON-221129 PC-DC-051', 'dell-5000', 'DA04-LIYONG-PC', 'liyong', 'R&D+SBI', 'M5-A-09', 'JSXP406', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈传真のデバイス（JSXP438）
('HYRON-210118 PC-DC-033', 'dell-5080', 'DA04-chencz-pc', 'chencz', 'MTI-SC-MW', 'M2-A-05', 'JSXP438', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆建周のデバイス（JSXP446）
('HYRON-231127 PC-DC-003', 'Optiplex', 'NRTU-GAOBY-PC', 'lujz', 'R&D+SBI', 'M5-A-09', 'JSXP446', '无权限修改机器名', 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王天润のデバイス（JS2047）
('HYRON-220926 PC-DC-007', 'dell-5000', 'DA04-WANGTR-PC3', 'wangtr-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2047', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 高雨彤のデバイス（JS2176）
('HYRON-221129 PC-DC-068', 'optiplex', 'DA04-GAOYT-PC', 'gaoyt', 'D204-TRSW', 'M2-A-02', 'JS2176', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵晨云のデバイス（JS2177）
('HYRON-220914 PC-DC-050', 'dell-5000', 'DA04-ZHAOCHENY-PC3', 'zhaocheny', 'TEC', 'M2-A-03', 'JS2177', NULL, 62, 202, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 田季伟のデバイス（JS2187）
('HYRON-240828 PC-DC-009', 'dell-7010', 'DA04-TIANJW-PC', 'tianjw-MS', 'MS-GCP', 'M2-A-01', 'JS2187', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 祁文轩のデバイス（JS1926）
('HYRON-241118 PC-DC-005', 'dell-7010', 'DA04-LUNA-PC11', 'qiwx', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1926', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 吴智雄のデバイス（JS1939）
('HYRON-181112 PC-DC-013', 'dell-5050', 'DA04-WZX-001', 'wuzhix', 'D204-TRSW', 'M2-A-02', 'JS1939', '已经归还', 62, 203, 33, NULL, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240416 PC-DC-001', 'optiplex', 'DA04-Heyuan-01', 'zhangyan', 'D204-TRSW', 'M2-A-02', 'JS1939', '陈卫东远程工作用', 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-251201 PC-DC-015', 'dell-1250', 'DA04-WZX-002', 'wuzhix', 'D204-TRSW', 'M2-A-02', 'JS1939', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 左恩のデバイス（JS1941）
('HYRON-241118 PC-DC-006', 'dell-7010', 'DA04-ZUOEN-PC01', 'zuoen-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1941', NULL, 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王昱のデバイス（JS1973）
('HYRON-241118 PC-DC-040', 'dell-7010', 'DA04-WANGYU-PC', 'wangy', 'MTI-PIT', 'M2-A-05', 'JS1973', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张攀のデバイス（JS1981）
('HYRON-241118 PC-DC-068', 'dell-7010', 'DA04-ZHANGPAN-01', 'zhangpan', 'D204-TRSW', 'M2-A-02', 'JS1981', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李兴旺のデバイス（JS1982）
('HYRON-241118 PC-DC-008', 'dell-7010', 'DA04-LIXW-PC', 'lixw', 'MTI-TECH-DC', 'M2-A-03', 'JS1982', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 母丽丽のデバイス（JS1992）
('HYRON-241118 PC-DC-004', 'dell-7010', 'DA04-MULL-PC', 'mull', 'MTI-PIT', 'M2-A-05', 'JS1992', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 顾航睿のデバイス（JS1996）
('HYRON-241118 PC-DC-007', 'dell-7010', 'DA04-LUNA-PC11', 'guhr', 'MTI-健診-DX', 'M2-A-03', 'JS1996', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈诺のデバイス（JS2005）
('HYRON-241118 PC-DC-039', 'dell-7010', 'DA04-CHENNUO-PC01', 'chennuo-nrtu', 'D204-NRTU', 'M2-A-02', 'JS2005', NULL, 62, 202, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 高鑫のデバイス（JS2008）
('HYRON-241118 PC-DC-003', 'dell-7010', 'DA04-GAOXIN-PC01', 'gaoxin2-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2008', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王小龙のデバイス（JS2019）
('HYRON-241118 PC-DC-042', 'dell-7010', 'DA04-WANGXL-PC', 'wangxl-ms', 'MS-リフト', 'M2-A-01', 'JS2019', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何启明のデバイス（JS2024）
('HYRON-241118 PC-DC-041', 'dell-7010', 'DA04-HEQM-PC', 'heqm', 'R&D+SBI', 'M5-A-09', 'JS2024', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘子俊のデバイス（JS2034）
('HYRON-241118 PC-DC-043', 'dell-7010', 'DA04-liuzj-PC', 'liuzj-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2034', NULL, 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 蔡文强のデバイス（JS2037）
('HYRON-211217 PC-DC-042', 'dell-5090', 'DA04-CAIWQ-PC', 'caiwq', 'MTI-TECH-DC', 'M2-A-03', 'JS2037', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 姜成杰のデバイス（JS2178）
('HYRON-191217 PC-DC-006', 'dell-5070', 'DA04-JIANGCJ-PC', 'jiangcj', 'MTI-SC-MEDIC', 'M2-A-05', 'JS2178', NULL, 62, 202, 33, 43, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙毅のデバイス（JSXP536）
('HYRON-210118 PC-DC-015', 'dell-5070', 'DA04-SUNYI-PC', 'suny', 'R&D+SBI', 'M5-A-09', 'JSXP536', NULL, 62, 202, 33, 42, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆语涵のデバイス（JSXP538）
('HYRON-221129 PC-DC-019', 'OptiPlex5000', 'DESKTOP-QINQMVA', 'luyh', 'R&D+SBI', 'M5-A-09', 'JSXP538', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 季荷飞のデバイス（JSXP539）
('HYRON-220914 PC-DC-011', 'dell-5000', 'DA04_JIHEFEI-PC', 'jihf', 'R&D+SBI', 'M5-A-09', 'JSXP539', NULL, 62, 203, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 戴明のデバイス（JSXP541）
('HYRON-210621 PC-DC-021', 'dell-5080', 'DA04-JSXP541-PC', 'daiming', 'R&D+SBI', 'M5-A-09', 'JSXP541', NULL, 62, 203, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曹光友のデバイス（JSXP551）
('HYRON-210118 PC-DC-027', 'dell-5080', 'DA04-CAOGY-PC', 'yangxk', 'D204-NRTU', 'M5-A-09', 'JSXP551', NULL, 62, 203, 33, 43, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 黄羽轩のデバイス（JSSX077）
('HYRON-190605 PC-DC-016', 'dell-5050', 'HUANGYX-PC', 'huangyux', 'イーグル', 'M2-A-05', 'JSSX077', NULL, 62, 202, 34, NULL, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 徐睿のデバイス (DA02部門)（JS0898）
('HYRON-210125 PC-DC-005', 'dell-5080', 'DA02-XURUI-PC1', 'xurui', 'R&D+SBI', 'M5-A-09', 'JS0898', '大显示器是DA02申请', 62, 202, 35, 43, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 胡浩磊のデバイス (DA02部門)（JS1158）
('HYRON-220414 PC-DC-042', 'dell-5090', 'NRTU-huhl-PC', 'huhl-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1158', NULL, 62, 202, 33, 41, 52, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆跃跃のデバイス (DA02部門)（JS2056）
('HYRON-240416 PC-DC-018', 'OptiPlex Tower7010', 'DA02-LUYY-PC2', 'luyy-ms', 'MS-リフト', 'M2-A-01', 'JS2056', '名下有台主机未登记，借用杨爱玉的显示器HYRON-240826 Minitor-003', 62, 203, 35, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 吴正明のデバイス (DA02部門)（JS0226）
('HYRON-240416 PC-DC-012', 'OptiPlex', 'wuzm-DA02-01', 'wuzm', 'イーグル', 'M2-A-05', 'JS0226', NULL, 62, 202, 35, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-221129 PC-DC-048', 'dell-5000', 'DESKTOP-7S6JGB6', 'wuzm', 'CAS-经费', 'M2-A-05', 'JS0226', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 朱亚明のデバイス (DA02部門)（JS0296）
('HYRON-211217 PC-DC-057', 'dell-5090', 'S2D-ZHUYM-PC02', 'zhuym', 'イーグル', 'M2-A-05', 'JS0296', NULL, 62, 202, 33, 42, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何桢のデバイス (DA02部門)（JS0941）
('HYRON-220414 PC-DC-044', 'dell-5090', 'S2D-HEZHEN-PC02', 'hezhen', 'イーグル', 'M2-A-05', 'JS0941', NULL, 62, 202, 35, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙萧のデバイス (DA02部門)（JS1672）
('HYRON-240416 PC-DC-013', 'dell-7010', 'DESKTOP-sunxiao', 'sunxiao', 'イーグル', 'M2-A-05', 'JS1672', NULL, 62, 203, 35, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈楠钢のデバイス (DA02部門)（JS1699）
('HYRON-220926 PC-DC-006', 'dell-5000', 'DA04-CHENNG-PC4', 'chenng', 'CAS-经费', 'M2-A-05', 'JS1699', NULL, 62, 202, 33, 41, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 顾新宇のデバイス (DA02部門)（JS1929）
('HYRON-211217 PC-DC-056', 'dell-5090', 'DESKTOP-TANGKAI', 'guxiny', 'イーグル', 'M2-A-05', 'JS1929', NULL, 62, 202, 35, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈兆伟のデバイス (DA02部門)（JS2167）
('HYRON-151020 PC-DC-002', 'dell-7080', 'DA02-CHENZHAOWEI-PC', 'chenzw', 'CAS-经费', 'M2-A-05', 'JS2167', NULL, 62, 202, 33, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 田梵君のデバイス (DA02部門)（JS2171）
('HYRON-170904 PC-DC-009', 'dell-5050', 'DA02-Tianfj-PC2', 'tianfj', 'CAS-经费', 'M2-A-05', 'JS2171', NULL, 62, 202, 33, 43, 43, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 卢国颖のデバイス (DA02部門)（JS2172）
('HYRON-160818 PC-DC-016', 'dell-5040', 'DA02-LuGY-PC2', 'luhuoy', 'CAS-经费', 'M2-A-05', 'JS2172', NULL, 62, 202, 33, 43, 43, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆红非のデバイス (DA05部門)（JS1137）
('HYRON-240828 PC-DC-008', 'OptiPlex Tower 7010', 'DA02-ZHANGYF-PC', 'luhf-ms', 'MS-リフト', 'M2-A-01', 'JS1137', '日本出差中', 62, 203, 33, 43, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何渊のデバイス (DA05部門)（SH726）
('HYRON-250123 nobo-001', NULL, NULL, 'heyuan', 'D204-TRSW', 'M2-A-02', 'SH726', '南京笔记本', 62, 203, 33, 44, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

-- device_ip テーブルの作成とデータ挿入
DROP TABLE IF EXISTS device_ip;
DROP SEQUENCE IF EXISTS device_ip_ip_id_seq CASCADE;
CREATE SEQUENCE device_ip_ip_id_seq;
CREATE TABLE device_ip (
    ip_id integer PRIMARY KEY DEFAULT nextval('device_ip_ip_id_seq'),
    device_id character varying(50) REFERENCES device_info(device_id),
    ip_address character varying(50),
    create_time timestamp(6) without time zone,
    creater character varying(100),
    update_time timestamp(6) without time zone,
    updater character varying(100)
);

--device_ip テーブルへのデータ挿入
INSERT INTO device_ip (device_id, ip_address, create_time, update_time, creater, updater) VALUES 

-- JS0014 林飞のデバイス1
('HYRON-221129 PC-DC-089', '173.28.100.112', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0014 林飞のデバイス2
('HYRON-211217 PC-DC-058', '192.168.203.139', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0020 刘文丰のデバイス
('HYRON-221129 PC-DC-055', '172.28.100.50', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0054 朱德涛のデバイス
('hyron-220914 pc-dc-053', '10.6.1.36', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-220914 pc-dc-053', '10.6.1.244', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-220914 pc-dc-053', '10.6.3.232', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0105 潘小琴のデバイス1
('HYRON-211029 MAC-001', '10.6.1.48', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0105 潘小琴のデバイス2
('HYRON-240828 PC-DC-002', '174.28.100.36', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-002', '174.28.100.166', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0105 潘小琴のデバイス3
('HYRON-241118 PC-DC-020', '10.6.1.68', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0108 陈国宝のデバイス1
('HYRON-211217 PC-DC-046', '172.28.101.130', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-046', '172.28.101.10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0108 陈国宝のデバイス6
('hyron-120412 pc-dc-007', '174.28.100.229', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0108 陈国宝のデバイス7
('HYRON-240416 PC-DC-020', '174.28.100.30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-020', '174.28.100.160', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0108 陈国宝のデバイス5
('HYRON-221018 PC-DC-022', '10.6.1.13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0129 武志鹏のデバイス
('HYRON-211217 PC-DC-041', '10.6.1.108', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0177 曹向阳のデバイス1
('HYRON-211217 PC-DC-045', '10.6.1.116', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-045', '10.6.3.227', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0177 曹向阳のデバイス2
('HYRON-191107 PC-DC-025', '10.6.1.241', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0199 欧金龙のデバイス1
('HYRON-220914 PC-DC-056', '10.6.1.126', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0199 欧金龙のデバイス2
('HYRON-2020703 PC-iMac-001', '10.6.1.129', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0199 欧金龙のデバイス3
('HYRON-210118 PC-DC-034', '172.28.1.60', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0263 王燕飞のデバイス
('HYRON-231127 PC-DC-008', '10.6.1.110', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0266 周仲山のデバイス
('HYRON-230705 nobo-001', '10.6.1.79', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-230705 nobo-001', '172.28(DHCP)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0291 徐冬琴のデバイス
('HYRON-211217 PC-DC-043', '10.6.1.121', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-043', '10.6.1.195', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-043', '10.6.3.250', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0298 聂建军のデバイス1
('HYRON-131216 PC-DC-028', '10.6.1.65', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0298 聂建军のデバイス2
('HYRON-211217 PC-DC-040', '10.6.1.66', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-040', '10.6.1.221', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-040', '10.6.3.236', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0298 聂建军のデバイス3
('HYRON-210305 PC-DC-011', '10.6.1.106', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0298 聂建军のデバイス4
('hyron-130408 nobo-001', '10.6.1.220', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0300 曹天扬のデバイス
('HYRON-181210 PC-DC-016', '10.6.3.202', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0356 卢淑美のデバイス1
('HYRON-160818 PC-DC-011', '10.6.3.213', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0356 卢淑美のデバイス2
('HYRON-150813 PC-DC-009', '10.6.1.31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0356 卢淑美のデバイス3
('hyron-120412 pc-dc-018', '10.6.1.39', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-120412 pc-dc-018', '10.6.3.201', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0356 卢淑美のデバイス4
('HYRON-191107 PC-DC-012', '10.6.3.225', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-191107 PC-DC-012', '10.6.1.170', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-191107 PC-DC-012', '10.6.1.203', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0356 卢淑美のデバイス5
('hyron-120412 pc-dc-013', '10.6.1.127', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0426 袁晓东のデバイス1
('HYRON-200106 MACMINI-001', '172.28.100.170', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0426 袁晓东のデバイス2
('HYRON-211217 PC-DC-050', '10.6.1.37', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-050', '10.6.1.255', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-050', '10.6.3.211', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0426 袁晓东のデバイス3
('HYRON-151127 PC-DC-006', '172.28.100.201', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0426 袁晓东のデバイス4
('HYRON-240828 PC-DC-005', '192.168.203.105', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0426 袁晓东のデバイス5
('HYRON-220414 PC-DC-047', '10.6.1.147', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0449 李琪のデバイス1
('HYRON-221129 PC-DC-042', '10.6.1.112', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-042', '10.6.3.252', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-042', '10.6.1.240', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0449 李琪のデバイス2
('HYRON-161205 PC-DC-001', '10.6.1.12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0572 李行のデバイス
('HYRON-181112 PC-DC-025', '10.6.1.50', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-181112 PC-DC-025', '10.6.1.200', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0596 王晓舟のデバイス
('HYRON-240416 PC-DC-017', '174.28.100.15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-017', '174.28.100.135', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0609 张娟のデバイス1
('HYRON-240828 PC-DC-011', '174.28.100.72', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-011', '174.28.100.203', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0609 张娟のデバイス2
('HYRON-241118 PC-DC-009', '10.6.1.201', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0611 袁娟のデバイス
('HYRON-211217 PC-DC-038', '10.6.1.119', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-038', '10.6.1.135', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0619 赵莉のデバイス1
('HYRON-221129 PC-DC-041', '10.6.1.56', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-041', '10.6.3.222', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-041', '10.6.1.208', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0619 赵莉のデバイス2
('HYRON-171221 PC-DC-014', '10.6.1.123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0619 赵莉のデバイス3
('Hyron-191107 nobo-001', '10.6.1.78', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0619 赵莉のデバイス4
('HYRON-131216 PC-DC-014', '10.6.1.81', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0627 陈卫东のデバイス
('HYRON-190605 PD-DC-017', '10.6.1.1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0720 莫尚勇のデバイス1
('HYRON-221129 PC-DC-043', '172.28.101.24', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0746 张炎のデバイス
('HYRON-221129 PC-DC-050', '172.28.101.12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-050', '172.28.101.132', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0751 孙博のデバイス
('HYRON-240828 PC-DC-010', '10.6.1.128', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0798 陈辰のデバイス
('HYRON-241118 PC-DC-002', '10.6.1.247', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0800 董鸣辉のデバイス1
('HYRON-211217 PC-DC-027', '173.28.100.111', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0800 董鸣辉のデバイス2
('HYRON-170620 PC-DC-008', '10.6.1.205', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0800 董鸣辉のデバイス3
('hyron-130920 PC-DC-013', '173.28.100.200', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0800 董鸣辉のデバイス4
('HYRON-231127 PC-DC-007', '173.28.100.122', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0833 杨爱玉のデバイス1
('HYRON-240416 PC-DC-015', '174.28.100.16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-015', '174.28.100.136', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0833 杨爱玉のデバイス2
('HYRON-240416 PC-DC-016', '174.28.100.13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-016', '174.28.100.133', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0842 张卫华のデバイス
('HYRON-240828 PC-DC-006', '172.28.101.13', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-006', '172.28.101.133', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0942 许加国のデバイス
('HYRON-220914 PC-DC-049', '172.28.100.165', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0958 杨皓伟のデバイス
('HYRON-220914 PC-DC-055', '172.28.101.11', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220914 PC-DC-055', '172.28.101.131', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1061 赵康2のデバイス
('230621-029(暂)', '10.6.2.247', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1075 汪海燕のデバイス
('HYRON-211217 PC-DC-047', '10.6.1.137', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1148 蔡翔宇のデバイス
('HYRON-210621 PC-DC-010', '10.6.1.105', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210621 PC-DC-010', '10.6.3.226', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1150 陈殊熠のデバイス
('HYRON-211217 PC-DC-020', '173.28.100.142', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-020', '10.6.1.140', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1174 罗佳のデバイス
('HYRON-240828 PC-DC-012', '174.28.100.73', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-012', '174.28.100.204', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1180 孙鹏のデバイス
('HYRON-190605 PC-DC-054', '10.6.1.245', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-190605 PC-DC-054', '10.6.2.254', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1244 潘宇のデバイス
('HYRON-211217 PC-DC-044', '10.6.1.59', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-044', '10.6.3.207', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1246 王邵赟のデバイス1
('HYRON-210805 PC-DC-006', '10.6.3.240', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210805 PC-DC-006', '10.6.1.14', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210805 PC-DC-006', '10.6.1.199', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1285 王冠杰のデバイス
('HYRON-210118 PC-DC-024', '172.28.100.56', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210118 PC-DC-024', '172.28.100.156', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1307 邓怿泽のデバイス
('HYRON-220414 PC-DC-040', '173.28.100.125', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220414 PC-DC-040', '10.6.1.98', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1339 王子晗のデバイス
('HYRON-220926 PC-DC-021', '10.6.1.107', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220926 PC-DC-021', '10.6.3.230', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1347 张欢のデバイス
('HYRON-211217 PC-DC-019', '10.6.2.245', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1359 罗翔のデバイス1
('HYRON-220926 PC-DC-008', '10.6.3.210', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1359 罗翔のデバイス2
('hyron-190805 macmini-001', '10.6.1.150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1363 应加俊のデバイス
('HYRON-231127 PC-DC-004', '10.6.1.96', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-231127 PC-DC-004', '10.6.3.251', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1402 王孟洛のデバイス
('HYRON-210621 PD-DC-018', '10.6.1.92', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210621 PD-DC-018', '10.6.2.251', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1435 贾乃康のデバイス1
('HYRON-171017 PC-DC-006', '10.6.1.159', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-171017 PC-DC-006', '10.6.3.218', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1436 姜榕のデバイス
('HYRON-240828 PC-DC-001', '192.168.203.109', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1510 章捷のデバイス1
('HYRON-251201 PC-DC-016', '173.28.100.120', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1519 潘箬婷のデバイス
('HYRON-191107 PC-DC-010', '10.6.1.104', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1527 丁楠のデバイス
('HYRON-221129 PC-DC-071', '10.6.2.214', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1555 华丰のデバイス
('HYRON-240416 PC-DC-019', '174.28.100.131', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1564 丁飞鸿のデバイス
('HYRON-221129 PC-DC-070', '10.6.1.76', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1565 杜记航のデバイス
('HYRON-220914 PC-DC-051', '10.6.1.77', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220914 PC-DC-051', '10.6.1.242', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1599 曲家志のデバイス1
('HYRON-210118 PC-DC-001', '10.6.1.63', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1599 曲家志のデバイス2
('HYRON-171017 PC-DC-020', '10.6.1.189', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1607 施炜玮のデバイス
('HYRON-210118 PC-DC-012', '10.6.1.23', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1613 杨杨のデバイス
('HYRON-220926 PC-DC-009', '10.6.1.18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1640 朱国威のデバイス
('HYRON-231127 PC-DC-002', '10.6.1.57', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1663 庄子泓のデバイス
('HYRON-191107 PC-DC-031', '173.28.100.144', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1683 刘乃伟のデバイス
('HYRON-220914 PC-DC-054', '172.28.100.147', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1697 卞春荣のデバイス
('HYRON-220914 PC-DC-052', '10.6.1.109', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220914 PC-DC-052', '10.6.3.254', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1715 赵祥富のデバイス
('HYRON-221129 PC-DC-044', '10.6.1.134', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-044', '10.6.1.198', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-044', '10.6.3.220', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1719 刘皖豫のデバイス
('HYRON-240416 PC-DC-014', '174.28.100.130', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-014', '174.28.100.10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1720 何江龙のデバイス1
('HYRON-220926 PC-DC-023', '10.6.3.237', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220926 PC-DC-023', '10.6.1.160', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1736 李润哲のデバイス
('HYRON-220125 PC-DC-017', '10.6.1.70', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220125 PC-DC-017', '10.6.3.233', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1757 李纪飞のデバイス
('HYRON-241118 PC-DC-001', '10.6.1.61', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1809 李智勇のデバイス
('HYRON-240828 PC-DC-007', '10.6.1.93', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1840 马泽原のデバイス1
('HYRON-221129 PC-DC-069', '10.6.1.69', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-069', '10.6.3.216', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1873 宋柳叶のデバイス
('HYRON-220414 PC-DC-049', '10.6.1.84', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1882 李宝林のデバイス
('HYRON-170425 PC-DC-006', '10.6.1.94', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2045 杨祥坤のデバイス
('230621-027(暂)', '173.28.100.116', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2050 李芷栅のデバイス
('HYRON-210125 PC-DC-015', '10.6.1.102', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2053 王天昊のデバイス
('HYRON-221129 PC-DC-007', '173.28.100.139', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2060 唐凯2のデバイス
('HYRON-220414 PC-DC-043', '10.6.1.157', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP406 李勇のデバイス
('HYRON-221129 PC-DC-051', '172.28.100.153', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP438 陈传真のデバイス
('HYRON-210118 PC-DC-033', '10.6.1.146', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP446 陆建周のデバイス
('HYRON-231127 PC-DC-003', '172.28.100.186', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2047 王天润のデバイス
('HYRON-220926 PC-DC-007', '173.28.100.145', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2176 高雨彤のデバイス
('HYRON-221129 PC-DC-068', '172.28.101.136', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-068', '172.28.101.16', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2177 赵晨云のデバイス
('HYRON-220914 PC-DC-050', '10.6.1.131', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2187 田季伟のデバイス
('HYRON-240828 PC-DC-009', '174.28.100.71', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-009', '174.28.100.202', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1926 祁文轩のデバイス
('HYRON-241118 PC-DC-005', '10.6.1.138', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1939 吴智雄のデバイス1
('HYRON-181112 PC-DC-013', '172.28.101.137', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-181112 PC-DC-013', '172.28.101.17', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1939 吴智雄のデバイス2
('HYRON-240416 PC-DC-001', '172.28.101.135', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1939 吴智雄のデバイス3
('HYRON-251201 PC-DC-015', '172.28.101.137', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1941 左恩のデバイス
('HYRON-241118 PC-DC-006', '173.28.100.149', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1973 王昱のデバイス
('HYRON-241118 PC-DC-040', '10.6.1.154', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1981 张攀のデバイス
('HYRON-241118 PC-DC-068', '172.28.101.20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1982 李兴旺のデバイス
('HYRON-241118 PC-DC-008', '10.6.1.117', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1992 母丽丽のデバイス
('HYRON-241118 PC-DC-004', '10.6.1.163', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-241118 PC-DC-004', '10.6.1.225', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1996 顾航睿のデバイス
('HYRON-241118 PC-DC-007', '10.6.1.164', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2005 陈诺のデバイス
('HYRON-241118 PC-DC-039', '173.28.100.150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2008 高鑫のデバイス
('HYRON-241118 PC-DC-003', '173.28.100.146', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2019 王小龙のデバイス
('HYRON-241118 PC-DC-042', '10.6.1.97', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2024 何启明のデバイス
('HYRON-241118 PC-DC-041', '172.28.100.166', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2034 刘子俊のデバイス
('HYRON-241118 PC-DC-043', '173.28.100.148', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2037 蔡文强のデバイス
('HYRON-211217 PC-DC-042', '10.6.1.168', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2178 姜成杰のデバイス
('HYRON-191217 PC-DC-006', '10.6.1.161', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-191217 PC-DC-006', '10.6.1.232', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP536 孙毅のデバイス
('HYRON-210118 PC-DC-015', '172.28.100.165', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP538 陆语涵のデバイス
('HYRON-221129 PC-DC-019', '172.28.100.160', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP539 季荷飞のデバイス
('HYRON-220914 PC-DC-011', '172.28.100.164', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP541 戴明のデバイス
('HYRON-210621 PC-DC-021', '172.28.100.154', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSXP551 曹光友のデバイス
('HYRON-210118 PC-DC-027', '173.28.100.116', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JSSX077 黄羽轩のデバイス
('HYRON-190605 PC-DC-016', '10.6.1.197', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0898 徐睿のデバイス
('HYRON-210125 PC-DC-005', '172.28.100.251', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1158 胡浩磊のデバイス
('HYRON-220414 PC-DC-042', '173.28.100.135', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2056 陆跃跃のデバイス
('HYRON-240416 PC-DC-018', '174.28.100.68', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-018', '174.28.100.189', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-018', '10.6.1.44', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0226 吴正明のデバイス1
('HYRON-240416 PC-DC-012', '10.6.2.255', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0226 吴正明のデバイス2
('HYRON-221129 PC-DC-048', '10.6.2.237', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0296 朱亚明のデバイス
('HYRON-211217 PC-DC-057', '10.6.2.231', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS0941 何桢のデバイス
('HYRON-220414 PC-DC-044', '10.6.2.199', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1672 孙萧のデバイス
('HYRON-240416 PC-DC-013', '10.6.2.238', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-013', '10.6.1.248', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1699 陈楠钢のデバイス
('HYRON-220926 PC-DC-006', '10.6.1.144', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1929 顾新宇のデバイス
('HYRON-211217 PC-DC-056', '10.6.2.233', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2167 陈兆伟のデバイス
('HYRON-151020 PC-DC-002', '10.6.1.180', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2171 田梵君のデバイス
('HYRON-170904 PC-DC-009', '10.6.2.156', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS2172 卢国颖のデバイス
('HYRON-160818 PC-DC-016', '10.6.2.157', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- JS1137 陆红非のデバイス
('HYRON-240828 PC-DC-008', '174.28.100.12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-008', '174.28.100.132', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- SH726 何渊のデバイス
('HYRON-250123 nobo-001', '172.28.101.134', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

-- monitor_info テーブルに DA04部門の設備データを挿入する
-- 1人あたり複数の設備があるため、各設備ごとに1行挿入する
-- monitor_id は自動採番、device_id は「ホスト設備番号」、monitor_name は「モニター設備番号」

DROP TABLE IF EXISTS monitor_info;
DROP SEQUENCE IF EXISTS monitor_info_monitor_id_seq CASCADE;
CREATE SEQUENCE monitor_info_monitor_id_seq;
CREATE TABLE monitor_info (
    monitor_id integer PRIMARY KEY DEFAULT nextval('monitor_info_monitor_id_seq'),
    device_id character varying(50) REFERENCES device_info(device_id),
    monitor_name character varying(100),
    create_time timestamp(6) without time zone,
    creater character varying(100),
    update_time timestamp(6) without time zone,
    updater character varying(100)
);

INSERT INTO monitor_info (device_id, monitor_name, create_time, update_time, creater, updater) VALUES

-- 林飞 (JS0014)
('HYRON-221129 PC-DC-089', 'HYRON-241118 Minitor-131', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-058', 'HYRON-151127 PC-DC-004JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 刘文丰 (JS0020)
('HYRON-221129 PC-DC-055', 'HYRON-221129 PC-DC-055', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-055', 'HYRON-241118 Minitor-075', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 朱德涛 (JS0054)
('hyron-220914 pc-dc-053', 'HYRON-241118 Minitor-076', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 潘小琴 (JS0105)
('HYRON-211029 MAC-001', 'HYRON-171017 PC-DC-006JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-002', 'HYRON-240826 Minitor-009', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-241118 PC-DC-020', 'HYRON-170620 PC-DC-007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈国宝 (JS0108)
('HYRON-211217 PC-DC-046', 'HYRON-240729 Minitor-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-046', 'HYRON-211217 PC-DC-046', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-200902 PC-DC-001', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220622 PC-DC-001', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220622 PC-DC-002', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-230828 nobo-001', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221018 PC-DC-022', 'HYRON-181112 PC-DC-001JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-120412 pc-dc-007', 'hyron-101027 pc-dc-005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-020', 'HYRON-240826 Minitor-007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 武志鹏 (JS0129)
('HYRON-211217 PC-DC-041', 'HYRON-211217 PC-DC-041', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-041', 'HYRON-250604 Minitor-183', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 曹向阳 (JS0177)
('HYRON-211217 PC-DC-045', 'HYRON-211217 PC-DC-045', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-191107 PC-DC-025', 'HYRON-250604 Minitor-184', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 欧金龙 (JS0199)
('HYRON-220914 PC-DC-056', 'HYRON-241118 Minitor-077', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-2020703 PC-iMac-001', 'HYRON-2020703 PC-iMac-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210118 PC-DC-034', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王燕飞 (JS0263)
('HYRON-231127 PC-DC-008', 'HYRON-201102 PC-DC-008JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-231127 PC-DC-008', 'HYRON-250604 Minitor-185', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 周仲山 (JS0266)
('HYRON-230705 nobo-001', 'HYRON-240619 Minitor-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 徐冬琴 (JS0291)
('HYRON-211217 PC-DC-043', 'HYRON-241118 Minitor-083', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 聂建军 (JS0298)
('HYRON-131216 PC-DC-028', 'HYRON-131216 PC-DC-028JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-040', 'HYRON-211217 PC-DC-040', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-040', 'HYRON-241118 Minitor-078', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210305 PC-DC-011', 'HYRON-220914 PC-DC-052', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-130408 nobo-001', 'hyron-130408 nobo-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 曹天扬 (JS0300)
('HYRON-181210 PC-DC-016', 'HYRON-241118 Minitor-079', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 卢淑美 (JS0356)
('HYRON-160818 PC-DC-011', 'HYRON-160818 PC-DC-011', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-150813 PC-DC-009', '-', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-120412 pc-dc-018', 'hyron-110104 pc-dc-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-191107 PC-DC-012', 'HYRON-241118 Minitor-084', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-120412 pc-dc-013', '-', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 袁晓东 (JS0426)
('HYRON-200106 MACMINI-001', 'HYRON-160818 PC-DC-018', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-050', 'HYRON-241118 Minitor-080', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-050', 'HYRON-211217 PC-DC-047', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-151127 PC-DC-006', 'HYRON-151127 PC-DC-006JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240828 PC-DC-005', 'HYRON-240826 Minitor-010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220414 PC-DC-047', 'HYRON-220414 PC-DC-047', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李琪 (JS0449)
('HYRON-221129 PC-DC-042', 'HYRON-241118 Minitor-081', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-161205 PC-DC-001', 'HYRON-161205 PC-DC-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李行 (JS0572)
('HYRON-181112 PC-DC-025', 'HYRON-250604 Minitor-186', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王晓舟 (JS0596)
('HYRON-240416 PC-DC-017', 'HYRON-240826 Minitor-004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 张娟 (JS0609)
('HYRON-240828 PC-DC-011', 'HYRON-241118 Minitor-085', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-241118 PC-DC-009', 'HYRON-221129 PC-DC-050', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 袁娟 (JS0611)
('HYRON-211217 PC-DC-038', 'HYRON-241118 Minitor-086', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-038', 'HYRON-181112 PC-DC-021JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 赵莉 (JS0619)
('HYRON-221129 PC-DC-041', 'HYRON-221129 PC-DC-041', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-041', 'HYRON-241118 Minitor-087', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-171221 PC-DC-014', 'HYRON-220926 PC-DC-023', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('Hyron-191107 nobo-001', 'Hyron-191107 nobo-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-131216 PC-DC-014', '-', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈卫东 (JS0627)
('HYRON-190605 PD-DC-017', 'HYRON-241118 Minitor-088', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-190605 PD-DC-017', 'HYRON-190605 PD-DC-017', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 莫尚勇 (JS0720)
('HYRON-221129 PC-DC-043', 'HYRON-221129 PC-DC-043', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-043', 'HYRON-241118 Minitor-082', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('Longtoo-131219 Nobo-004', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 张炎 (JS0746)
('HYRON-221129 PC-DC-050', 'HYRON-221129 PC-DC-050', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-050', 'HYRON-250604 Minitor-187', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 孙博 (JS0751)
('HYRON-240828 PC-DC-010', 'HYRON-241118 Minitor-127', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈辰 (JS0798)
('HYRON-241118 PC-DC-002', 'HYRON-181112 PC-DC-013', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-241118 PC-DC-002', 'HYRON-250604 Minitor-188', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 董鸣辉 (JS0800)
('HYRON-211217 PC-DC-027', 'HYRON-241118 Minitor-089', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-170620 PC-DC-008', 'HYRON-160818 PC-DC-006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-130920 PC-DC-013', '100623-002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-231127 PC-DC-007', 'HYRON-241118 Minitor-089', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 杨爱玉 (JS0833)
('HYRON-240416 PC-DC-015', 'HYRON-240826 Minitor-002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-016', 'HYRON-240826 Minitor-003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 张卫华 (JS0842)
('HYRON-240828 PC-DC-006', 'HYRON-240826 Monitor-011', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 许加国 (JS0942)
('HYRON-220914 PC-DC-049', 'HYRON-160818 PC-DC-018', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 杨皓伟 (JS0958)
('HYRON-220914 PC-DC-055', 'HYRON-241118 Minitor-091', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 赵康2 (JS1061)
('230621-029(暂)', 'HYRON-191107 PC-DC-023JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('230621-029(暂)', 'HYRON-250604 Minitor-191', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 汪海燕 (JS1075)
('HYRON-211217 PC-DC-047', 'HYRON-210118 PC-DC-040', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 蔡翔宇 (JS1148)
('HYRON-210621 PC-DC-010', 'HYRON-241118 Minitor-090', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈殊熠 (JS1150)
('HYRON-211217 PC-DC-020', 'HYRON-211217 PC-DC-020', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-020', 'HYRON-250604 Minitor-192', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 罗佳 (JS1174)
('HYRON-240828 PC-DC-012', 'HYRON-241118 Minitor-128', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 孙鹏 (JS1180)
('HYRON-190605 PC-DC-054', 'HYRON-241118 Minitor-094', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 潘宇 (JS1244)
('HYRON-211217 PC-DC-044', 'HYRON-211217 PC-DC-044', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-044', 'HYRON-250604 Minitor-193', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王邵赟 (JS1246)
('HYRON-210805 PC-DC-006', 'HYRON-241118 Minitor-093', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211220 MAC-001', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221018 PC-DC-021', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王冠杰 (JS1285)
('HYRON-210118 PC-DC-024', 'HYRON-171221 PC-DC-027JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210118 PC-DC-024', 'HYRON-250604 Minitor-194', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 邓怿泽 (JS1307)
('HYRON-220414 PC-DC-040', 'HYRON-220414 PC-DC-040', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220414 PC-DC-040', 'HYRON-250604 Minitor-195', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王子晗 (JS1339)
('HYRON-220926 PC-DC-021', 'HYRON-250604 Minitor-196', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 张欢 (JS1347)
('HYRON-211217 PC-DC-019', 'HYRON-211217 PC-DC-019', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-211217 PC-DC-019', 'HYRON-250604 Minitor-197', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 罗翔 (JS1359)
('HYRON-220926 PC-DC-008', 'HYRON-220926 PC-DC-008', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220926 PC-DC-008', 'HYRON-250604 Minitor-198', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('hyron-190805 macmini-001', 'HYRON-190217 PC-DC-010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 应加俊 (JS1363)
('HYRON-231127 PC-DC-004', 'HYRON-221129 PC-DC-007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-231127 PC-DC-004', 'HYRON-250604 Minitor-199', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王孟洛 (JS1402)
('HYRON-210621 PD-DC-018', 'HYRON-210621 PD-DC-018 DELL', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210621 PD-DC-018', 'HYRON-250604 Minitor-202', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 贾乃康 (JS1435)
('HYRON-171017 PC-DC-006', 'HYRON-220414 PC-DC-044', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 姜榕 (JS1436)
('HYRON-240828 PC-DC-001', 'HYRON-240826 Minitor-008', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 章捷 (JS1510)
('HYRON-251201 PC-DC-016', 'HYRON-201102 PC-DC-013JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-251201 PC-DC-016', 'HYRON-241118 Minitor-092', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李文佳 (JS1514) - 日本出差中，无设备

-- 潘箬婷 (JS1519)
('HYRON-191107 PC-DC-010', 'HYRON-250604 Minitor-200', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 丁楠 (JS1527)
('HYRON-221129 PC-DC-071', 'HYRON-250604 Minitor-203', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 华丰 (JS1555)
('HYRON-240416 PC-DC-019', 'HYRON-240826 Minitor-005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 丁飞鸿 (JS1564)
('HYRON-221129 PC-DC-070', 'HYRON-250604 Minitor-204', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-070', 'HYRON-221129 PC-DC-070', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 杜记航 (JS1565)
('HYRON-220914 PC-DC-051', 'HYRON-250604 Minitor-205', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 曲家志 (JS1599)
('HYRON-210118 PC-DC-001', 'HYRON-250604 Minitor-207', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-171017 PC-DC-020', 'HYRON-190217 PC-DC-010', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 施炜玮 (JS1607)
('HYRON-210118 PC-DC-012', 'HYRON-250604 Minitor-208', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 杨杨 (JS1613)
('HYRON-220926 PC-DC-009', 'HYRON-220926 PC-DC-009', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220926 PC-DC-009', 'HYRON-250604 Minitor-209', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 朱国威 (JS1640)
('HYRON-231127 PC-DC-002', 'HYRON-250604 Minitor-210', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 庄子泓 (JS1663)
('HYRON-191107 PC-DC-031', 'HYRON-171017 PC-DC-007JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 刘乃伟 (JS1683)
('HYRON-220914 PC-DC-054', 'HYRON-220914 PC-DC-054', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220914 PC-DC-054', 'HYRON-250604 Minitor-211', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 卞春荣 (JS1697)
('HYRON-220914 PC-DC-052', 'HYRON-220914 PC-DC-052', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220914 PC-DC-052', 'HYRON-250604 Minitor-213', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 赵祥富 (JS1715)
('HYRON-221129 PC-DC-044', 'HYRON-250604 Minitor-214', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 刘皖豫 (JS1719)
('HYRON-240416 PC-DC-014', 'HYRON-240826 Minitor-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 何江龙 (JS1720)
('HYRON-220926 PC-DC-023', 'HYRON-220926 PC-DC-023', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220926 PC-DC-023', 'HYRON-250604 Minitor-215', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
(NULL, 'HYRON-200306 MONITOR-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
(NULL, 'Hyron-201119 Monitor-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
(NULL, 'Hyron-201119 Monitor-002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
(NULL, '苹果mc374 nobo-001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李润哲 (JS1736)
('HYRON-220125 PC-DC-017', 'HYRON-181210 PC-DC-020JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-220125 PC-DC-017', 'HYRON-250604 Minitor-216', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李纪飞 (JS1757)
('HYRON-241118 PC-DC-001', 'HYRON-221129 PC-DC-042', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-241118 PC-DC-001', 'HYRON-250604 Minitor-217', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李智勇 (JS1809)
('HYRON-240828 PC-DC-007', 'HYRON-240826 Minitor-012', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 马泽原 (JS1840)
('HYRON-221129 PC-DC-069', 'HYRON-221129 PC-DC-069', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-181112 PC-DC-002', 'HYRON-191107 PC-DC-029JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 宋柳叶 (JS1873)
('HYRON-220414 PC-DC-049', 'HYRON-210118 PC-DC-048 DELL', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李宝林 (JS1882)
('HYRON-170425 PC-DC-006', 'HYRON-181112 PC-DC-037JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 杨祥坤 (JS2045)
('230621-027(暂)', 'HYRON-181112 PC-DC-015JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李芷栅 (JS2050)
('HYRON-210125 PC-DC-015', 'HYRON-221129 PC-DC-051', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王天昊 (JS2053)
('HYRON-221129 PC-DC-007', 'HYRON-140304 PC-DC-006JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 唐凯2 (JS2060)
('HYRON-220414 PC-DC-043', 'HYRON-250604 Minitor-201', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李勇 (JSXP406)
('HYRON-221129 PC-DC-051', 'HYRON-221129 PC-DC-075', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈传真 (JSXP438)
('HYRON-210118 PC-DC-033', 'HYRON-150813 PC-DC-010JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陆建周 (JSXP446)
('HYRON-231127 PC-DC-003', 'HYRON-190605 PC-DC-016JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王天润 (JS2047)
('HYRON-220926 PC-DC-007', 'HYRON-220926 PC-DC-007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 高雨彤 (JS2176)
('HYRON-221129 PC-DC-068', 'HYRON-210118 PC-DC-015JDELL', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 赵晨云 (JS2177)
('HYRON-220914 PC-DC-050', 'HYRON-220914 PC-DC-050', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 田季伟 (JS2187)
('HYRON-240828 PC-DC-009', 'HYRON-240826 Minitor-014', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 祁文轩 (JS1926)
('HYRON-241118 PC-DC-005', 'HYRON-220914 PC-DC-055', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 吴智雄 (JS1939)
('HYRON-181112 PC-DC-013', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-240416 PC-DC-001', 'HYRON-221129 PC-DC-029', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-251201 PC-DC-015', 'HYRON-221129 PC-DC-072', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 左恩 (JS1941)
('HYRON-241118 PC-DC-006', 'HYRON-191107 PC-DC-011JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王昱 (JS1973)
('HYRON-241118 PC-DC-040', 'HYRON-191107 PC-DC-019JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 张攀 (JS1981)
('HYRON-241118 PC-DC-068', 'HYRON-181210 PC-DC-014JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 李兴旺 (JS1982)
('HYRON-241118 PC-DC-008', 'HYRON-221129 PC-DC-009', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 母丽丽 (JS1992)
('HYRON-241118 PC-DC-004', 'HYRON-220914 PC-DC-056', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 顾航睿 (JS1996)
('HYRON-241118 PC-DC-007', 'HYRON-191107 PC-DC-030JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈诺 (JS2005)
('HYRON-241118 PC-DC-039', 'HYRON-220914 PC-DC-053', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 高鑫 (JS2008)
('HYRON-241118 PC-DC-003', 'HYRON-181210 PC-DC-016JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 王小龙 (JS2019)
('HYRON-241118 PC-DC-042', 'HYRON-211217 PC-DC-043', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 何启明 (JS2024)
('HYRON-241118 PC-DC-041', 'HYRON-131216 PC-DC-014JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 刘子俊 (JS2034)
('HYRON-241118 PC-DC-043', 'HYRON-211217 PC-DC-027', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 蔡文强 (JS2037)
('HYRON-211217 PC-DC-042', 'HYRON-171017 PC-DC-020JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 姜成杰 (JS2178)
('HYRON-191217 PC-DC-006', 'HYRON-191217 PC-DC-006JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 孙毅 (JSXP536)
('HYRON-210118 PC-DC-015', 'HYRON--110704 PC-DC-017', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陆语涵 (JSXP538)
('HYRON-221129 PC-DC-019', 'HYRON-170904 PC-DC-004JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 季荷飞 (JSXP539)
('HYRON-220914 PC-DC-011', 'HYRON-171221 PC-DC-032JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 戴明 (JSXP541)
('HYRON-210621 PC-DC-021', 'HYRON-220414 PC-DC-043', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 曹光友 (JSXP551)
('HYRON-210118 PC-DC-027', 'HYRON-220914 PC-DC-051', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 黄羽轩 (JSSX077)
('HYRON-190605 PC-DC-016', 'HYRON-201102 PC-DC-017JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 徐睿 (JS0898) - DA02部門
('HYRON-210125 PC-DC-005', '210125 PC-DC-005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210125 PC-DC-005', '250604 Monitor-082', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 胡浩磊 (JS1158) - DA02部門
('HYRON-220414 PC-DC-042', 'HYRON-250604 Minitor-095', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陆跃跃 (JS2056) - DA02部門
('HYRON-240416 PC-DC-018', 'HYRON-240826 Minitor-006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 吴正明 (JS0226) - DA02部門
('HYRON-240416 PC-DC-012', 'HYRON-241118 Minitor-033', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-221129 PC-DC-048', 'HYRON-220926 PC-DC-021', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 朱亚明 (JS0296) - DA02部門
('HYRON-211217 PC-DC-057', 'HYRON-241118 Minitor-034', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 何桢 (JS0941) - DA02部門
('HYRON-220414 PC-DC-044', 'HYRON-250604 Minitor-086', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 孙萧 (JS1672) - DA02部門
('HYRON-240416 PC-DC-013', 'HYRON-221129 PC-DC-090', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈楠钢 (JS1699) - DA02部門
('HYRON-220926 PC-DC-006', 'HYRON-220926 PC-DC-006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 顾新宇 (JS1929) - DA02部門
('HYRON-211217 PC-DC-056', 'HYRON-220926 PC-DC-005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈兆伟 (JS2167) - DA02部門
('HYRON-151020 PC-DC-002', '-', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 田梵君 (JS2171) - DA02部門
('HYRON-170904 PC-DC-009', 'HYRON-211217 PC-DC-022', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 卢国颖 (JS2171) - DA02部門
('HYRON-160818 PC-DC-016', 'HYRON-210621 PC-DC-029', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陆红非 (JS1137) - DA05部門
('HYRON-240828 PC-DC-008', 'HYRON-240826 Minitor-013', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 何渊 (SH726) - DA05部門
('HYRON-250123 nobo-001', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
-- 母丽丽 (JS1992)
('HYRON-241118 PC-DC-004', 'HYRON-220914 PC-DC-056', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 顾航睿 (JS1996)
('HYRON-241118 PC-DC-007', 'HYRON-191107 PC-DC-030JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),

-- 陈诺 (JS2005)
('HYRON-241118 PC-DC-039', 'HYRON-220914 PC-DC-053',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 高鑫 (JS2008)
('HYRON-241118 PC-DC-003', 'HYRON-181210 PC-DC-016JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),

-- 王小龙 (JS2019)
('HYRON-241118 PC-DC-042', 'HYRON-211217 PC-DC-043', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),

-- 何启明 (JS2024)
('HYRON-241118 PC-DC-041', 'HYRON-131216 PC-DC-014JD',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 刘子俊 (JS2034)
('HYRON-241118 PC-DC-043', 'HYRON-211217 PC-DC-027', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 蔡文强 (JS2037)
('HYRON-211217 PC-DC-042', 'HYRON-171017 PC-DC-020JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 姜成杰 (JS2178)
('HYRON-191217 PC-DC-006', 'HYRON-191217 PC-DC-006JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 孙毅 (JSXP536)
('HYRON-210118 PC-DC-015', 'HYRON--110704 PC-DC-017', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
-- 陆语涵 (JSXP538)
('HYRON-221129 PC-DC-019', 'HYRON-170904 PC-DC-004JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 季荷飞 (JSXP539)
('HYRON-220914 PC-DC-011', 'HYRON-171221 PC-DC-032JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 戴明 (JSXP541)
('HYRON-210621 PC-DC-021', 'HYRON-220414 PC-DC-043', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 曹光友 (JSXP551)
('HYRON-210118 PC-DC-027', 'HYRON-220914 PC-DC-051', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),   
-- 黄羽轩 (JSSX077)
('HYRON-190605 PC-DC-016', 'HYRON-201102 PC-DC-017JD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 徐睿 (JS0898) - DA02部門
('HYRON-210125 PC-DC-005', '210125 PC-DC-005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
('HYRON-210125 PC-DC-005', '250604 Monitor-082', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 胡浩磊 (JS1158) - DA02部門
('HYRON-220414 PC-DC-042', 'HYRON-250604 Minitor-095', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陆跃跃 (JS2056) - DA02部門
('HYRON-240416 PC-DC-018', 'HYRON-240826 Minitor-006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),

-- 吴正明 (JS0226) - DA02部門
('HYRON-240416 PC-DC-012', 'HYRON-241118 Minitor-033', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),
('HYRON-221129 PC-DC-048', 'HYRON-220926 PC-DC-021', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
-- 朱亚明 (JS0296) - DA02部門
('HYRON-211217 PC-DC-057', 'HYRON-241118 Minitor-034', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 何桢 (JS0941) - DA02部門
('HYRON-220414 PC-DC-044', 'HYRON-250604 Minitor-086', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 孙萧 (JS1672) - DA02部門
('HYRON-240416 PC-DC-013', 'HYRON-221129 PC-DC-090', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈楠钢 (JS1699) - DA02部門
('HYRON-220926 PC-DC-006', 'HYRON-220926 PC-DC-006', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),   
-- 顾新宇 (JS1929) - DA02部門
('HYRON-211217 PC-DC-056', 'HYRON-220926 PC-DC-005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陈兆伟 (JS2167) - DA02部門
('HYRON-151020 PC-DC-002', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),

-- 田梵君 (JS2171) - DA02部門
('HYRON-170904 PC-DC-009', 'HYRON-211217 PC-DC-022', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'admin', 'admin'),

-- 卢国颖 (JS2172) - DA02部門
('HYRON-160818 PC-DC-016', 'HYRON-210621 PC-DC-029', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 陆红非 (JS1137) - DA05部門
('HYRON-240828 PC-DC-008', 'HYRON-240826 Minitor-013', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),

-- 何渊 (SH726) - DA05部門
('HYRON-250123 nobo-001', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
