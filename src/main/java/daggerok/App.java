package daggerok;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

@Slf4j
public class App {
  public static void main(String[] args) {
    SeContainer container = SeContainerInitializer.newInstance().initialize();
    log.info("yo!");
  }
}
