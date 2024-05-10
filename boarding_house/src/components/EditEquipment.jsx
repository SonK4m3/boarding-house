import { useEffect, useState } from "react";
import {
  ADD_EQUIPMENT_ROOM,
  GET_EQUIPMENTS,
  UPDATE_EQUIPMENT_ROOM,
} from "../constant/ENDPOINT";

function EditEquipment({
  room,
  chosenEquipmentRoom,
  cancelEdit,
  updateEquipmentRoom,
}) {
  if (room === null || room === undefined) return;

  const [equipments, setEquipments] = useState([]);
  const [chosenEquipment, setChoosingEquipment] = useState(null);

  const onCancel = () => {
    setChoosingEquipment(null);
    cancelEdit();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(room, chosenEquipmentRoom, chosenEquipment);

    if (chosenEquipmentRoom !== null) {
      const newEquipmentRoom = {
        ...chosenEquipmentRoom,
        equipment: chosenEquipment,
      };

      var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");

      var requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(newEquipmentRoom),
        redirect: "follow",
      };

      try {
        const response = await fetch(
          `${UPDATE_EQUIPMENT_ROOM}`,
          requestOptions
        );

        const data = await response.json();
        console.log(data);
        if (data.success) {
          alert("Cập nhật thành công!");
          updateEquipmentRoom((prev) => {
            const updatedArray = prev.map((item) => {
              if (item.id === data.result.id) {
                return { ...item, ...data.result };
              }
              return item;
            });

            const isNewItem = !prev.some((item) => item.id === data.result.id);
            if (isNewItem) {
              updatedArray.push(data.result);
            }

            return updatedArray;
          });
          onCancel();
        } else {
          alert("Cập nhật đồ dùng thất bại, hãy thử lại!");
        }
      } catch (err) {
        console.log(err);
      }
    } else {
      const newEquipmentRoom = {
        equipment: chosenEquipment,
        room: room,
      };

      var myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/json");

      var requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: JSON.stringify(newEquipmentRoom),
        redirect: "follow",
      };

      try {
        const response = await fetch(`${ADD_EQUIPMENT_ROOM}`, requestOptions);

        const data = await response.json();
        console.log(data);
        if (data.success) {
          alert("Thêm thành công!");

          updateEquipmentRoom((prev) => [...prev, data.result]);
          onCancel();
        } else {
          alert("Thêm đồ dùng thất bại, hãy thử lại!");
        }
      } catch (err) {
        console.log(err);
      }
    }
  };

  useEffect(() => {
    const fetchEquipments = async () => {
      var requestOptions = {
        method: "GET",
        redirect: "follow",
      };

      fetch(`${GET_EQUIPMENTS}`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
          console.log(result);
          if (result.success) {
            setEquipments(result.result);
          } else {
            alert("Hệ thống xảy ra lỗi, vui lòng thử lại!");
          }
        })
        .catch((error) => console.log("error", error));
    };

    fetchEquipments();
  }, []);

  return (
    <div
      className="modal fade"
      id="exampleModal"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      tabIndex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 className="modal-title fs-5" id="exampleModalLabel">
              {chosenEquipmentRoom
                ? "Cập nhật đồ dùng cho "
                : "Thêm đồ dùng mới cho "}
              {room.name}
            </h1>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              onClick={onCancel}
            ></button>
          </div>
          <div className="modal-body">
            {chosenEquipmentRoom ? (
              <>
                <div className="mb-3">
                  <label
                    htmlFor="exampleFormControlInput1"
                    className="form-label"
                  >
                    Đồ dùng đã chọn:
                  </label>

                  <select
                    className="form-select"
                    aria-label="Disabled select example"
                    id="exampleFormControlInput1"
                    disabled
                  >
                    <option value={chosenEquipmentRoom.equipment.id}>
                      {chosenEquipmentRoom.equipment.name}
                    </option>
                  </select>
                </div>
                <div className="mb-3">
                  <label
                    htmlFor="exampleFormControlInput2"
                    className="form-label"
                  >
                    Đồ dùng thay thế:
                  </label>
                  <select
                    className={`form-select`}
                    aria-label="Default select example"
                    id="exampleFormControlInput2"
                    value={chosenEquipment ? chosenEquipment.id : "-1"}
                    onChange={(e) => {
                      setChoosingEquipment(equipments[e.target.value - 1]);
                    }}
                  >
                    <option value="-1">Chọn đồ dùng</option>
                    {equipments !== null &&
                      equipments.map((equipment, ind) => (
                        <option value={equipment.id} key={ind}>
                          {equipment.name}
                        </option>
                      ))}
                  </select>
                </div>
              </>
            ) : (
              <>
                <div className="mb-3">
                  <label
                    htmlFor="exampleFormControlInput3"
                    className="form-label"
                  >
                    Đồ dùng mới:
                  </label>
                  <select
                    className={`form-select`}
                    aria-label="Default select example"
                    id="exampleFormControlInput3"
                    value={chosenEquipment ? chosenEquipment.id : "-1"}
                    onChange={(e) => {
                      setChoosingEquipment(equipments[e.target.value - 1]);
                    }}
                  >
                    <option value="-1">Chọn đồ dùng</option>
                    {equipments !== null &&
                      equipments.map((equipment, ind) => (
                        <option value={equipment.id} key={ind}>
                          {equipment.name}
                        </option>
                      ))}
                  </select>
                </div>
              </>
            )}
          </div>
          <div className="modal-footer">
            <button
              type="button"
              className="btn btn-secondary"
              data-bs-dismiss="modal"
              onClick={onCancel}
            >
              Quay lại
            </button>
            <button
              type="button"
              className="btn btn-primary"
              data-bs-dismiss="modal"
              onClick={handleSubmit}
            >
              Lưu thay đổi
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EditEquipment;
