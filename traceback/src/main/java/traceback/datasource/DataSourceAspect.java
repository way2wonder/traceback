package traceback.datasource;


import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(0) 
public class DataSourceAspect
{
    @Pointcut("execution( * traceback.service.impl.BreedServiceImpl.*(..))")
    public void pointCut()
    {}
    
    @Pointcut("execution( * traceback.synchronization.service.*.*(..))")
    public void pointCut2(){}
    
    @Pointcut("@annotation(traceback.datasource.DataSource)") 
    public void changeDataSource() {} 

   // @Before(value = "pointCut() || pointCut2()")
    @Before(value = "changeDataSource()")
    public void before(JoinPoint point)
    {
        Object target = point.getTarget();

        String method = point.getSignature().getName();

        Class<? extends Object> classz = target.getClass();

        Class<?>[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
        try
        {
            Method m = classz.getMethod(method, parameterTypes);

            if (m != null && m.isAnnotationPresent(DataSource.class))
            {
                DataSource data = m.getAnnotation(DataSource.class);
                HandleDataSource.putDataSource(data.value());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}