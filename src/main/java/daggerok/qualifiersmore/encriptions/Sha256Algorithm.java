package daggerok.qualifiersmore.encriptions;

import daggerok.qualifiersmore.qualifiers.Sha256;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Sha256
public class Sha256Algorithm implements CustomDigestAlgorithm {

    @Override
    public String encrypt(String plainTextPassword) {
        return Try.of(() -> {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(plainTextPassword.getBytes(UTF_8));
            byte[] passwordDigest = messageDigest.digest();
            return new String(Base64.getEncoder().encode(passwordDigest), UTF_8);
        }).getOrElseThrow(throwable -> {
            log.error(throwable.getLocalizedMessage());
            return new RuntimeException(throwable);
        });
    }
}
