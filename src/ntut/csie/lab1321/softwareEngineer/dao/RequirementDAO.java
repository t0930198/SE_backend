package ntut.csie.lab1321.softwareEngineer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public boolean creatRequirement(Requirement requirement, int projectId){
		
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			long currentTime = System.currentTimeMillis();
			con = DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO requirement SET name=?, description=?, star_time=? ,hadfix=?, projectid =?");
			pstm.setString(1, requirement.getRequirementName());
			pstm.setString(2, requirement.getRequirementDescription());
			pstm.setLong(3, currentTime);
			pstm.setBoolean(4, requirement.getRequirementHadfix());
			pstm.setLong(5, projectId);
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
	
	public boolean updateRequirement(Requirement requirement, int id,boolean hadfix, int projectId){
		int updateCount = -1;
		boolean updateStatus = false;
		Connection con = null;
		PreparedStatement pstm = null;
		con = DBConnector.connectToMySQL();
		try{
			pstm = con.prepareStatement("UPDATE requirement SET name=?, description=?, command=?, hadfix=?, type=? WHERE id = '" + id + "'" + "AND projectid = '" + projectId + "'");
			pstm.setString(1, requirement.getRequirementName());
			pstm.setString(2, requirement.getRequirementDescription());
			pstm.setString(3, requirement.getRequirementCommand());
			pstm.setBoolean(4, hadfix);
			pstm.setString(5, requirement.getRequirementType());
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
	public Requirement getRequirementByName(String name, int projectId){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Requirement  requirement = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM requirement WHERE name =" + "'" + name + "'" + "AND projectid =" + "'" + projectId + "'");
			if(rs.next()){
				requirement = new Requirement(name);
				requirement.setId(rs.getInt("id"));
				requirement.setRequirementDescription(rs.getString("description"));
				requirement.setRequirementStartTime(rs.getLong("star_time"));
				requirement.setmRequirementCommand(rs.getString("command"));
				requirement.setRequirementHadfix(rs.getBoolean("hadfix"));
				requirement.setRequirementType(rs.getString("type"));
			}
		}catch (SQLException e){
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
		return requirement;
		
	}
	public Requirement getRequirementByrId(int id, int projectId){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Requirement requirement = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM requirement WHERE id =" + "'" + id + "'" + "AND projectid =" + "'" + projectId + "'");
			if (rs.next()){
				requirement = new Requirement(id);
				requirement.setRequirementName(rs.getString("name"));
				requirement.setRequirementDescription(rs.getString("description"));
				requirement.setRequirementStartTime(rs.getLong("star_time"));
				requirement.setmRequirementCommand(rs.getString("command"));
				requirement.setRequirementHadfix(rs.getBoolean("hadfix"));
				requirement.setRequirementType(rs.getString("type"));
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
	
	public ArrayList<Requirement> getRequirements(int projectid){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Requirement> requirements = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM requirement WHERE projectid = '" + projectid +"'");
			
			requirements = new ArrayList<Requirement>();
			while (rs.next()){
				Requirement requirement = new Requirement(rs.getInt("id"));
				requirement.setRequirementName(rs.getString("name"));
				requirement.setRequirementDescription(rs.getString("description"));
				requirement.setRequirementStartTime(rs.getLong("star_time"));
				requirement.setmRequirementCommand(rs.getString("command"));
				requirement.setRequirementHadfix(rs.getBoolean("hadfix"));
				requirement.setRequirementType(rs.getString("type"));
				requirements.add(requirement);
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
		return requirements;
	}
	
	public boolean delete(int id){

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;

		con = DBConnector.connectToMySQL();

		try{
			stm = con.createStatement();
			int count = stm.executeUpdate("DELETE FROM requirement WHERE id=" + "'" + id + "'");
			if(count == 1)
				return true;
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

