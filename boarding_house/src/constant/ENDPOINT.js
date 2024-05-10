const BASE_URL = "http://localhost:8080";
const API = `${BASE_URL}/api`;

export const LOGIN = `${API}/login`;
export const GET_FLOORS = `${API}/floors`;
export const GET_ROOMS = `${API}/rooms`;
export const GET_EQUIPMENT_ROOMS = `${API}/equipment-rooms`;
export const ADD_EQUIPMENT_ROOM = `${API}/equipment-room/add`;
export const UPDATE_EQUIPMENT_ROOM = `${API}/equipment-room/update`;
export const REMOVE_EQUIPMENT_ROOM = `${API}/equipment-room/remove`;

export const GET_TENANT_ROOM = `${API}/tenant-room`;

export const CREATE_REPORT = `${API}/equipment-room/create-report`;

export const GET_EQUIPMENTS = `${API}/equipments`;