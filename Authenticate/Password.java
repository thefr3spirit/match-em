import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Password extends JFrame {
    private JLabel label1, label2;
    private JTextField tfield1;
    private JPasswordField tfield2;
    private JButton button1;

    public Password () {
        //create window
        setTitle ("Authentication");
        setSize (400, 200);
        setLayout (new FlowLayout ());

        //the components
        label1 = new JLabel ("Enter your email or username: ");
        tfield1 = new JTextField (30);
        
        label2 = new JLabel ("Enter your password: ");
        tfield2 = new JPasswordField (16);

        button1 = new JButton ("Enter");

        //Add action listener to button1
        button1.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                //get texts from fields 1 and 2
                String password, username;
                username = tfield1.getText ();
                password = tfield2.getText ();

                //dialog message
                if (!username.equals("thefr3spirit") || !password.equals("the123")) {
                    JOptionPane.showMessageDialog (Password.this, "Your password or username is incorrect.");
                } else {
                    JOptionPane.showMessageDialog (Password.this, "Welcome to MATCH-EM");
                }
            }
        });

        //ADD components to the frame
        add (label1);
        add (tfield1);
        add (label2);
        add (tfield2);
        add (button1);
        //set default close op
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setVisible (true);
    }

    public static void main (String[] args) {
        //create instance of Password
        new Password ();
    }
}