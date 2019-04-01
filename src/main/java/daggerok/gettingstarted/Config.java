package daggerok.gettingstarted;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.BeanManager;

public class Config {

  public static void main(String[] args) {

    SeContainerInitializer.newInstance()
                          .setClassLoader(Config.class.getClassLoader())
                          .addPackages(Config.class,
                                       BeanManager.class)
                          .initialize();
/*
    final SeContainerInitializer initializer = SeContainerInitializer.newInstance()...
    try (final SeContainer container = initializer.initialize()) {
      final MessageService messageServices = container.select(MessageService.class)
                                                      .get();
      messageServices.greet();
    }
*/
  }
}
