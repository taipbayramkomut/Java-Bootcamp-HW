package GUInterface;
import renova.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * It is the page or class that allows the customer to choose a product.
 * @author TOSHIBA
 *
 */
public class OrderDetailsPage extends TemplateScreen  {
	private static final long serialVersionUID = 1L;
	private final JComboBox spread;
	private JComboBox products;
	private ArrayList<String> elements = new ArrayList<>();
	private String type = "Donan�m";
	private String productName;
	private JLabel[] infos = new JLabel[6];
	private JTextField inp;
	private boolean flag = true;

    public OrderDetailsPage(Customer customer){
    	this.customer = customer;
        JPanel middle = new JPanel();
        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));
        textDesign(middle,"Sipari� �r�n Ekleme");

        JPanel info = new JPanel();
        info.setBackground(new Color(gray));
 //       info.setMaximumSize(new Dimension(500,300));
        info.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        GridLayout infoGrid= new GridLayout(2,1);
        infoGrid.setVgap(20);
        info.setLayout(infoGrid);

        elements.add("Donan�m");
        elements.add("Yaz�l�m");
        elements.add("Kitap��k");
        spread = addSelectFromList(info, elements,"�r�n Tipi", "");
        spread.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ((e.getStateChange() == ItemEvent.SELECTED)) {
                	DefaultComboBoxModel<String> boxes = new DefaultComboBoxModel<String>();
            	    type = spread.getSelectedItem().toString();
            	    elements.clear();
            	    if(type.equals("Donan�m")) {
            	    	boxes.addElement("SSD");
            	    	boxes.addElement("Klavye");
            	    	productName = "SSD";
            	    }
            	    else if(type.equals("Yaz�l�m")){
            	    	boxes.addElement("PES 2019");
            	    	boxes.addElement("Adobe Reader");
            	    	boxes.addElement("Eclipse");
            	    	productName = "PES 2019";
            	    }
            	    else {
            	    	boxes.addElement("Modern Operating Systems");
            	    	boxes.addElement("UNIX System Programming");
               	    	productName = "Modern Operating Systems";
            	    }
            	    products.setModel(boxes);
                }
            }
        });
        middle.add(info);
        
        elements.clear();
        if(type.equals("Donan�m")) {
            elements.add("SSD");
            elements.add("Klavye");
        }
        else if(type.equals("Yaz�l�m")){
            elements.add("PES 2019");
            elements.add("Adobe Reader");
            elements.add("Eclipse");
        }
        else {
            elements.add("Modern Operating Systems");
            elements.add("UNIX System Programming");
        }
        
        products = addSelectFromList(info, elements,"�r�n �smi", "");
        products.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
    	        if ((e.getStateChange() == ItemEvent.SELECTED)) {
    		    	productName = products.getSelectedItem().toString();
    		    	System.out.println(productName);
    	        }
    	        getProductInfo();
            }
        });
        middle.add(info);
        
        
        JPanel textInfo = new JPanel();
        textInfo.setBackground(new Color(gray));
//        textInfo.setMaximumSize(new Dimension(800, 400));
        textInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoGrid = new GridLayout(7, 1);
        infoGrid.setVgap(10);
        infoGrid.setHgap(5);
        textInfo.setLayout(infoGrid);
       
        infos[0] = textDesign(textInfo, "�r�n Numaras�     " ,"-------------------");
        infos[1] = textDesign(textInfo, "�r�n Birim Fiyat� " ,"-------------------");
        infos[2] = textDesign(textInfo, "Garanti S�resi    " ,"-------------------");
        infos[3] = textDesign(textInfo, "Yay�nlay�c�s�     " ,"-------------------");
        infos[4] = textDesign(textInfo, "Yaz�l�m Lisans�   " ,"-------------------");
        inp = dataPanels(textInfo, "�r�n Adedi         ","");
        infos[5] = textDesign(textInfo, "Toplam Tutar   " ,"0.00 TL");
        middle.add(textInfo);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
//        buttons.setMaximumSize(new Dimension(500,100));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 80, 120, 80));
        GridLayout buttonsGrid= new GridLayout(1,2);
        buttonsGrid.setHgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons,"GER�","BACK");
        addingButton(buttons,"EKLE","GO");
        middle.add(buttons);

        emptyBackground(item);
        item.add(middle);
        emptyBackground(item);
    }

    /**
     * One of the two buttons is to return to the previous page, the other is for proceeding from the next page.
     * @param e For button state control
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "BACK": {
                main_page.setVisible(false);
                new OrderEntryPage(customer);
                break;
            }
            case "GO": {
            	if(flag) 
            		main_page.setVisible(false);
            	else
            		JOptionPane.showMessageDialog(main_page,"Tam say� adedi giriniz!", "Sistem Giri� Hatas�", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }
    
    private	void getProductInfo() {
    	Scanner sc;
		try {
			sc = new Scanner(new File("product.csv"));
			sc.useDelimiter(";"); 
			int count = 0;
			String name = "";
			String description;
			int id_num = 0;
			while (sc.hasNext()){
				String temp = sc.next();
				if(count % 5 == 0)
					name = temp.toString();
				else if(count % 5 == 1)
					id_num = Integer.parseInt(temp.toString());
				else if(count % 5 == 2) {
					description = temp.toString();
					System.out.println(description + "-" + name);
					if(description.equals(productName) && name.equals(type)) {
						double price = Double.parseDouble(sc.next());
						infos[0].setText(String.valueOf(id_num));
						infos[1].setText(String.valueOf(price));
						try {
							int numberOfItems = Integer.parseInt(inp.getText());
							infos[5].setText(String.valueOf(price * numberOfItems));
							flag = true;
						}
						catch(Exception e) {
							e.printStackTrace();
							flag = false;
						}
						if(description.equals("Donan�m")) {
							int warranty = Integer.parseInt(sc.next());
							product = new Hardware(warranty, name, id_num, description, price);
							infos[2].setText(String.valueOf(warranty));
						}
						else if(description.equals("Yaz�l�m")){
							String licence = sc.next();
							product = new Software(licence, name, id_num, description, price);
							infos[3].setText(licence);
						}
						else {
							String publisher = sc.next();
							product = new Manual(publisher, name, id_num, description, price);
							infos[4].setText(publisher);
						}
					}
				}
				count++;
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
    

}


