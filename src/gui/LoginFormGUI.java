package gui;

import constants.CommonConstants;
import db.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFormGUI extends Form{

    public LoginFormGUI(){
        super("Login");
        addGUIComponents();
    }

    private void addGUIComponents(){
        // create login label
        JLabel loginLabel = new JLabel("Login");

        // configure component's x,y position and width/height values relative to the GUI
        loginLabel.setBounds(0, 25, 530, 100);

        // change the font color
        loginLabel.setForeground(CommonConstants.TEXT_COLOR);

        // change the font size
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));

        // center text
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // add component to GUI
        add(loginLabel);

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
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setForeground(CommonConstants.TEXT_COLOR);
        passwordLabel.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setForeground(CommonConstants.TEXT_COLOR);
        passwordField.setBackground(CommonConstants.SECONDARY_COLOR);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 18));

        add(passwordLabel);
        add(passwordField);

        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));

        // change the cursor to a hand when hovering over the cursor button
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(CommonConstants.TEXT_COLOR);
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                String username = usernameField.getText();

                // get password
                String password = new String(passwordField.getPassword());

                // check db if the username and password combo is valid
                if(MyJDBC.validateLogin(username, password)){
                    JOptionPane.showMessageDialog(LoginFormGUI.this,
                            "Login Success!");
                } else {
                    // login failed
                    JOptionPane.showMessageDialog(LoginFormGUI.this,
                            "Login Failed");
                }
            }
        });
        add(loginButton);

        // create register label (used to load the register GUI)
        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonConstants.TEXT_COLOR);
        registerLabel.setBounds(125, 600, 250, 30);

        // add functionality so when clicked it will launch the register form GUI
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFormGUI.this.dispose();

                new RegisterFormGUI().setVisible(true);
            }
        });

        add(registerLabel);


    }
}
