package renova;

public class Software extends Product{
	/**
	 * Yaz�l�m �r�n�n lisans bilgisidir.
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
