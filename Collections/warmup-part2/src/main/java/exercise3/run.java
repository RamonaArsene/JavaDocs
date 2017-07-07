package exercise3;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by ramona.arsene on 7/7/2017.
 */
public class run {
    public static void main(String[] args) {

        Map<StudentA, BigDecimal> studentA = new HashMap<StudentA, BigDecimal>();
        Map<StudentB, BigDecimal> studentB = new HashMap<StudentB, BigDecimal>();
        Map<StudentC, BigDecimal> studentC = new HashMap<StudentC, BigDecimal>();
        Map<StudentD, BigDecimal> studentD = new HashMap<StudentD, BigDecimal>();

        studentA.put(new StudentA("Andrei", "Serban"), new BigDecimal(0.004));
        studentA.put(new StudentA("Andrei", "Mocanu"), new BigDecimal(0.004));

        for(Student key : studentA.keySet()){
            System.out.println("StudentA: " + key.getFirstName() + key.getLastName());
        }

        studentB.put(new StudentB("Mihai", "Serban"), new BigDecimal(0.0005));
        studentB.put(new StudentB("Andrei", "Mocanu"), new BigDecimal(0.004));

        for(Student key : studentB.keySet()){
            System.out.println("StudentB: " + key.getFirstName() + key.getLastName());
        }

        studentC.put(new StudentC("Ion", "Serban"), new BigDecimal(0.0005));
        studentC.put(new StudentC("Andrei", "Serban"), new BigDecimal(0.004));

        for(Student key : studentC.keySet()){
            System.out.println("StudentC: " + key.getFirstName() + key.getLastName());
        }

        studentD.put(new StudentD("Andrei", "Serban"), new BigDecimal(0.0005));
        studentD.put(new StudentD("Mihai", "Serban"), new BigDecimal(0.004));

        for(Student key : studentD.keySet()){
            System.out.println("StudentD: " + key.getFirstName() + key.getLastName());
        }



    }
}
