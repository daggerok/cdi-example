package daggerok.logs;

import daggerok.logs.payment.PaymentBean;
import lombok.SneakyThrows;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class App {

  @Inject
  private PaymentBean paymentBean;

  @SneakyThrows
  public void on(@Observes ContainerInitialized event) {
    paymentBean.pay();
    paymentBean.reset();
    paymentBean.pay();
  }
}
