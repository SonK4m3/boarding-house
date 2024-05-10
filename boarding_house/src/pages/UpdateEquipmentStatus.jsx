import { useEffect, useState } from "react";
import { GET_EQUIPMENT_ROOMS, GET_TENANT_ROOM } from "../constant/ENDPOINT";
import EquipmentListRoom from "../components/EquipmenListRoom";
import ReportEquipment from "../components/ReportEquipment";

function UpdateEquipmentStatus() {
  const tenantRoom = sessionStorage.getItem("tenantRoom");
  const room = tenantRoom ? JSON.parse(tenantRoom) : null;
  const [equipmentRooms, setEquipmentRooms] = useState(null);
  const [chosenItem, setchosenItem] = useState(null);

  useEffect(() => {
    const fetchEquipmentRooms = async () => {
      var requestOptions = {
        method: "GET",
        redirect: "follow",
      };

      fetch(`${GET_EQUIPMENT_ROOMS}?roomId=${room.id}`, requestOptions)
        .then((response) => response.json())
        .then((result) => {
          console.log(result);
          if (result.success) {
            setEquipmentRooms(result.result);
          }
        })
        .catch((error) => console.log("error", error));
    };

    if (room !== null) {
      fetchEquipmentRooms();
    }
  }, []);

  return (
    <div>
      <h4>{room.name}</h4>
      <div>
        {equipmentRooms && (
          <EquipmentListRoom
            equipmentRooms={equipmentRooms}
            setchosenItem={setchosenItem}
          />
        )}
        <ReportEquipment item={chosenItem} setchosenItem={setchosenItem} />
      </div>
    </div>
  );
}

export default UpdateEquipmentStatus;
