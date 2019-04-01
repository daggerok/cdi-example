package daggerok.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {

  @Produces
  public Logger log(InjectionPoint injectionPoint) {
    final String name = injectionPoint.getMember().getDeclaringClass().getName();
    return LoggerFactory.getLogger(name);
  }
}
