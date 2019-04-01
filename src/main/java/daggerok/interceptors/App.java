package daggerok.interceptors;

import daggerok.interceptors.events.PaymentService;
import lombok.SneakyThrows;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class App {

  @Inject
  private PaymentService paymentService;

  @SneakyThrows
  public void on(@Observes ContainerInitialized event) {
    System.out.println(event);
    paymentService.pay("hello");
    paymentService.pay("world");
    paymentService.reset("good bye...");
  }
}
