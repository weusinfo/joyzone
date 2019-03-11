package com.joyzone.platform.datasource.aspect.annotation;

import java.lang.annotation.*;

/**
 * 
 * @author Louis.he
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
