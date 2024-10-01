package fshare.tech.middleware.router.aop;

import fshare.tech.middleware.router.DBContextHolder;
import fshare.tech.middleware.router.annotation.DBRouter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: XiaoYe
 * @create: 2024-10-01 18:10
 * @description: TODO
 */
@Aspect
@Component
public class DBRouterJoinPoint {
    @Pointcut("@annotation(fshare.tech.middleware.router.annotation.DBRouter)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(dbRouter)")
    public Object doRouter(ProceedingJoinPoint jp, DBRouter dbRouter) throws Throwable {
        String dbKey = dbRouter.dbKey();
        if (StringUtils.isBlank(dbKey)) {
            throw new RuntimeException("annotation DBRouter key is null！");
        }
        DBContextHolder.setDBKey(dbRouter.dbKey());
        try {
            return jp.proceed();
        } finally {
            DBContextHolder.clearDBKey();
        }
    }
}
