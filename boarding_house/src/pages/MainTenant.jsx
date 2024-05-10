import { useEffect, useRef, useState } from "react";
import { GET_TENANT_ROOM } from "../constant/ENDPOINT";

function MainTenant() {
  const storedMember = sessionStorage.getItem("loggedInMember");
  const loggedInMember = storedMember ? JSON.parse(storedMember) : null;
  const tenantRoom = useRef(null);
  const [load, setLoad] = useState(false);

  useEffect(() => {
    const fetchTenantRoom = async () => {
      var requestOptions = {
        method: "GET",
        redirect: "follow",
      };

      try {
        const response = await fetch(
          `${GET_TENANT_ROOM}?memberId=${loggedInMember.id}`,
          requestOptions
        );

        const data = await response.json();
        console.log(data);
        if (data.success) {
          tenantRoom.current = data.result;
          sessionStorage.setItem("tenantRoom", JSON.stringify(data.result));
        } else {
          alert("Bạn chưa thuê trọ, hay thuê trọ để xem thông tin!");
        }
      } catch (err) {
        console.log(err);
      }
    };

    if (loggedInMember !== null) {
      console.log(loggedInMember);
      fetchTenantRoom();
    }
  }, []);

  return (
    <div className="w-100 h-100 d-flex justify-content-center">
      <div className="w-auto text-center">
        <h1 className="mb-3">Tenant home page</h1>

        <div className="mb-5">
          {loggedInMember !== null && (
            <h4>
              {loggedInMember.name.firstName} {loggedInMember.name.lastName}
            </h4>
          )}
          {tenantRoom.current !== null ?? <h5>{tenantRoom.current.name}</h5>}
        </div>

        <a
          href="/update-equipment-status"
          className="btn btn-primary d-block mb-3"
        >
          Update status of equipment
        </a>
      </div>
    </div>
  );
}

export default MainTenant;
