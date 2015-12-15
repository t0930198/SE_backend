package ntut.csie.lab1321.softwareEngineer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ntut.csie.lab1321.softwareEngineer.dbConnect.DBConnector;
import ntut.csie.lab1321.softwareEngineer.model.Account;

public class AccountDAO {
	private static AccountDAO mInstance;

	public static AccountDAO getInstance() {
		if (mInstance == null) {
			mInstance = new AccountDAO();
		}
		return mInstance;
	}

	public boolean creatAccount(Account account) {
		Connection con = null;
		PreparedStatement pstm = null;j
		try {
			con = DBConnector.connectToMySQL();
			pstm = con.prepareStatement("INSERT INTO user SET account=?, password=?, email=?");
			pstm.setString(1, account.getmUsername());
			pstm.setString(2, account.getmPassword());
			pstm.setString(3, account.getmEmail());
			pstm.execute();
			pstm.close();
			return true;
		} catch (SQLException e) {
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

	public boolean updateAccount(Account account, int id) {
		int updateCount = -1;
		boolean updateStatus = false;
		Connection con = null;
		PreparedStatement pstm = null;
		con = DBConnector.connectToMySQL();
		try {
			pstm = con.prepareStatement("UPDATE user SET account=?, password=?, email=? WHERE id=?");
			pstm.setString(1, account.getmUsername());
			pstm.setString(2, account.getmPassword());
			pstm.setString(3, account.getmEmail());
			pstm.setInt(4, id);
			updateCount = pstm.executeUpdate();

			// 是否有更新
			if (updateCount > 0) {
				updateStatus = true;
			}
			pstm.close();
			con.close();
		} catch (SQLException e) {
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
	public Account getAccountByName(String name){
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Account account = null;
		try{
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM user WHERE account =" + "'" + name + "'");
			if (rs.next()) {
				account = new Account(name);
				account.setmUsername(rs.getString("id"));
				account.setmPassword(rs.getString("password"));
				account.setmEmail(rs.getString("email"));
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
		return account;
	}
	
	public Account getAccountById(int id) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		Account account = null;
		try {
			con = DBConnector.connectToMySQL();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM user WHERE id =" + "'" + id + "'");
			if (rs.next()) {
				account = new Account(id);
				account.setmUsername(rs.getString("account"));
				account.setmPassword(rs.getString("password"));
				account.setmEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
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
		return account;
	}

}
