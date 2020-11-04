/*
  Relationshipi class

  @author tarala
 * @version 1
 * attributes, getters and setters
 */
import java.util.ArrayList;
import java.util.List;

public class Relationship {

    // marriage, children of that marriage

        private String relationshipNum;
        private Person wife;
        private Person husband;
        private String marriageDate;
        private String divorceDate;
        private String marriageLocation;
        private String deathDate;
        private String deathCity;
        private List<Person> children;



        public Relationship(String relationshipNum, Person wife, Person husband, String marriageDate, String divorceDate, String marriageLocation){
            this.relationshipNum = relationshipNum;
            this.wife = wife;
            this.husband = husband;
            this.marriageDate = marriageDate;
            this.divorceDate = divorceDate;
            this.marriageLocation = marriageLocation;
            children = new ArrayList<>();

        }
    public String toString() {return relationshipNum + ": " +  wife + " and " + husband + "\n";

    }
        //look up relationship by relationship num and add children

    // getters and setters: TODO delete unnecessary and reflect in other documents
    public String getRelationshipNum() {
        return relationshipNum;
    }

    public void setRelationshipNum(String relationshipNum) {
        this.relationshipNum = relationshipNum;
    }


    public Person getWife() {
        return wife;
    }

    public void setWife(Person wife) {
        this.wife = wife;
    }

    public Person getHusband() {
        return husband;
    }

    public void setHusband(Person husband) {
        this.husband = husband;
    }

    public String getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(String marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getDivorceDate() {
        return divorceDate;
    }

    public void setDivorceDate(String divorceDate) {
        this.divorceDate = divorceDate;
    }

    public String getMarriageLocation() {
        return marriageLocation;
    }

    public void setMarriageLocation(String marriageLocation) {
        this.marriageLocation = marriageLocation;
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

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

}
