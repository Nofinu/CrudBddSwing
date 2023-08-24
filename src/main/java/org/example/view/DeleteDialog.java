package org.example.view;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {
    private JPanel mainPage;
    private JDialog jDialog;
    private ContactDao contactDao;

    public DeleteDialog(JPanel mainPage) {
        super();
        this.mainPage = mainPage;
        contactDao = new ContactDao();
        jDialog=this;
    }

    public void start (){
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        this.setLocationRelativeTo(mainPage);
        this.setSize(400,150);
        this.setTitle("Insert Page");
        gridBagLayout.rowHeights = new int[3];
        gridBagLayout.columnWidths = new int[3];
        this.setLayout(gridBagLayout);

        constraint.gridx = 0;
        constraint.gridy = 0;
        Label idLabel = new Label("Id :");
        idLabel.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(idLabel,constraint);

        constraint.gridx = 1;
        constraint.gridy = 0;
        JTextField idTextField= new JTextField(15);
        this.add(idTextField,constraint);

        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        JButton enter = new JButton("Ok");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(contactDao.delete(Integer.parseInt(idTextField.getText()))){
                    JOptionPane.showMessageDialog(jDialog,"Contact delete");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(jDialog,"Error durant la suppresion");
                }
            }
        });
        this.add(enter,constraint);

        constraint.gridx = 2;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(cancel,constraint);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
