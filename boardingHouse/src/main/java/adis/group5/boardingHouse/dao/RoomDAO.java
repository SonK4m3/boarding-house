package adis.group5.boardingHouse.dao;

import adis.group5.boardingHouse.model.BoardingHouse;
import adis.group5.boardingHouse.model.Floor;
import adis.group5.boardingHouse.model.Room;
import adis.group5.boardingHouse.model.RoomType;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends DAO {
    public RoomDAO() {
        super();
    }

    public List<Room> getRoomsByFloor(Floor floor) {
        List<Room> rooms = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT * FROM se.room JOIN se.roomtype ON se.room.typeId = se.roomtype.id WHERE se.room.floorId = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, floor.getId());

            rs = pre.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                RoomType roomType = new RoomType(rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
                room.setId(rs.getInt(1));
                room.setName(rs.getString(2));
                room.setRoomType(roomType);
                room.setFloor(floor);

                rooms.add(room);
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public Room getRoomById(int id) {
        try {
            connect();
            String sql = "SELECT se.room.id, se.room.name, se.room.typeId, se.roomType.size, se.roomType.price, se.roomType.name as \"typeName\", \n" +
                    "    se.room.floorId, se.floor.name, se.floor.description, se.floor.boardinghouseId\n" +
                    "FROM se.room\n" +
                    "JOIN se.roomType ON se.room.typeId = se.roomType.id\n" +
                    "JOIN se.floor \n" +
                    "ON se.floor.id = se.room.floorId\n" +
                    "WHERE se.room.id = ?";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);

            rs = pre.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        new RoomType(rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6)),
                        new Floor(rs.getInt(7), rs.getString(8), rs.getString(9),
                                new BoardingHouse(rs.getInt(10), "", null))
                );
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Room getRoomByIdTenant(int id) {
        try {
            connect();
            String sql = "SELECT R.id, R.name, R.typeId, se.roomType.size, se.roomType.price, se.roomType.name as \"typeName\", \n" +
                    "R.floorId, se.floor.name, se.floor.description, se.floor.boardinghouseId\n" +
                    "                   FROM (\n" +
                    "                       SELECT room.* FROM se.renting, se.room as room " +
                    "                       WHERE se.renting.tenantId = ? AND se.renting.roomId = room.id\n" +
                    "                    ) as R\n" +
                    "                    JOIN se.roomType ON R.typeId = se.roomType.id\n" +
                    "                    JOIN se.floor \n" +
                    "                    ON se.floor.id = R.floorId;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);

            rs = pre.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getInt(1),
                        rs.getString(2),
                        new RoomType(rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6)),
                        new Floor(rs.getInt(7), rs.getString(8), rs.getString(9),
                                new BoardingHouse(rs.getInt(10), "", null))
                );
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
