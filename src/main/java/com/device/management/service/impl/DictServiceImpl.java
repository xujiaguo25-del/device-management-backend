package com.device.management.service.impl;

import com.device.management.entity.Dict;
import com.device.management.mapper.DictMapper;
import com.device.management.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 統合辞書テーブル（辞書タイプと項目を管理） 服务实现类
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}
