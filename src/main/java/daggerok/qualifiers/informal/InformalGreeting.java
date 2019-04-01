package daggerok.qualifiers.informal;

import daggerok.qualifiers.greeting.Greeting;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Informal
public class InformalGreeting extends Greeting {

  @Inject
  Logger log;

  @PostConstruct
  public void init() {
    log.info("{} initialized.", this.getClass());
  }

  public String greet(String name) {
    log.info("InformalGreeting.greet execution");
    return "Hi, " + name + "!";
  }
}
