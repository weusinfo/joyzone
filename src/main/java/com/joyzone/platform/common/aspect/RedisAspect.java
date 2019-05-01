package com.joyzone.platform.common.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.joyzone.platform.common.exception.JZException;

/**
 * Redis切面处理类
 *
 * @author Louis.he
 */
@Aspect
@Configuration
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //是否开启redis缓存  true开启   false关闭
    @Value("${spring.redis.open: true}")
    private boolean open;

    @Around("execution(* com.joyzone.platform.core.service.RedisService.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                logger.error("redis error", e);
                throw new JZException("Redis服务异常");
            }
        }
        return result;
    }
}
