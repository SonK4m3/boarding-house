import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import MainOwner from "./pages/MainOwner";
import ManageEquipmentPage from "./pages/ManageEquipmentPage";
import StatisticEquipmentPage from "./pages/StatisticEquipmentPage";
import MainTenant from "./pages/MainTenant";
import UpdateEquipmentStatus from "./pages/UpdateEquipmentStatus";

function App() {
  return (
    <div
      style={{
        height: "100%",
        width: "100%",
      }}
    >
      <Routes>
        <Route index element={<Login />} />
        <Route path="home" element={<MainOwner />} />
        <Route path="tenant-home" element={<MainTenant />} />
        <Route path="manage-equipment" element={<ManageEquipmentPage />} />
        <Route
          path="statistic-equipment"
          element={<StatisticEquipmentPage />}
        />
        <Route
          path="update-equipment-status"
          element={<UpdateEquipmentStatus />}
        />
      </Routes>
    </div>
  );
}

export default App;
