package tom.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBHelper {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/member";
	private static final String NAME = "com.mysql.jdbc.Driver";
	private static final String USER = "root";
	private static final String PASSWD = "1234";
	
	private Connection conn;
	private Statement stmt;
	private ResultSet rtst;
	
	public DBHelper(){
		try {
			Class.forName(NAME); //指定连接类型
			conn = (Connection) DriverManager.getConnection(URL, USER, PASSWD);
			stmt = (Statement) conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement getStatement(){
		return stmt;
	}
}
