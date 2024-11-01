package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame{
    private JPanel panel1;
    private JTextField username;
    private JPasswordField password;
    private JButton Loginbutton;
    private JFrame frame;

    private final String correctusername="admin";
    private final String correctpassword="parola";


    public Login(){

        frame=new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(250,200));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        Loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enterUsername=username.getText();
                String enterPassword=new String(password.getPassword());
                int result=JOptionPane.showConfirmDialog(frame,"Are you sure you want to log in?","Question",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(result==JOptionPane.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(frame,"You have selected cancel !");
                    return;
                }
                if(result==JOptionPane.YES_OPTION){
                    if(enterUsername.equals(correctusername)&&enterPassword.equals(correctpassword)){
                        JOptionPane.showMessageDialog(frame,"You have selected to log in !");
                            new MainWindow();
                    }
                }
                if(result==JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(frame,"You have selected not to log in !");
                }


            }
        });
    }
}
