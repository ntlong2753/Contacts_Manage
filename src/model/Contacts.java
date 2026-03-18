package model;

public class Contacts {
    private String phoneNumber;
    private String name;
    private String gender;
    private String group;
    private String address;
    private String bithDate;
    private String email;

    public Contacts() {

    }

    public Contacts(String phoneNumber, String name, String gender,String bithDate, String group, String address, String email) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.gender = gender;
        this.group = group;
        this.address = address;
        this.bithDate = bithDate;
        this.email = email;
    }

    public void setBithDate(String bithDate) {
        this.bithDate = bithDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBithDate() {
        return bithDate;
    }

    public void setBithDate() {
        this.bithDate = bithDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() <= 16) {
            this.name = name;
        } else {
            this.name = name.substring(0, 16);
        }
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
