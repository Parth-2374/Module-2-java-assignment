package com.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SwingCRUDDemo extends JFrame implements ActionListener {

    JLabel l1, l2, l3, l4;

    JTextField t1, t2, t3, t4;

    JButton b1, b2, b3, b4;

    Connection conn;

    public SwingCRUDDemo() {

        setLayout(new FlowLayout());

        l1 = new JLabel("ID");
        l2 = new JLabel("First Name");
        l3 = new JLabel("Last Name");
        l4 = new JLabel("Email");

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);

        b1 = new JButton("Insert");
        b2 = new JButton("Update");
        b3 = new JButton("Select");
        b4 = new JButton("Delete");

        add(l1);
        add(t1);

        add(l2);
        add(t2);

        add(l3);
        add(t3);

        add(l4);
        add(t4);

        add(b1);
        add(b2);
        add(b3);
        add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        setSize(300, 300);
        setVisible(true);

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc",
                    "root",
                    "");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {

        try {

            int id = Integer.parseInt(t1.getText());

            String fname = t2.getText();

            String lname = t3.getText();

            String email = t4.getText();

            // INSERT
            if (e.getSource() == b1) {

                PreparedStatement pst = conn.prepareStatement(
                        "insert into employee values(?,?,?,?)");

                pst.setInt(1, id);
                pst.setString(2, fname);
                pst.setString(3, lname);
                pst.setString(4, email);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Record Inserted");
            }

            // UPDATE
            if (e.getSource() == b2) {

                PreparedStatement pst = conn.prepareStatement(
                        "update employee set fname=?,lname=?,email=? where id=?");

                pst.setString(1, fname);
                pst.setString(2, lname);
                pst.setString(3, email);
                pst.setInt(4, id);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Record Updated");
            }

            // SELECT
            if (e.getSource() == b3) {

                PreparedStatement pst = conn.prepareStatement(
                        "select * from employee where id=?");

                pst.setInt(1, id);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    t2.setText(rs.getString("fname"));
                    t3.setText(rs.getString("lname"));
                    t4.setText(rs.getString("email"));

                    JOptionPane.showMessageDialog(this,
                            "Record Found");
                }
            }

            // DELETE
            if (e.getSource() == b4) {

                PreparedStatement pst = conn.prepareStatement(
                        "delete from employee where id=?");

                pst.setInt(1, id);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Record Deleted");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new SwingCRUDDemo();
    }
}