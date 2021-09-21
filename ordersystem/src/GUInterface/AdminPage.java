package GUInterface;
import renova.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * First we get the characteristics of the disease of the epidemic. This page is it.
 */
public class AdminPage extends TemplateScreen implements ItemListener {
	private static final long serialVersionUID = 1L;
	private final JComboBox<String> spread;
	private JTextField datas[] = new JTextField[7];
	private String options = "Kurumsal �irket";

    public AdminPage(){
        JPanel middle = new JPanel();

        middle.setBackground(new Color(gray));
        middle.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));
        middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));
        textDesign(middle,"Y�netici Bilgi Sistemi");

        JPanel info = new JPanel();
        info.setBackground(new Color(gray));
 //       info.setMaximumSize(new Dimension(500,300));
        info.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        GridLayout infoGrid= new GridLayout(1,1);
        infoGrid.setVgap(20);
        info.setLayout(infoGrid);

        ArrayList<String> elements = new ArrayList<>();
        elements.add("Kurumsal �irket");
        elements.add("Bireysel M��teri");
        spread = addSelectFromList(info, elements,"M��teri Tipi", "");
        spread.addItemListener(this);
        middle.add(info);
        
        
        JPanel textInfo = new JPanel();
        textInfo.setBackground(new Color(gray));
//        textInfo.setMaximumSize(new Dimension(800, 400));
        textInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        infoGrid = new GridLayout(7, 1);
        infoGrid.setVgap(10);
        textInfo.setLayout(infoGrid);
       
        datas[0] = dataPanels(textInfo, "M��teri �smi         ","");
        datas[1] = dataPanels(textInfo, "Kullan�c� Numaras�  ","");
        datas[2] = dataPanels(textInfo, "Telefon Numaras�    ","");
        datas[3] = dataPanels(textInfo, "M��teri Adresi      ","");
        datas[4] = dataPanels(textInfo, "�irket Mail          ","");
        datas[5] = dataPanels(textInfo, "�irket �ndirimi      ","");
        datas[6] = dataPanels(textInfo, "Lisans Numaras�     ","");
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
                new OnlineOrderSystem();
                break;
            }
            case "GO": {
            	try {
            		int id = Integer.parseInt(datas[1].getText());
                	if(options.equals("Kurumsal �irket")) {
                    	try {
                    		int discount = Integer.parseInt(datas[5].getText());
                    		customer = new Company(datas[4].getText(), discount, datas[3].getText(), id, datas[0].getText(),datas[2].getText());
                        	JOptionPane.showMessageDialog(main_page,"M��teri eklendi.", "M��teri Durum", JOptionPane.PLAIN_MESSAGE);
                    	}
                    	catch(Exception ex) {
                    		JOptionPane.showMessageDialog(main_page,"�irket indirimi 0 ve �zeri tam say� olmal�d�r.", "M��teri Durum", JOptionPane.ERROR_MESSAGE);
                    	}
                	}
                	else {
                		customer = new Individual(datas[6].getText(), datas[3].getText(),id,datas[0].getText(),datas[2].getText());
                    	JOptionPane.showMessageDialog(main_page,"M��teri eklendi.", "M��teri Durum", JOptionPane.PLAIN_MESSAGE);
                	}
            	}
            	catch(Exception exc) {
                	JOptionPane.showMessageDialog(main_page,"Kullan�c� numaras� pozitif tam say� olmal�d�r.", "M��teri Durum", JOptionPane.ERROR_MESSAGE);
            	}
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
    	options = spread.getSelectedItem().toString();
    }
    
}
