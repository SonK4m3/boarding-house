package adis.group5.boardingHouse.model;

import java.util.Date;

public class Tenant extends Member{
    public Tenant(int id, String username, String password, FullName name, String phone, int gender, Date dateOfBirth, String role) {
        super(id, username, password, name, phone, gender, dateOfBirth, role);
    }
}
