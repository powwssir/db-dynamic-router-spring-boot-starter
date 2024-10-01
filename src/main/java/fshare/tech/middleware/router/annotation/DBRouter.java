package fshare.tech.middleware.router.annotation;

import java.lang.annotation.*;

/**
 * @author: XiaoYe
 * @create: 2024-10-01 18:09
 * @description: 路由注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DBRouter {
    /**
     * 分库字段
     * 示例："1": ds_0 ; "default": ds_default（前缀省略）
     */
    String dbKey() default "";
}
