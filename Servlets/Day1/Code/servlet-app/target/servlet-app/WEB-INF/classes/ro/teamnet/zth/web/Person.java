package ro.teamnet.zth.web;

import java.util.Map;

/**
 * Created by ramona.arsene on 7/18/2017.
 */
public class Person {

    private String firstNamme;
    private String lastName;
    private Long age;
    private Boolean married;

    public Person(String firstNamme, String lastName, Long age, Boolean married){
        this.firstNamme = firstNamme;
        this.lastName = lastName;
        this.age = age;
        this.married = married;
    }

    public String getFirstNamme() {
        return firstNamme;
    }

    public void setFirstNamme(String firstNamme) {
        this.firstNamme = firstNamme;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }
}
