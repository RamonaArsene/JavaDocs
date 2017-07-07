package exercise3;

/**
 * Created by ramona.arsene on 7/7/2017.
 */
public class StudentC extends Student {

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Student student = (StudentC) o;
        if(this.firstName.equals(student.getFirstName()))
            return true;
        return false;
    }

    public int hashCode() {

        return ( 31* this.firstName.hashCode() + this.lastName.hashCode());
    }

    public StudentC(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentC(){}

}
