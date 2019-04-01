package daggerok.interceptors.events;

import lombok.ToString;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

@ToString
@Singleton
public class PaymentHandler {

  public void on(@Observes PaymentEvent event) {
    System.out.println(event);
  }

  public void on(@Observes ResetEvent event) {
    System.out.println(event);
  }
}
