package adis.group5.boardingHouse.dao;

import adis.group5.boardingHouse.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO extends DAO {
    public EquipmentDAO() {
        super();
    }

    public List<Equipment> getEquipments() {
        List<Equipment> equipments = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM se.equipment;";
            pre = conn.prepareStatement(sql);

            rs = pre.executeQuery();

            while (rs.next()) {
                Equipment equipment = new Equipment(
                        rs.getInt(1),
                        rs.getString(2)
                );

                equipments.add(equipment);
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipments;
    }
}
