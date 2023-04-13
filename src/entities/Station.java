package entities;

/**
 *
 * @author khali
 */
public class Station {
    private int id;
    private int capacite ;
    private String localisation;

    public Station() {
    }

    public Station(int id) {
        this.id = id;
    }

    public Station(int id, int capacite, String localisation) {
        this.id = id;
        this.capacite = capacite;
        this.localisation = localisation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return  localisation ;
    }

    
   
}