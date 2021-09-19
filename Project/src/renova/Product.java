package renova;

/**
 * �r�n s�n�f�m�zd�r. �st s�n�f�m�z olarak kullan�l�r. �r�nler 3 �e�ittir.
 * @author TOSHIBA
 *
 */
public abstract class Product {
	/**
	 * �r�n�n tan�m�d�r.
	 */
	protected String description;
	/**
	 * �r�ne atanan kimliktir.
	 */
	protected static int id = 0;
	/**
	 * �r�n�n ad�d�r.
	 */
	protected String name;
	/**
	 * �r�n fiyat�d�r.
	 */
	protected double retailPrice;
	
	public Product() {
		id++;
	}
	
	public String getDescription() {
		return description;
	}
}
