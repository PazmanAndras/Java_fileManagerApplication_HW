package z_movies_app;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LogIn extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	 private JTextField usernameField;
	 private JPasswordField passwordField;
	 private JButton loginButton;
	 private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unused")
	public LogIn() {
		setTitle("logIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
         
 
        
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("User: ");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField = new JPasswordField();

        // Panel összeállítása
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());

        // Frame beállítása
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginButton = new JButton("LogIn");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        loginButton.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\login.png"));
        
        
        ImageIcon imageIcon = new ImageIcon("img.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(newImage);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\user.png"));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(23)
        			.addComponent(lblNewLabel)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(49)
        					.addComponent(loginButton))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(18)
        					.addComponent(loginPanel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(67, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(32)
        					.addComponent(loginPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(loginButton))
        				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        
                // ActionListener hozzáadása a bejelentkezés gombhoz
                loginButton.addActionListener(this);
        pack();
        setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == loginButton) {
            // Felhasználónév és jelszó ellenőrzése
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("a") && password.equals("a")) {
                JOptionPane.showMessageDialog(this, "Sikeres bejelentkezés!");
                Program prog = new Program();
                prog.setVisible(true);
                dispose();
                
                
            } else {
                JOptionPane.showMessageDialog(this, "Hibás felhasználónév vagy jelszó!");
            }
        }
		
	}

}
