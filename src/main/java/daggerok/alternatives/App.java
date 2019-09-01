package daggerok.alternatives;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.interceptor.Interceptor;

interface MyBean {
    String getValue();
}

@Slf4j
class MyOriginalBean implements MyBean {

    @PostConstruct
    public void init() {
        log.info("I was created!");
    }

    @Override
    public String getValue() {
        return "I'm am an original one!";
    }
}

@Slf4j
@Alternative // Required: annotation must be enabled If two beans of same interface type are present in same codebase
@Priority(Interceptor.Priority.APPLICATION) // Optional: when enabled will be chosen... comment / uncomment to test
class MyAlternativeBean implements MyBean {

    @PostConstruct
    public void init() {
        log.info("I was created!");
    }

    @Override
    public String getValue() {
        return "I'm a fake!";
    }
}

@Slf4j
@Singleton
class Consumer {

    @Inject
    MyBean myBean;

    @PostConstruct
    public void init(/*@Observes ContainerInitialized event*/) {
        log.info("{}: {}", myBean, myBean.getValue());
    }
}

@Slf4j
public class App {

    public static void main(String[] args) {
        log.debug("use explicit beans definition to enable alternatives...");
        // javax.enterprise.inject.se.SeContainerInitializer.newInstance()
        //                                                  .addPackages(true, App.class)
        //                                                  .initialize();
        new Weld().enableDiscovery()
                  .addPackages(true, App.class)
                  .initialize();
    }
}
