package com.joyzone.platform.datasource.annotation;


import java.lang.annotation.*;

/**
 * 
 * @author Louis.he
 *Configure multiple datasource
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
