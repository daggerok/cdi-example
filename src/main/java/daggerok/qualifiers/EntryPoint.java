package daggerok.qualifiers;

import daggerok.qualifiers.greeting.GreetingMessageService;
import daggerok.qualifiers.informal.InformalGreetingMessageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Inject)
public class EntryPoint {

  private Logger log;
  private GreetingMessageService greetingMessageService;
  private InformalGreetingMessageService informalGreetingMessageService;

  public void observe(@Observes ContainerInitialized event) {
    log.debug("received: {}", event);
    greetingMessageService.greet();
    informalGreetingMessageService.greet();
  }
}
