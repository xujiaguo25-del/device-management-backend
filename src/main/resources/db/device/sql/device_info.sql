-- DA04部門のすべてのデバイス情報をdevice_infoテーブルに挿入
-- 注意：空の値（"_"、"-"、""）はすべてNULLとして処理
-- 確認ステータスはすべて「未確認」（dict_id=24）に設定
INSERT INTO device_info (device_id, device_model, computer_name, login_username, project, dev_room, user_id, remark, self_confirm_id, os_id, memory_id, ssd_id, hdd_id, create_time, creater, update_time, updater) VALUES
-- 林飞のデバイス（JS0014）
('HYRON-221129 PC-DC-089', 'dell-5000', 'DA04-LINF-PC', 'linf-nrtu', 'D204-NRTU', 'M10', 'JS0014', 'D1005开发室', 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211217 PC-DC-058', 'dell-5090', 'HYRON-JS-LINFEI', 'linfei', 'DA05支援', 'M10', 'JS0014', 'D1005开发室', 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘文丰のデバイス（JS0020）
('HYRON-221129 PC-DC-055', 'dell-5000', 'da04-liuwfnri', 'lfeng', 'R&D+SBI', 'M5-A-09', 'JS0020', '开发机', 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 朱德涛のデバイス（JS0054）
('hyron-220914 pc-dc-053', 'dell-5000', 'DA04-LUNA-PC09', 'zhudt', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0054', NULL, 24, 2, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 潘小琴のデバイス（JS0105）
('HYRON-211029 MAC-001', 'macmini2020', 'Mac', 'mac', 'MTI-TECH-SOL', 'M2-A-01', 'JS0105', '潘小琴保管', 24, 8, 12, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240828 PC-DC-002', 'dell-5090', 'DA04-PANXQ', 'panxq-ms', 'MS-API基盤', 'M2-A-01', 'JS0105', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-241118 PC-DC-020', 'Optiplex', 'DA04-LUNA-PC22', 'panxq', 'MTI-TECH-LUNA', 'M2-A-01', 'JS0105', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈国宝のデバイス（JS0108）
('HYRON-211217 PC-DC-046', 'dell-5090', 'S2D-chengb-PC4', 'chengb', 'D204-TRSW', 'M2-A-02', 'JS0108', '权限不足，无法修改PC名称', 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-200902 PC-DC-001', 'lenvo 刃7000', 'stj', 'chengb', 'D204-TRSW', 'M2-A-02', 'JS0108', NULL, 24, 9, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-220622 PC-DC-001', 'DELL 3650 GPU', 'stj', 'chengb', 'D204-TRSW', NULL, 'JS0108', 'DA02借用，李庆', 24, 9, 16, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-220622 PC-DC-002', 'DELL 3650', 'stj', 'chengb', 'D204-TRSW', 'M2-A-01', 'JS0108', '三菱食品AI测试用', 24, 9, 16, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-230828 nobo-001', 'ThinkBook14+', 'DA04-CHENGB-note', NULL, 'D204-TRSW', 'M2-A-02', 'JS0108', '未加域，需要加域', 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-221018 PC-DC-022', '康佳DT31', 'DA04-chengb-01', 'DA04-NRI-TRSW', 'D204-TRSW', 'M2-A-02', 'JS0108', '上网机', 24, 2, 12, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-120412 pc-dc-007', 'optiplex-790', 'MS', 'administrator', 'MS', 'M2-A-01', 'JS0108', '服务器，已安装杀毒软件', 24, 7, 12, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240416 PC-DC-020', 'dell-5090', 'DA04-CHENGB-M', 'chengb-ms', 'MS', 'M2-A-01', 'JS0108', '开发机', 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 武志鹏のデバイス（JS0129）
('HYRON-211217 PC-DC-041', 'dell-5090', 'D4-WUZP-PC', 'wuzp', 'MTI-SC-AMUSE', 'M2-A-03', 'JS0129', '无修改PC名权限', 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曹向阳のデバイス（JS0177）
('HYRON-211217 PC-DC-045', 'dell-5090', 'DA04-CAOXY-PC', 'caoxy', 'TEC', 'M2-A-03', 'JS0177', NULL, 24, 2, 13, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-191107 PC-DC-025', 'dell-5070', 'DA04-CAOXY-PC2', 'caoxy', 'MTI-健診-DX', 'M2-A-03', 'JS0177', NULL, 24, 3, 15, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 欧金龙のデバイス（JS0199）
('HYRON-220914 PC-DC-056', 'dell-5000', 'DA04-OUJL-PC', 'oujl', 'MTI-SC-AMUSE', 'M2-A-02', 'JS0199', NULL, 24, 2, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-2020703 PC-iMac-001', 'iMac', 'iMac', 'Amuse-iMAC', 'MTI-SC-AMUSE', 'M2-A-02', 'JS0199', NULL, 24, 8, 12, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-210118 PC-DC-034', 'dell-5080', 'ubuntukaoqin', 'hyron-kaoqin', 'MTI-SC-AMUSE', 'M2-A-05', 'JS0199', '考勤系统服务器', 24, 9, 12, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王燕飞のデバイス（JS0263）
('HYRON-231127 PC-DC-008', 'Optiplex', 'DA04-WANGYANFEI-PC', 'wangyf', 'MTI-TECH-DC', 'M2-A-03', 'JS0263', NULL, 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 周仲山のデバイス（JS0266）
('HYRON-230705 nobo-001', 'Thinkpad X1', 'DA04-ZHOUZS-X1', 'zhouzs', NULL, 'M2-A-02', 'JS0266', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 徐冬琴のデバイス（JS0291）
('HYRON-211217 PC-DC-043', 'dell-5090', 'DA04-XUDQ-PC', 'xudq', 'MTI-SC-MEDIC', 'M2-A-05', 'JS0291', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 聂建军のデバイス（JS0298）
('HYRON-131216 PC-DC-028', 'dell-7010', 'DA04-MTI-FILE', 'niejj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '文件服务器', 24, 7, 13, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211217 PC-DC-040', 'dell-5090', 'DA04-NIEJJ-PC01', 'niejj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '开发机', 24, 2, 14, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-210305 PC-DC-011', 'DELL-5080', 'DA04-LUNA-PC21', 'biancr', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '空头武志鹏开发机', 24, 3, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-130408 nobo-001', 'Thinkpad X230i', 'DA04-LUNA-PC16', 'niejj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0298', '笔记本', 24, 2, 11, NULL, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曹天扬のデバイス（JS0300）
('HYRON-181210 PC-DC-016', 'dell-5050', 'DA04-LUNA-PC13', 'caoty', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0300', '开发机', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 卢淑美のデバイス（JS0356）
('HYRON-160818 PC-DC-011', 'dell-5040', 'hyron-pism-dev', 'root', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器', 24, 10, 12, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-150813 PC-DC-009', 'dell-7020', 'D4-PIT-NTASVR1', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器', 24, 5, 12, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-120412 pc-dc-018', 'dell-790', 'ADSERVER-PC', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器(PiSM LB用、MTI-PIT)', 24, 1, 12, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-191107 PC-DC-012', 'dell-5070', 'DA04-LUSHM-PC', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '开发机', 24, 2, 15, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-120412 pc-dc-013', 'dell-790', 'D4-PIT-PISMCS', 'lushm', 'MTI-PIT', 'M2-A-05', 'JS0356', '服务器', 24, 6, 13, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 袁晓东のデバイス（JS0426）
('HYRON-200106 MACMINI-001', 'macmini2018', 'MACMINI-001', 'life-ranger', 'R&D+SBI', 'M5-A-09', 'JS0426', 'NCTS测试机，门口', 24, 8, 12, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211217 PC-DC-050', 'dell-5090', 'DA04-YUANXD-PC', 'yuanxd', 'MTI-TECH-LIFE', 'M2-A-03', 'JS0426', 'Life开发机', 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-151127 PC-DC-006', 'dell-7020', 'DA04-NCTS-SRV-1', 'administrator', 'R&D+SBI', 'M5-A-09', 'JS0426', 'NCTS服务器，许加国处', 24, 5, 13, 17, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240828 PC-DC-005', 'dell-7010', 'HYRON-JS-YUANXD', 'yuanxd', 'DA05支援', 'M10', 'JS0426', 'A05支援', 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-220414 PC-DC-047', 'dell-5090', 'MS-SRV-NET', 'yuanxd', 'MS-生成AI', 'M2-A-01', 'JS0426', 'MS上网机', 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李琪のデバイス（JS0449）
('HYRON-221129 PC-DC-042', 'dell-5000', 'DA04-liqi', 'liqi', 'MTI-TECH-DC', 'M2-A-03', 'JS0449', '开发机', 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-161205 PC-DC-001', 'dell-5040', 'DA04-DC-DBSVR', 'wangjun2011', 'MTI-TECH-DC', 'M2-A-03', 'JS0449', '服务器', 24, 2, 13, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李行のデバイス（JS0572）
('HYRON-181112 PC-DC-025', 'dell-5050', 'DA04-LIXING', 'lixing', 'CAS-经费', 'M2-A-05', 'JS0572', '外借', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王晓舟のデバイス（JS0596）
('HYRON-240416 PC-DC-017', 'dell-7010', 'DA04-WANGXZ-PC2', 'wangxz-ms', 'MS-リフト', 'M2-A-01', 'JS0596', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张娟のデバイス（JS0609）
('HYRON-240828 PC-DC-011', 'dell-7010', 'DA04-ZHANGJUAN-PC', 'zhangjuan-ms', 'MS-GCP', 'M2-A-01', 'JS0609', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-241118 PC-DC-009', 'Optiplex', 'DA04-ZHANGJUAN1-PC', 'x_zhangjuan', 'イーグル', 'M2-A-05', 'JS0609', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 袁娟のデバイス（JS0611）
('HYRON-211217 PC-DC-038', 'dell-5090', 'DA04-YUANJUAN-PC', 'yuanjuan', 'MTI-SC-MW', 'M2-A-02', 'JS0611', NULL, 24, 3, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵莉のデバイス（JS0619）
('HYRON-221129 PC-DC-041', 'dell-5000', 'DA04-LUNA-PC05', 'zhaoli', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0619', '开发机', 24, 2, 13, 19, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-171221 PC-DC-014', 'dell-5050', 'DA04-LUNA-PC18', 'lirunzhe', 'MTI-TECH-LUNA', 'M2-A-03', 'JS0619', '空头刘皖豫开发机', 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('Hyron-191107 nobo-001', 'dell Inspiron5493', 'DA04-MTG-PC', 'zhaoli', NULL, 'M2-A-03', 'JS0619', '会议室笔记本', 24, 2, 12, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-131216 PC-DC-014', 'OPTIPLEX', 'DA04', 'zhaoli', NULL, 'M2-A-03', 'JS0619', '部门服务器', 24, 4, 12, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈卫东のデバイス（JS0627）
('HYRON-190605 PD-DC-017', 'dell-5050', 'DA04-CHENWD-PC5', 'chenwd', 'イーグル', 'M2-A-02', 'JS0627', NULL, 24, 3, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 莫尚勇のデバイス（JS0720）
('HYRON-221129 PC-DC-043', 'dell-5000', 'DA04-MOSY-PC', 'mosy', 'D204-TRSW', 'M2-A-02', 'JS0720', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('Longtoo-131219 Nobo-004', '笔记本mac pro', NULL, 'mosy', 'D204-TRSW', 'M2-A-02', 'JS0720', '基本不用', 24, 8, NULL, NULL, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张炎のデバイス（JS0746）
('HYRON-221129 PC-DC-050', 'dell-5000', 'DA04-zhangy-PC3', 'zhangyan', 'D204-TRSW', 'M2-A-02', 'JS0746', NULL, 24, 3, 14, 19, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙博のデバイス（JS0751）
('HYRON-240828 PC-DC-010', 'optiplex tower 7010', 'DA04-SUNBO-PC3', 'sunbo', 'MS-GCP', 'M2-A-02', 'JS0751', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈辰のデバイス（JS0798）
('HYRON-241118 PC-DC-002', 'dell-7010', 'DA04-CHENC-PC', 'chenchen', 'MS', 'M2-A-01', 'JS0798', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 董鸣辉のデバイス（JS0800）
('HYRON-211217 PC-DC-027', 'dell-5090', 'DA04-DONGMH-PC02', 'dongmh-nrtu', 'D204-NRTU', 'M5-A-09', 'JS0800', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-170620 PC-DC-008', 'dell-5050', 'DA04-DONGMH-PC01', 'dmh', 'D204-NRTU', 'M5-A-09', 'JS0800', '上网机', 24, 2, 13, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-130920 PC-DC-013', 'dell-7020', 'DA04-DONGMH-SE', 'nrtuser01', 'D204-NRTU', 'M5-A-09', 'JS0800', '文件服务器', 24, 2, 12, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-231127 PC-DC-007', 'optiplex', 'DESKTOP-A2A123', 'ISSM', 'D204-NRTU', 'M5-A-09', 'JS0800', 'NRTU测试机', 24, 2, 13, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨爱玉のデバイス（JS0833）
('HYRON-240416 PC-DC-015', 'dell-7010', 'HYRON-YANGAIYU', 'yangay-ms', 'MS-リフト', 'M2-A-01', 'JS0833', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240416 PC-DC-016', 'dell-7010', 'DA02-MAJW-PC2', 'majw-ms', 'MS-リフト', 'M2-A-01', 'JS0833', '原马敬文使用，显示器在陆跃跃处，主机在陈国宝座位处', 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张卫华のデバイス（JS0842）
('HYRON-240828 PC-DC-006', 'dell-7010', 'DA04-ZHANGWEIHUA-PC', 'zhangweihua', 'D204-TRSW', 'M2-A-02', 'JS0842', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 许加国のデバイス（JS0942）
('HYRON-220914 PC-DC-049', 'dell-5000', 'DA04-SUNYI-PC', 'xujg', 'R&D+SBI', 'M5-A-09', 'JS0942', NULL, 24, 2, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨皓伟のデバイス（JS0958）
('HYRON-220914 PC-DC-055', 'dell-5000', 'S2D-yanghw-PC4', 'yanghw', 'D204-TRSW', 'M2-A-02', 'JS0958', '电脑名无修改权限', 24, 3, 13, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 马晓明のデバイス（JS1029）
('HYRON-191107 PC-DC-024', 'dell-5070', 'NRTU-maxm-PC', 'maxm-nrtu', NULL, '6F', 'JS1029', '外借', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵康2のデバイス（JS1061）
('230621-029(暂)', 'dell-5070', 'DA04-zhaokang2-PC', 'zhaokang2', 'MTI-SC-MW', 'M2-A-02', 'JS1061', 'IP设置了加速，换IP后部分软件不能用，所以暂时还用2段的这个IP', 24, 2, 15, 22, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 汪海燕のデバイス（JS1075）
('HYRON-211217 PC-DC-047', 'dell-5090', 'DA04-WANGHY-PC', 'wanghaiyan', 'MTI-SC-MW', 'M2-A-02', 'JS1075', '9/2~南京出勤', 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 蔡翔宇のデバイス（JS1148）
('HYRON-210621 PC-DC-010', 'dell-5080', 'DA04-CAIXY-PC', 'caixy', 'MTI-PIT', 'M2-A-05', 'JS1148', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈殊熠のデバイス（JS1150）
('HYRON-211217 PC-DC-020', 'dell-5090', 'DA04-CHENSY-PC', 'chensy-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1150', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 罗佳のデバイス（JS1174）
('HYRON-240828 PC-DC-012', 'dell-7010', 'DA04-LUOJIA-PC', 'luojia-ms', 'MS-GCP', 'M2-A-01', 'JS1174', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙鹏のデバイス（JS1180）
('HYRON-190605 PC-DC-054', 'dell-5050', 'DA04-sunpeng-pc', 'sunpeng', 'イーグル', 'M2-A-05', 'JS1180', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 康博成のデバイス（JS1239）
('HYRON-241223 PC-DC-010', 'dell-7020', 'DA04-KANGBC-PC2', 'kangbc', 'D311支援', NULL, 'JS1239', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 潘宇のデバイス（JS1244）
('HYRON-211217 PC-DC-044', 'dell-5090', 'S2D-panyu-PC', 'panyu', 'イーグル', 'M2-A-05', 'JS1244', '无修改PC名权限', 24, 2, 15, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王邵赟のデバイス（JS1246）
('HYRON-210805 PC-DC-006', 'dell-5080', 'DA04-WANGSY-PC', 'wangshaoyun', 'MTI-PIT', 'M2-A-05', 'JS1246', NULL, 24, 2, 15, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-211220 MAC-001', 'MAC mini', NULL, NULL, NULL, 'M2-A-03', 'JS1246', '部门纸质单据存放处的白色柜子里', 24, NULL, 12, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-221018 PC-DC-021', '康佳mini2022', 'ubuntu001', NULL, NULL, 'M2-A-05', 'JS1246', NULL, 24, 9, 12, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王冠杰のデバイス（JS1285）
('HYRON-210118 PC-DC-024', 'dell-5080', 'DA04-WANGGJ2-PC', 'wanggj2', 'R&D+SBI', 'M5-A-09', 'JS1285', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 邓怿泽のデバイス（JS1307）
('HYRON-220414 PC-DC-040', 'dell-5090', 'DA04-DENGYZ-PC4', 'dengyz-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1307', '小显示器？', 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王子晗のデバイス（JS1339）
('HYRON-220926 PC-DC-021', 'dell-5000', 'DA04-WANGZH-PC3', 'wangzihan', 'MTI-SC-MEDIC', 'M2-A-05', 'JS1339', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张欢のデバイス（JS1347）
('HYRON-211217 PC-DC-019', 'dell-5090', 'DA04-ZHANGHUAN3-PC', 'zhanghuan2', 'イーグル', 'M2-A-05', 'JS1347', NULL, 24, 3, 15, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 罗翔のデバイス（JS1359）
('HYRON-220926 PC-DC-008', 'dell-5000', 'DA04-luoxiang', 'luoxiang', 'MTI-TECH-LIFE', 'M2-A-03', 'JS1359', NULL, 24, 3, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('hyron-190805 macmini-001', 'macmini2018', 'MAC-mini-001', 'wangyu2', 'MTI-TECH-LIFE', 'M2-A-03', 'JS1359', '512是外接', 24, 8, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 应加俊のデバイス（JS1363）
('HYRON-231127 PC-DC-004', 'Optiplex', 'DA04-LUNA-PC03', 'yingjj', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1363', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王孟洛のデバイス（JS1402）
('HYRON-210621 PD-DC-018', 'dell-5080', 'DA04-WANGML-PC', 'wangml', 'イーグル', 'M2-A-05', 'JS1402', NULL, 24, 2, 15, 18, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 贾乃康のデバイス（JS1435）
('HYRON-171017 PC-DC-006', 'dell-5050', 'DA04-JIANK-PC', 'jiank', 'MTI-TECH-SOL', 'M2-A-02', 'JS1435', '潘小琴保管', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240828 PC-DC-018', 'Optiplex', 'HYRON-JS-JIANK', 'jiank', 'DA05支援', 'M10', 'JS1435', 'A05支援', 24, 2, 12, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 姜榕のデバイス（JS1436）
('HYRON-240828 PC-DC-001', 'Optiplex Tower', 'HYRON-JS-JIANGRONG', 'jiangrong', 'DA05支援', 'M10', 'JS1436', 'DA05支援', 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 章捷のデバイス（JS1510）
('HYRON-251201 PC-DC-016', 'Pro Tower QCT1250', 'DA04-ZHANGJIE-PC2', 'zhangjie-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1510', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-201102 PC-DC-013', 'dell-5080', NULL, NULL, NULL, NULL, 'JS1510', '章捷旧电脑，已初始化', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 潘箬婷のデバイス（JS1519）
('HYRON-191107 PC-DC-010', 'dell-5070', 'DA04-PANRT-PC', 'panrt', 'MTI-TECH-DC', 'M2-A-03', 'JS1519', NULL, 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 丁楠のデバイス（JS1527）
('HYRON-221129 PC-DC-071', 'Optiplex 5000', 'DA04-DINGNAN-PC', 'dingnan', 'イーグル', 'M2-A-05', 'JS1527', NULL, 24, 2, 15, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 华丰のデバイス（JS1555）
('HYRON-240416 PC-DC-019', 'dell-7010', 'DA04-HUAFENG-PC', 'huaf-ms', 'MS-リフト', 'M2-A-01', 'JS1555', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 丁飞鸿のデバイス（JS1564）
('HYRON-221129 PC-DC-070', 'dell-5000', 'DESKTOP-EU3U356', 'dingfh', 'CAS-经费', 'M2-A-05', 'JS1564', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杜记航のデバイス（JS1565）
('HYRON-220914 PC-DC-051', 'dell-5000', 'DA04-LUNA-PC10', 'dujh', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1565', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 汪坦のデバイス（JS1569）
('HYRON-190605 PC-DC-014', 'dell-5050', 'DA04-WANGTAN-PC', 'wangtan-nrtu', 'D311支援', NULL, 'JS1569', '外借', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曲家志のデバイス（JS1599）
('HYRON-210118 PC-DC-001', 'dell-5080', 'DA04-qujz-PC', 'qujz', 'MTI-SC-MW', 'M2-A-02', 'JS1599', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-171017 PC-DC-020', 'dell-5050', 'DA04-qujz-PC2', 'qujz', 'MTI-SC-MW', 'M2-A-02', 'JS1599', NULL, 24, 2, 12, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 施炜玮のデバイス（JS1607）
('HYRON-210118 PC-DC-012', 'dell-5080', 'DA04-SHIWW-PC', 'shiww', 'MTI-健診-DX', 'M2-A-03', 'JS1607', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨杨のデバイス（JS1613）
('HYRON-220926 PC-DC-009', 'dell-5000', 'HYRON-JS-YANGYANG', 'yangyang', 'MTI-TECH-LIFE', 'M2-A-03', 'JS1613', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 朱国威のデバイス（JS1640）
('HYRON-231127 PC-DC-002', 'Optiplex', 'DA04-ZHUGW-PC01', 'zhugw', 'MTI-SC-AMUSE', 'M2-A-02', 'JS1640', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 庄子泓のデバイス（JS1663）
('HYRON-191107 PC-DC-031', 'dell-5070', 'DA04-ZZH-PC', 'zhuangzh-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1663', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘乃伟のデバイス（JS1683）
('HYRON-220914 PC-DC-054', 'dell-5000', 'DA04-LIUNW-PC6', 'liunw', 'D204-NRTU', 'M5-A-09', 'JS1683', '10F', 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 居千涛のデバイス（JS1695）
('HYRON-210621 PC-DC-028', 'dell-5080', 'HYRON-JS-JUQT', 'juqt', 'POSI再構築', NULL, 'JS1695', '外借', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 卞春荣のデバイス（JS1697）
('HYRON-220914 PC-DC-052', 'dell-5000', 'DA04-LUNA-PC08', 'biancr', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1697', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵祥富のデバイス（JS1715）
('HYRON-221129 PC-DC-044', 'dell-5000', 'DA04-ZHAOXINAGFU-PC2', 'zhaoxiangfu', 'MTI-SC-MEDIC', 'M2-A-05', 'JS1715', NULL, 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘皖豫のデバイス（JS1719）
('HYRON-240416 PC-DC-014', 'OptiPlex Tower', 'HEJUNJIE-PC', 'hejj-ms', 'MS-リフト', 'M2-A-01', 'JS1719', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何江龙のデバイス（JS1720）
('HYRON-220926 PC-DC-023', 'dell-5000', 'DA04-LUNA-PC07', 'hejl', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1720', '显示器在卞春荣处', 24, 2, 14, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李润哲のデバイス（JS1736）
('HYRON-220125 PC-DC-017', 'dell-5090', 'DA04-LUNA-PC17', 'lirunzhe', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1736', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李纪飞のデバイス（JS1757）
('HYRON-241118 PC-DC-001', 'dell-7010', 'DA04-LIJF-PC2', 'lijf', 'MTI-TECH-DC', 'M2-A-03', 'JS1757', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王闯のデバイス（JS1761）
('HYRON-210621 PC-DC-030', 'dell-5080', 'DA04-WANGC-PC', 'wangchuang', 'R&D+SBI', '6F', 'JS1761', '外借', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李智勇のデバイス（JS1809）
('HYRON-240828 PC-DC-007', 'dell-7010', 'DA04-lizy2-PC2', 'lizy2', 'MTI-健診-DX', 'M2-A-03', 'JS1809', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈信地のデバイス（JS1812）
('HYRON-231127 PC-DC-006', 'optiplex', 'DA04-CHENXD', 'chenxd-nrtu', NULL, NULL, 'JS1812', '外借', 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 马泽原のデバイス（JS1840）
('HYRON-221129 PC-DC-069', 'dell-5000', 'DESKTOP-B08J4HA', 'mazy', NULL, 'M5', 'JS1840', '外借', 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-181112 PC-DC-002', NULL, NULL, NULL, NULL, '6F', 'JS1840', '已归还', 24, 3, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 徐欣のデバイス（JS1843）
('HYRON-231127 PC-DC-010', 'optiplex', 'DA04-XUXIN-PC', 'xuxin-nrtu', NULL, NULL, 'JS1843', '外借', 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 肖道一のデバイス（JS1866）
('HYRON-190217 PC-DC-006', 'dell-5050', 'DA04-xiaody-PC', 'xiaody', 'D309支援', NULL, 'JS1866', '外借', 24, 2, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 宋柳叶のデバイス（JS1873）
('HYRON-220414 PC-DC-049', 'dell-5090', 'DA04-SONGLY-PC', 'songly', 'MTI-SC-AMUSE', 'M2-A-02', 'JS1873', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李宝林のデバイス（JS1882）
('HYRON-170425 PC-DC-006', 'dell-5040', 'S2D-LIBL-PC1', 'libl', 'MTI-TECH-DC', 'M2-A-03', 'JS1882', '无修改PC名权限', 24, 2, 13, NULL, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 杨祥坤のデバイス（JS2045）
('230621-027(暂)', 'dell-5090', 'DA04-YANGXK-PC2', 'yangxk2-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2045', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李芷栅のデバイス（JS2050）
('HYRON-210125 PC-DC-015', 'dell-5080', 'DA04-LIZS-PC', 'lizs', 'MTI-SC-MW', 'M2-A-02', 'JS2050', NULL, 24, 2, 13, 19, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王天昊のデバイス（JS2053）
('HYRON-221129 PC-DC-007', 'dell-5000', 'DA04-WANGTH-PC1', 'wangth-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2053', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 唐凯2のデバイス（JS2060）
('HYRON-220414 PC-DC-043', 'dell-5090', 'DA04-TANGKAI-PC', 'tangkai2', 'MTI-PIT', 'M2-A-05', 'JS2060', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李勇のデバイス（JSXP406）
('HYRON-221129 PC-DC-051', 'dell-5000', 'DA04-LIYONG-PC', 'liyong', 'R&D+SBI', 'M5-A-09', 'JSXP406', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈传真のデバイス（JSXP438）
('HYRON-210118 PC-DC-033', 'dell-5080', 'DA04-chencz-pc', 'chencz', 'MTI-SC-MW', 'M2-A-05', 'JSXP438', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆建周のデバイス（JSXP446）
('HYRON-231127 PC-DC-003', 'Optiplex', 'NRTU-GAOBY-PC', 'lujz', 'R&D+SBI', 'M5-A-09', 'JSXP446', '无权限修改机器名', 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王天润のデバイス（JS2047）
('HYRON-220926 PC-DC-007', 'dell-5000', 'DA04-WANGTR-PC3', 'wangtr-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2047', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张慧慧のデバイス（JSXP454）
('HYRON-221129 PC-DC-030', 'dell-5000', 'DA04-ZHANGHH-PC', 'zhanghh2', NULL, 'M5-A-09', 'JSXP454', '外借', 24, 2, 13, 19, 21, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 高雨彤のデバイス（JS2176）
('HYRON-221129 PC-DC-068', 'optiplex', 'DA04-GAOYT-PC', 'gaoyt', 'D204-TRSW', 'M2-A-02', 'JS2176', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 赵晨云のデバイス（JS2177）
('HYRON-220914 PC-DC-050', 'dell-5000', 'DA04-ZHAOCHENY-PC3', 'zhaocheny', 'TEC', 'M2-A-03', 'JS2177', NULL, 24, 2, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 田季伟のデバイス（JS2187）
('HYRON-240828 PC-DC-009', 'dell-7010', 'DA04-TIANJW-PC', 'tianjw-MS', 'MS-GCP', 'M2-A-01', 'JS2187', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 祁文轩のデバイス（JS1926）
('HYRON-241118 PC-DC-005', 'dell-7010', 'DA04-LUNA-PC11', 'qiwx', 'MTI-TECH-LUNA', 'M2-A-03', 'JS1926', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 吴智雄のデバイス（JS1939）
('HYRON-181112 PC-DC-013', 'dell-5050', 'DA04-WZX-001', 'wuzhix', 'D204-TRSW', 'M2-A-02', 'JS1939', '已经归还', 24, 3, 13, NULL, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-240416 PC-DC-001', 'optiplex', 'DA04-Heyuan-01', 'zhangyan', 'D204-TRSW', 'M2-A-02', 'JS1939', '陈卫东远程工作用', 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-251201 PC-DC-015', 'dell-1250', 'DA04-WZX-002', 'wuzhix', 'D204-TRSW', 'M2-A-02', 'JS1939', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 左恩のデバイス（JS1941）
('HYRON-241118 PC-DC-006', 'dell-7010', 'DA04-ZUOEN-PC01', 'zuoen-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1941', NULL, 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王昱のデバイス（JS1973）
('HYRON-241118 PC-DC-040', 'dell-7010', 'DA04-WANGYU-PC', 'wangy', 'MTI-PIT', 'M2-A-05', 'JS1973', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 张攀のデバイス（JS1981）
('HYRON-241118 PC-DC-068', 'dell-7010', 'DA04-ZHANGPAN-01', 'zhangpan', 'D204-TRSW', 'M2-A-02', 'JS1981', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 李兴旺のデバイス（JS1982）
('HYRON-241118 PC-DC-008', 'dell-7010', 'DA04-LIXW-PC', 'lixw', 'MTI-TECH-DC', 'M2-A-03', 'JS1982', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 母丽丽のデバイス（JS1992）
('HYRON-241118 PC-DC-004', 'dell-7010', 'DA04-MULL-PC', 'mull', 'MTI-PIT', 'M2-A-05', 'JS1992', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 顾航睿のデバイス（JS1996）
('HYRON-241118 PC-DC-007', 'dell-7010', 'DA04-LUNA-PC11', 'guhr', 'MTI-健診-DX', 'M2-A-03', 'JS1996', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈诺のデバイス（JS2005）
('HYRON-241118 PC-DC-039', 'dell-7010', 'DA04-CHENNUO-PC01', 'chennuo-nrtu', 'D204-NRTU', 'M2-A-02', 'JS2005', NULL, 24, 2, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 高鑫のデバイス（JS2008）
('HYRON-241118 PC-DC-003', 'dell-7010', 'DA04-GAOXIN-PC01', 'gaoxin2-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2008', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 王小龙のデバイス（JS2019）
('HYRON-241118 PC-DC-042', 'dell-7010', 'DA04-WANGXL-PC', 'wangxl-ms', 'MS-リフト', 'M2-A-01', 'JS2019', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何启明のデバイス（JS2024）
('HYRON-241118 PC-DC-041', 'dell-7010', 'DA04-HEQM-PC', 'heqm', 'R&D+SBI', 'M5-A-09', 'JS2024', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 刘子俊のデバイス（JS2034）
('HYRON-241118 PC-DC-043', 'dell-7010', 'DA04-liuzj-PC', 'liuzj-nrtu', 'D204-NRTU', 'M5-A-09', 'JS2034', NULL, 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 蔡文强のデバイス（JS2037）
('HYRON-211217 PC-DC-042', 'dell-5090', 'DA04-CAIWQ-PC', 'caiwq', 'MTI-TECH-DC', 'M2-A-03', 'JS2037', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 姜成杰のデバイス（JS2178）
('HYRON-191217 PC-DC-006', 'dell-5070', 'DA04-JIANGCJ-PC', 'jiangcj', 'MTI-SC-MEDIC', 'M2-A-05', 'JS2178', NULL, 24, 2, 13, 19, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙毅のデバイス（JSXP536）
('HYRON-210118 PC-DC-015', 'dell-5070', 'DA04-SUNYI-PC', 'suny', 'R&D+SBI', 'M5-A-09', 'JSXP536', NULL, 24, 2, 13, 18, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆语涵のデバイス（JSXP538）
('HYRON-221129 PC-DC-019', 'OptiPlex5000', 'DESKTOP-QINQMVA', 'luyh', 'R&D+SBI', 'M5-A-09', 'JSXP538', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 季荷飞のデバイス（JSXP539）
('HYRON-220914 PC-DC-011', 'dell-5000', 'DA04_JIHEFEI-PC', 'jihf', 'R&D+SBI', 'M5-A-09', 'JSXP539', NULL, 24, 3, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 戴明のデバイス（JSXP541）
('HYRON-210621 PC-DC-021', 'dell-5080', 'DA04-JSXP541-PC', 'daiming', 'R&D+SBI', 'M5-A-09', 'JSXP541', NULL, 24, 3, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 曹光友のデバイス（JSXP551）
('HYRON-210118 PC-DC-027', 'dell-5080', 'DA04-CAOGY-PC', 'yangxk', 'D204-NRTU', 'M5-A-09', 'JSXP551', NULL, 24, 3, 13, 19, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 黄羽轩のデバイス（JSSX077）
('HYRON-190605 PC-DC-016', 'dell-5050', 'HUANGYX-PC', 'huangyux', 'イーグル', 'M2-A-05', 'JSSX077', NULL, 24, 2, 14, NULL, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 徐睿のデバイス (DA02部門)（JS0898）
('HYRON-210125 PC-DC-005', 'dell-5080', 'DA02-XURUI-PC1', 'xurui', 'R&D+SBI', 'M5-A-09', 'JS0898', '大显示器是DA02申请', 24, 2, 15, 19, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 胡浩磊のデバイス (DA02部門)（JS1158）
('HYRON-220414 PC-DC-042', 'dell-5090', 'NRTU-huhl-PC', 'huhl-nrtu', 'D204-NRTU', 'M5-A-09', 'JS1158', NULL, 24, 2, 13, 17, 22, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆跃跃のデバイス (DA02部門)（JS2056）
('HYRON-240416 PC-DC-018', 'OptiPlex Tower7010', 'DA02-LUYY-PC2', 'luyy-ms', 'MS-リフト', 'M2-A-01', 'JS2056', '名下有台主机未登记，借用杨爱玉的显示器HYRON-240826 Minitor-003', 24, 3, 15, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 吴正明のデバイス (DA02部門)（JS0226）
('HYRON-240416 PC-DC-012', 'OptiPlex', 'wuzm-DA02-01', 'wuzm', 'イーグル', 'M2-A-05', 'JS0226', NULL, 24, 2, 15, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),
('HYRON-221129 PC-DC-048', 'dell-5000', 'DESKTOP-7S6JGB6', 'wuzm', 'CAS-经费', 'M2-A-05', 'JS0226', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 朱亚明のデバイス (DA02部門)（JS0296）
('HYRON-211217 PC-DC-057', 'dell-5090', 'S2D-ZHUYM-PC02', 'zhuym', 'イーグル', 'M2-A-05', 'JS0296', NULL, 24, 2, 13, 18, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何桢のデバイス (DA02部門)（JS0941）
('HYRON-220414 PC-DC-044', 'dell-5090', 'S2D-HEZHEN-PC02', 'hezhen', 'イーグル', 'M2-A-05', 'JS0941', NULL, 24, 2, 15, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 孙萧のデバイス (DA02部門)（JS1672）
('HYRON-240416 PC-DC-013', 'dell-7010', 'DESKTOP-sunxiao', 'sunxiao', 'イーグル', 'M2-A-05', 'JS1672', NULL, 24, 3, 15, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈楠钢のデバイス (DA02部門)（JS1699）
('HYRON-220926 PC-DC-006', 'dell-5000', 'DA04-CHENNG-PC4', 'chenng', 'CAS-经费', 'M2-A-05', 'JS1699', NULL, 24, 2, 13, 17, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 顾新宇のデバイス (DA02部門)（JS1929）
('HYRON-211217 PC-DC-056', 'dell-5090', 'DESKTOP-TANGKAI', 'guxiny', 'イーグル', 'M2-A-05', 'JS1929', NULL, 24, 2, 15, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陈兆伟のデバイス (DA02部門)（JS2167）
('HYRON-151020 PC-DC-002', 'dell-7080', 'DA02-CHENZHAOWEI-PC', 'chenzw', 'CAS-经费', 'M2-A-05', 'JS2167', NULL, 24, 2, 13, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 田梵君のデバイス (DA02部門)（JS2171）
('HYRON-170904 PC-DC-009', 'dell-5050', 'DA02-Tianfj-PC2', 'tianfj', 'CAS-经费', 'M2-A-05', 'JS2171', NULL, 24, 2, 13, 19, 19, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 卢国颖のデバイス (DA02部門)（JS2172）
('HYRON-160818 PC-DC-016', 'dell-5040', 'DA02-LuGY-PC2', 'luhuoy', 'CAS-经费', 'M2-A-05', 'JS2172', NULL, 24, 2, 13, 19, 19, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 陆红非のデバイス (DA05部門)（JS1137）
('HYRON-240828 PC-DC-008', 'OptiPlex Tower 7010', 'DA02-ZHANGYF-PC', 'luhf-ms', 'MS-リフト', 'M2-A-01', 'JS1137', '日本出差中', 24, 3, 13, 19, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin'),

-- 何渊のデバイス (DA05部門)（SH726）
('HYRON-250123 nobo-001', NULL, NULL, 'heyuan', 'D204-TRSW', 'M2-A-02', 'SH726', '南京笔记本', 24, 3, 13, 22, NULL, CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');