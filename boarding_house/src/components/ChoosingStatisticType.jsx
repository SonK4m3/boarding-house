import { useState } from "react";

const equipmentTypeList = [{ id: 1, name: "Số lượng" }];

function ChoosingStatisticType({
  chosenStatisticType,
  setChosenStatisticType,
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
          Chọn loại thống kê:
        </label>

        <select
          className="form-select"
          aria-label="Default select example"
          id="exampleFormControlInput1"
          value={chosenStatisticType ? chosenStatisticType.id : "-1"}
          onChange={(e) => setChosenStatisticType(e.target.value)}
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
        <button className="btn btn-primary me-3" onClick={previousStep}>
          Quay lại
        </button>
        <button
          className="btn btn-primary"
          onClick={() => {
            if (chosenStatisticType === "-1" || chosenStatisticType === null) {
              alert("Hãy chọn loại thống kê dùng trước!");
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

export default ChoosingStatisticType;
