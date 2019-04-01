package daggerok.interceptors.logging;

import org.slf4j.Logger;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.Objects;

@Logged
@Dependent
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggedInterceptor {

  @Inject
  Logger log;

  //@javax.interceptor.AroundConstruct
  //@javax.annotation.PostConstruct
  @AroundInvoke
  public Object logAction(InvocationContext context) throws Exception {
    final Method method = context.getMethod();
    final String aClass = method.getDeclaringClass().getName();
    final String aMethod = method.getName();
    log.info("{}.{} params: {}", aClass, aMethod, context.getParameters());
    try {
      final Object result = context.proceed();
      if (Objects.nonNull(result)) log.info("result: {}", result);
      return result;
    } catch (Exception e) {
      log.error("{}.{} error: {}", aClass, aMethod, e.getLocalizedMessage(), e);
      throw e;
    }
  }
}
