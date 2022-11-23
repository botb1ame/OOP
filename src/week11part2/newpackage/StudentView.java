
package week11part2.newpackage;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentView implements ActionListener, WindowListener{
    private JPanel pn1;
    private JPanel pn2;
    private JPanel pn3;
    private JPanel pn4;

    private JLabel txt1;
    private JLabel txt2;
    private JLabel txt3;
    private JLabel showmoney;
    
    private JTextField nameinp;
    private JTextField idinp;
    
    private JButton bn1;
    private JButton bn2;


    public StudentView(){
        JFrame fr = new JFrame("Student View");
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();
        pn4 = new JPanel();

        txt1 = new JLabel("ID :");
        txt2 = new JLabel("Name :");
        txt3 = new JLabel("Money :");
        
        nameinp = new JTextField();
        idinp = new JTextField();
        showmoney = new JLabel("0");

        bn1 = new JButton("Deposit");
        bn2 = new JButton("Withdraw");
        bn1.addActionListener(this);
        bn2.addActionListener(this);

        pn1.setLayout(new GridLayout(1, 2));
        
        pn2.setLayout(new GridLayout(3, 1));
        pn2.add(txt1);
        pn2.add(txt2);
        pn2.add(txt3);
        
        pn3.setLayout(new GridLayout(3, 1));
        pn3.add(idinp);
        pn3.add(nameinp);
        pn3.add(showmoney);
        
        pn4.setLayout(new GridLayout(1, 2));
        pn4.add(bn1);
        pn4.add(bn2);

        pn1.add(pn2);
        pn1.add(pn3);
        fr.add(pn1);
        fr.add(pn4, BorderLayout.SOUTH);

        fr.addWindowListener(this);
        fr.setSize(300, 300);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        int num = Integer.parseInt(showmoney.getText());

        if (ae.getSource().equals(bn1)){
            System.out.println("in");
            showmoney.setText(num + 100 + "");
        }
        if (ae.getSource().equals(bn2)){
            System.out.println("out");
            if (Integer.parseInt(showmoney.getText()) > 100){
                showmoney.setText((num - 100) + "");
            }
        }
    }

    
    @Override
    public void windowOpened(WindowEvent e) {
        try(FileInputStream fin = new FileInputStream("StudentM.dat");){
            ObjectInputStream oin = new ObjectInputStream(fin);
            Student s1 = (Student) oin.readObject();
            idinp.setText(s1.getID()+"");
            nameinp.setText(s1.getName()+"");
            showmoney.setText(s1.getMoney()+"");
            System.out.println("File found, connected");
        }

        catch(IOException er){
            System.out.println("Can't connect to file");
            File file = new File("StudentM.dat");
            try {
                file.createNewFile();
                System.out.println("Created a file");
            } 
            catch (IOException ex) {
                System.out.println("Can't create file");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Class error");
        } 
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("-----------------");
        System.out.println("Saving");
        System.out.println("Name: " + nameinp.getText());
        System.out.println("Id: " + Integer.parseInt(idinp.getText()));
        System.out.println("Money: " + Integer.parseInt(showmoney.getText()));
        System.out.println("-----------------");


        Student student = new Student(nameinp.getText(), Integer.parseInt(idinp.getText()), Integer.parseInt(showmoney.getText()));
        try(FileOutputStream fout = new FileOutputStream("StudentM.dat")){
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(student);
            System.out.println("Saved");
            
            oout.close(); fout.close();
        }
        catch(IOException er){
            System.out.println("Save failed!");
            er.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
