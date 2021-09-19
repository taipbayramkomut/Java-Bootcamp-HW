package renova;

/**
 * M��teri bir kurumsal �irket olabilir
 * @author TOSHIBA
 *
 */
public class Company extends Customer{
	/**
	 * Kurumsal �irketin adres bilgisidir.
	 */
	private String contact;
	/**
	 * Kurumsal �irkete yap�lacak �r�n ba��na indirim miktar�d�r.
	 */
	private int discount;
	
	public Company(String contact, int discount,String address,int id, String name,String phone){
		super(address,id,name,phone);
		this.contact = contact;
		this.discount = discount;
	}
	
	public String getContact() {
		return contact;
	}

	public int getDiscount() {
		return discount;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}

	
}
