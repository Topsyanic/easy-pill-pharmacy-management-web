/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Topsy
 */

@Logged
@Interceptor

public class LoggingInterceptor {

    private static final Logger LOG = Logger.getLogger(LoggingInterceptor.class.getName());

    public LoggingInterceptor() {
    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        LOG.log(Level.INFO, "Entering {1}:{0}() ", new Object[]{ctx.getMethod().getName(), ctx.getMethod().getDeclaringClass().getName()});
        return ctx.proceed();
    }
}
