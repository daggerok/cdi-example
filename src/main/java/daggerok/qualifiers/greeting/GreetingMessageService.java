package daggerok.qualifiers.greeting;

import org.slf4j.Logger;

import javax.inject.Inject;

public class GreetingMessageService {

  @Inject
  private Logger log;

  @Inject
  private Greeting greeting;

  public void greet() {
    log.info("message: {}", greeting.greet("@Inject Greeting greeting"));
  }
}
