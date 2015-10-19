package farmacia.modelo.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import farmacia.modelo.dao.BaseDatos;
import farmacia.modelo.dao.UbicacionDao;

public class UbicacionImp implements UbicacionDao{

	public boolean isUbicacionCorrect(String pasillo, String estante,
			String repisa) {
		int pas = Integer.parseInt(pasillo);
		int est = Integer.parseInt(estante);
		boolean respuesta = false;
		switch (pas) {
		case 1:
			if (est == 1 || est == 2) {
				respuesta = true;
			} else {
				respuesta = false;
			}
			break;

		case 2:
			if (est == 2 || est == 3) {
				respuesta = true;
			} else {
				respuesta = false;
			}
			break;

		case 3:
			if (est == 3 || est == 4) {
				respuesta = true;
			} else {
				respuesta = false;
			}
			break;
		}
		return respuesta;
	}

	@Override
	public int getIdUbicacion(String pasillo, String estante, String repisa) throws SQLException {
		String query = "select * from tblubicacion where pasillo="
				+ pasillo + " and estante=" + estante + " and repisa='"
				+ repisa + "'";
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
