package org.example.view;

import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class MainPage {
    private JPanel mainPage;

    public MainPage() {

        mainPage = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[3];
        gridBagLayout.rowHeights = new int[2];
        mainPage.setLayout(gridBagLayout);

        GridBagConstraints constraint = new GridBagConstraints();


        constraint.gridx = 0;
        constraint.gridy = 0;
        JButton buttonInsertion = new JButton("Insert");
        buttonInsertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonInsertAction();
            }
        });
        mainPage.add(buttonInsertion,constraint);

        constraint.gridx = 1;
        constraint.gridy = 0;
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEditAction();
            }
        });
        mainPage.add(buttonEdit,constraint);

        constraint.gridx = 2;
        constraint.gridy = 0;
        JButton buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonDeleteAction();
            }
        });
        mainPage.add(buttonDelete,constraint);

        constraint.gridx = 1;
        constraint.gridy = 1;
        JButton buttonSelect = new JButton("Select");
        buttonSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSelectAction();
            }
        });
        mainPage.add(buttonSelect,constraint);

    }

    private void buttonInsertAction (){
       InsertDialog insertDialog = new InsertDialog(mainPage);
       insertDialog.start();
    }

    private void buttonEditAction (){
        EditDialog editDialog = new EditDialog(mainPage);
        editDialog.start();
    }

    private void buttonDeleteAction (){
        DeleteDialog deleteDialog =new DeleteDialog(mainPage);
        deleteDialog.start();
    }

    private void buttonSelectAction (){
        SelectDialog selectDialog =new SelectDialog(mainPage);
        selectDialog.start();
    }
}
