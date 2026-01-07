package com.device.management.repository;


import com.device.management.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
* 设备 Repository
* */
@Repository
public interface  DeviceRepository extends JpaRepository<Device, String> {
    // ユーザーIDに基づいてデバイスを検索する
    List<Device> findByUserId(String userId);

    // 開発室に基づいてデバイスを検索する
    List<Device> findByDevRoom(String devRoom);

    // プロジェクト名で機器を検索する
    List<Device> findByProject(String project);

}
