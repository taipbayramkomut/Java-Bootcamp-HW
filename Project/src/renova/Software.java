package renova;

public class Software extends Product{
	/**
	 * Yazýlým ürünün lisans bilgisidir.
	 */
	private String licence;
	
	public Software(String licence, String description, String name, double retailPrice) {
		super();
		this.licence = licence;
		this.description = description;
		this.name = name;
		this.retailPrice = retailPrice;
	}

	public String getLicence() {
		return licence;
	}
	
}
