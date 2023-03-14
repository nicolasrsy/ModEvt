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
public class Produit extends appli.lib.PropertyChangeSupport {
    private Integer stock;
    private Integer id_produit;
    private Integer seuilRupture;
    private String nom_produit;
    

    public Produit (Integer qte, Integer id, Integer seuil,String n ) { 
        super();
        stock=qte;
        id_produit=id;
        seuilRupture=seuil;
        nom_produit=n;
    }   

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        Integer oldValue=this.stock;
        this.stock = stock;
        this.firePropertyChange("stock", oldValue, stock);
    }

    public Integer getId_produit() {
        return id_produit;
    }

    public void setId_produit(Integer id_produit) {
        this.id_produit = id_produit;
    }

    public Integer getSeuilRupture() {
        return seuilRupture;
    }

    public void setSeuilRupture(Integer seuilRupture) {
        Integer oldValue=this.seuilRupture;
        this.seuilRupture = seuilRupture;
        this.firePropertyChange("seuilRupture", oldValue, seuilRupture);
        
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }
    
    public String toString() {
        return (" Nom :" + nom_produit +" IdProduit :" + id_produit +" stock :" + stock + " seuil :" +seuilRupture);
    }
};
    

