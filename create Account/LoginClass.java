import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginClass extends JFrame {
    private AccountCreation accountCreation;

    public LoginClass() {
        accountCreation = new AccountCreation();

        // Setup main frame
        setTitle("User Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Components for login
        JLabel label1 = new JLabel("Enter your email or username: ");
        JTextField tfield1 = new JTextField(30);

        JLabel label2 = new JLabel("Enter your password: ");
        JPasswordField tfield2 = new JPasswordField(30);

        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameOrEmail = tfield1.getText();
                String password = new String(tfield2.getPassword());

                if (accountCreation.validatePassword(usernameOrEmail, password)) {
                    JOptionPane.showMessageDialog(LoginClass.this, "Welcome to MATCH-EM");
                } else {
                    JOptionPane.showMessageDialog(LoginClass.this, "Your password or username is incorrect.");
                }
            }
        });

        // Add action listener for create account button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the create account window
                new CreateAccountWindow(accountCreation);
            }
        });

        // Add components to the frame
        add(label1);
        add(tfield1);
        add(label2);
        add(tfield2);
        add(loginButton);
        add(createAccountButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginClass();
    }
}

class CreateAccountWindow extends JFrame {
    private AccountCreation accountCreation;

    public CreateAccountWindow(AccountCreation accountCreation) {
        this.accountCreation = accountCreation;

        setTitle("Create Account");
        setSize(400, 250);
        setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Username: ");
        JTextField tfield1 = new JTextField(30);

        JLabel label2 = new JLabel("Email: ");
        JTextField tfield2 = new JTextField(30);

        JLabel label3 = new JLabel("Password: ");
        JPasswordField tfield3 = new JPasswordField(30);

        JButton createAccountButton = new JButton("Create Account");

        // Add action listener for create account button
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfield1.getText();
                String email = tfield2.getText();
                String password = new String(tfield3.getPassword());

                if (accountCreation.createAccount(username, email, password)) {
                    JOptionPane.showMessageDialog(CreateAccountWindow.this, "Account created successfully.");
                    dispose(); // Close the create account window
                } else {
                    JOptionPane.showMessageDialog(CreateAccountWindow.this, "Username or email already exists.");
                }
            }
        });

        // Add components to the frame
        add(label1);
        add(tfield1);
        add(label2);
        add(tfield2);
        add(label3);
        add(tfield3);
        add(createAccountButton);

        setVisible(true);
    }
}
