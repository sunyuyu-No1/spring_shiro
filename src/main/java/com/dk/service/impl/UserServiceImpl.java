package com.dk.service.impl;

import com.dk.mapper.TPersonMapper;
import com.dk.pojo.TPerson;
import com.dk.pojo.TPersonExample;
import com.dk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Deacription TODO
 * @Author ASUS
 * @Date 2020/12/7 19:40
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private TPersonMapper tPersonMapper;

    @Override
    public TPerson findByUsername(String username) {
        TPersonExample tPersonExample = new TPersonExample();
        TPersonExample.Criteria criteria = tPersonExample.createCriteria();
        criteria.andPnameEqualTo(username);
        List<TPerson> list = tPersonMapper.selectByExample(tPersonExample);
//        TPerson tPerson = Optional.ofNullable(tPeople).orElse(null).get(0);
        return list == null || list.size() ==0 ? null:list.get(0);
    }
}
