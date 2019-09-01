package daggerok.conversationscoped;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.context.ConversationContext;
import org.jboss.weld.context.unbound.UnboundLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.jboss.weld.inject.WeldInstance;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.TransientReference;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import java.beans.Transient;
import java.io.Serializable;
import java.util.concurrent.ConcurrentSkipListMap;

@Slf4j
@ApplicationScoped
class UpperCaseRequester {

    @Any
    @Inject
    @Default
    private Instance<Messages> responders;

    private void on(@Observes ContainerInitialized event) {
        log.info(event.toString());
        // log.info(responders.stream()
        //                    .collect(toCollection(CopyOnWriteArrayList::new))
        //                    .toString());
        responders.stream()
                  .map(messages -> messages.add("hello"))
                  .map(messages -> messages.add("world"))
                  .flatMap(messages -> messages.complete()
                                               .entrySet()
                                               .stream())
                  .map(Object::toString)
                  .forEach(log::info);
    }
}

@Slf4j
@ConversationScoped
class Messages implements Serializable {

    private static final long serialVersionUID = 2582346501954537983L;

    @Inject
    private Conversation conversation;

    @Getter
    private ConcurrentSkipListMap<Long, String> messages;

    @PostConstruct
    public void init() {
        log.info("...");
        messages = new ConcurrentSkipListMap<>();
    }

    private void on(@Observes ContainerInitialized event) {
        log.info(event.toString());
    }

    public Messages add(String message) {
        if (conversation.isTransient()) {
            conversation.setTimeout(123456);
            conversation.begin();
            log.debug("conversation started");
        }
        messages.put(System.currentTimeMillis(), message);
        log.debug("message added");
        return this;
    }

    public ConcurrentSkipListMap<Long, String> complete() {
        conversation.end();
        log.debug("conversation ended");
        return messages;
    }
}

@Slf4j
public class App {

    public static void main(String[] args) {

        // SeContainerInitializer.newInstance()
        //                       .addPackages(true, App.class)
        //                       .initialize();
        log.info("doesnt worked: No active contexts for scope type javax.enterprise.context.ConversationScoped");

        // Weld weld = new Weld();
        // WeldContainer container = weld.disableDiscovery()
        //                               .addPackages(true, App.class)
        //                               .initialize();
        //
        // Instance<ConversationContext> conversationContextInstance =
        //         container.select(ConversationContext.class, UnboundLiteral.INSTANCE);
        // ConversationContext ctx = conversationContextInstance.get();
        // ctx.activate();
        //
        // WeldInstance<UpperCaseRequester> requesterInstance =
        //         container.select(UpperCaseRequester.class, UnboundLiteral.INSTANCE);
        // requesterInstance.get();
        log.info("and this example too (WELD-001303)...");
    }
}
