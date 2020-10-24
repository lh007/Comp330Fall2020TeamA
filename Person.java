/*
  Person class

  @author tarala
 * @version 2
 * attributes, getters and setters, create relationships (parents,sibling,marriage)
 */


import java.util.ArrayList;
import java.util.Arrays;

public class Person {
    private String personNum;
    private String lastName;
    private String firstName;
    private String nameSuffix;
    private String birthday;
    private String birthCity;
    private String deathDate;
    private String deathCity;
    //private String parentRelationship;
    private String partner;
    private ArrayList parents;
    private ArrayList siblings;
    private ArrayList children;



    public Person(String personNum, String lastName, String firstName, String nameSuffix, String birthday,
                  String birthCity, String deathDate, String deathCity) { //, String relationship)
        this.personNum = personNum;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameSuffix = nameSuffix;
        this.birthday = birthday;
        this.birthCity = birthCity;
        this.deathDate = deathDate;
        this.deathCity = deathCity;
        //this.parentRelationship = relationship;
        parents = new ArrayList<Person>();
        siblings = new ArrayList<Person>();
        children = new ArrayList<Person>();

    }

    public String relationship(){
        return "";
    }
    public void setParents(Person p1, Person p2) {
        parents.add(Arrays.asList(p1, p2));
        //p1.children.add(child);
        //p2.children.add(child);

    }
//    public void setSpouses(Person partner2){
//        if (this.getSpouse().equals("") && partner2.equals("")){
//            this.partner = partner2.getFirstName();
//            partner2.partner = this.getFirstName();
//        }
//    }

    public void setSiblings(Person sibling2){
        this.siblings.add(sibling2);
        sibling2.siblings.add(this);

    }

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

//    public String getParentRelationship() {
//        return parentRelationship;
//    }
    public String getSpouse(){
        return partner;
    }
    public ArrayList<Person> getChildren() {
        return children;
    }

    public ArrayList<Person> getParents() {
        return parents;
    }

    public ArrayList<Person> getSiblings() {
        return siblings;
    }
}