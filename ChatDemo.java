
package week11;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class ChatDemo implements ActionListener, WindowListener{
    private JFrame fr;
    private JPanel pn1;
    private JPanel pn2;
    private JPanel pn3;
            
    private JTextArea txtarea;
    private JTextField txtfld;
    
    private JButton b1;
    private JButton b2;
    
    public ChatDemo(){
        fr = new JFrame();
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();
        
        txtarea =  new JTextArea(20, 45);
        txtfld =  new JTextField(45);
        
        b1 = new JButton("Submit");
        b2 = new JButton("Reset");
        
        fr.addWindowListener(this);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        
        pn1.setLayout(new BorderLayout());
        pn1.add(txtarea);
        
        pn2.setLayout(new GridLayout(2,1));
        pn2.add(txtfld);
        
        pn3.setLayout(new FlowLayout());
        pn3.add(b1);
        pn3.add(b2);
        
        pn2.add(pn3);
        pn1.add(pn2, BorderLayout.SOUTH);
        fr.add(pn1);
        
        fr.pack();
        fr.setSize(500,525);
        fr.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource().equals(b1)){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            txtarea.setText(txtarea.getText()+ "\n" + dtf.format(LocalDateTime.now()) + " : " + txtfld.getText());
        }
        if (ae.getSource().equals(b2)){
            txtarea.setText("");
        }
        
    }
    


    public static void main(String[] args) throws FileNotFoundException {
        
        new ChatDemo();
    }

    @Override
    public void windowOpened(WindowEvent we) {
        try (FileInputStream fin = new FileInputStream("ChatDemo.txt");){
        int i = fin.read();
        while (i != -1) {
            txtarea.setText(txtarea.getText() + (char)i);
            i = fin.read();
        }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void windowClosing(WindowEvent we) {
        String str = txtarea.getText();
        try(FileOutputStream fout = new FileOutputStream("ChatDemo.txt");) {
            for(int i = 0; i < str.length(); i++)
            fout.write(str.charAt(i));
            System.out.println("Saved");
        } 
        catch (IOException e) {
            System.out.println(e);
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
