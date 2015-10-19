package farmacia.modelo.dto;

public class Medicamento {
	
	private String clave;
	private String nombre;
	private float precio;
	private Tipo tipo;
	private Ubicacion ubicacion;
	private Marca marca;
	private Oferta oferta;
	private String farmaco;
	private String concentracion;
	private String existencia;
	private String presentacion;
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public String getFarmaco() {
		return farmaco;
	}
	public void setFarmaco(String farmaco) {
		this.farmaco = farmaco;
	}
	public String getConcentracion() {
		return concentracion;
	}
	public void setConcentracion(String concentracion) {
		this.concentracion = concentracion;
	}
	public String getExistencia() {
		return existencia;
	}
	public void setExistencia(String existencia) {
		this.existencia = existencia;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	
	

}
