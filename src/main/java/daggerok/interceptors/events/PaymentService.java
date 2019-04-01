package daggerok.interceptors.events;

import daggerok.interceptors.logging.Logged;
import lombok.ToString;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

@Logged
@ToString
@Singleton
public class PaymentService {

  @Inject
  private Event<PaymentEvent> payments;

  @Inject
  private Event<ResetEvent> resets;

  public void pay(String payload) {
    payments.fire(new PaymentEvent(payload));
  }

  public void reset(String payload) {
    resets.fire(new ResetEvent(payload));
  }
}
