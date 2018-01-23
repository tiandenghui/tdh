package com.kuliao.horcrux.app.service;

import com.kuliao.horcrux.app.dao.ServiceInfoDao;
import com.kuliao.horcrux.app.model.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceInfoService {


    @Autowired
    ServiceInfoDao serviceInfoDao;

    public ServiceInfo getServiceInfoByUserName(String userName) {
        return serviceInfoDao.findService(userName);
    }

    public ServiceInfo getServiceInfoByServiceName(String serviceName){return  serviceInfoDao.findUser(serviceName);}


    public int insertOneService(String userName,String serviceName,String servicePort) {
        return serviceInfoDao.insertOneService(userName,serviceName,servicePort);
    }

    public int insertServiceInfo(ServiceInfo serviceInfo) {
        return serviceInfoDao.insertServiceInfo(serviceInfo);
    }

    public boolean deleteOneUserByUserName(String userName)
    {
        return serviceInfoDao.deleteOneServiceInfoByUserName(userName);
    }

    public boolean updateOneUserByName(ServiceInfo serviceInfo,String userName)
    {
        return serviceInfoDao.updateUser(serviceInfo,userName);
    }

}
