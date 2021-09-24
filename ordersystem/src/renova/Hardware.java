package renova;

public class Hardware extends Product implements Taxable{
	/**
	 * Donanýmýn garanti süresidir.
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
	 * Donanýma verilen ekstra vergi miktarýdýr.
	 */
	@Override
	public double getTaxt() {
		return retailPrice * 0.08;
	}
	
	
}
