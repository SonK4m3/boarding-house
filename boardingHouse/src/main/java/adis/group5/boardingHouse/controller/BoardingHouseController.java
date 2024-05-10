package adis.group5.boardingHouse.controller;

import adis.group5.boardingHouse.dao.*;
import adis.group5.boardingHouse.dto.ReportDTO;
import adis.group5.boardingHouse.model.*;
import adis.group5.boardingHouse.model.response.DataResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BoardingHouseController {
    private final RoomDAO roomDAO = new RoomDAO();
    private final FloorDAO floorDAO = new FloorDAO();
    private final EquipmentRoomDAO equipmentRoomDAO = new EquipmentRoomDAO();
    private final EquipmentDAO equipmentDAO = new EquipmentDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    private final ReportDAO reportDAO = new ReportDAO();
    private final Gson gson = new Gson();

    @GetMapping("/rooms")
    public ResponseEntity<?> getRoomsOnFloor(@RequestParam int floorId) {
        try {
            List<Room> rooms = roomDAO.getRoomsByFloor(new Floor(floorId, "", "", null)); // Assuming you have a method to retrieve all rooms

            if (rooms != null && !rooms.isEmpty()) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", rooms)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "No rooms found", null)), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tenant-room")
    public ResponseEntity<?> getTenantRoom(@RequestParam int memberId) {
        try {

            int tenantId = memberDAO.getTenantId(memberId);
            System.out.println(tenantId);
            Room room = roomDAO.getRoomByIdTenant(tenantId);
            if (room != null) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", room)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "No room found", null)), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/floors")
    public ResponseEntity<?> getFloorsInBoardingHouse(@RequestParam int boardingHouseId) {
        try {
            List<Floor> floors = floorDAO.getFloorsById(boardingHouseId);

            if (floors != null && !floors.isEmpty()) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", floors)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "No floor found in boarding house", null)), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/equipment-rooms")
    public ResponseEntity<?> getEquipmentInRoom(@RequestParam int roomId) {
        try {
            List<EquipmentRoom> equipmentRoomList = equipmentRoomDAO.getEquipmentsInRoom(roomId);

            return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", equipmentRoomList)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/equipment-room/add")
    public ResponseEntity<?> addEquipmentInToRoom(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody();
        try {
            EquipmentRoom equipmentRoom = new Gson().fromJson(body, EquipmentRoom.class);

            boolean success = equipmentRoomDAO.addEquipmentRoom(equipmentRoom);
            if (success) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", equipmentRoom)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Failure", null)), HttpStatus.CONFLICT);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/equipment-room/update")
    public ResponseEntity<?> updateEquipmentInToRoom(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody();
        try {
            EquipmentRoom equipmentRoom = new Gson().fromJson(body, EquipmentRoom.class);

            boolean success = equipmentRoomDAO.updateEquipmentRoom(equipmentRoom);
            if (success) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", equipmentRoom)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Failure", null)), HttpStatus.CONFLICT);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/equipment-room/remove")
    public ResponseEntity<?> removeEquipmentInToRoom(@RequestParam int equipmentRoomId) {
        try {
            boolean success = equipmentRoomDAO.removeEquipmentRoom(equipmentRoomId);
            if (success) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", null)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Failure", null)), HttpStatus.CONFLICT);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/equipment-room/create-report")
    public ResponseEntity<?> createReport(@RequestParam String reason, @RequestParam int tenantId, @RequestParam int equipmentRoomId) {
        try {
            Report report = new Report(-1, reason, "not-done", new Date(),
                    new Tenant(tenantId, "", "", null, "", -1, null, ""),
                    new EquipmentRoom(equipmentRoomId, null, null));

            boolean success = reportDAO.createReport(report);
            if (success) {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", true)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Failure", null)), HttpStatus.CONFLICT);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/statistic-reports")
    public ResponseEntity<?> getStatisticReport() {
        try {
            List<ReportDTO> equipmentRoomList = reportDAO.getStatisticEquipment();

            return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", equipmentRoomList)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/equipments")
    public ResponseEntity<?> getEquipments() {
        try {
            List<Equipment> equipmentList = equipmentDAO.getEquipments();

            return new ResponseEntity<>(gson.toJson(new DataResponse<>(true, "Success", equipmentList)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(gson.toJson(new DataResponse<>(false, "Something went wrong", null)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
