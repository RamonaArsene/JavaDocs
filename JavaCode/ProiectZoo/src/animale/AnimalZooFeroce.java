package animale;
import exceptions.*;
import oameni.IngrijitorZoo;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {

    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }


    public boolean mananca(Object obj) throws AnimalManancaOmException, AnimalPeCaleDeDisparitieException, AnimalManancaAnimalException{
        if (obj instanceof IngrijitorZoo) {
            throw new AnimalManancaOmException("Hrana nu poate fi omul!");
        }

        else {
            if(obj == null){
                throw new AnimalPeCaleDeDisparitieException("Animalul este pe cale de disparitie!");
            }
            else {
                if(obj instanceof Animal){
                    throw new AnimalManancaAnimalException("Animalul nu poate manca animal!");
                }
                else {
                System.out.println("Animalul zoo feroce mananca.");
                return true;
                }
            }



        }
    }

    public void faceBaie(){
        System.out.println("Animalul zoo feroce face baie");
    }

    public void seJoaca(){
        System.out.println("Animalul zoo feroce seJoaca");
    }


}
