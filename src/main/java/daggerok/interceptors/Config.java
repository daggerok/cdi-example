package daggerok.interceptors;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.BeanManager;

public class Config {

  public static void main(String[] args) {

    SeContainerInitializer.newInstance()
                          .setClassLoader(Config.class.getClassLoader())
                          .addPackages(true, // recursively
                                       Config.class,
                                       BeanManager.class) // require <exclude name="org.jboss.weld.**"/> into beans.xml
                          .initialize();
  }
}
