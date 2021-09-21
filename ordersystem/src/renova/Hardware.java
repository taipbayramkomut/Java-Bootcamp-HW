package renova;

public class Hardware extends Product implements Taxable{
	/**
	 * Donan�m�n garanti s�residir.
	 */
	private int warrantyPeriod;
	
	public Hardware(int warrantyPeriod, String description, String name, double retailPrice) {
		super();
		this.warrantyPeriod = warrantyPeriod;
		this.description = description;
		this.name = name;
		this.retailPrice = retailPrice;
	}

	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}
	
	/**
	 * Donan�ma verilen ekstra vergi miktar�d�r.
	 */
	@Override
	public double getTaxt() {
		return retailPrice * 0.08;
	}
	
	
}
