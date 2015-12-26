package ntut.csie.lab1321.softwareEngineer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ntut.csie.lab1321.softwareEngineer.dbConnect.DBConnector;
import ntut.csie.lab1321.softwareEngineer.model.Test;
public class TestDAO {
	private static TestDAO mInstance;
	
	public static TestDAO getInstance(){
		if(mInstance == null){
			mInstance = new TestDAO();
		}
		return mInstance;
	}
	public boolean creatTest(Test test){
		Connection con =null;
		PreparedStatement pstm = null;
		try{
			con =  DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO test SET name=?, description=?, testrid=?");
			pstm.setString(1, test.getTestName());
			pstm.setString(2, test.getTestDescription());
			pstm.setInt(3, test.getTestRid());
			pstm.execute();
			pstm.close();
			return true;
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return false;
	}
	
	public boolean updateTest(Test test,int id){
		int updateCount =-1;
		boolean updateStatus = false;
		Connection con = null;
		PreparedStatement pstm = null;
		con = DBConnector.connectToMySQL();
		try{
			pstm = con.prepareStatement("UPDATE test SET name=?, description=?,testrid=? WHERE id=?");
			pstm.setString(1, test.getTestName());
			pstm.setString(2, test.getTestDescription());
			pstm.setInt(3, test.getTestRid());
			pstm.setInt(4, id);
			updateCount = pstm.executeUpdate();
			
			if(updateCount>0){
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
	public Test getTestByID(int id){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Test test = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM test WHERE id =" + "'" + id + "'");
			if(rs.next()){
				test = new Test(id);
				test.setTestName(rs.getString("name"));
				test.setTestDescription(rs.getString("description"));
				test.setTestRid(rs.getInt("testrid"));				
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
		return test;
	}
	
	public ArrayList<Test> getTests(){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Test> tests =null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM test");
			
			tests = new ArrayList<Test>();
			while(rs.next()){
				Test test = new Test(rs.getInt("id"));
				test.setTestName(rs.getString("name"));
				test.setTestDescription(rs.getString("description"));
				test.setTestRid(rs.getInt("testrid"));
				tests.add(test);
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
		return tests;
	}
	
	

}
