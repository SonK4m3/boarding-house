import { useEffect, useState } from "react";
import { CREATE_REPORT } from "../constant/ENDPOINT";

export function objectToQueryString(obj) {
  const queryString = Object.entries(obj)
    .map(([key, value]) => {
      if (Array.isArray(value) && value.length > 0) {
        return value.map((item) => `${key}=${encodeURI(item)}`).join("&");
      } else if (value !== "") {
        return `${key}=${encodeURI(value)}`;
      }
    })
    .join("&");

  return queryString;
}

function ReportEquipment({ item, setchosenItem }) {
  const [reason, setReason] = useState("");
  const storedMember = sessionStorage.getItem("loggedInMember");
  const loggedInMember = storedMember ? JSON.parse(storedMember) : null;

  const handleSubmit = async () => {
    if (reason === "") {
      alert("Hãy nhập lý do!");
      return;
    }

    const params = {
      reason: reason.trim(),
      tenantId: loggedInMember.id,
      equipmentRoomId: item.id,
    };
    console.log(params);
    var raw = "";

    var requestOptions = {
      method: "POST",
      body: raw,
      redirect: "follow",
    };

    fetch(`${CREATE_REPORT}?${objectToQueryString(params)}`, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        alert("Phản hồi thành công!");
      })
      .catch((error) => console.log("error", error));

    console.log(reason);
  };

  useEffect(() => {
    console.log(item);
  }, []);

  return (
    <div
      className="modal fade"
      id="reportModal"
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
              Phản hồi đồ dùng
            </h1>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              onClick={() => {
                setchosenItem(null);
              }}
            ></button>
          </div>
          <div className="modal-body">
            <div className="mb-3">
              <label htmlFor="exampleFormControlInput1" className="form-label">
                Lý do
              </label>
              <input
                type="email"
                className="form-control"
                id="exampleFormControlInput1"
                placeholder="Lý do"
                value={reason}
                onChange={(e) => setReason(e.target.value)}
              />
            </div>
            {/* {item !== null && (
              <div className="mb-3">
                <label
                  htmlFor="exampleFormControlInput1"
                  className="form-label"
                >
                  Đồ dùng
                </label>
                <input
                  type="email"
                  className="form-control"
                  id="exampleFormControlInput1"
                  value={item.name}
                  disabled
                />
              </div>
            )} */}
          </div>
          <div className="modal-footer">
            <button
              type="button"
              className="btn btn-secondary"
              data-bs-dismiss="modal"
              onClick={() => {
                setchosenItem(null);
              }}
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

export default ReportEquipment;
