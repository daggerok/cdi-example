package daggerok.interceptors.events;

import lombok.Value;

@Value
public class PaymentEvent {
  private final String payload;
}
