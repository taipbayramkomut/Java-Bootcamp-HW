package renova;

public class Hardware extends Product implements Taxable{
	/**
	 * Donan�m�n garanti s�residir.
	 */
	private int warrantyPeriod;
	
	public Hardware(int warrantyPeriod, String description, int id, String name, double retailPrice) {
		super();
		this.warrantyPeriod = warrantyPeriod;
		this.description = description;
		this.id = id;
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
