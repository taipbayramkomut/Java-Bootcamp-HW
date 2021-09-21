package renova;

/**
 * Müþteri bireysel olabilir.
 * @author TOSHIBA
 *
 */
public class Individual extends Customer{
	/**
	 * Bireysel müþterinin lisans numarasýdýr.
	 */
	private String licNumber;
	
	public Individual(String licNumber, String address,int id, String name,String phone) {
		super(address,id,name,phone);
		this.setLicNumber(licNumber);
	}

	public String getLicNumber() {
		return licNumber;
	}

	public void setLicNumber(String licNumber) {
		this.licNumber = licNumber;
	}

	public String toString() {
		return "Individual Customer: " + licNumber;
	}
	
}
