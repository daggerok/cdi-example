package daggerok.any;

import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import javax.inject.Singleton;

public class App {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                              .addPackages(true, App.class)
                              .initialize();
    }

    @Slf4j
    @Singleton
    public static class AllBeansRuntimeScanner {

        @Inject @Any
        Instance<I> instances;

        private void on(@Observes ContainerInitialized event) {
            for (I instance : instances) {
                log.info("{}", instance);
            }
        }
    }

    public interface I {}

    @Singleton
    public static class I1 implements I {}

    @Singleton
    public static class I2 implements I {}

    @Singleton
    public static class I3 implements I {}

    @Singleton
    public static class I4 implements I {}
}
