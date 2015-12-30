package ntut.csie.lab1321.softwareEngineer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ntut.csie.lab1321.softwareEngineer.dbConnect.DBConnector;
import ntut.csie.lab1321.softwareEngineer.model.Requirement_Relation;;

public class Requirement_RelationDAO {
	private static Requirement_RelationDAO mInstance;
	
	public static Requirement_RelationDAO getInstance(){
		if(mInstance == null){
			mInstance = new Requirement_RelationDAO();
		}
		return mInstance;
	}
	public boolean creatRequirementRelation(Requirement_Relation requirementrelation,int rid){
		Connection con =null;
		PreparedStatement pstm = null;
		try{
			con =  DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO requirement_relation SET rid=?, relation_id=?");
			pstm.setInt(1, requirementrelation.getRequirementId());
			pstm.setInt(2, requirementrelation.getRelationId());
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
	
	public Requirement_Relation getRelationByRequirementId(int rid){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Requirement_Relation requirementrelation = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM requirement_relation WHERE rid = '" + rid +"'");
			if(rs.next()){
				requirementrelation = new Requirement_Relation(rid);
				requirementrelation.setId(rs.getInt("id"));
				requirementrelation.setRelationId(rs.getInt("relation_id"));
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
		return requirementrelation;
	}
	
	public ArrayList<Requirement_Relation> getRelationsByRequirementId(int rid){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		ArrayList<Requirement_Relation> requirementrelations = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM requirement_relation WHERE rid = '" + rid +"'");
			requirementrelations = new ArrayList<Requirement_Relation>();
			while(rs.next()){
				Requirement_Relation requirementrelation = new Requirement_Relation(rs.getInt("rid"));
				requirementrelation.setId(rs.getInt("id"));
				requirementrelation.setRelationId(rs.getInt("relation_id"));
				requirementrelations.add(requirementrelation);
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
		return requirementrelations;
	}
	
	public boolean deleteone(int id){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		con = DBConnector.connectToMySQL();
		
		try{
			stm = con.createStatement();
			int count = stm.executeUpdate("DELETE FROM requirement_relation WHERE id =" + "'" + id + "'");
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
	
	public boolean deletemanny(int rid){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		con = DBConnector.connectToMySQL();
		
		try{
			stm = con.createStatement();
			int count = stm.executeUpdate("DELETE * FROM requirement_relation WHERE rid =" + "'" + rid + "'");
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
