package GUInterface;
import renova.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * It is the page or class that allows the customer to choose a product.
 * @author TOSHIBA
 *
 */
public class OrderDetailsPage extends TemplateScreen implements ItemListener {
	private static final long serialVersionUID = 1L;
	private final JComboBox spread;
	private JComboBox products;
	private ArrayList<String> elements = new ArrayList<>();
	private String type = "Donaným";

    public OrderDetailsPage(Customer customer){
    	this.customer = customer;
        JPanel middle = new JPanel();
        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));
        textDesign(middle,"Sipariþ Ürün Ekleme");

        JPanel info = new JPanel();
        info.setBackground(new Color(gray));
 //       info.setMaximumSize(new Dimension(500,300));
        info.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        GridLayout infoGrid= new GridLayout(2,1);
        infoGrid.setVgap(20);
        info.setLayout(infoGrid);

        elements.add("Donaným");
        elements.add("Yazýlým");
        elements.add("Kitapçýk");
        spread = addSelectFromList(info, elements,"Ürün Tipi", "");
        spread.addItemListener(this);
        middle.add(info);
        
        elements.clear();
        if(type.equals("Donaným")) {
            elements.add("SSD");
            elements.add("Klavye");
        }
        else if(type.equals("Yazýlým")){
            elements.add("PES 2019");
            elements.add("Adobe Reader");
            elements.add("Eclipse");
        }
        else {
            elements.add("Modern Operating Systems");
            elements.add("UNIX System Programming");
        }
        
        products = addSelectFromList(info, elements,"Ürün Ýsmi", "");
        products.addItemListener(this);
        middle.add(info);
        
        
        JPanel textInfo = new JPanel();
        textInfo.setBackground(new Color(gray));
//        textInfo.setMaximumSize(new Dimension(800, 400));
        textInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoGrid = new GridLayout(7, 1);
        infoGrid.setVgap(10);
        infoGrid.setHgap(5);
        textInfo.setLayout(infoGrid);
       
        textDesign(textInfo, "Ürün Numarasý     " ,"-------------------");
        textDesign(textInfo, "Ürün Birim Fiyatý " ,"-------------------");
        textDesign(textInfo, "Garanti Süresi    " ,"-------------------");
        textDesign(textInfo, "Yayýnlayýcýsý     " ,"-------------------");
        textDesign(textInfo, "Yazýlým Lisansý   " ,"-------------------");
        dataPanels(textInfo, "Ürün Adedi         ","");
        textDesign(textInfo, "Toplam Tutar   " ,"0.00 TL");
        middle.add(textInfo);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
//        buttons.setMaximumSize(new Dimension(500,100));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 80, 120, 80));
        GridLayout buttonsGrid= new GridLayout(1,2);
        buttonsGrid.setHgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons,"GERÝ","BACK");
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
                main_page.setVisible(false);
                break;
            }
        }
    }
    

    /**
     * To check states the combo boxes
     * @param e  For combo box state control
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
    	DefaultComboBoxModel<String> boxes = new DefaultComboBoxModel<String>();
    	type = spread.getSelectedItem().toString();
        elements.clear();
        if(type.equals("Donaným")) {
            boxes.addElement("SSD");
            boxes.addElement("Klavye");
        }
        else if(type.equals("Yazýlým")){
            boxes.addElement("PES 2019");
            boxes.addElement("Adobe Reader");
            boxes.addElement("Eclipse");
        }
        else {
            boxes.addElement("Modern Operating Systems");
            boxes.addElement("UNIX System Programming");
        }
        products.setModel(boxes);
    }
}
