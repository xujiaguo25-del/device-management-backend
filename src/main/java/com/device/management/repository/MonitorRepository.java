package com.device.management.repository;

import com.device.management.entity.DevicePermission;
import com.device.management.entity.MonitorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MonitorRepository extends
        JpaRepository<MonitorInfo, Integer>,
        JpaSpecificationExecutor<MonitorInfo>,
        QueryByExampleExecutor<MonitorInfo> {
}
