package renova;

/**
 * Ürün sýnýfýmýzdýr. Üst sýnýfýmýz olarak kullanýlýr. Ürünler 3 çeþittir.
 * @author TOSHIBA
 *
 */
public abstract class Product {
	/**
	 * Ürünün tanýmýdýr.
	 */
	protected String description;
	/**
	 * Ürüne atanan kimliktir.
	 */
	protected static int id = 0;
	/**
	 * Ürünün adýdýr.
	 */
	protected String name;
	/**
	 * Ürün fiyatýdýr.
	 */
	protected double retailPrice;
	
	public Product() {
		id++;
	}
	
	public String getDescription() {
		return description;
	}
}
