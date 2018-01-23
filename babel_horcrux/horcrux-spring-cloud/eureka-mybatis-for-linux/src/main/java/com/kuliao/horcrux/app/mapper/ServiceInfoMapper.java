package com.kuliao.horcrux.app.mapper;

import com.kuliao.horcrux.app.model.ServiceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;


@Mapper
public interface ServiceInfoMapper {

    @Select("select * from serviceinfo where user_name=#{userName}")//mybatis的注解
    public ServiceInfo findService(String userName);

    @Select("select * from serviceinfo where service_name=#{serviceName}")
    public ServiceInfo findUser(String serviceName);   //从model层对象开始

    @Insert("insert into serviceinfo(service_id,user_name,service_name,service_port) values(#{serviceId},#{userName},#{serviceName},#{servicePort})")
    public int insertOneService(@Param("serviceId") Integer serviceId,@Param("userName") String userName, @Param("serviceName") String serviceName, @Param("servicePort") String servicePort);

    @Insert("insert into serviceinfo(service_id,user_name,service_name,service_port) values(#{serviceId},#{userName},#{serviceName},#{servicePort})")
    public int insertServiceInfo(@RequestBody ServiceInfo serviceInfo);


    @Delete("delete from serviceinfo where user_name=#{userName}")
    public boolean deleteOneServiceInfoByUserName(String userName);

    @Update("update serviceinfo set service_id=#{newServiceId},user_name=#{newUserName},service_name=#{newServiceName},service_port=#{newServicePort} where user_name=#{userName}")
    public boolean updateUser(@Param("serviceId") Integer newServiceId,@Param("newUserName") String newUserName, @Param("newServiceName") String newServiceName, @Param("newServicePort") String newServicePort, @Param("userName") String userName);

}
