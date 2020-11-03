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
            System.out.println("Would you like to search for parents, children, or siblings of a specific person?");
            answer = scanner.nextLine().toUpperCase();
            if (answer.contains("PARENT")) {
                System.out.println("You've searched for parents!");
                searchParents();
                }
            if (answer.contains("CHILDREN")) {
                System.out.println("You've searched for children!");
                searchChildren();
                }
            if (answer.contains("SIBLINGS")) {
                searchSiblings();
                }
            }
        }

    public void searchMember(){ //completed
        System.out.println("Enter Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (persons.containsKey(searchIDNum)){
            System.out.println(persons.get(searchIDNum).getFirstName() + " " + persons.get(searchIDNum).getLastName() +  " " + persons.get(searchIDNum).getNameSuffix() + " was found. Birth and death information listed below:");
            if (persons.get(searchIDNum).getBirthday().equals(" ")) {
                System.out.println(persons.get(searchIDNum).getFirstName() + "'s birthday or is not listed");
            }
            else{
                System.out.println("Born: " + persons.get(searchIDNum).getBirthday() + " in " + persons.get(searchIDNum).getBirthCity());
            }
            if (persons.get(searchIDNum).getDeathDate().equals(" ")) {
                System.out.println(persons.get(searchIDNum).getFirstName() + " 's death day is not listed");
            }
            else{
                System.out.println("Died: " + persons.get(searchIDNum).getDeathDate() + " in " + persons.get(searchIDNum).getDeathCity());
            }
        }
    }
// test cases for searchParents():
// p1 (Parents of Dick Johnson are: Sarah Susan  Smith and Dick  Johnson)
// p15 (no parents found)
// p17 (Parents of Sally Abigale Smith are: Henry Adam Smith and there is no mother found)

    public void searchParents() { // not working for one or more values are not found
        System.out.println("Enter Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (persons.containsKey(searchIDNum)) {
            String parentRelationshipNum = persons.get(searchIDNum).getParentRelationship();
            if (relationships.get(parentRelationshipNum).getWife().equals(null) && relationships.get(parentRelationshipNum).getHusband().equals(null)) {
                System.out.println("No parents found");
            }
            // TODO fix cases in which one spouse value is not found
            else if (relationships.get(parentRelationshipNum).getWife().equals(" ")) {
                System.out.println("Parents of " + persons.get(searchIDNum).getFirstName() + " are: " + relationships.get(parentRelationshipNum).getHusband() + " and there is no mother found");
            } else if (relationships.get(parentRelationshipNum).getHusband().equals(" ")) {
                System.out.println("Parents of " + persons.get(searchIDNum).getFirstName() + " are: " + relationships.get(parentRelationshipNum).getWife() + " and there is no father found");
            } else {
                System.out.println("Parents of " + persons.get(searchIDNum).getFirstName() + " " + persons.get(searchIDNum).getLastName() + " are: " + relationships.get(parentRelationshipNum).getWife() + " and " + relationships.get(parentRelationshipNum).getHusband());
            }
        }
    }

    // test cases: p1 (Dick Johnson Jr has childrens: Jane Sarah Johnson, Sally Abigale Johnson BVM)
    public void searchChildren(){
        System.out.println("Enter Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();


    }
// logic is messed up here
    public void searchSiblings(){
        System.out.println("Enter Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (persons.containsKey(searchIDNum)) {
            String parentRelationshipNum = persons.get(searchIDNum).getParentRelationship();
            if (parentRelationshipNum.equals(" ")) {
                System.out.println(relationships.get(parentRelationshipNum).getWife() + " " + relationships.get(parentRelationshipNum).getHusband() + " have no children listed");
            } else {
                System.out.println(persons.get(searchIDNum).getFirstName() + " " + persons.get(searchIDNum).getLastName() + " " + persons.get(searchIDNum).getNameSuffix() + " has childrens whom are siblings:");
                for (Person i : relationships.get(parentRelationshipNum).getChildren()) {
                    System.out.println(i);

                }
            }
        }
    }
    public void addMember(){
        System.out.println("Add Person attributes separated by commas. If no attribute exists, add a space and comma anyways.");
        System.out.println("The order is as follows: person ID Num, last name, first name, suffix, birthday, birth city, death day, death city, parent ID Num");
        String data = scanner.nextLine();
        Person newPerson = makePerson(data);
        System.out.println(newPerson.getFirstName() + newPerson.getLastName() + " has been created!");
        //TODO create marriage (if necessary), set relationship with parent, and add child to parent's children arraylist
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
        i++; //skips children line
        for (; i <= informationArray.length - 1; i++) {
            String currentLine = informationArray[i];
            String[] childrenArray = currentLine.split(",");
            String parentsRelationshipNum = childrenArray[0];
            String childNum = childrenArray[1];
            relationships.get(parentsRelationshipNum).getChildren().add(persons.get(childNum));

            //System.out.println(parentsRelationshipNum + ": " + relationships.get(parentsRelationshipNum).getWife() + relationships.get(parentsRelationshipNum).getHusband() + " has " + relationships.get(parentsRelationshipNum).getChildren());
        }

    }

// assign attributes from text file to specific person and relationship

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



}


