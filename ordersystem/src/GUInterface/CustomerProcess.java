package GUInterface;

import javax.swing.*;

import renova.Company;
import renova.Individual;

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
	private JTextField[] fields = new JTextField[7];

    public CustomerProcess(){
        JPanel middle = new JPanel();

        middle.setBackground(new Color(gray));
        middle.setLayout(new GridLayout(3,1));
        
        textDesign(middle,"M��teri Sipari� Sistemi");
        
        JPanel textInfo = new JPanel();
        textInfo.setBackground(new Color(gray));
 //       textInfo.setMaximumSize(new Dimension(800, 400));
        textInfo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        GridLayout infoGrid = new GridLayout(2, 1);
        infoGrid.setVgap(10);
        textInfo.setLayout(infoGrid);
        
        fields[0] = dataPanels(textInfo, "Kullan�c� �sminiz  ","");
        fields[1] = dataPanels(textInfo, "Kullan�c� Numaran�z  ","");
        middle.add(textInfo);

        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
//        buttons.setMaximumSize(new Dimension(500,100));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 80, 100, 80));
        GridLayout buttonsGrid= new GridLayout(1,2);
        buttonsGrid.setHgap(20);
        buttons.setLayout(buttonsGrid);
        addingButton(buttons,"GER�","BACK");
        addingButton(buttons,"G�R��","GO");
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
            		if(!validity(fields[0].getText(), id))
            			throw new Exception();
                    main_page.setVisible(false);
                	new OrderEntryPage(customer);
            	}
            	catch(Exception exc) {
            		System.out.println(exc.getMessage());
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
    private boolean validity(String name, int number) {
		try {
			Scanner sc = new Scanner(new File("Customer.csv"));  
			sc.useDelimiter(";"); 
			int count = 0;
			String n = "";
			while (sc.hasNext()){
				String temp = sc.next();
				if(count % 7 == 0)
					n = temp.toString();
				else if(count % 7 == 1) {
					int id_num = Integer.parseInt(temp);
					if(id_num == number) {
						if(n.equals(name)) {
							int discount = 0;
							String address = sc.next();
							String phone = sc.next();
							String contact = sc.next();
							String dsc= sc.next();
							if(!dsc.equals(" "))
								discount = Integer.parseInt(dsc);
							String lic = sc.next();
							System.out.println(n + " " + id_num + " " + address + " " + phone + " " + contact + " " +  discount + " " + lic);
							if(lic.equals(" "))
								customer = new Company(contact, discount, address, id_num, n, phone);
							else
								customer = new Individual(lic, address, id_num, n, phone);
							return true;
						}
						else {
							JOptionPane.showMessageDialog(main_page,"Ge�ersiz Kullan�c� �smi!", "Sistem Giri� Hatas�", JOptionPane.ERROR_MESSAGE);
							return false;
						}
					}
				}
				count++;
			}   
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
		JOptionPane.showMessageDialog(main_page,"Ge�ersiz Kullan�c� Numaras�!", "Sistem Giri� Hatas�", JOptionPane.ERROR_MESSAGE);
		return false;
    }
    
}
