package adis.group5.boardingHouse.dao;

import adis.group5.boardingHouse.Util.Util;
import adis.group5.boardingHouse.dto.ReportDTO;
import adis.group5.boardingHouse.model.EquipmentRoom;
import adis.group5.boardingHouse.model.Report;
import adis.group5.boardingHouse.model.Room;
import adis.group5.boardingHouse.model.RoomType;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO extends DAO {
    public ReportDAO() {
        super();
    }

    public boolean createReport(Report report) {
        try {
            connect();
            String sql = "    INSERT INTO se.report(reason, state, se.report.time, tenantId, equipmentroomId) VALUES (?, ?, ?, ?, ?);";
            pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, report.getReason());
            pre.setString(2, report.getState());
            pre.setDate(3, Util.convertToSqlDate(report.getTime()));
            pre.setInt(4, report.getTenant().getId());
            pre.setInt(5, report.getEquipmentRoom().getId());

            int affectedRows = pre.executeUpdate();
            if (affectedRows > 0) {
                rs = pre.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return true;
                }
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ReportDTO> getStatisticEquipment() {
        List<ReportDTO> reports = new ArrayList<>();

        try {
            connect();
            String sql = "SELECT e.name AS equipmentName, COUNT(r.id) AS reportCount\n" +
                    "        FROM se.report r\n" +
                    "        JOIN se.equipmentroom er ON r.equipmentroomId = er.id\n" +
                    "        JOIN se.equipment e ON er.equipmentId = e.id\n" +
                    "        GROUP BY e.name ORDER BY reportCount DESC;";
            pre = conn.prepareStatement(sql);

            rs = pre.executeQuery();

            while (rs.next()) {
                ReportDTO room = new ReportDTO(rs.getInt(1), rs.getString(2), rs.getInt(3));
                reports.add(room);
            }

            close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reports;
    }
}
