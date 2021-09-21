package GUInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OnlineOrderSystem extends TemplateScreen {

	private static final long serialVersionUID = 1L;


	public OnlineOrderSystem() {
        JPanel operationTable = new JPanel();
        operationTable.setBackground(new Color(gray));
        operationTable.setLayout(new GridLayout(4,1));

        JPanel table = new JPanel();
        table.setBackground(new Color(gray));
        table.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        table.setLayout(new GridLayout());

        JPanel textTable = new JPanel();
        textTable.setBackground(Color.white);
        textTable.setLayout(new GridLayout());
        table.add(textTable);

        textDesign(textTable,"Çevrimiçi Sipariþ Sistemi");

        operationTable.add(table);
        systemInfo(operationTable,"Yönetici Bilgi Sistemi", "ADMIN");
        systemInfo(operationTable, "Müþteri Sipariþ Sistemi", "USER");
        exitButton(operationTable);

        emptyBackground(item);
        item.add(operationTable);
        emptyBackground(item);

    }


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


    @Override
    public void actionPerformed(ActionEvent e) {
        String command= e.getActionCommand();
        switch (command) {
            case "ADMIN":{
                main_page.setVisible(false);
                new AdminPage();
                break;
            }
            case "USER":{
                main_page.setVisible(false);
                new CustomerProcess();
                break;
            }
            case "EXIT":{
                main_page.setVisible(false);
                System.exit(1);
                break;
            }
        }
    }

}

