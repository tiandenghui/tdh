package com.kuliao.babelhorcrux.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ThrowExceptionFilter extends ZuulFilter {

    //首先定义过滤类上传的日志类型
    private static Logger LOGGER = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    //过滤器类型
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤器顺序，数值越小优先级越高
    @Override
    public int filterOrder() {
        return 0;
    }

    //过滤器开关
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        LOGGER.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
            //抛出运行时异常，此处要进行异常处理。两种方式
            //一种用try/catch，catch中直接在上下文中加入"error.status_code"
            //另一种使用使用error过滤器
            doSomething();

        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }

}
