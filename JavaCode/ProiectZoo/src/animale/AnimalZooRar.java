package animale;


import exceptions.*;
import oameni.IngrijitorZoo;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class AnimalZooRar extends Animal{

    private String nume;
    private String numeTaraOrigine;

    public AnimalZooRar(){

    }

    public AnimalZooRar(String nume){
        this.setNume(nume);
    }

    public AnimalZooRar(String nume, String numeTaraOrigine){
        this.setNumeTaraOrigine(numeTaraOrigine);
        this.setNume(nume);
    }

    public void faceBaie(){
        System.out.println("AnimalulZooRar face baie.");
    }

    public void seJoaca(){
        System.out.println("AnimalulZooRar se joaca.");
        super.doarme();
    }

    public boolean mananca(Object obj) throws AnimalManancaOmException, AnimalManancaAnimalException {
        if (obj instanceof IngrijitorZoo) {
            throw new AnimalManancaOmException("Hrana nu poate fi omul!");
        }
        else {
            if(obj instanceof Animal){
                throw new AnimalManancaOmException("Hrana nu poate fi un animal!");
            }
            else{
                System.out.println("Animalul zoo rar mananca.");
                return true;
            }

        }
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumeTaraOrigine() {
        return numeTaraOrigine;
    }

    public void setNumeTaraOrigine(String numeTaraOrigine) {
        this.numeTaraOrigine = numeTaraOrigine;
    }
}
