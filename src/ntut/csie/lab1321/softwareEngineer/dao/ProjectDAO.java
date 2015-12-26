package ntut.csie.lab1321.softwareEngineer.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ntut.csie.lab1321.softwareEngineer.dbConnect.DBConnector;
import ntut.csie.lab1321.softwareEngineer.model.Project;

public class ProjectDAO {
	private static ProjectDAO mInstance;
	public static ProjectDAO getInstance(){
		if(mInstance == null){
			mInstance = new ProjectDAO();
		}
		return mInstance;
	}
	public boolean createProject(Project project){
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			con = DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO project SET name=?, notes=?");
			pstm.setString(1, project.getName());
			pstm.setString(2, project.getNote());
			pstm.execute();
			pstm.close();
			return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean updateProject(Project project, int id){
		int updateCount = -1;
		boolean updateStatus = false;
		Connection con = null;
		PreparedStatement pstm = null;
		con = DBConnector.connectToMySQL();
		try{
			pstm = con.prepareStatement("UPDATE project SET name=?, notes=? WHERE id=?");
			pstm.setString(1, project.getName());
			pstm.setString(2, project.getNote());
			pstm.setInt(3, id);
			
			updateCount = pstm.executeUpdate();
			if(updateCount > 0){
				updateStatus = true;
			}
			pstm.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return updateStatus;
	}
	public Project getProjectByName(String name){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Project project = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM project WHERE name =" + "'" + name + "'");
			if(rs.next()){
				project = new Project(name);
				project.setId(rs.getInt("id"));
				project.setNote(rs.getString("notes"));
				
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
		return project;
	}
	public Project getProjectById(int id){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Project project = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM project WHERE id =" + "'" + id + "'");
			if(rs.next()){
				project = new Project(id);
				project.setName(rs.getString("name"));
				project.setNote(rs.getString("notes"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
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
		return project;
	}
	public boolean delete(int projectId){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		con = DBConnector.connectToMySQL();

		try{
			stm = con.createStatement();
			int count = stm.executeUpdate("DELETE FROM project WHERE id =" + "'" + projectId + "'");
			if(count == 1){
				return true;
			}
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
