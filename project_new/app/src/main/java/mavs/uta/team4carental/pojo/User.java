package mavs.uta.team4carental.pojo;

import java.io.Serializable;

public class User implements Serializable{

    private String username;
    private String password;
    private String role;
    private String uta_id;
    private String lastname;
    private String firstname;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zipcode;

    private String member;
    private String status;

    public User(
            String username,
            String password,
            String role,
            String firstname,
            String lastname,
            String phone,
            String email,
            String address,
            String city,
            String state,
            String zipcode,
            String member,
            String status
    ) {
        super();
        this.username = username;
        this.password = password;
        this.role =role;
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.member = member;
        this.status = status;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getMember() {
        return member;
    }
    public void setMember(String member) {
        this.member = member;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[User]" +
                "\nusername=" + username +
                "\npassword=" + password +
                "\nrole=" + role +
                "\nlastname=" + lastname +
                "\nfirstname=" + firstname +
                "\nphone=" + phone +
                "\nemail=" + email +
                "\naddress=" + address +
                "\ncity=" + city +
                "\nstate=" + state +
                "\nzipcode=" + zipcode +
                "\nmember=" + member +
                "\nstatus="+status +
                "\n";
    }

}
