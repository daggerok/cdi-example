package daggerok.weld.mybeans;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
public class MyApp {

    @Inject
    private MyService myService;

    public void run() {
        log.info("I'm saying: {}", myService.sayHi());
    }
}
