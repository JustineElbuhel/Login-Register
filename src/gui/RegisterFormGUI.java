package gui;

import com.mysql.cj.log.Log;
import constants.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

public class RegisterFormGUI extends Form{

    public RegisterFormGUI(){
        super("Register");
        addRegistrationComponents();
    }

    private void addRegistrationComponents(){
        // create register label
        JLabel registerLabel = new JLabel("Register");

        // configure component's x,y position and width/height values relative to the GUI
        registerLabel.setBounds(0, 25, 530, 100);

        // change the font color
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);

        // change the font size
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add component to GUI
        add(registerLabel);

        // create username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(CommonConstants.TEXT_COLOR);
        usernameLabel.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setForeground(CommonConstants.TEXT_COLOR);
        usernameField.setBackground(CommonConstants.SECONDARY_COLOR);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));


        add(usernameLabel);
        add(usernameField);

        // create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 250, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 285, 450, 55);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 18));

        add(passwordLabel);
        add(passwordField);

        // create password confirmation label
        JLabel passwordConfirmationLabel = new JLabel("Confirm Password:");
        passwordConfirmationLabel.setBounds(30, 350, 400, 25);
        passwordConfirmationLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordConfirmationLabel.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordConfirmationLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create password text field
        JPasswordField passwordConfirmationField = new JPasswordField();
        passwordConfirmationField.setBounds(30, 385, 450, 55);
        passwordConfirmationField.setForeground(CommonConstants.TEXT_COLOR);
        passwordConfirmationField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordConfirmationField.setFont(new Font("Dialog", Font.PLAIN, 18));

        add(passwordConfirmationLabel);
        add(passwordConfirmationField);

        // create register button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));

        // change the cursor to a hand when hovering over the cursor button
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(CommonConstants.TEXT_COLOR);
        registerButton.setBounds(125, 520, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = new String(passwordField.getPassword());

                // get confirmed password
                String confirmedPassword = new String(passwordConfirmationField.getPassword());

                // validate user input
                if(validateUserInput(username, password, confirmedPassword)){
                    // register user to the db
                    if(MyJDBC.register(username, password)) {
                        // dispose of this gui
                        RegisterFormGUI.this.dispose();

                        // take user back to the login gui
                        LoginFormGUI loginFormGUI = new LoginFormGUI();
                        loginFormGUI.setVisible(true);

                        // create a result dialog
                        JOptionPane.showMessageDialog(loginFormGUI,
                                "Registered Account Successfully!");
                    } else {
                        // registration failed (likely due to the user already existing in the db)
                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "Error: Username already taken");
                    }
                } else {
                    // invalid user input
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,
                            "Username must be at least 6 characters and/or " +
                            "Passwords must match");
                }
            }
        });
        add(registerButton);

        // create login label (used to load the login GUI)
        JLabel loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);
        loginLabel.setBounds(125, 600, 250, 30);

        // add functionality so when clicked it will launch the login form GUI
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFormGUI.this.dispose();

                new LoginFormGUI().setVisible(true);
            }
        });
        add(loginLabel);

    }

    private boolean validateUserInput(String username, String password, String confirmPassword){
        // all fields must have a value
        if(username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0){
            return false;
        }

        // username must have at least 6 characters
        if(username.length() < 6){
            return false;
        }

        // passwords should match
        if(!password.equals(confirmPassword)){
            return false;
        }

        // passes validation
        return true;
    }
}










