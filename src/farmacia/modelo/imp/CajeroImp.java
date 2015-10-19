package farmacia.modelo.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import farmacia.modelo.dao.BaseDatos;
import farmacia.modelo.dao.CajeroDao;
import farmacia.modelo.dto.Cajero;

public class CajeroImp implements CajeroDao {

	@Override
	public int insertCajero(Cajero cajero) throws SQLException{
		int resultado = 0;
		String query = "INSERT INTO tblCajeros values (?,?,?,?,?)";
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		PreparedStatement ps = cn.prepareStatement(query);
		ps.setInt(1, cajero.getId());
		ps.setString(2, cajero.getNombre());
		ps.setString(3, cajero.getApellido());
		ps.setString(4, cajero.getPassword());
		ps.setInt(5, cajero.getEdad());
		resultado = resultado + ps.executeUpdate();
		cn.close();
		return resultado;
	}
	
	public static boolean isCajeroExists(int id) throws SQLException{
		boolean resultado = false;
		String query = "select * from tblCajeros where ID = "+id;
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		if(res.next()){
			resultado = true;
		}
		cn.close();
		return resultado;
		
	}
	
	public static Cajero getCajero(int id){
		String query = "select * from tblCajeros where ID = " + id;
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		try {
			Statement st = cn.createStatement();
			ResultSet res = st.executeQuery(query);
			if (res.next()) {
				Cajero cajero = new Cajero();
				cajero.setId(res.getInt("ID"));
				cajero.setNombre(res.getString("Nombre"));
				cajero.setApellido(res.getString("Apellido"));
				cajero.setPassword(res.getString("Password"));
				cajero.setEdad(res.getInt("Edad"));
				cn.close();
				return cajero;
			} else {
				cn.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			System.out.println("Me ejecuto");
		}
		
	}

	@Override
	public int modificarCajero(Cajero cajero, int id) throws SQLException {
		int resultado = 0;
		String query = "UPDATE tblCajeros set Nombre = ?, Apellido = ?, Edad = ?"
				+ " WHERE ID = " + id;
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		PreparedStatement ps = cn.prepareStatement(query);
		ps.setString(1, cajero.getNombre());
		ps.setString(2, cajero.getApellido());
		ps.setInt(3, cajero.getEdad());
		resultado = resultado + ps.executeUpdate();
		cn.close();
		return resultado;
	}

	@Override
	public Cajero getLogueoCajero(int user, String pass) throws SQLException {
		String query = "select * from tblCajeros where ID = "
				+ user
				+ " AND Password = '"
				+ pass
				+ "'";
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		try {
			Statement st = cn.createStatement();
			ResultSet res = st.executeQuery(query);
			if (res.next()) {
				System.out.println("Si existe");
				Cajero cajero = new Cajero();
				cajero.setId(res.getInt("ID"));
				cajero.setNombre(res.getString("Nombre"));
				cajero.setApellido(res.getString("Apellido"));
				cajero.setPassword(res.getString("Password"));
				cajero.setEdad(res.getInt("Edad"));
				cn.close();
				return cajero;
			} else {
				cn.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
