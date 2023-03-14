/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.evenement;

import appli.context.AppContext;
import appli.objets.CommandeReappro;
import appli.objets.Relance;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 *
 * @author carine
 */
public class RelanceReappro extends TimerTask {
    
    final private CommandeReappro appro;
    final private AppContext context;
    
    public RelanceReappro(CommandeReappro appro, AppContext app){
        this.appro=appro;
        this.context=app;
        
    }
    public void run () {
        System.out.println("exécution relance");
        System.out.println("etat"+appro.getEtat());
        if (!appro.getEtat().equals("terminée")){
            DateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
            String d = fd.format(Calendar.getInstance().getTime());   
            Relance r=new Relance (context.getMaxIdRelance()+1,appro, d, "généré automatiquement après 1h");
            context.addRelance(r);
        }
        
    }
    
}
