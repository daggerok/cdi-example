package daggerok.cdiextension.myextension;

import javax.enterprise.event.Reception;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.spi.ObserverMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Set;

public class MyExternalObserver implements ObserverMethod<Object> {

    @Override
    public Class<?> getBeanClass() {
        return null; // LibraryFromNotOwnedCode.class i'm extending...
    }

    @Override
    public Type getObservedType() {
        return Object.class; // listening event
    }

    @Override
    public Set<Annotation> getObservedQualifiers() {
        return Collections.emptySet();
    }

    @Override
    public Reception getReception() {
        return Reception.ALWAYS;
    }

    @Override
    public TransactionPhase getTransactionPhase() {
        // here where magic is happening
        return null;
    }
}
