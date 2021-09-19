package renova;

/**
 * Müþteri bir kurumsal þirket olabilir
 * @author TOSHIBA
 *
 */
public class Company extends Customer{
	/**
	 * Kurumsal þirketin adres bilgisidir.
	 */
	private String contact;
	/**
	 * Kurumsal þirkete yapýlacak ürün baþýna indirim miktarýdýr.
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
