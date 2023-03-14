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
public class Relance {
    
    private CommandeReappro reappro;
    private String dateRelance;
    private String commentaire;
    private Integer id_relance;
    
    public Relance (Integer id,CommandeReappro c, String d, String comment) {
        this.id_relance=id;
        this.reappro=c;
        this.dateRelance=d;
        this.commentaire=comment;
    }

    public CommandeReappro getReappro() {
        return reappro;
    }

    public Integer getId_relance() {
        return id_relance;
    }

    public String getDateRelance() {
        return dateRelance;
    }

    public String getCommentaire() {
        return commentaire;
    }
    
 
    public String toString() {
        return (" reappro :" + reappro.getId_reappro() +" id_relance :" + id_relance +" date :" + dateRelance + " commentaire :" +commentaire);
    }
    
}
