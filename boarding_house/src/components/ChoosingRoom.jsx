import { useEffect, useState } from "react";
import { GET_FLOORS, GET_ROOMS } from "../constant/ENDPOINT";

function ChoosingRoom({
  choosing,
  setChoosing,
  fetchEquipmentRooms,
  setChosenRoom,
}) {
  const [floors, setFloors] = useState([]);
  const [rooms, setRooms] = useState([]);
  const [isFetchEquipment, setIsFetchEquipment] = useState(false);

  const fetchRooms = async (floorId) => {
    var requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch(`${GET_ROOMS}?floorId=${floorId}`, requestOptions)
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        if (result.success) {
          setRooms(result.result);
          return true;
        } else {
          alert(`Tầng ${floorId} không có phòng cho thuê!`);
          return false;
        }
      })
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    const fetchFloors = async () => {
      var requestOptions = {
        method: "GET",
        redirect: "follow",
      };

      fetch(`${GET_FLOORS}?boardingHouseId=1`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
          console.log(result);
          if (result.success) {
            setFloors(result.result);
            if (result.result.length > 0) {
              setChoosing({ ...choosing, floor: result.result[0].id });
              fetchRooms(result.result[0].id);
            }
          } else {
            alert("Tòa nhà không có tầng nào cho thuê!");
          }
        })
        .catch((error) => console.log("error", error));
    };

    fetchFloors();
  }, []);

  return (
    <div className="w-100">
      <h1 className="mb-3">Chọn phòng</h1>
      <div className="mb-3">
        <select
          className="form-select"
          aria-label="Default select example"
          value={choosing.floor}
          onChange={async (e) => {
            const success = await fetchRooms(e.target.value);
            if (success)
              setChoosing((prev) => ({ ...prev, floor: e.target.value }));
          }}
        >
          {/* <option value="-1">Chọn tầng</option> */}
          {floors !== null &&
            floors.map((floor, ind) => (
              <option value={floor.id} key={ind}>
                {floor.name}
              </option>
            ))}
        </select>
      </div>
      <div className="mb-3">
        <select
          className={`form-select ${isFetchEquipment ? "" : "is-invalid"}`}
          aria-label="Default select example"
          value={choosing.room}
          onChange={(e) => {
            setChoosing((prev) => ({ ...prev, room: e.target.value }));
            setChosenRoom(rooms[e.target.value - 1]);
            setIsFetchEquipment(false);
          }}
        >
          <option value="-1">Chọn phòng</option>
          {rooms !== null &&
            rooms.map((room, ind) => (
              <option value={room.id} key={ind}>
                {room.name}
              </option>
            ))}
        </select>
        {choosing.room !== "-1" && (
          <div id="validationServer04Feedback" className="invalid-feedback">
            Hãy bấm chọn để xem đồ trong {rooms[choosing.room - 1].name}
          </div>
        )}
      </div>
      <button
        className="btn btn-primary"
        onClick={async () => {
          await fetchEquipmentRooms();
          setIsFetchEquipment(true);
        }}
      >
        Chọn
      </button>
    </div>
  );
}

export default ChoosingRoom;
