package com.dk.mapper;

import com.dk.pojo.TPerson;
import com.dk.pojo.TPersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPersonMapper {
    int countByExample(TPersonExample example);

    int deleteByExample(TPersonExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(TPerson record);

    int insertSelective(TPerson record);

    List<TPerson> selectByExample(TPersonExample example);

    TPerson selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") TPerson record, @Param("example") TPersonExample example);

    int updateByExample(@Param("record") TPerson record, @Param("example") TPersonExample example);

    int updateByPrimaryKeySelective(TPerson record);

    int updateByPrimaryKey(TPerson record);
}