package daggerok.weld;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Slf4j
class MyService {

    @PostConstruct
    public void init() {
        log.info("initialized!");
    }

    String sayHi() {
        return "hi";
    }
}

@Slf4j
class MyApp {

    @Inject
    MyService myService;

    void run() {
        log.info("I'm saying: {}", myService.sayHi());
    }
}

@Slf4j
class MyReceiver {
    private void on(@Observes String message) {
        log.info("received: {}", message);
    }
}

public class App {

    public static void main(String[] args) {
        WeldContainer container = new Weld().disableDiscovery()
                                            .addPackages(true, App.class)
                                            .initialize();
        MyApp myApp = container.select(MyApp.class)
                               .get();
        myApp.run();
        container.event().select(String.class).fire("Hola!");
        container.shutdown(); // container will be shutdown only after all events get received...
    }
}
