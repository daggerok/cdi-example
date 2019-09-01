package daggerok.weld.mybeans;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class MyService {

    @PostConstruct
    public void init() {
        log.info("initialized!");
    }

    public String sayHi() {
        return "hi";
    }
}
