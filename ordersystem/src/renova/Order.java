package renova;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.swing.DefaultListModel;

/**
 * Sipari� sistemini kontrol eden ana s�n�ft�r.
 * @author TOSHIBA
 *
 */
public class Order {
	/**
	 * Sipari�i veren m��terimizdir. �ki �e�ittir Bireysel veya Kurumsal �irket...
	 */
	private Customer customer;
	/**
	 * Sipari�e atanan benzersiz id
	 */
	private int id;
	/**
	 * �r�nlerin bilgilerini saklayan liste yap�s�...
	 */
	private DefaultListModel<OrderItem> items;
	/**
	 * Sipari� en son g�ncellendi�i zaman bilgisi...
	 */
	private Date orderDate;
	/**
	 * Bu sipari� i�in t�m �r�nler tutar�n� saklar.
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
	 * �lgili m��terinin sipari�ine, yeni �r�n eklemesi i�lemlerini ger�ekle�tiren methoddur.
	 * @param item
	 */
	public void addOrderItem(OrderItem item) {
		items.addElement(item);
		orderDate = new Date();
		orderTotal += item.getItemTotal();
		if(customer instanceof Company) {
			// E�er m��teri �irket ise toplam tutardan indirimi ��kar�r�z. Toplam tutar indirimden b�y�k olmal�d�r.
			if(orderTotal >= ((Company) customer).getDiscount())
				orderTotal = orderTotal - ((Company) customer).getDiscount();
		}
		saveData("order" + id + ".csv");
		System.out.println("Sipari� " + id + "'ye " + item.getProduct().getDescription() + " �r�n� eklendi.");
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
