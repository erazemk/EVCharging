package si.fri.prpo.skupina00.evcharging.services.interceptors;

import si.fri.prpo.skupina00.evcharging.services.annotations.LogCalls;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@LogCalls
public class LogCallsInterceptor{

    private static final Logger log = Logger.getLogger(LogCallsInterceptor.class.getName());

    @AroundInvoke
    public Object logCalls(InvocationContext context) throws Exception {

        return context.proceed();
    }
}
