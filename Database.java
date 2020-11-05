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
    }

    public void menu() {
        System.out.println("What would you like to do? Search or add member or find relationships");
        String answer = scanner.nextLine().toUpperCase();

        if (answer.contains("SEARCH")) {
            searchMember();
        }
        else if (answer.contains("ADD")) {
            addMember();
        }
        else if (answer.contains("RELATIONSHIP")) {
            System.out.println("Would you like to search for parents, children, or siblings of a specific person?");
            answer = scanner.nextLine().toUpperCase();
            if (answer.contains("PARENT")) {
                System.out.println("You've searched for parents!");
                findParents();
            }
            else if (answer.contains("CHILD")) {
                System.out.println("You've searched for children!");
                findChildren();
            }
            else if (answer.contains("SIBLING")) {
                findSiblings();
            }
        }
        else {
            System.out.println("Sorry, action is not recognized! Please enter in a valid option.");
            menu();
        }
    }
    //test cases: "Dick Johnson  " (Dick Johnson   was found. Birth and death information listed below: Born: 3/1/1915 in Detroit Died: 6/13/1985 in Ann Arbor)
    public void searchMember() {
        System.out.println("Enter person's full name. Include a double space if suffix is not found.");
        String searchName = scanner.nextLine();
        for (Person i : persons.values()) {
            if (i.toString().equals(searchName)) {
                System.out.println(i.getFirstName() + " " + i.getLastName() + " " + i.getNameSuffix() + " was found. Birth and death information listed below:");
                if (i.getBirthday().equals(" ")) {
                    System.out.println(i.getFirstName() + "'s birthday or is not listed");
                } else {
                    System.out.println("Born: " + i.getBirthday() + " in " + i.getBirthCity());
                }
                if (i.getDeathDate().equals(" ")) {
                    System.out.println(i.getFirstName() + " 's death day is not listed");
                } else {
                    System.out.println("Died: " + i.getDeathDate() + " in " + i.getDeathCity());
                }
            }
//            if (!i.toString().equals(searchName)) {
//                System.out.println(searchName + " is not found in the family tree. Would you like to add this person? Reply Yes or No. ");
//                searchName = scanner.nextLine();
//                if (searchName.equals("Yes")){
//                    addMember();
//                }
//                else {
//                    menu();
//                }
//            }
            }
        }


// test cases for searchParents():
// p1 (Parents of Dick Johnson are: Sarah Susan  Smith and Dick  Johnson)
// p15 (no parents found)
// p17 (Parents of Sally Abigale Smith are: Henry Adam Smith and there is no mother found)

    public void findParents() { // not working for one or more values are not found
        System.out.println("Enter Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (persons.containsKey(searchIDNum)) {
            String parentRelationshipNum = persons.get(searchIDNum).getParentRelationship();
            if (relationships.get(parentRelationshipNum).getWife().equals(null) && relationships.get(parentRelationshipNum).getHusband().equals(null)) {
                System.out.println("No parents listed");
            }
            // TODO fix cases in which one spouse value is not found
            else if (relationships.get(parentRelationshipNum).getWife().toString().equals(" ")) {
                System.out.println("Parents of " + persons.get(searchIDNum).getFirstName() + " are: " + relationships.get(parentRelationshipNum).getHusband() + " and there is no mother found");
            } else if (relationships.get(parentRelationshipNum).getHusband().toString().equals(" ")) {
                System.out.println("Parents of " + persons.get(searchIDNum).getFirstName() + " are: " + relationships.get(parentRelationshipNum).getWife() + " and there is no father found");
            } else {
                System.out.println("Parents of " + persons.get(searchIDNum).getFirstName() + " " + persons.get(searchIDNum).getLastName() + " are: " + relationships.get(parentRelationshipNum).getWife() + " and " + relationships.get(parentRelationshipNum).getHusband());
            }
        }
    }

    // test cases: r9 (Relationship R9 has childrens listed below: Dick Johnsom IV John J Smith)
    // r10 (Relationship R10 is not listed. Please enter a valid Relationship ID.)
    //
    public void findChildren(){
        System.out.println("Enter Relationship ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (relationships.containsKey(searchIDNum)) {
            System.out.println("Relationship " + searchIDNum + " has childrens listed below: ");
            for (Person i : relationships.get(searchIDNum).getChildren()) {
                System.out.println(i);
            }
        System.out.println("Would you like to search for another list of children?");
            if (searchIDNum.equals("YES")){
                findChildren();
            }
            else {
                menu();
            }
        }
        else if (!relationships.containsKey(searchIDNum)){
           System.out.println("Relationship " + searchIDNum + " is not listed. Please enter a valid Relationship ID." );
           findChildren();
        }
        else if (relationships.get(searchIDNum).getChildren().isEmpty()) {
            System.out.println("No children listed");
        }

    }
// test cases: p1 (The siblings of Dick Johnson Jr are listed below: Jane Sarah Johnson Sally Abigale Johnson BVM)
    // p13 (The siblings of Betty Jane Smith   are listed below: There are no siblings found)
    public void findSiblings() {
        System.out.println("Enter Person ID: ");
        String searchIDNum = scanner.nextLine().toUpperCase();
        if (persons.containsKey(searchIDNum)) {
            String parentRelationshipNum = persons.get(searchIDNum).getParentRelationship();
            System.out.println("The siblings of " + persons.get(searchIDNum).toString() + " are listed below:");
            if (relationships.get(parentRelationshipNum).getChildren().size() == 1){
                System.out.println("There are no siblings found");
            }
            for (Person i : relationships.get(parentRelationshipNum).getChildren())
                if (i.toString().equals(persons.get(searchIDNum).toString())) { // skips over oneself in list of children to find siblings
                    continue;
                }
                else {
                    System.out.println(i);
                }
        }
        else {
            System.out.println("Person was not found. Please enter a valid ID key.");
            findSiblings();
        }
    }
    //test case: add > "P32,La,Tara, ,06/06/2000,Houston, , , , " > yes > "R10,P32,P31,11/04/2020, ,Chicago"
    public void addMember(){
        System.out.println("Add Person attributes separated by commas. Please make sure all IDs begin with a capital letter. If no attribute exists, add a space and comma anyways.");
        System.out.println("The order is as follows: person ID Num, last name, first name, suffix, birthday, birth city, death day, death city, parent ID Num");
        String data = scanner.nextLine();
        Person newPerson = makePerson(data);
        persons.put(newPerson.getPersonNum(), newPerson);
        System.out.println(newPerson.getFirstName() + " " + newPerson.getLastName() + " has been created!");

        System.out.println("Is this person married? Enter yes or no.");
        data = scanner.nextLine().toUpperCase();
        if (data.equals("YES")){
            System.out.println("Add Relationship attributes separated by commas. Please make sure all IDs begin with a capital letter");
            System.out.println("The order is as follows: relationship ID Num, wife ID num, husband ID num, marriage date, divorce date, marriage location. If no attribute exists, add a space anyways.");
            data = scanner.nextLine();
            Relationship newRelationship = makeRelationship(data);
            relationships.put(newRelationship.getRelationshipNum(), newRelationship);
            System.out.println("Marriage " + newRelationship.getRelationshipNum() + " was created between: " + newRelationship.getWife() + " and " + newRelationship.getHusband() + " on " + newRelationship.getMarriageDate());

        }
        else {
            System.out.println("No marriage will be created.");
        }
        System.out.println("Does this person have any children?");
        data = scanner.nextLine().toUpperCase();
        if (data.equals("YES")){
            System.out.println("How many children does this person have?");
            data = scanner.nextLine();
            for (int i = 0; i<=Integer.parseInt(data); i++){
                System.out.println("Enter in child's ID num: ");
                data = scanner.nextLine().toUpperCase();
                System.out.println("Enter in relationship ID num: ");
                String relationshipNum = scanner.nextLine().toUpperCase();
                relationships.get(relationshipNum).getChildren().add(persons.get(data));
                System.out.println(persons.get(data).toString() + " has been added to " + newPerson.toString() + "'s children list");
            }
        }
        else {
            System.out.println("No children will be created");
        }
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
            //System.out.println(persons.values());
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
            relationships.put(currentRelationship.getRelationshipNum(), currentRelationship);
            //System.out.println(relationships.values());
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
            persons.get(childNum).getParents().add(relationships.get(parentsRelationshipNum).getWife());
            persons.get(childNum).getParents().add(relationships.get(parentsRelationshipNum).getHusband());
            //System.out.println(persons.get(childNum).getParents());
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


