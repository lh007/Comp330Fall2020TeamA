import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {
    String familyFile = "FamilyTreeInputTextFile.csv";
    ArrayList<Person> individuals = new ArrayList<>();
    ArrayList<Person> relationships = new ArrayList<>();
    private void readFile() throws Exception { // reads in the file
        String fileName = this.familyFile;
        String data = readFileAsString(fileName);
        String[] informationArray = data.split("\n"); // array of strings called riddleArray is split by /
        for (String i: informationArray){
            Person currentPerson = this.makePerson(i);
            if (currentPerson.getPersonNum().contains("P")){
                individuals.add(currentPerson);
            }
            if (currentPerson.getPersonNum().contains("R")){
                relationships.add(currentPerson);
            }
        }

    }
    private Person makePerson(String data) {
        String[] personArray = data.split(",");
        String personNum = personArray[0];
        String lastName = personArray[1];
        String firstName =personArray[2];
        String nameSuffix = personArray[3];
        String birthday = personArray[4];
        String birthCity = personArray[5];
        String deathDate = personArray[6];
        String deathCity = personArray[7];
        String relationship = personArray[8];
        Person result = new Person(personNum, lastName, firstName, nameSuffix, birthday, birthCity, deathDate, deathCity, relationship);
        return result;
    }
    public String readFileAsString(String fileName)throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;

    }
    public void main(String[] args){
        System.out.println(individuals);
    }
}

