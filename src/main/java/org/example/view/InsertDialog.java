package org.example.view;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertDialog extends JDialog {

    private ContactDao contactDao;
    private JPanel mainPage;
    private JDialog jDialog;
    public InsertDialog(JPanel mainPage) {
        super();
        this.mainPage = mainPage;
        contactDao = new ContactDao();
        jDialog=this;
    }

    public void start (){
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();
        this.setLocationRelativeTo(mainPage);
        this.setSize(300,150);
        this.setTitle("Insert Page");
        gridBagLayout.rowHeights = new int[3];
        gridBagLayout.columnWidths = new int[2];
        this.setLayout(gridBagLayout);

        constraint.gridx = 0;
        constraint.gridy = 0;
        Label nameLabel = new Label("Name :");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(nameLabel,constraint);

        constraint.gridx = 1;
        constraint.gridy = 0;
        JTextField nameTextField = new JTextField(15);
        this.add(nameTextField,constraint);

        constraint.gridx = 0;
        constraint.gridy = 1;
        Label numLabel = new Label("Number :");
        numLabel.setFont(new Font("Arial", Font.BOLD, 13));
        this.add(numLabel,constraint);

        constraint.gridx = 1;
        constraint.gridy = 1;
        JTextField numTextField = new JTextField(15);
        this.add(numTextField,constraint);

        Container container = new Container();
        container.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton enter = new JButton("Insert");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contact contact = new Contact(nameTextField.getText(),numTextField.getText());

                if(contactDao.addContact(contact)){
                    dispose();
                    JOptionPane.showMessageDialog(jDialog,"Contact ajouté");
                }else{
                    JOptionPane.showMessageDialog(jDialog,"Erreur lors de l'ajout du contact");
                }

            }
        });
        container.add(enter,constraint);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        container.add(cancel,constraint);
        constraint.gridx=0;
        constraint.gridy=2;
        constraint.gridwidth=3;
        this.add(container,constraint);


        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
