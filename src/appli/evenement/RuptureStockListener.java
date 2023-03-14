/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.evenement;

import appli.context.AppContext;
import appli.objets.CommandeReappro;
import appli.objets.Produit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author carine
 */
public class RuptureStockListener implements PropertyChangeListener {
    
    final private AppContext context;
    
    public RuptureStockListener(AppContext app){
        context=app;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (predicat_evt (evt)) trigger_evt (evt);
        
    }
    
    private boolean predicat_evt (PropertyChangeEvent evt){
        boolean res=false;
        if (evt.getPropertyName().equals("stock")) {
                Produit p = (Produit)evt.getSource();
                Integer oldValue=(Integer) evt.getOldValue();
                Integer newValue=(Integer) evt.getNewValue();
                Integer s = p.getSeuilRupture();
                res=(oldValue>=s && (newValue<s));
        } else 
            if (evt.getPropertyName().equals("seuilRupture")) {
                Produit p = (Produit)evt.getSource();
                Integer oldValue=(Integer) evt.getOldValue();
                Integer newValue=(Integer) evt.getNewValue();
                Integer stock = p.getStock();
                res=((stock>oldValue) && (stock<=newValue));
        }    
        return res;
    }
    
    private void trigger_evt (PropertyChangeEvent evt){
        
        // Traitement rupture de stock par le stock ou par le seuil
        System.out.println ("trigger Event ");
        System.out.println ("produit :" + evt.getSource());
        System.out.println("property " + evt.getPropertyName()+" old "+ evt.getOldValue()+ " new "+ evt.getNewValue());
        
        Integer qte=((Produit)evt.getSource()).getSeuilRupture()+50;
        DateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
        String d = fd.format(Calendar.getInstance().getTime());    
        String c;
        if (evt.getPropertyName().equals("stock")) c="Générée suite à une rupture de stock";
        else c="Générée suite à un changement de seuil";
        CommandeReappro e=new CommandeReappro (context.getMaxIdReappro()+1,qte,(Produit)(evt.getSource()),d,c);
        context.addCommandeReappro(e);
    }
}
