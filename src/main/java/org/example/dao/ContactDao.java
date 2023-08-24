package org.example.dao;

import org.example.connexion.ConnectionUtil;
import org.example.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private Connection con;

    private PreparedStatement ps;

    protected ResultSet resultSet;


    public boolean addContact(Contact contact) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO `contact`(`name`,`numbers`) VALUES(?,?) ");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            int n = ps.executeUpdate();
            return n>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean update (Contact contact){
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("UPDATE contact SET name = ?,numbers = ? where id = ?");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            ps.setInt(3, contact.getId());
            int n = ps.executeUpdate();
            return n>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete (int id){
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("DELETE FROM contact WHERE id = ? ");
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            return n>0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contact findById(int id) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("SELECT id,name,numbers FROM contact where id = ? ");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                return  new Contact(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("numbers"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contact> findAll (){
        con = ConnectionUtil.getConnection();
        List<Contact> list = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT id,name,numbers FROM contact ");
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                 list.add(new Contact(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("numbers")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}