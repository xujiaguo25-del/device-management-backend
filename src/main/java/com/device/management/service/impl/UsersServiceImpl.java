package com.device.management.service.impl;

import com.device.management.entity.Users;
import com.device.management.mapper.UsersMapper;
import com.device.management.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ユーザテーブル（ユーザ基本情報を保存） 服务实现类
 * </p>
 *
 * @author xiaos
 * @since 2026-01-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
