package cn.eleven.session2;

/**
 * Created by User on 2017/7/10.
 */
public class IdCard {

    private  Integer id;
    private String number;
    private Person person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
