package oameni;
import animale.*;

import animale.AnimalZooFeroce;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo{
    private int salariu;
    public void lucreaza(Animal animal){
        System.out.println("Veterinarul are grija de animal.");
        this.calculeazaBonusSalarial();
        if(animal instanceof AnimalZooFeroce){
            ((AnimalZooFeroce) animal).faceBaie();
        }
    }

    public void calculeazaBonusSalarial(){
        this.setSalariu(this.getSalariu() + this.valoareBonusPerAnimal * 2);

    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }
}
