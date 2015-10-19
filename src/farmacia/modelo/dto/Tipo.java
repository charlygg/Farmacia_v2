package farmacia.modelo.dto;

public class Tipo {
	
	private int idTipo;
	private String tipo;
	
	public Tipo(int idTipo, String tipo){
		this.idTipo = idTipo;
		this.tipo = tipo;
	}
	
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
