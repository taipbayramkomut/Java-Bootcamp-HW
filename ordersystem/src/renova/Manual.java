package renova;

public class Manual extends Product{
	/**
	 * Klavuzu yayýnlayan kiþi bilgisi.
	 */
	private String publisher;
	
	public Manual(String publisher, String description, int id,String name, double retailPrice) {
		super();
		this.publisher = publisher;
		this.description = description;
		this.id = id;
		this.name = name;
		this.retailPrice = retailPrice;
	}

	public String getPublisher() {
		return publisher;
	}
}
