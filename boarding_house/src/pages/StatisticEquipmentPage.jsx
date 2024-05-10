import { useState } from "react";
import ChoosingEquipmentType from "../components/ChoosingEquipmentType";
import ChoosingStatisticType from "../components/ChoosingStatisticType";
import EquipmentReplacedList from "../components/EquipmentReplacedList";

function StatisticEquipmentPage() {
  const [step, setStep] = useState(1);

  const [chosenEquipmentType, setChosenEquipmentType] = useState(null);
  const [chosenStatisticType, setChosenStatisticType] = useState(null);

  const nextStep = () => {
    setStep((prev) => prev + 1);
  };

  const previousStep = () => {
    setStep((prev) => prev - 1);
  };

  return (
    <div className="w-100 h-100 text-center">
      <h1>Thống kê đồ dùng</h1>
      <div className="m-3">
        {step === 1 && (
          <ChoosingEquipmentType
            chosenEquipmentType={chosenEquipmentType}
            setChosenEquipmentType={setChosenEquipmentType}
            previousStep={previousStep}
            nextStep={nextStep}
          />
        )}
        {step === 2 && (
          <ChoosingStatisticType
            chosenStatisticType={chosenStatisticType}
            setChosenStatisticType={setChosenStatisticType}
            previousStep={previousStep}
            nextStep={nextStep}
          />
        )}
        {step === 3 && <EquipmentReplacedList />}
      </div>
      <div className="m-3">
        <a href="home" className="btn btn-dark">
          Quay lại trang chủ
        </a>
      </div>
    </div>
  );
}

export default StatisticEquipmentPage;
