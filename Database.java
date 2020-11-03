/*
  Database Class

  @author tarala
 * reads in file

 */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Database {
    String familyFile = "FamilyTreeInputTextFile.csv";
    HashMap<String, Person> persons = new HashMap<>();
    HashMap<String, Relationship> relationships = new HashMap<>();

    Scanner scanner = new Scanner(System.in);


    public Database() throws Exception {
        this.readFile();
        this.menu();
        //this.searchMember();

    }
    public void menu(){
        System.out.println("What would you like to do? Search or add member or find relationships");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.contains("SEARCH")) {
            searchMember();
        }
        if (answer.contains("ADD")) {
            addMember();
        }
        if (answer.contains("RELATIONSHIP")){
            System.out.println("Would you like to search for parents or children of a specific person?");
            answer = scanner.nextLine().toUpperCase();
            if (answer.contains("PARENT")) {
                System.out.print("You've searched for parents!");
                }
            if (answer.contains("CHILDREN")) {
                System.out.print("You've searched for children!");

                }
            }
        }


    public void searchMember(){
        System.out.println("Enter in Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (persons.containsKey(searchIDNum)){
            System.out.println(persons.get(searchIDNum).getFirstName() + " " + persons.get(searchIDNum).getLastName() +  " " + persons.get(searchIDNum).getNameSuffix());
            System.out.println("Born: " + persons.get(searchIDNum).getBirthday() + " in " + persons.get(searchIDNum).getBirthCity());
            if (persons.get(searchIDNum).getDeathDate().equals(" ")) {
                System.out.println(persons.get(searchIDNum).getFirstName() + " has not died yet!");
            }
            else{
                System.out.println("Died: " + persons.get(searchIDNum).getDeathDate() + " in " + persons.get(searchIDNum).getDeathCity());
            }

        }

    }


    public void addMember(){

    }

    public String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;

    }

    private void readFile() throws Exception { // reads in the file
        String fileName = this.familyFile;
        String data = readFileAsString(fileName);
        String[] informationArray = data.split("\n"); // array of strings called informationArray is split by new line
        int i = 1;
        for (; i <= informationArray.length - 1; i++) {
            String currentLine = informationArray[i];
            if (currentLine.startsWith(",")) {
                continue;
            }
            if (currentLine.startsWith("Partnership")) {
                break;
            }
            Person currentPerson = this.makePerson(currentLine);
            persons.put(currentPerson.getPersonNum(), currentPerson);
            //System.out.println("Person " + currentPerson.getFirstName() + " was created");
           //System.out.println(currentPerson.getFirstName() + " was born in " + currentPerson.getBirthCity() + " on " + currentPerson.getBirthday());


        }
        i++; // skips partnership row
        for (; i <= informationArray.length - 1; i++) {
            String currentLine = informationArray[i];
            if (currentLine.startsWith(",")) {
                continue;
            }
            if (currentLine.startsWith("Children")) {
                break;
            }
            Relationship currentRelationship = this.makeRelationship(currentLine);
            //createMarriage(relationshipNum, wife, husband, marriageDate, divorceDate, marriageLocation);
            relationships.put(currentRelationship.getRelationshipNum(), currentRelationship);
            //System.out.println(currentLine);
            //System.out.println("Marriage " + currentRelationship.getRelationshipNum() + " was created between: " + currentRelationship.getWife() + " and " + currentRelationship.getHusband() + " on " + currentRelationship.getMarriageDate());

        }
        i++;
        for (; i <= informationArray.length - 1; i++) {
            String currentLine = informationArray[i];
            String[] childrenArray = currentLine.split(",");
            String parentsRelationshipNum = childrenArray[0];
            String childNum = childrenArray[1];
            relationships.get(parentsRelationshipNum).getChildren().add(persons.get(childNum));
            //System.out.println(parentsRelationshipNum + " has " + relationships.get(parentsRelationshipNum).getChildren());
        }

    }


    private Person makePerson(String data) {
        String[] personArray = data.split(",");
        //System.out.println(data);
        //System.out.println(personArray.length);
        String personNum = personArray[0];
        String lastName = personArray[1];
        String firstName = personArray[2];
        String nameSuffix = personArray[3];
        String birthday = personArray[4];
        String birthCity = personArray[5];
        String deathDate = personArray[6];
        String deathCity = personArray[7];
        String parentRelationship = (personArray.length > 8 ? personArray[8] : " ");
        Person result = new Person(personNum, lastName, firstName, nameSuffix, birthday, birthCity, deathDate, deathCity, parentRelationship);
        return result;
    }

    public Relationship makeRelationship(String data) {
        String [] relationshipsArray = data.split(",");
        String relationshipNum = relationshipsArray[0];
        Person wife = persons.get(relationshipsArray[1]); // returns null if nothing is found
        Person husband = persons.get(relationshipsArray[2]);
        String marriageDate = relationshipsArray[3];
        String divorceDate = relationshipsArray[4];
        String marriageLocation = relationshipsArray[5];
        Relationship result = new Relationship(relationshipNum, wife, husband, marriageDate, divorceDate, marriageLocation);
        return result;
    }


    //creates marriage relationship using index 1 and 2 of relationship array respectively
    public void createMarriage(String relationshipNum, String wife, String husband, String marriageDate, String divorceDate, String marriageLocation) {
        Person female = persons.get(wife);
        Person male = persons.get(husband);
        //female.setSpouses(male);
        for (Person p : persons.values()) {
            if (p.getParentRelationship().equals(relationshipNum)) {
                p.setParents(female, male);
            }
        }

    }

    public void createSiblings() {
        for (Person p1 : persons.values()) {
            for (Person p2 : persons.values()) {
                if (p1.getParents().equals(p2.getParents())) {
                    p1.setSiblings(p2);
                }
            }
        }
    }
}


