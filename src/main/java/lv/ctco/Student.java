package lv.ctco;

public class Student {

    private static int ID_GENERATOR =0;
    private String firstName;
    private String lastName;
    private int id;

    public Student(String firstName,String lastName) {
        this.id = ID_GENERATOR++;
        this.firstName=firstName;
        this.lastName=lastName;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
