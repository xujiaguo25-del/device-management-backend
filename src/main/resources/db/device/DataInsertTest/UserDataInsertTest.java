package com.device;

import com.device.management.DeviceManagementApplication;
import com.device.management.repository.UserRepository;
import com.device.management.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;

@SpringBootTest(classes = DeviceManagementApplication.class)
public class UserDataInsertTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void insertTestUsers() {
        // テストユーザーの挿入
        User testUser = User.builder()
                .userId("USER001")
                .deptId("D001")
                .name("user")
                .userTypeId(12L)
                .password(passwordEncoder.encode("HYRON@JS2026"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(testUser);

        // 管理者ユーザーの挿入
        User adminUser = User.builder()
                .userId("ADMIN001")
                .deptId("D000")
                .name("admin")
                .userTypeId(11L)
                .password(passwordEncoder.encode("HYRON@JS2026"))
                .createTime(LocalDateTime.now())
                .creater("system")
                .updateTime(LocalDateTime.now())
                .updater("system")
                .build();
        userRepository.save(adminUser);

        // users.sql からのユーザー挿入開始
        // DA04 部署のユーザー
        User user_JS0014 = User.builder()
                .userId("JS0014")
                .deptId("DA04")
                .name("林飞")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0014);

        User user_JS0020 = User.builder()
                .userId("JS0020")
                .deptId("DA04")
                .name("刘文丰")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0020);

        User user_JS0054 = User.builder()
                .userId("JS0054")
                .deptId("DA04")
                .name("朱德涛")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0054);

        User user_JS0105 = User.builder()
                .userId("JS0105")
                .deptId("DA04")
                .name("潘小琴")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0105);

        User user_JS0108 = User.builder()
                .userId("JS0108")
                .deptId("DA04")
                .name("陈国宝")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0108);

        User user_JS0129 = User.builder()
                .userId("JS0129")
                .deptId("DA04")
                .name("武志鹏")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0129);

        User user_JS0177 = User.builder()
                .userId("JS0177")
                .deptId("DA04")
                .name("曹向阳")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0177);

        User user_JS0199 = User.builder()
                .userId("JS0199")
                .deptId("DA04")
                .name("欧金龙")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0199);

        User user_JS026L3 = User.builder()
                .userId("JS026L3")
                .deptId("DA04")
                .name("王燕飞")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS026L3);

        User user_JS026L6 = User.builder()
                .userId("JS026L6")
                .deptId("DA04")
                .name("周仲山")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS026L6);

        User user_JS0291 = User.builder()
                .userId("JS0291")
                .deptId("DA04")
                .name("徐冬琴")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0291);

        User user_JS0298 = User.builder()
                .userId("JS0298")
                .deptId("DA04")
                .name("聂建军")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0298);

        User user_JS0300 = User.builder()
                .userId("JS0300")
                .deptId("DA04")
                .name("曹天扬")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0300);

        User user_JS0356 = User.builder()
                .userId("JS0356")
                .deptId("DA04")
                .name("卢淑美")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0356);

        User user_JS0426L = User.builder()
                .userId("JS0426L")
                .deptId("DA04")
                .name("袁晓东")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0426L);

        User user_JS0449 = User.builder()
                .userId("JS0449")
                .deptId("DA04")
                .name("李琪")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0449);

        User user_JS0572 = User.builder()
                .userId("JS0572")
                .deptId("DA04")
                .name("李行")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0572);

        User user_JS0596 = User.builder()
                .userId("JS0596")
                .deptId("DA04")
                .name("王晓舟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0596);

        User user_JS0609 = User.builder()
                .userId("JS0609")
                .deptId("DA04")
                .name("张娟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0609);

        User user_JS0611 = User.builder()
                .userId("JS0611")
                .deptId("DA04")
                .name("袁娟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0611);

        User user_JS0619 = User.builder()
                .userId("JS0619")
                .deptId("DA04")
                .name("赵莉")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0619);

        User user_JS0627 = User.builder()
                .userId("JS0627")
                .deptId("DA04")
                .name("陈卫东")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0627);

        User user_JS0720 = User.builder()
                .userId("JS0720")
                .deptId("DA04")
                .name("莫尚勇")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0720);

        User user_JS0746 = User.builder()
                .userId("JS0746")
                .deptId("DA04")
                .name("张炎")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0746);

        User user_JS0751 = User.builder()
                .userId("JS0751")
                .deptId("DA04")
                .name("孙博")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0751);

        User user_JS0798 = User.builder()
                .userId("JS0798")
                .deptId("DA04")
                .name("陈辰")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0798);

        User user_JS0800 = User.builder()
                .userId("JS0800")
                .deptId("DA04")
                .name("董鸣辉")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0800);

        User user_JS0833 = User.builder()
                .userId("JS0833")
                .deptId("DA04")
                .name("杨爱玉")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0833);

        User user_JS0842 = User.builder()
                .userId("JS0842")
                .deptId("DA04")
                .name("张卫华")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0842);

        User user_JS0942 = User.builder()
                .userId("JS0942")
                .deptId("DA04")
                .name("许加国")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0942);

        User user_JS0958 = User.builder()
                .userId("JS0958")
                .deptId("DA04")
                .name("杨皓伟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0958);

        User user_JS1023 = User.builder()
                .userId("JS1023")
                .deptId("DA04")
                .name("李文昊")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1023);

        User user_JS1029 = User.builder()
                .userId("JS1029")
                .deptId("DA04")
                .name("马晓明")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1029);

        User user_JS1061 = User.builder()
                .userId("JS1061")
                .deptId("DA04")
                .name("赵康2")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1061);

        User user_JS1075 = User.builder()
                .userId("JS1075")
                .deptId("DA04")
                .name("汪海燕")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1075);

        User user_JS1148 = User.builder()
                .userId("JS1148")
                .deptId("DA04")
                .name("蔡翔宇")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1148);

        User user_JS1150 = User.builder()
                .userId("JS1150")
                .deptId("DA04")
                .name("陈殊熠")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1150);

        User user_JS1174 = User.builder()
                .userId("JS1174")
                .deptId("DA04")
                .name("罗佳")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1174);

        User user_JS1180 = User.builder()
                .userId("JS1180")
                .deptId("DA04")
                .name("孙鹏")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1180);

        User user_JS1239 = User.builder()
                .userId("JS1239")
                .deptId("DA04")
                .name("康博成")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1239);

        User user_JS1244 = User.builder()
                .userId("JS1244")
                .deptId("DA04")
                .name("潘宇")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1244);

        User user_JS1246 = User.builder()
                .userId("JS1246")
                .deptId("DA04")
                .name("王邵赟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1246);

        User user_JS1285 = User.builder()
                .userId("JS1285")
                .deptId("DA04")
                .name("王冠杰")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1285);

        User user_JS1307 = User.builder()
                .userId("JS1307")
                .deptId("DA04")
                .name("邓怿泽")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1307);

        User user_JS1339 = User.builder()
                .userId("JS1339")
                .deptId("DA04")
                .name("王子晗")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1339);

        User user_JS1347 = User.builder()
                .userId("JS1347")
                .deptId("DA04")
                .name("张欢")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1347);

        User user_JS1359 = User.builder()
                .userId("JS1359")
                .deptId("DA04")
                .name("罗翔")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1359);

        User user_JS1363 = User.builder()
                .userId("JS1363")
                .deptId("DA04")
                .name("应加俊")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1363);

        User user_JS1402 = User.builder()
                .userId("JS1402")
                .deptId("DA04")
                .name("王孟洛")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1402);

        User user_JS1435 = User.builder()
                .userId("JS1435")
                .deptId("DA04")
                .name("贾乃康")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1435);

        User user_JS1436 = User.builder()
                .userId("JS1436")
                .deptId("DA04")
                .name("姜榕")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1436);

        User user_JS1510 = User.builder()
                .userId("JS1510")
                .deptId("DA04")
                .name("章捷")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1510);

        User user_JS1514 = User.builder()
                .userId("JS1514")
                .deptId("DA04")
                .name("李文佳")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1514);

        User user_JS1519 = User.builder()
                .userId("JS1519")
                .deptId("DA04")
                .name("潘箬婷")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1519);

        User user_JS1527 = User.builder()
                .userId("JS1527")
                .deptId("DA04")
                .name("丁楠")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1527);

        User user_JS1555 = User.builder()
                .userId("JS1555")
                .deptId("DA04")
                .name("华丰")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1555);

        User user_JS1564 = User.builder()
                .userId("JS1564")
                .deptId("DA04")
                .name("丁飞鸿")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1564);

        User user_JS1565 = User.builder()
                .userId("JS1565")
                .deptId("DA04")
                .name("杜记航")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1565);

        User user_JS1569 = User.builder()
                .userId("JS1569")
                .deptId("DA04")
                .name("汪坦")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1569);

        User user_JS1599 = User.builder()
                .userId("JS1599")
                .deptId("DA04")
                .name("曲家志")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1599);

        User user_JS1607 = User.builder()
                .userId("JS1607")
                .deptId("DA04")
                .name("施炜玮")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1607);

        User user_JS1613 = User.builder()
                .userId("JS1613")
                .deptId("DA04")
                .name("杨杨")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1613);

        User user_JS1640 = User.builder()
                .userId("JS1640")
                .deptId("DA04")
                .name("朱国威")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1640);

        User user_JS1663 = User.builder()
                .userId("JS1663")
                .deptId("DA04")
                .name("庄子泓")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1663);

        User user_JS1683 = User.builder()
                .userId("JS1683")
                .deptId("DA04")
                .name("刘乃伟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1683);

        User user_JS1695 = User.builder()
                .userId("JS1695")
                .deptId("DA04")
                .name("居千涛")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1695);

        User user_JS1697 = User.builder()
                .userId("JS1697")
                .deptId("DA04")
                .name("卞春荣")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1697);

        User user_JS1715 = User.builder()
                .userId("JS1715")
                .deptId("DA04")
                .name("赵祥富")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1715);

        User user_JS1719 = User.builder()
                .userId("JS1719")
                .deptId("DA04")
                .name("刘皖豫")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1719);

        User user_JS1720 = User.builder()
                .userId("JS1720")
                .deptId("DA04")
                .name("何江龙")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1720);

        User user_JS1736 = User.builder()
                .userId("JS1736")
                .deptId("DA04")
                .name("李润哲")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1736);

        User user_JS1757 = User.builder()
                .userId("JS1757")
                .deptId("DA04")
                .name("李纪飞")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1757);

        User user_JS1761 = User.builder()
                .userId("JS1761")
                .deptId("DA04")
                .name("王闯")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1761);

        User user_JS1809 = User.builder()
                .userId("JS1809")
                .deptId("DA04")
                .name("李智勇")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1809);

        User user_JS1812 = User.builder()
                .userId("JS1812")
                .deptId("DA04")
                .name("陈信地")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1812);

        User user_JS1840 = User.builder()
                .userId("JS1840")
                .deptId("DA04")
                .name("马泽原")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1840);

        User user_JS1843 = User.builder()
                .userId("JS1843")
                .deptId("DA04")
                .name("徐欣")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1843);

        User user_JS1866 = User.builder()
                .userId("JS1866")
                .deptId("DA04")
                .name("肖道一")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1866);

        User user_JS1873 = User.builder()
                .userId("JS1873")
                .deptId("DA04")
                .name("宋柳叶")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1873);

        User user_JS1882 = User.builder()
                .userId("JS1882")
                .deptId("DA04")
                .name("李宝林")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1882);

        User user_JS1888 = User.builder()
                .userId("JS1888")
                .deptId("DA04")
                .name("马强")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1888);

        User user_JS1902 = User.builder()
                .userId("JS1902")
                .deptId("DA04")
                .name("胡寒池")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1902);

        User user_JS2045 = User.builder()
                .userId("JS2045")
                .deptId("DA04")
                .name("杨祥坤")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2045);

        User user_JS2050 = User.builder()
                .userId("JS2050")
                .deptId("DA04")
                .name("李芷栅")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2050);

        User user_JS2053 = User.builder()
                .userId("JS2053")
                .deptId("DA04")
                .name("王天昊")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2053);

        User user_JS2060 = User.builder()
                .userId("JS2060")
                .deptId("DA04")
                .name("唐凯2")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2060);

        User user_JSXP406 = User.builder()
                .userId("JSXP406")
                .deptId("DA04")
                .name("李勇")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP406);

        User user_JSXP438 = User.builder()
                .userId("JSXP438")
                .deptId("DA04")
                .name("陈传真")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP438);

        User user_JSXP446 = User.builder()
                .userId("JSXP446")
                .deptId("DA04")
                .name("陆建周")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP446);

        User user_JS2047 = User.builder()
                .userId("JS2047")
                .deptId("DA04")
                .name("王天润")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2047);

        User user_JSXP454 = User.builder()
                .userId("JSXP454")
                .deptId("DA04")
                .name("张慧慧")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP454);

        User user_JS2176 = User.builder()
                .userId("JS2176")
                .deptId("DA04")
                .name("高雨彤")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2176);

        User user_JS2177 = User.builder()
                .userId("JS2177")
                .deptId("DA04")
                .name("赵晨云")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2177);

        User user_JS2187 = User.builder()
                .userId("JS2187")
                .deptId("DA04")
                .name("田季伟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2187);

        User user_JS1926L = User.builder()
                .userId("JS1926L")
                .deptId("DA04")
                .name("祁文轩")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1926L);

        User user_JS1939 = User.builder()
                .userId("JS1939")
                .deptId("DA04")
                .name("吴智雄")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1939);

        User user_JS1941 = User.builder()
                .userId("JS1941")
                .deptId("DA04")
                .name("左恩")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1941);

        User user_JS1973 = User.builder()
                .userId("JS1973")
                .deptId("DA04")
                .name("王昱")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1973);

        User user_JS1981 = User.builder()
                .userId("JS1981")
                .deptId("DA04")
                .name("张攀")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1981);

        User user_JS1982 = User.builder()
                .userId("JS1982")
                .deptId("DA04")
                .name("李兴旺")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1982);

        User user_JS1992 = User.builder()
                .userId("JS1992")
                .deptId("DA04")
                .name("母丽丽")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1992);

        User user_JS1996 = User.builder()
                .userId("JS1996")
                .deptId("DA04")
                .name("顾航睿")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1996);

        User user_JS2005 = User.builder()
                .userId("JS2005")
                .deptId("DA04")
                .name("陈诺")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2005);

        User user_JS2008 = User.builder()
                .userId("JS2008")
                .deptId("DA04")
                .name("高鑫")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2008);

        User user_JS2019 = User.builder()
                .userId("JS2019")
                .deptId("DA04")
                .name("王小龙")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2019);

        User user_JS2024 = User.builder()
                .userId("JS2024")
                .deptId("DA04")
                .name("何启明")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2024);

        User user_JS2034 = User.builder()
                .userId("JS2034")
                .deptId("DA04")
                .name("刘子俊")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2034);

        User user_JS2037 = User.builder()
                .userId("JS2037")
                .deptId("DA04")
                .name("蔡文强")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2037);

        User user_JS2178 = User.builder()
                .userId("JS2178")
                .deptId("DA04")
                .name("姜成杰")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2178);

        User user_JSXP536 = User.builder()
                .userId("JSXP536")
                .deptId("DA04")
                .name("孙毅")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP536);

        User user_JSXP538 = User.builder()
                .userId("JSXP538")
                .deptId("DA04")
                .name("陆语涵")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP538);

        User user_JSXP539 = User.builder()
                .userId("JSXP539")
                .deptId("DA04")
                .name("季荷飞")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP539);

        User user_JSXP541 = User.builder()
                .userId("JSXP541")
                .deptId("DA04")
                .name("戴明")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP541);

        User user_JSXP551 = User.builder()
                .userId("JSXP551")
                .deptId("DA04")
                .name("曹光友")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSXP551);

        User user_JSSX077 = User.builder()
                .userId("JSSX077")
                .deptId("DA04")
                .name("黄羽轩")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JSSX077);

        // DA02 部署のユーザー
        User user_JS0898 = User.builder()
                .userId("JS0898")
                .deptId("DA02")
                .name("徐睿")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0898);

        User user_JS1158 = User.builder()
                .userId("JS1158")
                .deptId("DA02")
                .name("胡浩磊")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1158);

        User user_JS2056 = User.builder()
                .userId("JS2056")
                .deptId("DA02")
                .name("陆跃跃")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2056);

        User user_JS0226L = User.builder()
                .userId("JS0226L")
                .deptId("DA02")
                .name("吴正明")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0226L);

        User user_JS0296 = User.builder()
                .userId("JS0296")
                .deptId("DA02")
                .name("朱亚明")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0296);

        User user_JS0941 = User.builder()
                .userId("JS0941")
                .deptId("DA02")
                .name("何桢")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS0941);

        User user_JS1672 = User.builder()
                .userId("JS1672")
                .deptId("DA02")
                .name("孙萧")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1672);

        User user_JS1699 = User.builder()
                .userId("JS1699")
                .deptId("DA02")
                .name("陈楠钢")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1699);

        User user_JS1929 = User.builder()
                .userId("JS1929")
                .deptId("DA02")
                .name("顾新宇")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1929);

        User user_JS2167 = User.builder()
                .userId("JS2167")
                .deptId("DA02")
                .name("陈兆伟")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2167);

        User user_JS2171 = User.builder()
                .userId("JS2171")
                .deptId("DA02")
                .name("田梵君")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2171);

        User user_JS2172 = User.builder()
                .userId("JS2172")
                .deptId("DA02")
                .name("卢国颖")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS2172);

        // DA05 部署のユーザー
        User user_JS1137 = User.builder()
                .userId("JS1137")
                .deptId("DA05")
                .name("陆红非")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_JS1137);

        User user_SH726L = User.builder()
                .userId("SH726L")
                .deptId("DA05")
                .name("何渊")
                .userTypeId(26L)
                .password(passwordEncoder.encode("123456"))
                .createTime(LocalDateTime.now())
                .creater("admin")
                .updateTime(LocalDateTime.now())
                .updater("admin")
                .build();
        userRepository.save(user_SH726L);
    }
}