package exercise3;

/**
 * Created by ramona.arsene on 7/7/2017.
 */
public class StudentB extends Student {
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Student student = (StudentB) o;
        if(this.firstName.equals(student.getFirstName()) && this.lastName.equals(student.getLastName()))
            return true;
        return false;
    }

    public int hashCode() {

        return ( 31 * this.firstName.hashCode());
    }

    public StudentB(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public StudentB(){}

}
