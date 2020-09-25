public class Person {
    private String personNum;
    private String lastName;
    private String firstName;
    private String nameSuffix;
    private String birthday;
    private String birthCity;
    private String deathDate;
    private String deathCity;
    private String relationship;


    public Person(String personNum, String lastName, String firstName, String nameSuffix, String birthday,
                  String birthCity, String deathDate, String deathCity, String relationship) {
        this.personNum = personNum;
        this.lastName = lastName;
        this.firstName = firstName;
        this.nameSuffix = nameSuffix;
        this.birthday = birthday;
        this.birthCity = birthCity;
        this.deathDate = deathDate;
        this.deathCity = deathCity;
        this.relationship = relationship;

    }
    public String getPersonNum(){
        return personNum;
    }
}