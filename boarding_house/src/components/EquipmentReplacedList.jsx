import { useEffect } from "react";

function EquipmentReplacedList() {
  const [reports, setReports] = useState([]);
  const [filteredReports, setFilteredReports] = useState([]);

  useEffect(() => {}, []);

  return (
    <div>
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
            <th scope="col">Tên</th>
            <th scope="col">Phản hồi trạng thái</th>
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
                  data-bs-target="#reportModal"
                  onClick={() => {
                    setchosenItem(item);
                  }}
                >
                  Cập nhật
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default EquipmentReplacedList;
