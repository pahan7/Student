package lv.ctco;

public class Student {

    private static int ID_GENERATOR =0;
    private String firstName;
    private String lastName;
    private int id;

    public Student() {
        this.id = ID_GENERATOR++;
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
