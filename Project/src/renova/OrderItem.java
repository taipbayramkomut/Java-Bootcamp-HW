package renova;

/**
 * Sipari�e eklenecek �r�nlerin miktar�n� ve s�ras�n� tutan s�n�ft�r.
 * @author TOSHIBA
 *
 */
public class OrderItem {
	/**
	 * �r�n�n s�ras�d�r.
	 */
	private int lineNbr;
	/**
	 * Sipari� edilecek �r�n bilgisini tutar.
	 */
	private Product product;
	/**
	 * Sipari� edilecek �r�n�n say�s�d�r.
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
	 * E�er �r�n donan�m ise vergisi vard�r.
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
