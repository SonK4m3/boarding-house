package adis.group5.boardingHouse.model;

public class EquipmentRoom {
    private int id;
    private Room room;
    private Equipment equipment;

    public EquipmentRoom(int id, Room room, Equipment equipment) {
        this.id = id;
        this.equipment = equipment;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
