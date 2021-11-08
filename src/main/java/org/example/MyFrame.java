package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public final class MyFrame extends JFrame {

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;
    private final Color NEUTRAL_PANEL_COLOR = Color.YELLOW;
    private final Color FAILURE_PANEL_COLOR = Color.RED;
    private final Color SUCCESS_PANEL_COLOR = Color.GREEN;
    private JPanel mainPanel = new JPanel();
    private JPanel inputFieldsPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JButton loginButton = new JButton("Login");
    private JButton cancelButton = new JButton("Cancel");
    private JPasswordField passwordField = new JPasswordField();
    private JTextField usernameField = new JTextField();
    private JLabel loginDescriptionLabel = new JLabel("Type in your login and password! We will not steal your data :) !");
    private Map<String, String> userData = new HashMap();

    public MyFrame(String title) throws HeadlessException{
        super(title);
        this.populateDummyUserData();

        this.setSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        initializeComponents();
    }

    private void initializeComponents() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        inputFieldsPanel.setLayout(new BoxLayout(inputFieldsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new FlowLayout());

        inputFieldsPanel.setMaximumSize(new Dimension((int)(0.8 * WINDOW_WIDTH), 200));

        mainPanel.setBackground(NEUTRAL_PANEL_COLOR);
        inputFieldsPanel.setBackground(NEUTRAL_PANEL_COLOR);
        buttonsPanel.setBackground(NEUTRAL_PANEL_COLOR);

        inputFieldsPanel.add(new Label("Login:"));
        inputFieldsPanel.add(usernameField);
        inputFieldsPanel.add(new Label("Password:"));
        inputFieldsPanel.add(passwordField);

        mainPanel.add(loginDescriptionLabel);
        mainPanel.add(inputFieldsPanel);
        buttonsPanel.add(loginButton);
        buttonsPanel.add(cancelButton);

        loginButton.addActionListener( (event) -> handleUserLogin(event) );
        cancelButton.addActionListener( (event) -> clearUserLoginForms() );

        mainPanel.add(buttonsPanel);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void populateDummyUserData(){
        userData.put("nick", "123");
        userData.put("bob14", "xyz");
        userData.put("Peter", "admin");
    }

    private void handleUserLogin(ActionEvent event){
        String typedLogin = usernameField.getText();
        String typedPassword = String.valueOf(passwordField.getPassword());

        if(userData.containsKey(typedLogin)){
            String userPassword = userData.get(typedLogin);
            if(userPassword.equals(typedPassword))
                handleSuccessfulLogin();
        }
        else
            handleUnsuccessfulLogin();
    }

    private void clearUserLoginForms(){
        this.passwordField.setText("");
        this.usernameField.setText("");
    }

    private void handleSuccessfulLogin(){
        mainPanel.setBackground(SUCCESS_PANEL_COLOR);
        buttonsPanel.setBackground(SUCCESS_PANEL_COLOR);
        JOptionPane.showMessageDialog(this,
                "Successful login!",
                "Yay!",
                JOptionPane.INFORMATION_MESSAGE);
        clearUserLoginForms();
    }

    private void handleUnsuccessfulLogin(){
        mainPanel.setBackground(FAILURE_PANEL_COLOR);
        buttonsPanel.setBackground(FAILURE_PANEL_COLOR);
        JOptionPane.showMessageDialog(this,
                "Invalid login!",
                ":(",
                JOptionPane.ERROR_MESSAGE);
        this.clearUserLoginForms();
    }

}
