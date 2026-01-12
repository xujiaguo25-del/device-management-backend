package PermissonDataInsertTest;

import com.device.DeviceManagementApplication;
import com.device.management.entity.Dict;
import com.device.management.repository.UserRepository;
import com.device.management.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = DeviceManagementApplication.class)
public class UsersDataInsertTest {

    @Autowired
    private UserRepository userRepository;

    // 匹配SQL中的时间格式（精确到6位毫秒）
    private final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    @Test
    public void insertAllUserData() {
        List<User> userList = new ArrayList<>();

        // 1. 原始SQL中users表的8条数据（字段值完全复用）
        userList.add(buildUser("123123", "d", "wlf", 12L, "e",
                "2026-01-05 07:15:13.050239", null,
                "2026-01-05 07:15:13.050239", null));
        userList.add(buildUser("TESTUSER", "DA04", "张三", 11L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));
        userList.add(buildUser("USER_001", "DA04", "刘文丰", 12L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));
        userList.add(buildUser("USER_002", "DA04", "朱德涛", 12L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));
        userList.add(buildUser("USER_003", "DA04", "潘小琴", 12L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));
        userList.add(buildUser("USER_004", "DA04", "陈国宝", 12L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));
        userList.add(buildUser("USER_005", "DA02", "徐睿", 12L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));
        userList.add(buildUser("USER_006", "DA04", "管理员", 11L, "$2a$10$EixZaYb05t8Gp4z5H8XQ5OeF5G6H7J8K9L0M1N2O3P4Q5R6S7T8U",
                "2026-01-05 08:42:53.616181", "admin",
                "2026-01-05 08:42:53.616181", "admin"));

        // 执行批量插入
        userRepository.saveAll(userList);
    }

    /**
     * 构建User对象（复用原始SQL字段值，处理时间格式转换）
     */
    private User buildUser(String userId, String deptId, String name, Long userTypeId, String password,
                           String createTimeStr, String creater,
                           String updateTimeStr, String updater) {
        User user = new User();
        user.setUserId(userId);
        user.setDeptId(deptId);
        user.setName(name);
        user.setUserType(
                Dict.builder()
                        .id(userTypeId)
                        .build());

        user.setPassword(password);

        // 时间字符串转Instant（匹配User实体类的时间类型）
        user.setCreateTime(LocalDateTime.parse(createTimeStr, dtFormatter)
                .atZone(ZoneId.systemDefault()).toInstant());
        user.setCreater(creater);
        user.setUpdateTime(LocalDateTime.parse(updateTimeStr, dtFormatter)
                .atZone(ZoneId.systemDefault()).toInstant());
        user.setUpdater(updater);
        return user;
    }
}