package org.example.view;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog {
    private JPanel mainPage;
    private ContactDao contactDao;
    private JDialog jDialog;

    public EditDialog(JPanel mainPage) {
        super();
        this.mainPage = mainPage;
        this.contactDao = new ContactDao();
        jDialog = this;
    }

    public void start(){
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        this.setLocationRelativeTo(mainPage);
        this.setSize(400,150);
        this.setTitle("Insert Page");
        gridBagLayout.rowHeights = new int[4];
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

        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        Label nameLabel = new Label("Name :");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(nameLabel,constraint);

        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridwidth = 1;
        JTextField nameTextField = new JTextField(15);
        this.add(nameTextField,constraint);

        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 1;
        Label numLabel = new Label("Number :");
        numLabel.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(numLabel,constraint);

        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.gridwidth = 2;
        JTextField numTextField = new JTextField(15);
        this.add(numTextField,constraint);

        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.gridwidth = 2;
        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contact = contactDao.findById(Integer.parseInt(idTextField.getText()));
                if(contact != null){
                    nameTextField.setText(contact.getName());
                    numTextField.setText(contact.getNumber());
                }
            }
        });
        this.add(search,constraint);

        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 1;
        JButton enter = new JButton("Insert");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contact =  new Contact(Integer.parseInt(idTextField.getText()),nameTextField.getText(),numTextField.getText());
                if(contactDao.update(contact)){
                    JOptionPane.showMessageDialog(jDialog,"Contact Update");
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(jDialog,"Erreur lors de l'update du contact");
                }
            }
        });
        this.add(enter,constraint);

        constraint.gridx = 1;
        constraint.gridy = 3;
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
