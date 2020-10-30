/*
  Database Class

  @author tarala
 * reads in file

 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    String familyFile = "FamilyTreeInputTextFile.csv";
    String[] personArray;
    String[] relationshipsArray;

    HashMap<String, Person> db = new HashMap<>();

    public Database() throws Exception {
        this.readFile();

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

        for (int i = 0; i <= informationArray.length-1; i++) {
            String currentLine = informationArray[i];

            if (currentLine.startsWith("P")) {
                Person currentPerson = this.makePerson(currentLine);
                db.put(currentPerson.getPersonNum(), currentPerson);
                System.out.println("Person " + currentPerson.getFirstName() + " was created");
                System.out.println(currentPerson.getFirstName() + " was born in " + currentPerson.getBirthCity() + " on " + currentPerson.getBirthday());
            }
//creates formatting of relationship array
            if (currentLine.startsWith("R")) {
                relationshipsArray = currentLine.split(",");
                String relationshipNum = relationshipsArray[0];
                String wife = relationshipsArray[1];
                String husband = relationshipsArray[2];
                String marriageDate = relationshipsArray[3];
                String divorceDate = relationshipsArray[4];
                String marriageLocation = relationshipsArray[5];
                createMarriage(relationshipNum, wife, husband, marriageDate, divorceDate, marriageLocation);

            }

        }

    }

    private Person makePerson(String data) {
        personArray = data.split(",");
        String personNum = personArray[0];
        String lastName = personArray[1];
        String firstName = personArray[2];
        String nameSuffix = personArray[3];
        String birthday = personArray[4];
        String birthCity = personArray[5];
        String deathDate = personArray[6];
        String deathCity = personArray[7];
        String parentRelationship = personArray[8];
        Person result = new Person(personNum, lastName, firstName, nameSuffix, birthday, birthCity, deathDate, deathCity, parentRelationship);
        return result;
    }

//creates marriage relationship using index 1 and 2 of relationship array respectively
    public void createMarriage(String relationshipNum, String wife, String husband, String marriageDate, String divorceDate, String marriageLocation) {
        Person female = db.get(wife);
        Person male = db.get(husband);
        //female.setSpouses(male);
        for (Person p: db.values()){
            if (p.getParentRelationship().equals(relationshipNum)){
                p.setParents(female, male);
            }
        }

    }

    public void createSiblings(){
        for (Person p1: db.values()){
            for (Person p2: db.values()){
                if (p1.getParents().equals(p2.getParents())){
                    p1.setSiblings(p2);
                }
                }
            }
        }
    }


