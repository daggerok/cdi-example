package daggerok.cdiextension.my;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class CdiExtension implements Extension {

    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event) {
        System.out.println("before...");
    }

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery event) {
        System.out.println("after...");
    }
}
