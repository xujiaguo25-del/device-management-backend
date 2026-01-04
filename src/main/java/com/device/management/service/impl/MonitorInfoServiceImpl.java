package com.device.management.service.impl;

import com.device.management.entity.MonitorInfo;
import com.device.management.mapper.MonitorInfoMapper;
import com.device.management.service.IMonitorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * モニター情報テーブル（機器に関連するモニターを保存） 服务实现类
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Service
public class MonitorInfoServiceImpl extends ServiceImpl<MonitorInfoMapper, MonitorInfo> implements IMonitorInfoService {

}
