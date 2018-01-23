package com.kuliao.horcrux.app.mapper;

import com.kuliao.horcrux.app.model.ServiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface ServiceInfoMapper {

    @Select("select * from serviceinfo where userName=#{userName}")//mybatis的注解
    public ServiceInfo findService(String userName);

}
