package renova;

/**
 * Sipari�leri verecek olan m��teri s�n�f�m�zd�r.
 * @author TOSHIBA
 *
 */
public class Customer{
	/**
	 * M��terinin adress bilgisidir.
	 */
	private String address;
	/**
	 * M��terinin kullan�c� kimli�idir.
	 */
	private int id;
	/**
	 * M��terinin ismidir.
	 */
	private String name;
	/**
	 * M��terinin telefon numaras�d�r.
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
