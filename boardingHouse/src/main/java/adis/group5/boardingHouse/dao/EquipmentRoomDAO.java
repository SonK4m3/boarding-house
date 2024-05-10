package adis.group5.boardingHouse.dao;

import adis.group5.boardingHouse.model.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRoomDAO extends DAO {

    public EquipmentRoomDAO() {
        super();
    }

    public EquipmentRoom getEquipmentRoomById(int id) {
        try {
            connect();
            String sql = "SELECT se.equipmentroom.id, A.*, se.equipment.* FROM se.equipmentroom\n" +
                    "JOIN \n" +
                    "\t(SELECT se.room.id, se.room.name as \"roomName\", se.room.typeId, se.roomType.size, se.roomType.price, se.roomType.name as \"typeName\", \n" +
                    "\t\tse.room.floorId, se.floor.name as \"floorName\", se.floor.description, se.floor.boardinghouseId\n" +
                    "\tFROM se.room\n" +
                    "\tJOIN se.roomType ON se.room.typeId = se.roomType.id\n" +
                    "\tJOIN se.floor \n" +
                    "\tON se.floor.id = se.room.floorId) as A ON A.id = se.equipmentroom.roomId\n" +
                    "JOIN se.equipment ON se.equipmentroom.equipmentId = se.equipment.id\n" +
                    "WHERE se.equipmentroom.id = ?;";

            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new EquipmentRoom(
                        rs.getInt(1),
                        new Room(
                                rs.getInt(2),
                                rs.getString(3),
                                new RoomType(
                                        rs.getInt(4),
                                        rs.getInt(5),
                                        rs.getInt(6),
                                        rs.getString(7)),
                                new Floor(
                                        rs.getInt(8),
                                        rs.getString(9),
                                        rs.getString(10),
                                        new BoardingHouse(rs.getInt(11), "", null))
                        ),
                        new Equipment(
                                rs.getInt(12),
                                rs.getString(13)
                        )
                );
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<EquipmentRoom> getEquipmentsInRoom(int roomId) {
        List<EquipmentRoom> equipmentRooms = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT se.equipmentroom.id, A.*, se.equipment.* FROM se.equipmentroom\n" +
                    "JOIN \n" +
                    "\t(SELECT se.room.id, se.room.name as \"roomName\", se.room.typeId, se.roomType.size, se.roomType.price, se.roomType.name as \"typeName\", \n" +
                    "\t\tse.room.floorId, se.floor.name as \"floorName\", se.floor.description, se.floor.boardinghouseId\n" +
                    "\tFROM se.room\n" +
                    "\tJOIN se.roomType ON se.room.typeId = se.roomType.id\n" +
                    "\tJOIN se.floor \n" +
                    "\tON se.floor.id = se.room.floorId) as A ON A.id = se.equipmentroom.roomId\n" +
                    "JOIN se.equipment ON se.equipmentroom.equipmentId = se.equipment.id\n" +
                    "WHERE se.equipmentroom.roomId = ? ORDER BY se.equipmentroom.id;";

            pre = conn.prepareStatement(sql);
            pre.setInt(1, roomId);

            rs = pre.executeQuery();

            while (rs.next()) {
                EquipmentRoom equipmentRoom = new EquipmentRoom(
                        rs.getInt(1),
                        new Room(
                                rs.getInt(2),
                                rs.getString(3),
                                new RoomType(
                                        rs.getInt(4),
                                        rs.getInt(5),
                                        rs.getInt(6),
                                        rs.getString(7)),
                                new Floor(
                                        rs.getInt(8),
                                        rs.getString(9),
                                        rs.getString(10),
                                        new BoardingHouse(rs.getInt(11), "", null))
                        ),
                        new Equipment(
                                rs.getInt(12),
                                rs.getString(13)
                        )
                );

                equipmentRooms.add(equipmentRoom);
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipmentRooms;
    }

    public boolean addEquipmentRoom(EquipmentRoom equipmentRoom) {
        try {
            connect();
            String sql = "INSERT INTO se.equipmentroom (roomId, equipmentId) VALUES (?, ?);";
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, equipmentRoom.getRoom().getId());
            pre.setInt(2, equipmentRoom.getEquipment().getId());
            int affectedRows = pre.executeUpdate();
            if (affectedRows > 0) {
                rs = pre.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    EquipmentRoom newEquipmentRoom = getEquipmentRoomById(id);
                    equipmentRoom.setId(newEquipmentRoom.getId());
                    return true;
                }
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateEquipmentRoom(EquipmentRoom equipmentRoom) {
        try {
            connect();
            String updateSql = "UPDATE se.equipmentroom SET roomId = ?, equipmentId = ? WHERE id = ?";
            pre = conn.prepareStatement(updateSql);
            pre.setInt(1, equipmentRoom.getRoom().getId());
            pre.setInt(2, equipmentRoom.getEquipment().getId());
            pre.setInt(3, equipmentRoom.getId());

            int affectedRows = pre.executeUpdate();

            if (affectedRows > 0) {
//                return getEquipmentRoomById(equipmentRoom.getId());
                    return true;
            }
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean removeEquipmentRoom(int equipmentRoomId) {
        try {
            connect();
            String deleteSql = "DELETE FROM se.equipmentroom WHERE id = ?";
            pre = conn.prepareStatement(deleteSql);
            pre.setInt(1, equipmentRoomId);

            int affectedRows = pre.executeUpdate();

            close();

            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
