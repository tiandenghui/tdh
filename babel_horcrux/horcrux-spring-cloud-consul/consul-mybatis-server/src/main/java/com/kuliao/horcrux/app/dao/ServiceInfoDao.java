package com.kuliao.horcrux.app.dao;

import com.kuliao.horcrux.app.mapper.ServiceInfoMapper;
import com.kuliao.horcrux.app.model.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceInfoDao {

    @Autowired
    ServiceInfoMapper serviceInfoMapper;

    public ServiceInfo findService(String userName) {
//        ServiceInfo serviceInfo = new ServiceInfo();
//        serviceInfo.setServiceName(userName);
//        return serviceInfo;
        return serviceInfoMapper.findService(userName);
    }

}
