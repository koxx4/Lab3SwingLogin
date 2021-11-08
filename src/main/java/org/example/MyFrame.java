package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public final class MyFrame extends JFrame {

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;
    private final Color FAILURE_PANEL_COLOR = Color.RED;
    private final Color SUCCESS_PANEL_COLOR = Color.GREEN;
    private final JPanel mainPanel = new JPanel();
    private final JButton loginButton = new JButton("Login");
    private final JButton cancelButton = new JButton("Cancel");
    private final JPasswordField passwordField = new JPasswordField();
    private final JTextField usernameField = new JTextField();
    private final JLabel loginDescriptionLabel = new JLabel("Type in your login and password! We will not steal your data :) !");
    private final JMenuBar menuBar = new JMenuBar();
    private final Map<String, String> userData = new HashMap();

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
        var gbc = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(loginDescriptionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Login: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Password: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(loginButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(cancelButton, gbc);

        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        initializeMenuBar();

        loginButton.addActionListener( (event) -> handleUserLogin(event) );
        cancelButton.addActionListener( (event) -> clearUserLoginForms() );

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void initializeMenuBar() {
        var jMenu = new JMenu("Help");
        var possibleLoginsHintItem = new JMenuItem("All possible logins");
        possibleLoginsHintItem.addActionListener((event) -> showPossibleLoginsDialog());
        jMenu.add(possibleLoginsHintItem);
        menuBar.add(jMenu);
        setJMenuBar(this.menuBar);
    }

    private void showPossibleLoginsDialog() {
        StringBuilder loginsMessageBuilder = new StringBuilder();
        for(var login : this.userData.keySet())
            loginsMessageBuilder.append(String.format("Login: %s, password: %s \n",
                    login,
                    this.userData.get(login)));


        JOptionPane.showMessageDialog(this,
                loginsMessageBuilder.toString(),
                "Confidential ;)",
                JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(this,
                "Successful login! \u2611",
                "Yay!",
                JOptionPane.INFORMATION_MESSAGE);
        clearUserLoginForms();
    }

    private void handleUnsuccessfulLogin(){
        mainPanel.setBackground(FAILURE_PANEL_COLOR);
        JOptionPane.showMessageDialog(this,
                "Invalid login! \u274c",
                ":(",
                JOptionPane.ERROR_MESSAGE);
        this.clearUserLoginForms();
    }

}
