package daggerok.events;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Value(staticConstructor = "of")
class MyEvent {
    final String message;
}

@Slf4j
class MyEventListener {

    private void on(@Observes MyEvent event) {
        log.info("received: {}", event);
    }
}

@Slf4j
@ApplicationScoped
class MyEventProducer {

    @Inject
    private Event<MyEvent> events;

    private void on(@Observes @Initialized(ApplicationScoped.class) ContainerInitialized event) {
        events.fire(MyEvent.of("trololo-ololo..."));
    }
}

public class App {

    public static void main(String[] args) {
        try (WeldContainer container = new Weld().disableDiscovery()
                                                 .addPackages(false, App.class)
                                                 .initialize()) {
            container.event()
                     .select(MyEvent.class)
                     .fire(MyEvent.of("and ololo-trololo!"));
        }
    }
}
