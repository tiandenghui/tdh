package com.kuliao.horcrux.app.mapper;

import com.kuliao.horcrux.app.model.ServiceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;


@Mapper
public interface ServiceInfoMapper {

    @Select("select * from serviceinfo where userName=#{userName}")//mybatis的注解
    public ServiceInfo findService(String userName);

    @Select("select * from serviceinfo where serviceName=#{serviceName}")
    public ServiceInfo findUser(String serviceName);   //从model层对象开始

    @Insert("insert into serviceinfo(userName,serviceName,servicePort) values(#{userName},#{serviceName},#{servicePort})")
    public int insertOneService(@Param("userName") String userName, @Param("serviceName") String serviceName, @Param("servicePort") String servicePort);

    @Insert("insert into serviceinfo(userName,serviceName,servicePort) values(#{userName},#{serviceName},#{servicePort})")
    public int insertServiceInfo(@RequestBody ServiceInfo serviceInfo);


    @Delete("delete from serviceinfo where userName=#{userName}")
    public boolean deleteOneServiceInfoByUserName(String userName);

    @Update("update serviceinfo set userName=#{newUserName},serviceName=#{newServiceName},servicePort=#{newServicePort} where userName=#{userName}")
    public boolean updateUser(@Param("newUserName") String newUserName, @Param("newServiceName") String newServiceName, @Param("newServicePort") String newServicePort, @Param("userName") String userName);

}
