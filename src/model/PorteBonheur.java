package model;

public class PorteBonheur extends PieceVaisseau {

	private PouvoirSpecial pouvoirSpecial;
	
	public PorteBonheur(String name, int luck, PouvoirSpecial pouvoirSpecial) {
		super(name, 0, 0, 0, 0, 0, luck);
		this.pouvoirSpecial = pouvoirSpecial;
	}

	public PouvoirSpecial getPouvoirSpecial() {
		return pouvoirSpecial;
	}

	public void setPouvoirSpecial(PouvoirSpecial pouvoirSpecial) {
		this.pouvoirSpecial = pouvoirSpecial;
	}
}
