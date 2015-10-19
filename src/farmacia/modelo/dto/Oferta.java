package farmacia.modelo.dto;

public class Oferta {
	
	private int idOferta;
	private String oferta;
	
	public Oferta(int idOferta, String oferta){
		this.idOferta = idOferta;
		this.oferta = oferta;
	}
	
	public int getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}
	public String getOferta() {
		return oferta;
	}
	public void setOferta(String oferta) {
		this.oferta = oferta;
	}
	
	

}
