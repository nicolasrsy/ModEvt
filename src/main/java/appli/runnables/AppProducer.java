package appli.runnables;

import appli.objets.Client;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@ApplicationScoped
public class AppProducer implements Runnable {

    //nous récupérons à l'aide de CDI une fabrique de connexions JMS
    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = "quarkus.artemis.username")
    String userName;

    private final Random random = new Random();


    //planificateur d'exécution de tache
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

    //cette méthode est appellées lorsque l'initialisation de quarkus est terminée
    void onStart(@Observes StartupEvent ev) {
        //on planifie l'exécution de la méthode run() de cette classe:
        // - immédiatement (initialDelay=0
        // - toute les 5s (period = 5L, unit = secondes)
        scheduler.scheduleAtFixedRate(this, 0L, 5L, TimeUnit.SECONDS);
    }

    //cette méthode est appellées lorsque quarkus s'arrète
    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        nouveauClient("queue/client", new Client(random.nextInt(100000),"Paul", "Robert", "1 rue des rues" ));
        //la meme avec livraiso
    }

    private void nouveauClient(String destination, Client client){
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue(destination), client);
        }
    }

    private void livraison(String destination){
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue(destination), 1);
        }
    }
}