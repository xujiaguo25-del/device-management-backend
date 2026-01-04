package com.device.management.service.impl;

import com.device.management.entity.DeviceIp;
import com.device.management.mapper.DeviceIpMapper;
import com.device.management.service.IDeviceIpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 機器IPテーブル（機器に関連するIPアドレスを保存） 服务实现类
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Service
public class DeviceIpServiceImpl extends ServiceImpl<DeviceIpMapper, DeviceIp> implements IDeviceIpService {

}
