import { useEffect, useState } from "react";
import EquipmentList from "../components/EquipmentList";
import ChoosingRoom from "../components/ChoosingRoom";
import {
  GET_EQUIPMENT_ROOMS,
  REMOVE_EQUIPMENT_ROOM,
} from "../constant/ENDPOINT";
import EditEquipment from "../components/EditEquipment";

function ManageEquipmentPage() {
  const [choosing, setChoosing] = useState({
    floor: "-1",
    room: "-1",
  });
  const [equipmentRooms, setEquipmentRooms] = useState(null);

  const [chosenRoom, setChosenRoom] = useState({});
  const [chosenEquipmentRoom, setChosenEquipmentRoom] = useState(null);

  const fetchEquipmentRooms = async () => {
    console.log(choosing);
    if (choosing.room === "-1") {
      alert("Hãy chọn một phòng trước khi xem đồ dùng!");
      return;
    }

    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch(`${GET_EQUIPMENT_ROOMS}?roomId=${choosing.room}`, requestOptions)
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        if (result.success) {
          setEquipmentRooms(result.result);
        }
      })
      .catch((error) => console.log("error", error));
  };

  const cancelEdit = () => {
    setChosenEquipmentRoom(null);
  };

  const removeEquipmentRoom = async (equipmentRoomId) => {
    var requestOptions = {
      method: "POST",
      redirect: "follow",
    };

    try {
      const response = await fetch(
        `${REMOVE_EQUIPMENT_ROOM}?equipmentRoomId=${equipmentRoomId}`,
        requestOptions
      );

      const data = await response.json();
      console.log(data);
      if (data.success) {
        setEquipmentRooms(
          equipmentRooms.filter((it) => it.id !== equipmentRoomId)
        );
        alert(`Xóa thành công!`);
      } else {
        alert("Xóa đồ dùng thất bại, hãy thử lại!");
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="w-100 h-100">
      <div className="w-100 d-flex justify-content-between bg-light">
        <div className="w-25 m-3">
          <ChoosingRoom
            choosing={choosing}
            setChoosing={setChoosing}
            fetchEquipmentRooms={fetchEquipmentRooms}
            setChosenRoom={setChosenRoom}
          />
        </div>
        <div className="w-75 m-3">
          {equipmentRooms !== null ? (
            equipmentRooms.length > 0 ? (
              <EquipmentList
                equipmentRooms={equipmentRooms}
                setChosenEquipmentRoom={setChosenEquipmentRoom}
                removeEquipmentRoom={removeEquipmentRoom}
              />
            ) : (
              <div>
                <h4>
                  Chưa có đồ dùng,
                  <strong
                    style={{ cursor: "pointer" }}
                    className=""
                    data-bs-toggle="modal"
                    data-bs-target="#exampleModal"
                  >
                    {" "}
                    Thêm mới ?
                  </strong>
                </h4>
              </div>
            )
          ) : (
            <div>
              <h4>Hãy bấm chọn phòng để xem đồ dùng!</h4>
            </div>
          )}
        </div>
      </div>

      <EditEquipment
        room={chosenRoom}
        chosenEquipmentRoom={chosenEquipmentRoom}
        cancelEdit={cancelEdit}
        updateEquipmentRoom={setEquipmentRooms}
      />
      <div className="m-3">
        <a href="home" className="btn btn-dark">Quay về trang chủ</a>
      </div>
    </div>
  );
}

export default ManageEquipmentPage;
