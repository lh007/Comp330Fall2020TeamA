package Cycle2.ReleasedCode;/*
  Person class

  @author tarala
 * @version 3
 * attributes, getters and setters, tracks if is child of someone?)
 */

import java.util.ArrayList;

public class Person {
    private String personNum;
    private String lastName;
    private String firstName;
    private String nameSuffix;
    private String birthday;
    private String birthCity;
    private String deathDate;
    private String deathCity;
    private String parentRelationship;
    private String partner;
    private ArrayList parents;




    public Person(String personNum, String lastName, String firstName, String nameSuffix, String birthday,
                  String birthCity, String deathDate, String deathCity, String relationship){
        this.personNum = personNum;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameSuffix = nameSuffix;
        this.birthday = birthday;
        this.birthCity = birthCity;
        this.deathDate = deathDate;
        this.deathCity = deathCity;
        this.parentRelationship = relationship;
        parents = new ArrayList<Person>();

    }

    //overrides hashcode reference
    public String toString(){
        return firstName + " " + lastName + " " + nameSuffix;
    }


// getters and setters - TODO delete unnecessary and reflect in other documents
    public String getPersonNum(){
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getDeathCity() {
        return deathCity;
    }

    public void setDeathCity(String deathCity) {
        this.deathCity = deathCity;
    }

    public String getParentRelationship() {
        return parentRelationship;
    }
    public ArrayList<Person> getParents() {
        return parents;
    }


}