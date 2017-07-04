package animale;

import exceptions.AnimalManancaAnimalException;
import exceptions.AnimalManancaOmException;
import exceptions.AnimalPeCaleDeDisparitieException;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public abstract class Animal {

   public abstract boolean mananca (Object obj) throws AnimalManancaOmException, AnimalPeCaleDeDisparitieException, AnimalManancaAnimalException;
   public abstract void seJoaca();
   public abstract void faceBaie();
   public void doarme(){
        System.out.println("Animalul doarme");
    }

    public Animal(){

        System.out.println("Animal nou.");
    }

}
