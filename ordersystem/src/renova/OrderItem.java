package renova;

/**
 * Sipariþe eklenecek ürünlerin miktarýný ve sýrasýný tutan sýnýftýr.
 * @author TOSHIBA
 *
 */
public class OrderItem {
	/**
	 * Ürünün sýrasýdýr.
	 */
	private int lineNbr;
	/**
	 * Sipariþ edilecek ürün bilgisini tutar.
	 */
	private Product product;
	/**
	 * Sipariþ edilecek ürünün sayýsýdýr.
	 */
	private int quantity;
	
	public OrderItem(int lineNbr, Product product, int quantity) {
		this.lineNbr = lineNbr;
		this.product = product;
		this.quantity = quantity;	
	}
	
	public double getItemTotal() {
		return getUnitPrice() * getQuantity() + getTax() * getQuantity();
	}
	
	public int getLineNbr() {
		return lineNbr;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Eðer ürün donaným ise vergisi vardýr.
	 * @return
	 */
	public double getTax(){
		if(product instanceof Hardware) {
			return ((Hardware) product).getTaxt();
		}
		return 0.00;
	}
	
	public double getUnitPrice() {
		return product.retailPrice;
	}
	
	
}
