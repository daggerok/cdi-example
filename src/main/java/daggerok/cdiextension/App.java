package daggerok.cdiextension;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.se.SeContainerInitializer;

@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("1. create META-INF/serives/javax.enterprise.inject.spi.Exstension file");
        SeContainerInitializer.newInstance()
                              .addPackages(true, App.class)
                              .initialize();
    }
}
