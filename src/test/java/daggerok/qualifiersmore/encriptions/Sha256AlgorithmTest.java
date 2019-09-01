package daggerok.qualifiersmore.encriptions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Sha256AlgorithmTest {

    @Test
    void test() {
        Sha256Algorithm algorithm = new Sha256Algorithm();
        String encrypted = algorithm.encrypt("hello!");
        log.info(encrypted);
    }
}
