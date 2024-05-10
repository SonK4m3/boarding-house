package adis.group5.boardingHouse.dao;

import adis.group5.boardingHouse.model.BoardingHouse;
import adis.group5.boardingHouse.model.Floor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
public class FloorDAO extends DAO {
    public FloorDAO() {
        super();
    }

    public List<Floor> getFloorsById(int memberId) {
        List<Floor> floors = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM se.floor";
            pre = conn.prepareStatement(sql);
//            pre.setInt(1, memberId);

            rs = pre.executeQuery();

            while (rs.next()) {
                Floor floor = new Floor();
                BoardingHouse b = new BoardingHouse();
                b.setId(rs.getInt("boardinghouseId"));
                floor.setId(rs.getInt("id"));
                floor.setName(rs.getString("name"));
                floor.setDescription(rs.getString("description"));

                floors.add(floor);
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return floors;
    }
}
