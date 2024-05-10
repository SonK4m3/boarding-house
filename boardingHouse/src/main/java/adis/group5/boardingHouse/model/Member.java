package adis.group5.boardingHouse.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private int id;
    private String username;
    private String password;
    private FullName name;
    private String phone;
    private int gender;
    private Date dateOfBirth;
    private String role;

    public Member() {
        this.id = -1;
        this.username = "";
        this.password = "";
        this.name = new FullName("", "");
        this.phone = "";
        this.gender = 1;
        this.dateOfBirth = null;
        this.role = "";
    }

    public Member(int id, String username, String password, FullName name, String phone, int gender, Date dateOfBirth, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", role='" + role + '\'' +
                '}';
    }

    public static Member createMemberLogin(String username, String password) {
        Member member = new Member();
        member.username = username;
        member.password = password;
        return member;
    }
}
