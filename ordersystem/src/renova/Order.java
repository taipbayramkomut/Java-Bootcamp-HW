package renova;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.swing.DefaultListModel;

/**
 * Sipariþ sistemini kontrol eden ana sýnýftýr.
 * @author TOSHIBA
 *
 */
public class Order {
	/**
	 * Sipariþi veren müþterimizdir. Ýki çeþittir Bireysel veya Kurumsal þirket...
	 */
	private Customer customer;
	/**
	 * Sipariþe atanan benzersiz id
	 */
	private int id;
	/**
	 * Ürünlerin bilgilerini saklayan liste yapýsý...
	 */
	private DefaultListModel<OrderItem> items;
	/**
	 * Sipariþ en son güncellendiði zaman bilgisi...
	 */
	private Date orderDate;
	/**
	 * Bu sipariþ için tüm ürünler tutarýný saklar.
	 */
	private double orderTotal;
	
	public Order() {
		customer = null;
		items = new DefaultListModel<OrderItem>();
		orderDate = new Date();
		orderTotal = 0.00;
		id = 0;
	}
	
	public Order(Customer customer, int id) {
		this();
		this.customer = customer;
		this.id = id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getId() {
		return id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public DefaultListModel<OrderItem> getItems() {
		return items;
	}
	
	/**
	 * Ýlgili müþterinin sipariþine, yeni ürün eklemesi iþlemlerini gerçekleþtiren methoddur.
	 * @param item
	 */
	public void addOrderItem(OrderItem item) {
		items.addElement(item);
		orderDate = new Date();
		orderTotal += item.getItemTotal();
		if(customer instanceof Company) {
			// Eðer müþteri þirket ise toplam tutardan indirimi çýkarýrýz. Toplam tutar indirimden büyük olmalýdýr.
			if(orderTotal >= ((Company) customer).getDiscount())
				orderTotal = orderTotal - ((Company) customer).getDiscount();
		}
		saveData("order" + id + ".csv");
		System.out.println("Sipariþ " + id + "'ye " + item.getProduct().getDescription() + " ürünü eklendi.");
	}
	
	private void saveData(String filePath) {
		File file = new File(filePath);

		try {
	        PrintWriter outputfile = new PrintWriter(file);
		    StringBuilder order = new StringBuilder();
		    
		    order.append(customer.getName());
	        order.append(";" + customer.getId());
	        order.append(";" + customer.getAddress());
	        order.append(";" + customer.getPhone());
	        if(customer instanceof Company) {
	        	order.append(";" + ((Company)customer).getContact());
	        	order.append(";" + ((Company)customer).getDiscount());
	        }
	        else
	        	order.append(";" + ((Individual)customer).getLicNumber());
	        order.append("\n");
	        outputfile.append(order.toString());
	        outputfile.close();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
