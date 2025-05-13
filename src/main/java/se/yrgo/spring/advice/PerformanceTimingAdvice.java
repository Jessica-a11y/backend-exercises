package se.yrgo.spring.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class PerformanceTimingAdvice {

    public Object performTimingMeasurement(ProceedingJoinPoint method) throws Throwable{
        double start = System.nanoTime();

        try {
            Object object = method.proceed();
            return object;
        } finally {
            double end = System.nanoTime();
            double timeTaken = end - start;
            System.out.println("Method " + method.getSignature().getName() + " from " + method.getTarget().getClass() +  " took " + timeTaken + "ms");
        }
    }

    public void beforeAdviceTesting(JoinPoint jp) {
        System.out.println("This is wrtitten before "+ jp.getSignature().getName() +" runs");
    }

    /* Around Advice
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.nanoTime();

        try {
            Object value = invocation.proceed();
            return value;
        } finally {
            long end = System.nanoTime();
            long timeTaken = end - start;
            System.out.println("The method " + invocation.getMethod().getName() + " took " + timeTaken/1000000);
        }
    } */

   /*  Before Advice Method
   
   @Override
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
        System.out.println("Starting the before advice");
    } */

    /* After Advice Method

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Method returned this value: " + returnValue);
    } */
    
}
