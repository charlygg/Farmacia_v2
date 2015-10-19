package farmacia.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
	
	protected String db = "Farmacia.mdb";
	protected String user = "";
	protected String password = "";
//	protected String url = "jdbc:odbc:Driver=Microsoft Access Driver (*.mdb);DBQ="+db;
	protected String urlUCanAccess = "jdbc:ucanaccess://"+db;
	protected Connection cn;
	
	public BaseDatos(){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn=DriverManager.getConnection(urlUCanAccess, "", "");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection(){
		return cn;
	}

}
