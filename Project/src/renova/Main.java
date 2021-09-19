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
			System.out.print("Kurumsal Müþteri için K'ye Bireysel Müþteri için B'ye veya çýkýþ yapmak için Ç'ye basýnýz: ");
			String option = inp.next();
			
			// Sipariþi veren müþteri eðer þirket ise;
			if(option.equalsIgnoreCase("K")) {
				System.out.println("Kurumsal firma müþterisi seçildi.");
				// Sipariþi veren müþteri eðer bireysel ise;
				do {
					System.out.println("Yeni sipariþ eklemek ister? Evet (E) yada Hayýr (H).");
					String s = inp.next();
					if(s.equalsIgnoreCase("E")) {
						company(count);
					}
					else if(s.equalsIgnoreCase("H")) {
						break;
					}
					else {
						System.out.println("Lütfen E veya H giriniz.");
					}
				}while(true);
				count++;
			}
			
			// Sipariþi veren müþteri eðer bireysel ise;
			else if(option.equalsIgnoreCase("B")) {
				System.out.println("Bireysel müþteri seçildi.");
				// Sipariþi veren müþteri eðer bireysel ise;
				System.out.println("Yeni sipariþ eklemek ister? Evet (E) yada Hayýr (H).");
				do {
					String s = inp.next();
					if(s.equalsIgnoreCase("E")) {
						individual(count);
						System.out.println("Yeni sipariþ eklemek ister? Evet (E) yada Hayýr (H).");
					}
					else if(s.equalsIgnoreCase("H")) {
						break;
					}
					else {
						System.out.println("Lütfen E veya H giriniz.");
					}
				}while(true);
				count++;
			}
			
			else if(option.equalsIgnoreCase("Ç")) {
				System.out.println("Program sonlandý!");
				break;
			}
			else
				System.out.println("Tekrar deneyin, Lütfen K veya B harfi giriniz! Çýkýþ için Ç giriniz. ");
		}while(true);
		printList();
	}
	
	// Sipariþi veren müþteri eðer bireysel ise;
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
				System.out.println("Lütfen kullanýcý numaranýzý tam sayý giriniz.");
				check = true;
			}
		}while(check);
		
		Customer example = new Individual(customerInfos[0],customerInfos[1], Integer.parseInt(customerInfos[2]), customerInfos[3], customerInfos[4]);
		
		addOrder(example, count);
	}
	
	// Sipariþi veren müþteri eðer kurumsal þirket ise;
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
				System.out.println("Lütfen kullanýcý numarasýný veya indirim fiyatýný tam sayý giriniz.");
				check = true;
			}
		}while(check);
		
		Customer example = new Company(customerInfos[0], discount, customerInfos[1], test, customerInfos[3], customerInfos[4]);
	
		addOrder(example, count);
	}
	
	// Sipariþ oluþturup, ürünleri teker teker eklemeleri içeren ana methoddur.
	private static void addOrder(Customer example, int count) {
		/****** Yeni sipariþ eklendi.	******/
		Order order = new Order(example, count);
		int index = 0;
		do {
			System.out.println("Lütfen ürün çeþidi seçiniz: Hardware için (H), Software için (S), Manual için (M) giriniz.");
			String s = inp.next();
			Product product=null;
			if(s.equalsIgnoreCase("H")) {
				productInfo("HARDWARE");
				try {
					Integer.parseInt(productInfos[0]);
				}
				catch(Exception e) {
					System.out.println("Hardware ürününün garanti süresi tam sayý olmalýdýr.");
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
				System.out.println("Geçersiz ürün, iþlem baþarýsýz!");
			}
			System.out.println("Yeni bir ürün eklemek ister misiniz ? Evet için (E) giriniz.");
			s = inp.next();
			if(!s.equalsIgnoreCase("E")) {
				break;
			}
			index++;
		}while(true);
		orders.add(order);
		System.out.println("Sipariþ iþlemi tamamlandý.");	
	}
	
	// Ýki çeþit müþteri içinde ortak olamayan girdileri almak için methotlardýr.
	private static void customerInfo() {
		System.out.print("Þirketin iletiþim bilgisini giriniz: ");
		customerInfos[0] = inp.next();
		System.out.print("Þirketin indirim fiyatýný giriniz: ");
		customerInfos[5] = inp.next();
		generalInfo();
	}
	
	private static void individualInfo() {
		System.out.print("Lisans numaranýzý giriniz: ");
		customerInfos[0] = inp.next();
		generalInfo();
	}
	
	// Genel müþteri bilgileridir. Ýki çeþit müþteri içinde ortaktýr.
	private static void generalInfo() {
		System.out.print("Adres bilgisi giriniz: ");
		customerInfos[1] = inp.next();
		System.out.print("Kullanýcý numaranýzý giriniz: ");
		customerInfos[2] = inp.next();
		System.out.print("Adýnýzý giriniz: ");
		customerInfos[3] =  inp.next();
		System.out.print("Telefon Numaranýzý giriniz: ");
		customerInfos[4] =  inp.next();
	}
	
	// Sipariþe eklenecek ürün bilgilerini almak için methoddur.
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
		System.out.print("Ürünün tanýmýný giriniz: ");
		productInfos[1] = inp.next();
		System.out.print("Ürünün ismini giriniz: ");
		productInfos[2] = inp.next();
		System.out.print("Ürünün fiyatýný giriniz: ");
		productInfos[3] = inp.next();
		System.out.print("Üründen kaç adet alacaksýnýz? : ");
		productInfos[4] = inp.next();
	}
	
	// 3 ürün çeþidi için ortak genel ürün bilgilerinin kontrolleridir.
	private static void productCheck() {
		try {
			Integer.parseInt(productInfos[3]);
		}
		catch(Exception e) {
			System.out.println("Ürünün fiyatý tam sayý olmalýdýr.");
		}
		try {
			Integer.parseInt(productInfos[4]);
		}
		catch(Exception e) {
			System.out.println("Ürünün miktarý tam sayý olmalýdýr.");
		}
	}
		
	// Hardware , Software ve Klavuz ürünleri bilgelerine özel bilgileri girmek için methodlardýr...
	private static void hardwareInfo() {
		System.out.print("Ürünün garanti süresini giriniz: ");
		productInfos[0] = inp.next();
	}
	
	private static void softwareInfo() {
		System.out.print("Yazýlýmýn lisansýný giriniz: ");
		productInfos[0] = inp.next();
	}
	
	private static void manualInfo() {
		System.out.print("Klavuzun yayýnlayýcýsýný giriniz: ");
		productInfos[0] = inp.next();
	}
	
	public static void printList() {
		for(Order items: orders) {
			System.out.println("Müþteri ismi: " + items.getCustomer() + ".\n" +  "Sipariþ no: " + items.getId() + "\n" + "Sipariþ tarihi:" + items.getOrderDate());
			DefaultListModel<OrderItem> item = items.getItems();
			for(int i = 0; i <item.size();++i ) {
				System.out.println("Sipariþ toplam tutarý: "+ item.get(i).getItemTotal());
				System.out.println("Ürün adedi : " + item.get(i).getQuantity());
				System.out.println("Vergi miktarý : " + item.get(i).getTax());				
			}
		}
		System.out.println();
	}
	

	
}