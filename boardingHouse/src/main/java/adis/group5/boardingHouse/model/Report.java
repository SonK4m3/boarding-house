package adis.group5.boardingHouse.model;

import java.util.Date;

public class Report {
    private int id;
    private String reason;
    private String state;
    private Date time;
    private Tenant tenant;
    private EquipmentRoom equipmentRoom;

    public Report(int id, String reason, String state, Date time, Tenant tenant, EquipmentRoom equipmentRoom) {
        this.id = id;
        this.reason = reason;
        this.state = state;
        this.time = time;
        this.tenant = tenant;
        this.equipmentRoom = equipmentRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public EquipmentRoom getEquipmentRoom() {
        return equipmentRoom;
    }

    public void setEquipmentRoom(EquipmentRoom equipmentRoom) {
        this.equipmentRoom = equipmentRoom;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
