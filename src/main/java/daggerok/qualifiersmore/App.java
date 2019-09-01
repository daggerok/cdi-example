package daggerok.qualifiersmore;

import daggerok.qualifiersmore.encriptions.CustomDigestAlgorithm;
import daggerok.qualifiersmore.qualifiers.PlainText;
import daggerok.qualifiersmore.qualifiers.Sha256;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import javax.inject.Singleton;

@ApplicationScoped
public class App {

    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                              // .disableDiscovery()
                              .addPackages(true, App.class)
                              .initialize();
    }

    @Slf4j
    @Singleton
    public static class Service1 {

        @Inject
        @PlainText
        CustomDigestAlgorithm customDigestAlgorithm;

        private void on(@Observes ContainerInitialized event) {
            String string = "hello!";
            String encrypted = customDigestAlgorithm.encrypt(string);
            log.info("{} <-> {}", string, encrypted);
        }
    }

    @Slf4j
    @Singleton
    public static class Service2 {

        @Inject
        @Sha256
        CustomDigestAlgorithm customDigestAlgorithm;

        private void on(@Observes ContainerInitialized event) {
            String string = "hello!";
            String encrypted = customDigestAlgorithm.encrypt(string);
            log.info("{} <-> {}", string, encrypted);
        }
    }
}
