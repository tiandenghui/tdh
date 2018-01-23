package com.kuliao.horcrux.app.service.fallback;

import com.kuliao.horcrux.app.service.ServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements ServiceFeign {

    @Override
    public String sayHello(String name) {
        return "FeignFallback...";
    }
}
