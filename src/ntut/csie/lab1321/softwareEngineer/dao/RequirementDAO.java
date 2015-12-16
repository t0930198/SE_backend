package ntut.csie.lab1321.softwareEngineer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ntut.csie.lab1321.softwareEngineer.dbConnect.DBConnector;
import ntut.csie.lab1321.softwareEngineer.model.Requirement;
public class RequirementDAO {
	private static RequirementDAO mInstance;
	
	public static RequirementDAO getInstance(){
		if (mInstance == null) {
			mInstance = new RequirementDAO();
		}
		return mInstance;
	}
	public boolean creatRequirement(Requirement requirement){
		
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			long currentTime = System.currentTimeMillis();
			con = DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO requirement SET name=?, description=?, star_time=?");
			pstm.setString(1, requirement.getmReqirename());
			pstm.setString(2, requirement.getmReqiredescription());
			pstm.setLong(3, currentTime);
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
	
	public boolean updateRequirement(Requirement requirement, int id){
		int updateCount = -1;
		boolean updateStatus = false;
		Connection con = null;
		PreparedStatement pstm = null;
		con = DBConnector.connectToMySQL();
		try{
			pstm = con.prepareStatement("UPDATE requirement SET name=?, description=?, command=? WHERE id =?");
			pstm.setString(1, requirement.getmReqirename());
			pstm.setString(2, requirement.getmReqiredescription());
			pstm.setString(3, requirement.getmReqirecommand());
			pstm.setInt(4, id);
			updateCount = pstm.executeUpdate();
			
			if (updateCount > 0) {
				updateStatus = true;
			}
			pstm.close();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateStatus;
	}
	
	public Requirement getRequirementByrId(int id){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Requirement requirement = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM requirement WHERE id =" + "'" + id + "'");
			if (rs.next()){
				requirement = new Requirement(id);
				requirement.setmReqirename(rs.getString("name"));
				requirement.setmReqiredescription(rs.getString("description"));
				requirement.setmReqirestartime(rs.getLong("star_time"));
				requirement.setmReqirecommand(rs.getString("command"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return requirement;
	}
	public boolean delete(int id){

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;

		con = DBConnector.connectToMySQL();

		try{
			stm = con.createStatement();
			rs = stm.executeQuery("DELETE FROM requirement WHERE id = '"+id+"'");
			if(rs.rowDeleted())
				return true;
			else 
				return false;
		}
		catch(SQLException e){
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

