package exercise3;

/**
 * Created by ramona.arsene on 7/7/2017.
 */
public class StudentD extends Student{
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Student student = (StudentD) o;
        if(this.firstName.equals(student.getFirstName()) && this.lastName.equals(student.getLastName()))
            return true;
        return false;
    }

    public int hashCode() {

        return ( 31* this.lastName.hashCode() + this.firstName.hashCode());
    }

    public StudentD(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentD(){}

}
