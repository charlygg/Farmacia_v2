package farmacia.modelo.dao;

import java.sql.SQLException;

public interface UbicacionDao {

	public boolean isUbicacionCorrect(String pasillo, String estante,
			String repisa);

	public int getIdUbicacion(String pasillo, String estante, String repisa) throws SQLException;

}
