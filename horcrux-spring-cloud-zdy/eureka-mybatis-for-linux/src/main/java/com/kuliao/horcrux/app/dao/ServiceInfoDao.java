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

    public ServiceInfo findUser(String serviceName) {
        return serviceInfoMapper.findUser(serviceName);
    }

    public int insertOneService(Integer serviceId,String userName, String serviceName, String servicePort) {
        return serviceInfoMapper.insertOneService(serviceId,userName, serviceName, servicePort);
    }

    //方式2
    public int insertServiceInfo(ServiceInfo serviceInfo) {
        return serviceInfoMapper.insertServiceInfo(serviceInfo);
    }

    public boolean deleteOneServiceInfoByUserName(String userName) {
        return serviceInfoMapper.deleteOneServiceInfoByUserName(userName);
    }

    public boolean updateUser(ServiceInfo serviceInfo, String userName) {
        return serviceInfoMapper.updateUser(serviceInfo.getServiceId(),serviceInfo.getUserName(), serviceInfo.getServiceName(), serviceInfo.getServicePort(), userName);
    }
}
