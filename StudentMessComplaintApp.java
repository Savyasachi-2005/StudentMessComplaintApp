import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMessComplaintApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel loginPanel, signupPanel, homePanel, profilePanel;
    private JButton loginButton, signupButton;
    private String storedUsername = "student1";
    private String storedPassword = "password123";
    private JLabel welcomeLabel;
    private List<String> complaints = new ArrayList<>();

    public StudentMessComplaintApp() {
        frame = new JFrame("Student Mess Complaint System");
        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        // Initializing panels
        loginPanel = createLoginPanel();
        signupPanel = createSignupPanel();
        homePanel = createHomePanel();
        profilePanel = new JPanel(); // Placeholder for profile panel (not used yet)

        // Adding panels to the frame with respective names for card layout
        frame.add(loginPanel, "Login");
        frame.add(signupPanel, "Signup");
        frame.add(homePanel, "Home");
        frame.add(profilePanel, "Profile");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
    }

    // Creates the navigation bar with login and signup buttons
    private JPanel createNavigationBar() {
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navBar.setBackground(new Color(0, 123, 255)); // Set background color of navbar
    
        // Login and Signup buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
    
        // Action listeners for navigation buttons
        loginButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Login"));
        signupButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Signup"));
    
        navBar.add(loginButton);
        navBar.add(signupButton);
    
        return navBar;
    }
    
    // Hides the navigation buttons after login or signup
    private void hideNavButtons() {
        loginButton.setVisible(false);
        signupButton.setVisible(false);
    }

    // Custom JPanel for responsive background image
    class ResponsiveImagePanel extends JPanel {
        private Image backgroundImage;
    
        public ResponsiveImagePanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.err.println("Error loading image: " + imagePath);
            }
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                int width = getWidth();
                int height = getHeight();
                g.drawImage(backgroundImage, 0, 0, width, height, this);
            }
        }
    }
    
    // Creates login panel with username and password fields
    private JPanel createLoginPanel() {
        JPanel panel = new ResponsiveImagePanel("login.png"); // Background image
        panel.setLayout(new BorderLayout());
    
        // Form for login
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(true); // Allow transparency
        formPanel.setBackground(new Color(255, 255, 255, 180)); // White with opacity
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel userLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
    
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                welcomeLabel.setText("Welcome, " + username + "!");
                cardLayout.show(frame.getContentPane(), "Home");
                hideNavButtons();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Adding form components to the grid
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);
    
        panel.add(createNavigationBar(), BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.SOUTH); // Place form at the bottom
    
        return panel;
    }
    
    // Creates signup panel with username, password, and email fields
    private JPanel createSignupPanel() {
        JPanel panel = new ResponsiveImagePanel("signup.png"); // Background image
        panel.setLayout(new BorderLayout());
    
        // Form for signup
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(true); // Allow transparency
        formPanel.setBackground(new Color(255, 255, 255, 180)); // White with opacity
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
    
        JLabel userLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
    
        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                storedUsername = username;
                storedPassword = password;
                JOptionPane.showMessageDialog(frame, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(frame.getContentPane(), "Login");
            }
        });
    
        // Adding form components to the grid
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(signupButton, gbc);
    
        panel.add(createNavigationBar(), BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.SOUTH); // Place form at the bottom
    
        return panel;
    }

    // Creates the home panel where complaints can be submitted
    private JPanel createHomePanel() {
        JPanel panel = new ResponsiveImagePanel("mess1.jpeg"); // Dynamically scales background
        panel.setLayout(new BorderLayout());
        
        // Welcome label with animation effect
        welcomeLabel = new JLabel("Welcome, " + storedUsername + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
        welcomeLabel.setForeground(Color.BLACK);
        
        // Animation for welcome label
        Timer timer = new Timer(100, new ActionListener() {
            private int fontSize = 24;
            private boolean growing = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (growing) {
                    fontSize++;
                    if (fontSize >= 36) growing = false;
                } else {
                    fontSize--;
                    if (fontSize <= 24) growing = true;
                }
                welcomeLabel.setFont(new Font("Arial", Font.BOLD, fontSize));
            }
        });
        timer.start();
        
        // Text area for entering complaints
        JTextArea complaintArea = new JTextArea(5, 30);
        JButton submitButton = new JButton("Submit Complaint");
        
        // Action to submit complaint
        submitButton.addActionListener(e -> {
            String complaint = complaintArea.getText().trim();
            if (complaint.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a complaint.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                complaints.add(complaint);
                writeComplaintToFile("Complaint by " + storedUsername + ": " + complaint);
                JOptionPane.showMessageDialog(frame, "Complaint submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                complaintArea.setText(""); // Clear the area after submission
            }
        });
        
        // Input panel for complaint text area and submit button
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setOpaque(false);
        inputPanel.add(new JLabel("Enter your complaint:"), BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(complaintArea), BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.SOUTH);
        
        panel.add(createNavigationBar(), BorderLayout.NORTH);
        panel.add(welcomeLabel, BorderLayout.NORTH); // Place welcome label at the top
        panel.add(inputPanel, BorderLayout.SOUTH); // Place complaint form at the bottom
        
        return panel;
    }

    // Writes the complaint to a text file
    private void writeComplaintToFile(String complaint) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt", true))) {
            writer.write(complaint);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving complaint.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentMessComplaintApp::new);
    }
}
