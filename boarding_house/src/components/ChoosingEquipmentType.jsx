import { useState } from "react";

const equipmentTypeList = [
  // { id: 1, name: "Tất cả đồ dùng" },
  // { id: 2, name: "Đồ dùng không cần thay thế" },
  { id: 3, name: "Đồ dùng cần thay thế" },
];

function ChoosingEquipmentType({
  chosenEquipmentType,
  setChosenEquipmentType,
  previousStep,
  nextStep,
}) {
  return (
    <div
      className="w-100"
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <div className="mb-3">
        <label htmlFor="exampleFormControlInput1" className="form-label">
          Chọn loại đồ dùng:
        </label>

        <select
          className="form-select"
          aria-label="Default select example"
          id="exampleFormControlInput1"
          value={chosenEquipmentType ? chosenEquipmentType.id : "-1"}
          onChange={(e) => setChosenEquipmentType(e.target.value)}
        >
          <option value="-1">Chọn loại</option>
          {equipmentTypeList.map((et, ind) => (
            <option key={ind} value={et.id}>
              {et.name}
            </option>
          ))}
        </select>
      </div>
      <div>
        <button
          className="btn btn-primary"
          onClick={() => {
            if (chosenEquipmentType === "-1" || chosenEquipmentType === null) {
              alert("Hãy chọn loại đồ dùng trước!");
              return;
            } else {
              nextStep();
            }
          }}
        >
          Chọn
        </button>
      </div>
    </div>
  );
}

export default ChoosingEquipmentType;
