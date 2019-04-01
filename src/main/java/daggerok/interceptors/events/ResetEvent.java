package daggerok.interceptors.events;

import lombok.Value;

@Value
public class ResetEvent {
  private final String payload;
}
