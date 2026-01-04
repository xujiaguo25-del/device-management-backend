package com.device.management.service.impl;

import com.device.management.entity.DevicePermission;
import com.device.management.mapper.DevicePermissionMapper;
import com.device.management.service.IDevicePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 機器使用権限テーブル（機器のドメイン、USB等の権限を保存：機器単位管理） 服务实现类
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Service
public class DevicePermissionServiceImpl extends ServiceImpl<DevicePermissionMapper, DevicePermission> implements IDevicePermissionService {

}
