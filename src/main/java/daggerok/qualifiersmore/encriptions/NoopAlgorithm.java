package daggerok.qualifiersmore.encriptions;

import daggerok.qualifiersmore.qualifiers.PlainText;

@PlainText
public class NoopAlgorithm implements CustomDigestAlgorithm {

    @Override
    public String encrypt(String plainTextPassword) {
        return plainTextPassword;
    }
}
