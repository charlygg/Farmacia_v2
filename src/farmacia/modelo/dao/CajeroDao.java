package farmacia.modelo.dao;

import java.sql.SQLException;

import farmacia.modelo.dto.Cajero;

public interface CajeroDao {
	
	public int insertCajero(Cajero cajero) throws SQLException;
	
	public int modificarCajero(Cajero cajero, int id) throws SQLException;
	
	public Cajero getLogueoCajero(int user, String pass) throws SQLException;

}
