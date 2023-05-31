package z_movies_app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ActorGuiApp extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmActors;
	private JTable table;
	private DefaultTableModel model;
	private JButton loadButton;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JTextField idField;
	private JTextField nameField;
	private JTextField movieField;
	private JTextField characterField;
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JLabel genderLabel;
	private JLabel cityLabel;
	private JPanel inputPanel;
	private File file;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JButton btnBack;


	public static void main(String[] args) {

		//new GuiApp();
		
		

	}


	public ActorGuiApp() {

		frmActors = new JFrame("Actors table");
		frmActors.getContentPane().setBackground(new Color(180, 180, 180));
		frmActors.setTitle("Actors");
		frmActors.setResizable(true);
		frmActors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		setBounds(100, 100, 450, 300);

		//model = new DefaultTableModel(new String[] { "ID", "Actor", "Gender", "Character" }, 0);
		
		String[] columnNames = {"ID", "NAME", "MOVIE TITLE", "CHARACTER"};
		model = new DefaultTableModel(columnNames, 0){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		
		
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

		textField = new JTextField();
		scrollPane.setColumnHeaderView(textField);
		textField.setColumns(10);

		lblNewLabel_1 = new JLabel("|");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		scrollPane.setColumnHeaderView(lblNewLabel_1);

		nameLabel = new JLabel("ID");
		nameLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		idField = new JTextField(10);
		ageLabel = new JLabel("Actor Name");
		ageLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		nameField = new JTextField(5);
		genderLabel = new JLabel("Movie title");
		genderLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		genderLabel.setToolTipText("Movie");
		movieField = new JTextField(10);
		cityLabel = new JLabel("Character in movie");
		cityLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		characterField = new JTextField(10);

		inputPanel = new JPanel();
		inputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		loadButton = new JButton("Load");
		loadButton.setBackground(new Color(0, 255, 204));
		loadButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		loadButton.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\refresh.png"));
		loadButton.addActionListener(this);
		addButton = new JButton("Add");
		addButton.setBackground(new Color(0, 255, 153));
		addButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		addButton.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\add-button.png"));
		addButton.addActionListener(this);
		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(108, 182, 255));
		updateButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateButton.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\edit.png"));
		updateButton.addActionListener(this);
		deleteButton = new JButton("Delete");
		deleteButton.setBackground(new Color(204, 51, 51));
		deleteButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteButton.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\remove.png"));
		deleteButton.addActionListener(this);
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(nameLabel)
							.addGap(18)
							.addComponent(idField, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addGap(25)
							.addComponent(loadButton)
							.addGap(18)
							.addComponent(addButton)
							.addGap(18)
							.addComponent(updateButton)
							.addGap(18)
							.addComponent(deleteButton))
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_inputPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_inputPanel.createSequentialGroup()
									.addComponent(cityLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(characterField))
								.addGroup(Alignment.LEADING, gl_inputPanel.createSequentialGroup()
									.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(ageLabel)
										.addComponent(genderLabel))
									.addGap(18)
									.addGroup(gl_inputPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(movieField, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))))))
					.addGap(12))
		);
		gl_inputPanel.setVerticalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLabel)
						.addComponent(idField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(ageLabel)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(movieField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(genderLabel))
					.addGap(26)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cityLabel)
						.addComponent(characterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(loadButton)
						.addComponent(addButton)
						.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(updateButton)
							.addComponent(deleteButton))))
		);
		inputPanel.setLayout(gl_inputPanel);
		
		btnBack = new JButton("");
		btnBack.setBackground(new Color(128, 128, 128));
		btnBack.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\previous.png"));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Program prog = new Program();
				prog.setVisible(true);
				dispose();

			}
		});
		
		JLabel lblNewLabel = new JLabel("ACTORS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(frmActors.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 667, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(302)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBack)
							.addGap(13)
							.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frmActors.getContentPane().setLayout(groupLayout);

		// Set up file
		file = new File("m_data/data.csv");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		/*
		  table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	            public void valueChanged(ListSelectionEvent event) {
	                // get the selected row index
	                int selectedRowIndex = table.getSelectedRow();

	                // update the input fields with the selected row data
	                idField.setText(model.getValueAt(selectedRowIndex, 0).toString());
	                nameField.setText(model.getValueAt(selectedRowIndex, 1).toString());
	                movieField.setText(model.getValueAt(selectedRowIndex, 2).toString());
	                characterField.setText(model.getValueAt(selectedRowIndex, 2).toString());
	           
	                
	            }
	        });
	   */

		// Show GUI
		frmActors.pack();
		frmActors.setLocationRelativeTo(null);
		frmActors.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == loadButton) {
			
			// Clear table
			model.setRowCount(0);

			// Load data from file
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(";");
					model.addRow(data);
				}
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == addButton) {
			
			// Get data from text fields
			// Add data to table

			String name = idField.getText();
			String age = nameField.getText();
			String gender = movieField.getText();
			String city = characterField.getText();

			String[] data = { name, age, gender, city };
			model.addRow(data);

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
				writer.write(name + ";" + age + ";" + gender + ";" + city);
				writer.newLine();
				writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			idField.setText("");
			nameField.setText("");
			movieField.setText("");
			characterField.setText("");
		} else if (e.getSource() == updateButton) {
			
			
			int row = table.getSelectedRow();

			if (row != -1) {
				// Get data from text fields
				String name = idField.getText();
				String age = nameField.getText();
				String gender = movieField.getText();
				String city = characterField.getText();

				// Update data in table
				model.setValueAt(name, row, 0);
				model.setValueAt(age, row, 1);
				model.setValueAt(gender, row, 2);
				model.setValueAt(city, row, 3);

				// Write data to file
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					for (int i = 0; i < model.getRowCount(); i++) {
						writer.write(model.getValueAt(i, 0) + ";" + model.getValueAt(i, 1) + ";"
								+ model.getValueAt(i, 2) + ";" + model.getValueAt(i, 3));
						writer.newLine();
					}
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				idField.setText("");
				nameField.setText("");
				movieField.setText("");
				characterField.setText("");
			}
		} else if (e.getSource() == deleteButton) {
			// Get selected row index
			int row = table.getSelectedRow();

			if (row != -1) {
				// Remove data from table
				model.removeRow(row);

				// Write data to file
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					for (int i = 0; i < model.getRowCount(); i++) {
						writer.write(model.getValueAt(i, 0) + ";" + model.getValueAt(i, 1) + ";"
								+ model.getValueAt(i, 2) + ";" + model.getValueAt(i, 3));
						writer.newLine();
					}
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				idField.setText("");
				nameField.setText("");
				movieField.setText("");
				characterField.setText("");
			}
		}

	}
}
