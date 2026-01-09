package com.device.management.repository;

import com.device.management.entity.DeviceInfo;
import com.device.management.entity.DevicePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevicePermissionRepository
        extends JpaRepository<DevicePermission, String>,
        JpaSpecificationExecutor<DevicePermission>
{

}