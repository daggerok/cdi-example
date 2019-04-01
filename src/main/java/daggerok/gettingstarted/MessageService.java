package daggerok.gettingstarted;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;

import javax.inject.Inject;

@AllArgsConstructor(onConstructor_ = @Inject)
public class MessageService {

  private Logger log;
  private Message message;

  public void greet() {
    log.info("message: {}", message.get());
  }
}
