package org.example.view;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectDialog extends JDialog{
    private JPanel mainPage;
    private ContactDao contactDao;
    private static String[] columnNames = {"Id","Name","Number"};
    private DefaultTableModel dtm = new DefaultTableModel(null, columnNames) {

        @Override
        public Class<?> getColumnClass(int col) {
            return getValueAt(0, col).getClass();
        }
    };

    public SelectDialog(JPanel mainPage) {
        super();
        this.mainPage = mainPage;
        contactDao = new ContactDao();
        dtm.addRow(new Object[]{"Id","Name","Number"});
    }

    public void start (){
        BorderLayout gridBagLayout = new BorderLayout();
        this.setLocationRelativeTo(mainPage);
        this.setSize(400,400);
        this.setTitle("Insert Page");
        this.setLayout(gridBagLayout);

        JTable table = new JTable(dtm);
        table.setSize(300,300);
        this.add(table,BorderLayout.NORTH);

        List<Contact> listContacts= contactDao.findAll();
        listContacts.forEach(c ->{
            dtm.addRow(new Object[]{c.getId(),c.getName(),c.getNumber()});
        });

        JButton cancel = new JButton("Ok");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(cancel,BorderLayout.SOUTH);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
