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

}
