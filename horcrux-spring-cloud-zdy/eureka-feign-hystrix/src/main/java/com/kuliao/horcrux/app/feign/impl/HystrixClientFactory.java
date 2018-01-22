package com.kuliao.horcrux.app.feign.impl;

import com.kuliao.horcrux.app.entity.User;
import com.kuliao.horcrux.app.feign.FeignHystrixClient;
import com.kuliao.horcrux.app.feign.FeignHystrixFactory;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFactory implements FallbackFactory<FeignHystrixClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFactory.class);

    @Override
    public FeignHystrixClient create(Throwable cause) {
        LOGGER.info("fallback; reason was: {}", cause.getMessage());

        return new FeignHystrixFactory() {
            @Override
            public User findById(Long id) {
                User user = new User();
                user.setId(-1L);
                return user;
            }

            @Override
            public String getMsg() {
                return "getMsg fallback...";
            }
        };
    }
}
