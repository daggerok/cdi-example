package daggerok.dependent;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import java.util.stream.IntStream;

@Slf4j
class Parent {

    @Inject
    private Instance<Child> children;

    @PostConstruct
    public void init() {
        log.info("{}: I was born!", this);
    }

    private void on(@Observes ContainerInitialized event) {
        IntStream.range(0, 3)
                 .forEach(value -> children.stream().forEach(child -> {}));
    }
}

@Slf4j
class Child {

    @PostConstruct
    public void init() {
        log.info("{}: I was born!", this);
    }
}

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("@Dependent scope will create new instance each time it's get injected!");
        SeContainerInitializer.newInstance()
                              .addPackages(true, App.class)
                              .initialize();
    }
}
