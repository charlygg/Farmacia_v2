package farmacia.modelo.dto;

public class Ubicacion {
	
	private int idUbicacion;
	private int pasillo;
	private int estante;
	private String repisa;
	
	public Ubicacion(int idUbicacion, int pasillo, int estante, String repisa){
		this.idUbicacion = idUbicacion;
		this.pasillo = pasillo;
		this.estante = estante;
		this.repisa = repisa;
	}
	
	public int getIdUbicacion() {
		return idUbicacion;
	}
	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public int getPasillo() {
		return pasillo;
	}
	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public String getRepisa() {
		return repisa;
	}
	public void setRepisa(String repisa) {
		this.repisa = repisa;
	}
	
	

}
