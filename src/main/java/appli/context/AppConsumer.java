package appli.context;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;

public class AppConsumer implements Runnable{

    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name = "quarkus.artemis.username")
    String userName;

    //indique si la classe est configurée pour recevoir les messages en boucle
    boolean running;

    //cette méthode démarre un nouveau thread exécutant l'instance en cours, jusqu'à ce que la variable running soit false.
    void onStart(@Observes StartupEvent ev) {
        running = true;
        new Thread(this).start();
    }


    void onStop(@Observes ShutdownEvent ev) {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
                //reçoit un message à partir de la queue queue/prices
                Message mess = context.createConsumer(context.createQueue("queue/prices"+userName)).receive();
                //converti ce message en int
                int price = Integer.parseInt(mess.getBody(String.class));
                //affiche le résultat dans la console
                System.out.println("from the consumer: " +price);

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
