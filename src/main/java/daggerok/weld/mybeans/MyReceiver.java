package daggerok.weld.mybeans;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.event.Observes;

@Slf4j
public class MyReceiver {
    private void on(@Observes String message) {
        log.info("received: {}", message);
    }
}
