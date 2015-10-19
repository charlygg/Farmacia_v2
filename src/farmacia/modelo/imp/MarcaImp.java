package farmacia.modelo.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import farmacia.modelo.dao.BaseDatos;

public class MarcaImp {
	
	public static int getIdMarca(String strMarca) throws SQLException{
		String query = "select * from tblMarca where Marca='" + strMarca + "'";
		System.out.println(query);
		int id = 0;
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		if(res.next()){
			id = res.getInt(1);
		}
		cn.close();
		return id;
	}

}
