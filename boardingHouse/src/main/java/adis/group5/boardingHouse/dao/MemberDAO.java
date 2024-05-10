package adis.group5.boardingHouse.dao;

import adis.group5.boardingHouse.model.FullName;
import adis.group5.boardingHouse.model.Member;

public class MemberDAO extends DAO {
    public MemberDAO() {
        super();
    }

    public boolean checkLogin(Member m) {
        try {
            connect();
            String sql = "SELECT * FROM se.member WHERE se.member.username = ? AND se.member.password = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, m.getUsername());
            pre.setString(2, m.getPassword());

            rs = pre.executeQuery();
//            id,username,password,phone,gender,dateOfBirth,role,firstName,lastName
            if (rs.next()) {
                m.setId(rs.getInt(1));
                m.setPhone(rs.getString(4));
                m.setGender(rs.getInt(5));
                m.setDateOfBirth(rs.getDate(6));
                m.setRole(rs.getString(7));
                m.setName(new FullName(rs.getString(8), rs.getString(9)));
                close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getTenantId(int memberId) {
        try {
            connect();
            String sql = "SELECT se.tenant.id FROM se.tenant WHERE se.tenant.memberId = ?;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, memberId);

            rs = pre.executeQuery();
            if (rs.next()) {
                int tenantId = rs.getInt(1);
                close();
                return tenantId;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
