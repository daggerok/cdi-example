package daggerok.qualifiers.greeting;

import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class Greeting {

  @Inject
  Logger log;

  @PostConstruct
  public void init() {
    log.info("{} initialized.", this.getClass());
  }

  public String greet(String name) {
    log.info("Greeting.greet execution");
    return "Hello, " + name + ".";
  }
}
