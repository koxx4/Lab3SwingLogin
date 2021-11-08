package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final JButton loginButton = new JButton("Login");
    private final JButton cancelButton = new JButton("Cancel");
    private final JPasswordField passwordField = new JPasswordField();
    private final JTextField usernameField = new JTextField();
    private final JLabel loginDescriptionLabel = new JLabel("Type in your login and password! We will not steal your data :) !");

    public LoginPanel() {
        super();

        var gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginDescriptionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Login: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Password: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(loginButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(cancelButton, gbc);

        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JLabel getLoginDescriptionLabel() {
        return loginDescriptionLabel;
    }

    public void clearLoginInputs(){
        usernameField.setText("");
        passwordField.setText("");
    }
}
