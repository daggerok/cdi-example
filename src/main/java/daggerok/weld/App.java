package daggerok.weld;

import daggerok.weld.mybeans.MyApp;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class App {

    public static void main(String[] args) {
        WeldContainer container = new Weld().disableDiscovery()
                                            .addPackages(true, App.class)
                                            .initialize();
        MyApp myApp = container.select(MyApp.class)
                               .get();
        myApp.run();
        container.event().select(String.class).fire("Hola!");
        container.shutdown(); // container will be shutdown only after all events get received...
    }
}
