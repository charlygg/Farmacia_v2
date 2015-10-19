package farmacia.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import farmacia.modelo.dto.Medicamento;

public interface MedicamentoDao {
	
	public ArrayList<Medicamento> getListadoMedicamentos(String palabra) throws SQLException;
	
	public int insertMedicamento(Medicamento medicamento) throws SQLException;
	
	public int updateMedicamento(String clave, Medicamento medicamento) throws SQLException;
	
	public ArrayList<Medicamento> filtrarTabla(String tipo, String palabra) throws SQLException;
	
	public int deleteMedicamento(String id) throws SQLException;
	
	public Medicamento getMedicamentoById(String clave) throws SQLException;
	

}
