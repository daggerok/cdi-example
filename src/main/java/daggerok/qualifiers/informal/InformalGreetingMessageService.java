package daggerok.qualifiers.informal;

import daggerok.qualifiers.greeting.Greeting;
import org.slf4j.Logger;

import javax.inject.Inject;

public class InformalGreetingMessageService {

  @Inject
  private Logger log;

  @Inject
  @Informal // all you need is this! to change from default implementation...
  private Greeting greeting;

  public void greet() {
    log.info("message: {}", greeting.greet("@Inject @Informal InformalGreeting greeting"));
  }
}
