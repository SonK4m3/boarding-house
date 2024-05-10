import { useEffect, useState } from "react";

const equipmentProp = {
  id: -1,
  equipment: {
    id: -1,
    name: "",
  },
  room: {
    id: -1,
    name: "",
    roomType: {
      id: -1,
      size: -1,
      price: -1,
      name: "",
    },
    floor: {
      id: -1,
      name: -1,
      description: "",
      boardingHouse: {
        id: -1,
        description: "",
      },
    },
  },
};

function EquipmentList({
  equipmentRooms,
  setChosenEquipmentRoom,
  removeEquipmentRoom,
}) {
  const [search, setSearch] = useState("");
  const [filteredEquipmentRooms, setFilteredEquipmentRooms] = useState([]);

  const handleSearch = (value) => {
    clearTimeout(timeoutId);

    timeoutId = setTimeout(() => {
      setSearch(value);
    }, 300);
  };

  useEffect(() => {
    const filteredList = equipmentRooms.filter((item) =>
      item.equipment.name.toLowerCase().includes(search.toLowerCase())
    );
    setFilteredEquipmentRooms(filteredList);
  }, [equipmentRooms, search]);

  return (
    <div className="w-100">
      {equipmentRooms.length > 0 && (
        <div className="mb-3">
          <h2>
            Đồ dùng của{" "}
            <span className="text-primary">{equipmentRooms[0].room.name}</span>
          </h2>
        </div>
      )}
      <div className="mb-3 d-flex">
        <input
          className="me-3"
          type="text"
          name="search"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
        <button className="btn btn-primary" onClick={() => {}}>
          Tìm
        </button>
      </div>
      <table className="table">
        <thead className="table-light">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Tên đồ dùng</th>
            <th scope="col">Thay đổi</th>
          </tr>
        </thead>
        <tbody>
          {filteredEquipmentRooms.map((item, ind) => (
            <tr key={ind}>
              <th scope="row">{ind + 1}</th>
              <td>{item.equipment.name}</td>
              <td>
                <button
                  className="btn btn-info me-3"
                  data-bs-toggle="modal"
                  data-bs-target="#exampleModal"
                  onClick={() => setChosenEquipmentRoom(item)}
                >
                  Cập nhật
                </button>
                <button
                  className="btn btn-danger"
                  onClick={() => removeEquipmentRoom(item.id)}
                >
                  Xóa
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button
        className="btn btn-success"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
      >
        Thêm mới
      </button>
    </div>
  );
}

export default EquipmentList;
