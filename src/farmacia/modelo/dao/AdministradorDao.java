package farmacia.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import farmacia.modelo.dto.Administrador;

public interface AdministradorDao {

	public ArrayList<Administrador> getListadoAdministradores();

	public Administrador getAdministrador(int id);
	
	public Administrador getLogueoAdministrador(String username, String password) throws Exception;

	public int insertAdministrador(Administrador administrador) throws SQLException;
	
	public boolean deleteAdministrador(int id);
	
	public int updateAdministrador(int ID, Administrador administrador) throws SQLException;

}
