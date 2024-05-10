package adis.group5.boardingHouse.model;

public class RoomType {
    private int id;
    private int size;
    private int price;
    private String name;

    public RoomType() {
        this.id = -1;
        this.size = 0;
        this.price = 0;
        this.name = "name";
    }

    public RoomType(int id, int size, int price, String name) {
        this.id = id;
        this.size = size;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", size=" + size +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
