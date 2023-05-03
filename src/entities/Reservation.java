/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Date;

/**
 *
 * @author khali
 */
public class Reservation {
    private int id;
    private Date date_debut;
    private Date date_fin;
    private int etat ,station_id;
    private String station_nom; 
    static final String ACCOUNT_SID ="AC6322202e674e9c2ede42f41107988ea1";
     static final String AUTH_TOKEN = "15e6276ab8399af2689300b26419d795";
   
    public Date getDate_debut() {
        return date_debut;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Reservation(int id, Date date_debut, Date date_fin, int etat) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
    }

    public Reservation(Date date_debut, Date date_fin, int etat, String station_nom) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.station_nom = station_nom;
    }

    public Reservation(int id, Date date_debut, Date date_fin, int etat, String station_nom) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.station_nom = station_nom;
    }

    public Reservation(int id, Date date_debut, Date date_fin, int etat, int station_id) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.station_id = station_id;
    }

    public Reservation(Date date_debut, Date date_fin, int etat, int station_id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
        this.station_id = station_id;
    }
    
    
    

    public Reservation(Date date_debut, Date date_fin, int etat) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.etat = etat;
    }
    

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

   

    public Reservation() {
    }

    public Reservation(int id) {
        this.id = id;
    }
    

    public Reservation(int id, Date date_debut, Date date_fin) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
  
    }

    public Reservation(Date date_debut, Date date_fin) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return date_debut;
    }

    public void setDatedebut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDatefin() {
        return date_fin;
    }

    public void setDatefin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", etat=" + etat + ", station_nom=" + station_nom + '}';
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public void setStation_nom(String station_nom) {
        this.station_nom = station_nom;
    }

    

 

       
    
    
}
