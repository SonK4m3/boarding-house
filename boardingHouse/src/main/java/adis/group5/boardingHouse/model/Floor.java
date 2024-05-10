package adis.group5.boardingHouse.model;

public class Floor {
    private int id;
    private String name;
    private String description;
    private BoardingHouse boardingHouse;

    public Floor(){
        this.id = -1;
        this.name = "name";
        this.description = "description";
        this.boardingHouse = null;
    }
    public Floor(int id, String name, String description, BoardingHouse boardingHouse) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.boardingHouse = boardingHouse;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BoardingHouse getBoardingHouse() {
        return boardingHouse;
    }

    public void setBoardingHouse(BoardingHouse boardingHouse) {
        this.boardingHouse = boardingHouse;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", boardingHouse=" + boardingHouse +
                '}';
    }
}
