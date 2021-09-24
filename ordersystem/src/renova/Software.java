package renova;

public class Software extends Product{
	/**
	 * Yaz�l�m �r�n�n lisans bilgisidir.
	 */
	private String licence;
	
	public Software(String licence, String description, int id, String name, double retailPrice) {
		super();
		this.licence = licence;
		this.description = description;
		this.id = id;
		this.name = name;
		this.retailPrice = retailPrice;
	}

	public String getLicence() {
		return licence;
	}
	
}
