package com.device.management.repository;

import com.device.management.entity.MonitorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorInfoRepository extends JpaRepository<MonitorInfo, Integer> {
    
    /**
     * デバイスIDでモニター情報を検索
     * Device エンティティの主キーは deviceId であるため、カスタムクエリを使用
     */
    @Query("SELECT m FROM MonitorInfo m WHERE m.device.deviceId = :deviceId")
    List<MonitorInfo> findByDeviceId(@Param("deviceId") String deviceId);
}
