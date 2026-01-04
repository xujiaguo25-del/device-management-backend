package com.device.management.service.impl;

import com.device.management.entity.DeviceInfo;
import com.device.management.mapper.DeviceInfoMapper;
import com.device.management.service.IDeviceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 機器情報テーブル（機器ハードウェア構成を保存） 服务实现类
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo> implements IDeviceInfoService {

}
