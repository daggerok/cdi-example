package daggerok.weld;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;

@Slf4j
class MyService {
    public String sayHi() {
        String greeting = "hi!";
        log.info(greeting);
        return greeting;
    }
}

@Slf4j
class MyApp implements Runnable {

    @Inject
    MyService myService;

    @Override
    public void run() {
        log.info("I'm saying: {}", myService.sayHi());
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
        container.shutdown();
    }
}
