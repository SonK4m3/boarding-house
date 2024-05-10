package adis.group5.boardingHouse.model;

import java.util.Date;

public class Owner extends Member{

    private BoardingHouse boardingHouse;
    public Owner(int id, String username, String password, FullName name, String phone, int gender, Date dateOfBirth, String role) {
        super(id, username, password, name, phone, gender, dateOfBirth, role);
    }

    public void setBoardingHouse(BoardingHouse boardingHouse) {
        this.boardingHouse = boardingHouse;
    }

    public BoardingHouse getBoardingHouse() {
        return boardingHouse;
    }
}
