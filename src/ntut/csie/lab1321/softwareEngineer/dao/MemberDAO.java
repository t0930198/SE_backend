package ntut.csie.lab1321.softwareEngineer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ntut.csie.lab1321.softwareEngineer.dbConnect.DBConnector;
import ntut.csie.lab1321.softwareEngineer.model.Account;
import ntut.csie.lab1321.softwareEngineer.model.Member;;

public class MemberDAO {
	private static MemberDAO mInstance;
	
	public static MemberDAO getInstance() {
		if (mInstance == null) {
			mInstance = new MemberDAO();
		}
		return mInstance;
	}
	
	public boolean createMemberDAO(Member member){
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			con = DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO member SET user_id=?, project_id=?, role=?");
			pstm.setInt(1, member.getUserId());
			pstm.setInt(2, member.getProjectId());
			pstm.setString(3, member.getRole());
			pstm.execute();
			pstm.close();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//update暫不支援role修改
//	public boolean updateMember(Member member){
//		
//	}
	//用於找此User所有專案
	public ArrayList<Member> getMembersByUserId(int userid){
		Connection con = null;
		Statement stm = null;		
		ResultSet rs = null;
		ArrayList<Member> Members = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM member WHERE user_id =" + "'" + userid + "'");
			Members = new ArrayList<Member>();
			while (rs.next()) {
				Member member = new Member(userid);
				member.setId(rs.getInt("id"));
				member.setProjectId(rs.getInt("project_id"));
				member.setRole(rs.getString("role"));
				Members.add(member);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Members;
	}

	public boolean delete(int projectid){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		con = DBConnector.connectToMySQL();
		
		try{
			stm = con.createStatement();
			int count = stm.executeUpdate("DELETE FROM Member WHERE project_id =" + "'" + projectid + "'");
			if(count == 1){
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
