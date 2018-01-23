package com.kuliao.babelhorcrux.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


//自定义过滤类  用来处理请求
@Component
public class AccessFilter extends ZuulFilter {

    //首先定义过滤类上传的日志类型
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    //过滤的类型
    @Override
    public String filterType() {
        return "pre";
    }

    //开启的顺序  数值越小优先级越高
    @Override
    public int filterOrder() {
        return 0;
    }

    // 是否启用
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //具体的实现
    @Override
    public Object run() {
        RequestContext rtx = RequestContext.getCurrentContext();
        HttpServletRequest request = rtx.getRequest();
        //日志信息   就是只打印信息
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
//        //访问令牌   获取参数 看请求是不是携带了令牌
//        Object accessToken = request.getParameter("accessToken");
//        if(accessToken == null) {
//            //打印出警告  还有其他 如 debug error 等类型的日志
//            logger.warn("access token is empty");  //日志警告
//            //用的最多的方法就是set
//            rtx.setSendZuulResponse(false);
//            rtx.setResponseStatusCode(404);
//            return null;
//        }
//        logger.info("access token ok");
        return null;
    }
}
