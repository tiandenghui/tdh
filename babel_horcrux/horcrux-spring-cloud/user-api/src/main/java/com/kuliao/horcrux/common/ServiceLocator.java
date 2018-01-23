package com.kuliao.horcrux.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kuliao.horcrux.api.OrderToolService;
import com.kuliao.horcrux.api.TestToolService;

/**
 * 系统调用各业务接口类
 */
public final class ServiceLocator
{
    private static ServiceLocator instance;
    
    private static ClassPathXmlApplicationContext context = null;

    public static ServiceLocator getInstance()
    {
        if(instance!=null) return instance;
        else return instance = new ServiceLocator();
    }
    
    static{
    	context = new ClassPathXmlApplicationContext(new String[] { "classpath:spring-consumer.xml" });
		context.start();
    }
    
    private Object getService(String service)
    {
        return context.getBean(service);
    }
    
    /**
     * 测试业务接口
     * @return IUserDBService
     */
    public TestToolService getTestToolService()
    {
        return (TestToolService)this.getService("testToolService");
    }
    
    /**
     * 订单信息业务接口
     * @return IUserAccountInfoDBService
     */
    public OrderToolService getOrderToolService()
    {
        return (OrderToolService)this.getService("orderToolService");
    }
}