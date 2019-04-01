package daggerok.interceptors.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerService {

  @Produces
  public Logger produceLogger(InjectionPoint injectionPoint) {
    final String name = injectionPoint.getMember().getDeclaringClass().getName();
    return LoggerFactory.getLogger(name);
  }
}
