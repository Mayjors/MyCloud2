package com.example.demo.config;

import com.example.demo.entity.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@EnableAsync
public class SystemLogAspect {
    private String requestPath = null ; // 请求地址
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private String user = null; // 操作人
    private HttpServletRequest request = null;//请求

    @Pointcut("@annotation(com.example.demo.config.OperationAnnotation)")
    public void logPointCut() {}

    @Before(value = "logPointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println(System.currentTimeMillis());
    }

    @After(value = "logPointCut()")
    public void after(JoinPoint joinPoint) {
        request = getHttpServletRequest();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        String title;
        String action;
        Integer sysType;
        Integer opType;
        Class<?>[] clazzs;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                clazzs = method.getParameterTypes();
                if (clazzs != null && clazzs.length == arguments.length && method.getAnnotation(OperationAnnotation.class) != null) {
                    request = getHttpServletRequest();
                    requestPath = request.getServletPath();
                    HttpSession session = request.getSession();
                    user = session.getAttribute("userName").toString();
                    title = method.getAnnotation(OperationAnnotation.class).content();
                    action = method.getAnnotation(OperationAnnotation.class).action();
                    sysType = method.getAnnotation(OperationAnnotation.class).sysType();
                    opType = method.getAnnotation(OperationAnnotation.class).opType();
                    endTimeMillis = System.currentTimeMillis();
                    SysLog log=new SysLog(user, requestPath,
                            (endTimeMillis-startTimeMillis)+"ms",
                            getNameAndValue(joinPoint).toString(), title, action,sysType,opType);
                    System.out.println("增加参数："+log);
//                    logMapper.insert(log);

                }
            }
        }
    }

    public Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if(paramValues[i] instanceof Integer || paramValues[i] instanceof String) {
                param.put(paramNames[i], paramValues[i]);
            }
        }
        return param;
    }

    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}
