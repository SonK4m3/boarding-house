package adis.group5.boardingHouse.model;

public class BoardingHouse {
    private int id;
    private String description;
    private Address address;

    public BoardingHouse() {
        this.id = -1;
        this.description = "description";
        this.address = null;
    }

    public BoardingHouse(int id, String description, Address address) {
        this.id = id;
        this.description = description;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BoardingHouse{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", address=" + address +
                '}';
    }
}
