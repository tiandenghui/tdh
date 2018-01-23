package com.kuliao.horcrux.app.dao;

import com.kuliao.horcrux.app.mapper.ServiceInfoMapper;
import com.kuliao.horcrux.app.model.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public int insertOneService(String userName, String serviceName, String servicePort) {
        return serviceInfoMapper.insertOneService(userName, serviceName, servicePort);
    }

    //方式2
    public int insertServiceInfo(ServiceInfo serviceInfo) {
        return serviceInfoMapper.insertServiceInfo(serviceInfo);
    }

    public boolean deleteOneServiceInfoByUserName(String userName) {
        return serviceInfoMapper.deleteOneServiceInfoByUserName(userName);
    }

    public boolean updateUser(ServiceInfo serviceInfo, String userName) {
        return serviceInfoMapper.updateUser(serviceInfo.getUserName(), serviceInfo.getServiceName(), serviceInfo.getServicePort(), userName);
    }
}
