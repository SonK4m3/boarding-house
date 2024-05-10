package adis.group5.boardingHouse.model;

public class Room {
    private int id;
    private String name;
    private RoomType roomType;
    private Floor floor;

    public Room() {
        this.id = -1;
        this.name = "name";
        this.roomType = null;
        this.floor = null;
    }
    public Room(int id, String name, RoomType roomType, Floor floor) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
        this.floor = floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomType=" + roomType +
                ", floor=" + floor +
                '}';
    }
}
