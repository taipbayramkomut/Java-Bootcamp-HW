package renova;

/**
 * Sipariþleri verecek olan müþteri sýnýfýmýzdýr.
 * @author TOSHIBA
 *
 */
public class Customer{
	/**
	 * Müþterinin adress bilgisidir.
	 */
	private String address;
	/**
	 * Müþterinin kullanýcý kimliðidir.
	 */
	private int id;
	/**
	 * Müþterinin ismidir.
	 */
	private String name;
	/**
	 * Müþterinin telefon numarasýdýr.
	 */
	private String phone;
	
	public Customer() {
		this(null, 0, null, null);
	}
	
	public Customer(String address,int id, String name,String phone) {
		this.address = address;
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
