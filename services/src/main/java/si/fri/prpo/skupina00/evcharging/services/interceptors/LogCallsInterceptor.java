package si.fri.prpo.skupina00.evcharging.services.interceptors;

import si.fri.prpo.skupina00.evcharging.services.annotations.LogCalls;
import si.fri.prpo.skupina00.evcharging.services.beans.LogCallsBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LogCalls
public class LogCallsInterceptor{

    @Inject
    private LogCallsBean logCallsBean;

    @AroundInvoke
    public Object logCalls(InvocationContext context) throws Exception {
        logCallsBean.methodCallCounter(context.getMethod().hashCode(), context.getMethod().getName());

        return context.proceed();
    }
}
