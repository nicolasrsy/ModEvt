/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.main;

import appli.context.AppContext;
import appli.objets.CommandeReappro;
import appli.objets.Produit;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carine
 */
public class AppliEvenementInterne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            AppContext context=new AppContext();
            Produit p =new Produit(20, 1, 10,"ramettes papier A4" );
            context.addProduit(p);
            p.setStock(8);
            System.out.println ("Après rupture de stock");
            System.out.println ("liste produits :");
            AffichageCollections(context.getProduits());
            System.out.println ("liste commandeReappro :");
            AffichageCollections(context.getCommandeReappros());
            p.setStock(6);
            System.out.println ("Après décrément du stock");
            System.out.println ("liste produits :");
            AffichageCollections(context.getProduits());
            System.out.println ("liste commandeReappro :");
            AffichageCollections(context.getCommandeReappros());
            System.out.println ("arrivée de réappro");
            Thread.sleep(10);
            CommandeReappro e=context.getCommandeReappro(1);
            context.ArriveeReappro(e);
            System.out.println ("liste produits :");
            AffichageCollections(context.getProduits());
            System.out.println ("liste commandeReappro :");
            AffichageCollections(context.getCommandeReappros());
            System.out.println ("liste Relances :");
            AffichageCollections(context.getRelances());
            p.setSeuilRupture (16);

            System.out.println ("Après changement seuil de rupture");
            System.out.println ("liste produits :");
            AffichageCollections(context.getProduits());
            System.out.println ("liste commandeReappro :");
            AffichageCollections(context.getCommandeReappros());
            System.out.println ("liste Relances :");
            AffichageCollections(context.getRelances());
            context.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(AppliEvenementInterne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
static void AffichageCollections (Collection c){
    Iterator i=c.iterator();
    while (i.hasNext()){
        System.out.println ("element "+i.next().toString());
    }
}
}
