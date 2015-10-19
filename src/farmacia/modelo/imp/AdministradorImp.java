package farmacia.modelo.imp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import farmacia.modelo.dao.AdministradorDao;
import farmacia.modelo.dao.BaseDatos;
import farmacia.modelo.dto.Administrador;

public class AdministradorImp implements AdministradorDao {

	@Override
	public ArrayList<Administrador> getListadoAdministradores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrador getAdministrador(int id) {
		String query = "select * from tblAdministradores where ID = " + id;
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		try {
			Statement st = cn.createStatement();
			ResultSet res = st.executeQuery(query);
			if (res.next()) {
				Administrador administrador = new Administrador();
				administrador.setID(res.getInt("ID"));
				administrador.setNombre(res.getString("Nombre"));
				administrador.setApellido(res.getString("Apellido"));
				administrador.setCorreo(res.getString("Correo"));
				administrador.setPassword(res.getString("Password"));
				administrador.setEdad(res.getInt("Edad"));
				cn.close();
				return administrador;
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
	public int insertAdministrador(Administrador administrador)
			throws SQLException {
		String query = "Insert into tblAdministradores (ID,Nombre,Apellido,Correo,Password,Edad)"
				+ " values (?,?,?,?,?,?)";
		int resultado = 0;
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		PreparedStatement ps = cn.prepareStatement(query);
		ps.setInt(1, administrador.getID());
		ps.setString(2, administrador.getNombre());
		ps.setString(3, administrador.getApellido());
		ps.setString(4, administrador.getCorreo());
		ps.setString(5, administrador.getPassword());
		ps.setInt(6, administrador.getEdad());
		resultado = resultado + ps.executeUpdate();
		cn.close();
		return resultado;
	}

	@Override
	public boolean deleteAdministrador(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Administrador getLogueoAdministrador(String username, String password)
			throws IOException, ClassNotFoundException, SQLException {
		String query = "select * from tblAdministradores where tblAdministradores.ID = "
				+ username
				+ " AND tblAdministradores.Password = '"
				+ password
				+ "'";
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		try {
			Statement st = cn.createStatement();
			ResultSet res = st.executeQuery(query);
			if (res.next()) {
				System.out.println("Si existe");
				Administrador administrador = new Administrador();
				administrador.setID(res.getInt("ID"));
				administrador.setNombre(res.getString("Nombre"));
				administrador.setApellido(res.getString("Apellido"));
				administrador.setCorreo(res.getString("Correo"));
				administrador.setPassword(res.getString("Password"));
				administrador.setEdad(res.getInt("Edad"));
				cn.close();
				return administrador;
			} else {
				cn.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static boolean isClaveExists(int clave) throws SQLException {
		String query = "Select * from tblAdministradores where ID=" + clave
				+ "";
		boolean resultado = false;
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		if (res.next()) {
			resultado = true;
		}
		cn.close();
		return resultado;
	}

	@Override
	public int updateAdministrador(int ID, Administrador administrador) throws SQLException{
		String query = "update tblAdministradores set Nombre=?, Apellido=?, Correo=?, "
				+ "Edad=? "
				+ " WHERE ID = " + ID;
		int resultado = 0;
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		PreparedStatement ps = cn.prepareStatement(query);
		ps.setString(1, administrador.getNombre());
		ps.setString(2, administrador.getApellido());
		ps.setString(3, administrador.getCorreo());
		ps.setInt(4, administrador.getEdad());
		resultado = resultado + ps.executeUpdate();
		cn.close();
		return resultado;
	}
}
