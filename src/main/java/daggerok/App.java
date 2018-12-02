package daggerok;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.BeanManager;

@Slf4j
public class App {
  public static void main(String[] args) {
    SeContainer container = SeContainerInitializer.newInstance()
                                                  .setClassLoader(App.class.getClassLoader())
                                                  .addPackages(App.class,
                                                               BeanManager.class)
                                                  .initialize();
    log.info("yo!");
  }
}
