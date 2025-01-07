import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;


public class StudentMessComplaintApp {
    private Frame frame;
    private CardLayout cardLayout;
    private Panel loginPanel, signupPanel, homePanel;
    
    // Database or dummy data for login (this could be replaced with real authentication logic)
    private String storedUsername = "student1";
    private String storedPassword = "password123";
    
    // Constructor to setup the GUI
    public StudentMessComplaintApp() {
        frame = new Frame("Student Mess Complaint System");
        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);
        
        // Initialize Panels
        loginPanel = createLoginPanel();
        signupPanel = createSignupPanel();
        homePanel = createHomePanel();
        
        // Adding Panels to Frame
        frame.add("Login", loginPanel);
        frame.add("Signup", signupPanel);
        frame.add("Home", homePanel);
        
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    // Method to create the Login Panel
    private Panel createLoginPanel() {
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1));
        
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordField.setEchoChar('*');
        
        Button loginButton = new Button("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                
                // Simple validation
                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    cardLayout.show(frame, "Home"); // Redirect to Home Page
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials, please try again.");
                }
            }
        });
        
        Button signupButton = new Button("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(frame, "Signup"); // Redirect to Signup Page
            }
        });
        
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);
        
        return panel;
    }

    // Method to create the Signup Panel
    private Panel createSignupPanel() {
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1));
        
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordField.setEchoChar('*');
        
        Button signupButton = new Button("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                
                // Here you can store the credentials in a database or file (currently it doesn't do that)
                storedUsername = username;
                storedPassword = password;
                
                JOptionPane.showMessageDialog(frame, "Signup successful!");
                cardLayout.show(frame, "Login"); // Redirect to Login Page
            }
        });
        
        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(signupButton);
        
        return panel;
    }

    // Method to create the Home Panel
    private Panel createHomePanel() {
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        
        TextArea complaintArea = new TextArea("Enter your complaint here...");
        Button submitButton = new Button("Submit Complaint");
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String complaint = complaintArea.getText();
                
                if (complaint.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a complaint.");
                } else {
                    // Write complaint to a text file
                    writeComplaintToFile(complaint);
                    JOptionPane.showMessageDialog(frame, "Complaint submitted successfully!");
                }
            }
        });
        
        panel.add(complaintArea, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        
        return panel;
    }

    // Method to write complaint to a text file
    private void writeComplaintToFile(String complaint) {
        try {
            // Open or create the file "complaints.txt" in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt", true));
            writer.write(complaint);
            writer.newLine();  // To add a new line after each complaint
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error while saving the complaint.");
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new StudentMessComplaintApp();
    }
}

