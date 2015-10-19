package farmacia.modelo.dto;

public class DetVenta {

	private int idDetalleVenta;
	private int idVenta;
	private String clave;
	private int cantidad;
	private double precio;

	public int getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
