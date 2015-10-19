package farmacia.modelo.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import farmacia.modelo.dao.BaseDatos;
import farmacia.modelo.dao.MedicamentoDao;
import farmacia.modelo.dto.Marca;
import farmacia.modelo.dto.Medicamento;
import farmacia.modelo.dto.Tipo;
import farmacia.modelo.dto.Ubicacion;

public class MedicamentoImp implements MedicamentoDao {

	@Override
	public ArrayList<Medicamento> getListadoMedicamentos(String order)
			throws SQLException {
		String query = "SELECT tblmedicamentos.Clave, tblmedicamentos.nombre, tblmedicamentos.precio, tblmedicamentos.idtipo, tbltipo.Tipo, tblmedicamentos.idubicacion, tblubicacion.pasillo, tblubicacion.estante, tblubicacion.repisa, tblmedicamentos.idmarca, tblmarca.Marca, tblmedicamentos.idoferta, tblofertas.Descripcion, tblmedicamentos.Farmaco, tblmedicamentos.Concentracion, tblmedicamentos.Existencia, tblmedicamentos.Presentacion "
				+ " FROM tblubicacion INNER JOIN (tbltipo INNER JOIN (tblofertas INNER JOIN (tblmarca INNER JOIN tblmedicamentos ON tblmarca.idmarca = tblmedicamentos.idmarca) ON tblofertas.idoferta = tblmedicamentos.idoferta) ON tbltipo.idtipo = tblmedicamentos.idtipo) ON tblubicacion.idubicacion = tblmedicamentos.idubicacion "
				+ "ORDER BY tblmedicamentos." + order + "";
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		ArrayList<Medicamento> medic = new ArrayList<Medicamento>();
		while (res.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento.setClave(res.getString(1));
			medicamento.setNombre(res.getString(2));
			medicamento.setPrecio(res.getFloat(3));
			medicamento.setTipo(new Tipo(res.getInt(4), res.getString(5)));
			medicamento.setUbicacion(new Ubicacion(res.getInt(6),
					res.getInt(7), res.getInt(8), res.getString(9)));
			medicamento.setMarca(new Marca(res.getInt(10), res.getString(11)));
			medicamento.setFarmaco(res.getString(14));
			medicamento.setConcentracion(res.getString(15));
			medicamento.setExistencia(res.getString(16));
			medic.add(medicamento);
		}
		cn.close();
		return medic;
	}

	@Override
	public int insertMedicamento(Medicamento medicamento) throws SQLException {
		String query = "insert into tblmedicamentos "
				+ "(Clave ,nombre ,precio ,idtipo ,idmarca ,idoferta ,Farmaco ,Concentracion ,Presentacion ,Existencia ,idubicacion ) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(query);
		BaseDatos BaseDatos = new BaseDatos();
		Connection cn = BaseDatos.getConnection();
		int res = 0;
		PreparedStatement ps = cn.prepareStatement(query);
		ps.clearParameters();
		ps.setString(1, medicamento.getClave());
		ps.setString(2, medicamento.getNombre());
		ps.setInt(3, (int) medicamento.getPrecio());
		ps.setInt(4, medicamento.getTipo().getIdTipo());
		ps.setInt(5, medicamento.getMarca().getIdMarca());
		ps.setInt(6, 4);
		ps.setString(7, medicamento.getFarmaco());
		ps.setString(8, medicamento.getConcentracion());
		ps.setString(9, medicamento.getPresentacion());
		ps.setInt(10, Integer.parseInt(medicamento.getExistencia()));
		ps.setInt(11, medicamento.getUbicacion().getIdUbicacion());
		res = res + ps.executeUpdate();
		cn.close();
		return res;
	}

	@Override
	public ArrayList<Medicamento> filtrarTabla(String campo, String palabra)
			throws SQLException {
		String query = "SELECT tblmedicamentos.Clave, tblmedicamentos.nombre, tblmedicamentos.precio, tblmedicamentos.idtipo, tbltipo.Tipo, tblmedicamentos.idubicacion, tblubicacion.pasillo, tblubicacion.estante, tblubicacion.repisa, tblmedicamentos.idmarca, tblmarca.Marca, tblmedicamentos.idoferta, tblofertas.Descripcion, tblmedicamentos.Farmaco, tblmedicamentos.Concentracion, tblmedicamentos.Existencia, tblmedicamentos.Presentacion "
				+ " FROM tblubicacion INNER JOIN (tbltipo INNER JOIN (tblofertas INNER JOIN (tblmarca INNER JOIN tblmedicamentos ON tblmarca.idmarca = tblmedicamentos.idmarca) ON tblofertas.idoferta = tblmedicamentos.idoferta) ON tbltipo.idtipo = tblmedicamentos.idtipo) ON tblubicacion.idubicacion = tblmedicamentos.idubicacion "
				+ "WHERE " + campo + " LIKE '%" + palabra + "%'; ";
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		ArrayList<Medicamento> medic = new ArrayList<Medicamento>();
		while (res.next()) {
			Medicamento medicamento = new Medicamento();
			medicamento.setClave(res.getString(1));
			medicamento.setNombre(res.getString(2));
			medicamento.setPrecio(res.getFloat(3));
			medicamento.setTipo(new Tipo(res.getInt(4), res.getString(5)));
			medicamento.setUbicacion(new Ubicacion(res.getInt(6),
					res.getInt(7), res.getInt(8), res.getString(9)));
			medicamento.setMarca(new Marca(res.getInt(10), res.getString(11)));
			medicamento.setFarmaco(res.getString(14));
			medicamento.setConcentracion(res.getString(15));
			medic.add(medicamento);
		}
		cn.close();
		return medic;
	}

	@Override
	public int deleteMedicamento(String clave) throws SQLException {
		String query = "delete from tblMedicamentos where clave='" + clave
				+ "'";
		System.out.println(query);
		int result = 0;
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		result = st.executeUpdate(query);
		if (result == 1) {
			System.out.println("Eliminado " + clave);
		}
		cn.close();
		return result;
	}

	public static int isClaveExists(String clave) throws SQLException {
		String query = "select * from tblmedicamentos where Clave='" + clave
				+ "'";
		System.out.println(query);
		int result = 0;
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		if (res.next()) {
			result = result + 1;
		}
		cn.close();
		return result;
	}

	@Override
	public int updateMedicamento(String clave, Medicamento medicamento)
			throws SQLException {
		String query = "update tblmedicamentos set"
				+ " nombre = ?,precio =?,idtipo =?,idmarca =?,idoferta =?,Farmaco =? ,"
				+ "Concentracion =?,Presentacion =?,Existencia =? ,idubicacion =? "
				+ "WHERE clave = '" + clave + "'";
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		int res = 0;
		PreparedStatement ps = cn.prepareStatement(query);
		ps.setString(1, medicamento.getNombre());
		ps.setInt(2, (int) medicamento.getPrecio());
		ps.setInt(3, medicamento.getTipo().getIdTipo());
		ps.setInt(4, medicamento.getMarca().getIdMarca());
		ps.setInt(5, 4);
		ps.setString(6, medicamento.getFarmaco());
		ps.setString(7, medicamento.getConcentracion());
		ps.setString(8, medicamento.getPresentacion());
		ps.setInt(9, Integer.parseInt(medicamento.getExistencia()));
		ps.setInt(10, medicamento.getUbicacion().getIdUbicacion());
		res = res + ps.executeUpdate();
		cn.close();
		return res;
	}

	@Override
	public Medicamento getMedicamentoById(String clave) throws SQLException {
		String query = "SELECT tblmedicamentos.Clave, tblmedicamentos.nombre, tblmedicamentos.precio, tblmedicamentos.idtipo, tbltipo.Tipo, tblmedicamentos.idubicacion, tblubicacion.pasillo, tblubicacion.estante, tblubicacion.repisa, tblmedicamentos.idmarca, tblmarca.Marca, tblmedicamentos.idoferta, tblofertas.Descripcion, tblmedicamentos.Farmaco, tblmedicamentos.Concentracion, tblmedicamentos.Existencia, tblmedicamentos.Presentacion "
				+ " FROM tblubicacion INNER JOIN (tbltipo INNER JOIN (tblofertas INNER JOIN (tblmarca INNER JOIN tblmedicamentos ON tblmarca.idmarca = tblmedicamentos.idmarca) ON tblofertas.idoferta = tblmedicamentos.idoferta) ON tbltipo.idtipo = tblmedicamentos.idtipo) ON tblubicacion.idubicacion = tblmedicamentos.idubicacion "
				+ "WHERE tblmedicamentos.Clave = '" + clave + "'";
		System.out.println(query);
		BaseDatos baseDatos = new BaseDatos();
		Connection cn = baseDatos.getConnection();
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query);
		Medicamento medicamento = null;
		if (res.next()) {
			medicamento = new Medicamento();
			medicamento.setClave(res.getString(1));
			medicamento.setNombre(res.getString(2));
			medicamento.setPrecio(res.getFloat(3));
			medicamento.setTipo(new Tipo(res.getInt(4), res.getString(5)));
			medicamento.setUbicacion(new Ubicacion(res.getInt(6),
					res.getInt(7), res.getInt(8), res.getString(9)));
			medicamento.setMarca(new Marca(res.getInt(10), res.getString(11)));
			medicamento.setFarmaco(res.getString(14));
			medicamento.setConcentracion(res.getString(15));
			medicamento.setExistencia(res.getString(16));
			medicamento.setPresentacion(res.getString(17));
		}
		cn.close();
		return medicamento;
	}

}
