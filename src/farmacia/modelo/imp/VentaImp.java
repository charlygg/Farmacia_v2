package farmacia.modelo.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import farmacia.modelo.dao.BaseDatos;
import farmacia.modelo.dto.DetVenta;
import farmacia.modelo.dto.Venta;

public class VentaImp {

	public int insertVenta(Venta listaVenta) throws SQLException {
		BaseDatos bd = new BaseDatos();
		Connection cn = bd.getConnection();
		int id = 0;
		String query = "INSERT INTO tblventa(Total,ID_cajero) values(?,?)";
		String query2 = "select MAX(ID_venta) FROM tblventa";
		System.out.println(query);
		
		PreparedStatement ps = cn.prepareStatement(query);
		ps.setDouble(1, listaVenta.getTotal());
		ps.setInt(2, listaVenta.getIdCajero());
		ps.executeUpdate();
		
		Statement st = cn.createStatement();
		ResultSet res = st.executeQuery(query2);
		if(res.next()){
			id = res.getInt(1);
			System.out.println("ID extraido de tblventa = " + id);
		} else {
			System.out.println("Error al extraer registro");
		}
		cn.close();
		return id;
	}

	public void insertDetVenta(ArrayList<DetVenta> listaDetalle) throws SQLException {
		BaseDatos bd = new BaseDatos();
		Connection cn = bd.getConnection();
		String query = "INSERT INTO tbldetventa(ID_venta, Clave, cantidad, precio) values(?,?,?,?)";
		for(DetVenta det: listaDetalle){
			System.out.println(query);
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setInt(1, det.getIdVenta());
			ps.setString(2, det.getClave());
			ps.setInt(3, det.getCantidad());
			ps.setDouble(4, det.getPrecio());
			ps.executeUpdate();
		}
		cn.close();
	}

}
