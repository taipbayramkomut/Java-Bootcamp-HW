package renova;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class Main {

	private static Scanner inp = new Scanner(System.in);
	private static String[] customerInfos = new String[6];
	private static String[] productInfos = new String[6];
	private static ArrayList<Order> orders= new ArrayList<>();
	
	public static void main(String[] args) {
		int count = 0;
		do {
			System.out.print("Kurumsal M��teri i�in K'ye Bireysel M��teri i�in B'ye veya ��k�� yapmak i�in �'ye bas�n�z: ");
			String option = inp.next();
			
			// Sipari�i veren m��teri e�er �irket ise;
			if(option.equalsIgnoreCase("K")) {
				System.out.println("Kurumsal firma m��terisi se�ildi.");
				// Sipari�i veren m��teri e�er bireysel ise;
				do {
					System.out.println("Yeni sipari� eklemek ister? Evet (E) yada Hay�r (H).");
					String s = inp.next();
					if(s.equalsIgnoreCase("E")) {
						company(count);
					}
					else if(s.equalsIgnoreCase("H")) {
						break;
					}
					else {
						System.out.println("L�tfen E veya H giriniz.");
					}
				}while(true);
				count++;
			}
			
			// Sipari�i veren m��teri e�er bireysel ise;
			else if(option.equalsIgnoreCase("B")) {
				System.out.println("Bireysel m��teri se�ildi.");
				// Sipari�i veren m��teri e�er bireysel ise;
				System.out.println("Yeni sipari� eklemek ister? Evet (E) yada Hay�r (H).");
				do {
					String s = inp.next();
					if(s.equalsIgnoreCase("E")) {
						individual(count);
						System.out.println("Yeni sipari� eklemek ister? Evet (E) yada Hay�r (H).");
					}
					else if(s.equalsIgnoreCase("H")) {
						break;
					}
					else {
						System.out.println("L�tfen E veya H giriniz.");
					}
				}while(true);
				count++;
			}
			
			else if(option.equalsIgnoreCase("�")) {
				System.out.println("Program sonland�!");
				break;
			}
			else
				System.out.println("Tekrar deneyin, L�tfen K veya B harfi giriniz! ��k�� i�in � giriniz. ");
		}while(true);
		printList();
	}
	
	// Sipari�i veren m��teri e�er bireysel ise;
	public static void individual(int count) {
		int test = 0;
		boolean check = false;
		do {
			individualInfo();
			try {
				test =Integer.parseInt(customerInfos[2]);
				check = false;
			}
			catch(Exception e) {
				System.out.println("L�tfen kullan�c� numaran�z� tam say� giriniz.");
				check = true;
			}
		}while(check);
		
		Customer example = new Individual(customerInfos[0],customerInfos[1], Integer.parseInt(customerInfos[2]), customerInfos[3], customerInfos[4]);
		
		addOrder(example, count);
	}
	
	// Sipari�i veren m��teri e�er kurumsal �irket ise;
	public static void company(int count) {
		int test = 0, discount = 0;
		boolean check = false;
		do {
			customerInfo();
			try {
				test =Integer.parseInt(customerInfos[2]);
				discount =Integer.parseInt(customerInfos[5]);
				check = false;
			}
			catch(Exception e) {
				System.out.println("L�tfen kullan�c� numaras�n� veya indirim fiyat�n� tam say� giriniz.");
				check = true;
			}
		}while(check);
		
		Customer example = new Company(customerInfos[0], discount, customerInfos[1], test, customerInfos[3], customerInfos[4]);
	
		addOrder(example, count);
	}
	
	// Sipari� olu�turup, �r�nleri teker teker eklemeleri i�eren ana methoddur.
	private static void addOrder(Customer example, int count) {
		/****** Yeni sipari� eklendi.	******/
		Order order = new Order(example, count);
		int index = 0;
		do {
			System.out.println("L�tfen �r�n �e�idi se�iniz: Hardware i�in (H), Software i�in (S), Manual i�in (M) giriniz.");
			String s = inp.next();
			Product product=null;
			if(s.equalsIgnoreCase("H")) {
				productInfo("HARDWARE");
				try {
					Integer.parseInt(productInfos[0]);
				}
				catch(Exception e) {
					System.out.println("Hardware �r�n�n�n garanti s�resi tam say� olmal�d�r.");
				}
				productCheck();
				product = new Hardware(Integer.parseInt(productInfos[0]),productInfos[1],productInfos[2],Double.parseDouble(productInfos[3]));
				OrderItem orderItem =  new OrderItem(index, product, Integer.parseInt(productInfos[4]));
				order.addOrderItem(orderItem);
			}
			else if(s.equalsIgnoreCase("S")) {
				productInfo("SOFTWARE");
				productCheck();
				product =new Software(productInfos[0],productInfos[1],productInfos[2],Double.parseDouble(productInfos[3]));
				OrderItem orderItem =  new OrderItem(index, product, Integer.parseInt(productInfos[4]));
				order.addOrderItem(orderItem);
			}
			else if(s.equalsIgnoreCase("M")) {
				productInfo("MANUAL");
				productCheck();
				product = new Manual(productInfos[0],productInfos[1],productInfos[2],Double.parseDouble(productInfos[3]));
				OrderItem orderItem =  new OrderItem(index, product, Integer.parseInt(productInfos[4]));
				order.addOrderItem(orderItem);
			}
			else {
				System.out.println("Ge�ersiz �r�n, i�lem ba�ar�s�z!");
			}
			System.out.println("Yeni bir �r�n eklemek ister misiniz ? Evet i�in (E) giriniz.");
			s = inp.next();
			if(!s.equalsIgnoreCase("E")) {
				break;
			}
			index++;
		}while(true);
		orders.add(order);
		System.out.println("Sipari� i�lemi tamamland�.");	
	}
	
	// �ki �e�it m��teri i�inde ortak olamayan girdileri almak i�in methotlard�r.
	private static void customerInfo() {
		System.out.print("�irketin ileti�im bilgisini giriniz: ");
		customerInfos[0] = inp.next();
		System.out.print("�irketin indirim fiyat�n� giriniz: ");
		customerInfos[5] = inp.next();
		generalInfo();
	}
	
	private static void individualInfo() {
		System.out.print("Lisans numaran�z� giriniz: ");
		customerInfos[0] = inp.next();
		generalInfo();
	}
	
	// Genel m��teri bilgileridir. �ki �e�it m��teri i�inde ortakt�r.
	private static void generalInfo() {
		System.out.print("Adres bilgisi giriniz: ");
		customerInfos[1] = inp.next();
		System.out.print("Kullan�c� numaran�z� giriniz: ");
		customerInfos[2] = inp.next();
		System.out.print("Ad�n�z� giriniz: ");
		customerInfos[3] =  inp.next();
		System.out.print("Telefon Numaran�z� giriniz: ");
		customerInfos[4] =  inp.next();
	}
	
	// Sipari�e eklenecek �r�n bilgilerini almak i�in methoddur.
	private static void productInfo(String type) {
		if(type.equals("HARDWARE")) {
			hardwareInfo();
		}
		else if(type.equals("SOFTWARE")) {
			softwareInfo();
		}
		else {
			manualInfo();
		}
		System.out.print("�r�n�n tan�m�n� giriniz: ");
		productInfos[1] = inp.next();
		System.out.print("�r�n�n ismini giriniz: ");
		productInfos[2] = inp.next();
		System.out.print("�r�n�n fiyat�n� giriniz: ");
		productInfos[3] = inp.next();
		System.out.print("�r�nden ka� adet alacaks�n�z? : ");
		productInfos[4] = inp.next();
	}
	
	// 3 �r�n �e�idi i�in ortak genel �r�n bilgilerinin kontrolleridir.
	private static void productCheck() {
		try {
			Integer.parseInt(productInfos[3]);
		}
		catch(Exception e) {
			System.out.println("�r�n�n fiyat� tam say� olmal�d�r.");
		}
		try {
			Integer.parseInt(productInfos[4]);
		}
		catch(Exception e) {
			System.out.println("�r�n�n miktar� tam say� olmal�d�r.");
		}
	}
		
	// Hardware , Software ve Klavuz �r�nleri bilgelerine �zel bilgileri girmek i�in methodlard�r...
	private static void hardwareInfo() {
		System.out.print("�r�n�n garanti s�resini giriniz: ");
		productInfos[0] = inp.next();
	}
	
	private static void softwareInfo() {
		System.out.print("Yaz�l�m�n lisans�n� giriniz: ");
		productInfos[0] = inp.next();
	}
	
	private static void manualInfo() {
		System.out.print("Klavuzun yay�nlay�c�s�n� giriniz: ");
		productInfos[0] = inp.next();
	}
	
	public static void printList() {
		for(Order items: orders) {
			System.out.println("M��teri ismi: " + items.getCustomer() + ".\n" +  "Sipari� no: " + items.getId() + "\n" + "Sipari� tarihi:" + items.getOrderDate());
			DefaultListModel<OrderItem> item = items.getItems();
			for(int i = 0; i <item.size();++i ) {
				System.out.println("Sipari� toplam tutar�: "+ item.get(i).getItemTotal());
				System.out.println("�r�n adedi : " + item.get(i).getQuantity());
				System.out.println("Vergi miktar� : " + item.get(i).getTax());				
			}
		}
		System.out.println();
	}
	

	
}