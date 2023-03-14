/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.context;

import appli.evenement.RelanceReappro;
import appli.evenement.RuptureStockListener;
import appli.objets.Client;
import appli.objets.Commande;
import appli.objets.CommandeReappro;
import appli.objets.Produit;
import appli.objets.Relance;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;


/**
 *
 * @author carine
 */
public class AppContext {
    
     final private Map<Integer, Produit> produits;
     final private Map<Integer,CommandeReappro> reappros;
     final private Map<Integer, Relance> relances;
     final private Map<Integer, Commande> commandes;
     final private Map<Integer, Client> clients;
     final private PropertyChangeListener reappro;
     final private Timer agenda;
    
     
   public AppContext () { 
         produits=new HashMap<>();
         reappros=new HashMap<>();
         relances=new HashMap<>();
         commandes=new HashMap<>();
         clients=new HashMap<>();
         reappro=new RuptureStockListener (this);
         agenda=new Timer();// pour les événements temporels
    }
   
   public void close() {
       agenda.cancel();
   }
   
   public void addCommandeReappro(CommandeReappro e) {
       reappros.put(e.getId_reappro(),e);
       agenda.schedule (new RelanceReappro (e, this), 1 );
   }
   
   //traitnement événement externe
   public void ArriveeReappro(CommandeReappro e) {
       e.setEtat("terminée");
       Produit p = e.getProduit();
       p.setStock(p.getStock()+e.getQte());
   }
   
   //traitnement événement externe
   public void ArriveeCommande(Produit p, Integer qte, Client cl, String datecom) throws StockInsuffisantException {
       if (p.getStock()> qte){
           Integer id=getMaxIdCommande();
           Commande com=new Commande (id+1, cl, p, qte, datecom, "en_cours");
           commandes.put(id, com);
           p.setStock(p.getStock()-qte);
       } else throw new StockInsuffisantException();
       
       
       
   }
   public void addRelance(Relance e) {
       relances.put(e.getId_relance(),e);
   }
   
    // fonction pour récupérer l'identifiant max utiliser
   public Integer getMaxIdReappro (){
       if (!reappros.isEmpty()) {
           Set<Integer> s=reappros.keySet();
       return (Collections.max(s));
       } else return 0;
   }
   
   public Integer getMaxIdCommande (){
       if (!commandes.isEmpty()) {
           Set<Integer> s=commandes.keySet();
       return (Collections.max(s));
       } else return 0;
   }
   public Integer getMaxIdRelance (){
       if (!relances.isEmpty()) {
           Set<Integer> s=relances.keySet();
       return (Collections.max(s));
       } else return 0;
   }
   
   public Integer getMaxIdProduit (){
       if (!produits.isEmpty()) {
           Set<Integer> s=produits.keySet();
           return (Collections.max(s));
       } else return 0;
       
   }
   
   // fonction qui ajoute un produit et met en place les listeners
   public void addProduit (Produit e ) {
       e.addPropertyChangeListener("stock", reappro);
       e.addPropertyChangeListener("seuilRupture", reappro);
       produits.put(e.getId_produit(),e);
   }
   
   public Produit getProduit (Integer id) {
       return produits.get(id);
   }
   
   public Relance getRelance (Integer id) {
       return relances.get(id);
   }
   
   public CommandeReappro getCommandeReappro (Integer id) {
       return reappros.get(id);
   }
   
   public Collection<Produit> getProduits () {
       return produits.values();
   }
   
   public Collection<CommandeReappro> getCommandeReappros () {
       return reappros.values();
   }
   
   public Collection<Relance> getRelances () {
       return relances.values();
   }
}
