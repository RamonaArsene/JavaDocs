package oameni;
import animale.*;
import exceptions.*;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo {

    private int salariu = 0;
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului");
        this.calculeazaBonusSalarial();

    }

    public void lucreaza(Animal animal, Object mancare) {
        boolean  hranit = false;
        this.lucreaza(animal);
        try {
            hranit = animal.mananca(mancare);
        } catch (AnimalManancaOmException ex ) {
            System.out.println("Problema: " + ex.getMessage());
        } catch(AnimalPeCaleDeDisparitieException ex){
            System.out.println("Problema: " + ex.getMessage());
        } catch(AnimalManancaAnimalException ex){
            System.out.println("Problema: " + ex.getMessage());
        }

        if(hranit) {
            this.calculeazaBonusSalarial();
        }

    }

    public void calculeazaBonusSalarial(){
        this.setSalariu(this.getSalariu() + this.valoareBonusPerAnimal * 3);

    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }
}
