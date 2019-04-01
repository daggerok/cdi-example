package daggerok.gettingstarted;

import lombok.AllArgsConstructor;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@AllArgsConstructor(onConstructor_ = @Inject)
public class EntryPoint {

  private Logger log;
  private MessageService messageService;

  public void observe(@Observes ContainerInitialized event) {
    log.debug("received: {}", event);
    messageService.greet();
  }
}
