package exercise3;

/**
 * Created by ramona.arsene on 7/7/2017.
 */
public class StudentA extends Student{
    public int hashCode() {
        return ( 31 * this.firstName.hashCode());
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Student student = (StudentA) o;
        if(this.firstName.equals(student.getFirstName()))
            return true;
        return false;
    }
    public StudentA(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
   public StudentA(){

    }


}
