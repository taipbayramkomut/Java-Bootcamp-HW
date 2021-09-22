package GUInterface;

import renova.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Order Entry Page,
 * When the customer enters his/her identity information correctly, he/she can place an order in this section.
 * @author TOSHIBA
 *
 */
public class OrderEntryPage extends TemplateScreen{
	private static final long serialVersionUID = 1L;
	
	public OrderEntryPage(Customer customer) {
		this.customer = customer;
        JPanel operationTable = new JPanel();
        operationTable.setBackground(new Color(gray));
        operationTable.setLayout(new GridLayout(4,1));

        JPanel table = new JPanel();
        table.setBackground(new Color(gray));
        table.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        table.setLayout(new GridLayout(2,1));

        JPanel textTable = new JPanel();
        textTable.setBackground(Color.white);
        textTable.setLayout(new GridLayout());
        table.add(textTable);

        textDesign(textTable,"SÝPARÝÞ GÝRÝÞ SAYFASI");
        
        JPanel userInfo = new JPanel();
        userInfo.setBackground(Color.white);
        userInfo.setLayout(new GridLayout());
        table.add(userInfo);
        textDesign(userInfo,"131044010 : Bayram Komut");

        operationTable.add(table);
        systemInfo(operationTable,"Sipariþ Ekle", "ADD");
        systemInfo(operationTable, "Sipariþ Güncelle", "UPDATE");
        exitButton(operationTable);

        emptyBackground(item);
        item.add(operationTable);
        emptyBackground(item);

    }

    /**
     * There is a button on the page to exit
     * @param operationTable main panel of the page
     */
    private void exitButton(JPanel operationTable){
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        GridLayout buttonLayout = new GridLayout(1,1);
        buttons.setLayout(buttonLayout);
        addingButton(buttons,"Çýkýþ","EXIT");
        operationTable.add(buttons);
    }

    private void systemInfo(JPanel operationTable, String heading, String command){
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(gray));
        buttons.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
        GridLayout buttonLayout = new GridLayout(1,1);
        buttons.setLayout(buttonLayout);
        addingButton(buttons,heading,command);
        operationTable.add(buttons);
    }


    /**
     * One of the two buttons is for program exit, the other is for proceeding from the next page.
     * @param e For button state control
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "ADD":{
                main_page.setVisible(false);
                new OrderDetailsPage(customer);
                break;
            }
            case "UPDATE":{
                main_page.setVisible(false);
                new OrderUpdatePage();
                break;
            }
            case "EXIT":{
                main_page.setVisible(false);
                new CustomerProcess();
                break;
            }
        }
    }
	
}
