package GUInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * It is the system that the customer can use. Customer must enter the user id.
 */
public class CustomerProcess extends TemplateScreen {
	private static final long serialVersionUID = 1L;
	private  JTextField[] fields = new JTextField[2];

    public CustomerProcess(){
        JPanel middle = new JPanel();

        middle.setBackground(new Color(gray));
        middle.setLayout(new GridLayout(3,1));
        
        textDesign(middle,"Müþteri Sipariþ Sistemi");
        
        JPanel textInfo = new JPanel();
        textInfo.setBackground(new Color(gray));
 //       textInfo.setMaximumSize(new Dimension(800, 400));
        textInfo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        GridLayout infoGrid = new GridLayout(2, 1);
        infoGrid.setVgap(10);
        textInfo.setLayout(infoGrid);
        
        fields[0] = dataPanels(textInfo, "Kullanýcý Ýsminiz  ","");
        fields[1] = dataPanels(textInfo, "Kullanýcý Numaranýz  ","");
        middle.add(textInfo);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
//        buttons.setMaximumSize(new Dimension(500,100));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 80, 100, 80));
        GridLayout buttonsGrid= new GridLayout(1,2);
        buttonsGrid.setHgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons,"GERÝ","BACK");
        addingButton(buttons,"GÝRÝÞ","GO");
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
                new OnlineOrderSystem();
                break;
            }
            case "GO": {
            	try {
            		int id = Integer.parseInt(fields[1].getText());
            		if(!validity(id))
            			throw new Exception();
                    main_page.setVisible(false);
                	new OrderEntryPage(customer);
            	}
            	catch(Exception exc) {
            		JOptionPane.showMessageDialog(main_page,"Geçersiz Kullanýcý Numarasý!", "Sistem Giriþ Hatasý", JOptionPane.ERROR_MESSAGE);
            	}
                break;
            }
        }
    }
    
    /**
     * It is the method that checks that the user number is unique.
     * @param number
     * @return
     */
    private boolean validity(int number) {
		try {
			Scanner sc = new Scanner(new File("Customer.csv"));  
			sc.useDelimiter(";"); 
			int count = 0;
			while (sc.hasNext()){
				String temp = sc.next();
				if(count % 7 == 1) {
					int id_num = Integer.parseInt(temp);
					if(id_num == number) {
						return true;
					}
				}
				if(count % 7 == 0) {
					count = 0;
				}
				count++;
			}   
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
		return false;
    }
    
}
