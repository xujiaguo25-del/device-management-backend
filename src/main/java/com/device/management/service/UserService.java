package com.device.management.service;

import com.device.management.dto.UserDTO;
import com.device.management.dto.DeviceDTO;
import com.device.management.dto.DictDTO;
import com.device.management.entity.User;
import com.device.management.dto.DictMapper;
import com.device.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final DeviceService deviceService;

    // ページングクエリ：デバイス条件でユーザーをフィルタリングし、ユーザータイプ辞書を関連付けて返す
    public Page<UserDTO> list(String deviceName, String userId, int page, int size) {
        // ページ数の調整：0から始まるページ番号に変換
        page = page > 0 ? page - 1 : 0;

        Pageable pageable = PageRequest.of(page, size, Sort.by("userId").ascending());

        // デバイスフィルタ条件に合致するUserIDリストを取得
        List<String> filteredUserIds = deviceService.findUserIdsByCondition(deviceName, userId);
        if (filteredUserIds.isEmpty()) return Page.empty(pageable);

        // ページングでUserエンティティをクエリ
        Page<User> userPage = userRepository.findByUserIdIn(filteredUserIds, pageable);
        List<User> users = userPage.getContent();

        // これらのユーザーのデバイスをバッチでロード
        Map<String, List<DeviceDTO>> userDeviceMap = deviceService.getDeviceMapByUserIds(
                users.stream().map(User::getUserId).collect(Collectors.toList())
        );

        // DTOを組み立て、辞書変換を含む
        List<UserDTO> dtoList = users.stream().map(user ->
                convertToUserDTO(user, userDeviceMap.getOrDefault(user.getUserId(), new ArrayList<>()))
        ).collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, userPage.getTotalElements());
    }

    private UserDTO convertToUserDTO(User user, List<DeviceDTO> devices) {
        // DictMapperを使用してUserエンティティのuserTypeDictをDictDTOに変換
        DictDTO userTypeDto = DictMapper.toDTO(user.getUserTypeDict());

        return UserDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .deptId(user.getDeptId())
                .userType(userTypeDto) // ここでオブジェクトを返す、数字ではない
                .createTime(user.getCreateTime())
                .creater(user.getCreater()) // UserDTOBuilderメソッドの解析できない問題を解決
                .updateTime(user.getUpdateTime())
                .updater(user.getUpdater())
                .devices(devices)
                .build();
    }
}