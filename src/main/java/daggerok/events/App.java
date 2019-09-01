package daggerok.events;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.event.Observes;

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

public class App {

    public static void main(String[] args) {
        try (WeldContainer container = new Weld().disableDiscovery()
                                                 .addPackages(false, App.class)
                                                 .initialize()) {
            container.event()
                     .select(MyEvent.class)
                     .fire(MyEvent.of("ololo-trololo"));
        }
    }
}
