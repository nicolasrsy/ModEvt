/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli.objets;

/**
 *
 * @author carine
 */
public class Commande {
    private Integer id_commande;
    private Client client;
    private Produit produit;
    private Integer qte;
    private String dateCom;
    private String etatCom;
    
    public Commande (Integer id_commande, Client client, Produit produit, Integer qte, String dateCom, String etatCom){
        this.id_commande=id_commande;
        this.client=client;
        this.produit=produit;
        this.dateCom=dateCom;
        this.etatCom=etatCom;
        this.qte=qte;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public String getDateCom() {
        return dateCom;
    }

    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }

    public String getEtatCom() {
        return etatCom;
    }

    public void setEtatCom(String etatCom) {
        this.etatCom = etatCom;
    }
    
    
}
