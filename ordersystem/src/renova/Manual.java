package renova;

public class Manual extends Product{
	/**
	 * Klavuzu yay�nlayan ki�i bilgisi.
	 */
	private String publisher;
	
	public Manual(String publisher, String description, String name, double retailPrice) {
		super();
		this.publisher = publisher;
		this.description = description;
		this.name = name;
		this.retailPrice = retailPrice;
	}

	public String getPublisher() {
		return publisher;
	}
}
