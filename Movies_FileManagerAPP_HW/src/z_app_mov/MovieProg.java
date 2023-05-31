package z_app_mov;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovieProg extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldAge;
	private JTextField textFieldGender;
	private JTextField textFieldCity;
	private JTable table;
	private DefaultTableModel tableModel;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieProg frame = new MovieProg();
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
	public MovieProg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Set up file
        file = new File("m_data/data.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        int click = 0;
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			    // Clear table
	            tableModel.setRowCount(0);

	            // Load data from file
	            try {
	                BufferedReader reader = new BufferedReader(new FileReader(file));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    String[] data = line.split(";");
	                    tableModel.addRow(data);
	                }
	                reader.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
			}
		});
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLoad.setBounds(39, 97, 110, 33);
		contentPane.add(btnLoad);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  // Get data from text fields
	            // Add data to table
	        	
	        	String name = textFieldName.getText();
	        	String age = textFieldAge.getText();
	        	String gender = textFieldGender.getText();
	        	String city = textFieldCity.getText();
	        	
	            String[] data = {name, age, gender, city};
	            tableModel.addRow(data);

	            // Write data to file
	            try {
	                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
	                writer.write(name + ";" + age + ";" + gender + ";" + city + "\n");
	                writer.newLine();
	                writer.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }

	            // Clear text fields
	            textFieldName.setText("");
	            textFieldAge.setText("");
	            textFieldGender.setText("");
	            textFieldCity.setText("");
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd.setBounds(39, 164, 110, 33);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Get selected row index
	            int row = table.getSelectedRow();

	            if (row != -1) {
	                // Get data from text fields
	                String name = textFieldName.getText();
	                String age =   textFieldAge.getText();
	                String gender =   textFieldGender.getText();
	                String city = textFieldCity.getText();

	                // Update data in table
	                tableModel.setValueAt(name, row, 0);
	                tableModel.setValueAt(age, row, 1);
	                tableModel.setValueAt(gender, row, 2);
	                tableModel.setValueAt(city, row, 3);

	                // Write data to file
	                try {
	                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	                    for (int i = 0; i < tableModel.getRowCount(); i++) {
	                        writer.write(tableModel.getValueAt(i, 0) + ";" + tableModel.getValueAt(i, 1) + ";" + tableModel.getValueAt(i, 2) + ";" +tableModel.getValueAt(i, 3) +"\n" );
	                        //writer.newLine();
	                    }
	                    writer.close();
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }

	                // Clear text fields
	                textFieldName.setText("");
	                textFieldAge.setText("");
	                textFieldGender.setText("");
	                textFieldCity.setText("");
	            }
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(39, 234, 110, 33);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				   // Get selected row index
				
				//table.setRowSelectionAllowed(true);
				
	            int row = table.getSelectedRow();
	            int rowCount = table.getSelectedRowCount();
	            
	            System.out.println(row + "," + rowCount );
	            System.out.println();
	            
	            if(table.getSelectedRowCount() == 1 && table.getSelectedRow() != -1) {
	            	
	            	tableModel.removeRow(table.getSelectedRow());
	            	 try {
		                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		                    for (int i = 0; i < tableModel.getRowCount()-1; i++) {
		                        writer.write(tableModel.getValueAt(i, 0) + ";" + tableModel.getValueAt(i, 1) + ";" + tableModel.getValueAt(i, 2) + ";" + tableModel.getValueAt(i, 3));
		                        writer.newLine();
		                    }
		                    writer.close();
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
	            }
	            

	            if (row != -1) {
//	                // Remove data from table
//	            	tableModel.removeRow(row);
//
//	                // Write data to file
//	                try {
//	                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//	                    for (int i = 0; i < tableModel.getRowCount(); i++) {
//	                        writer.write(tableModel.getValueAt(i, 0) + ";" + tableModel.getValueAt(i, 1) + ";" + tableModel.getValueAt(i, 2) + ";" + tableModel.getValueAt(i, 3));
//	                        writer.newLine();
//	                    }
//	                    writer.close();
//	                } catch (IOException ex) {
//	                    ex.printStackTrace();
//	                }

	                // Clear text fields
	                textFieldName.setText("");
	                textFieldAge.setText("");
	                textFieldGender.setText("");
	                textFieldCity.setText("");
	            }
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRemove.setBounds(39, 302, 110, 33);
		contentPane.add(btnRemove);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(212, 100, 61, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAge.setBounds(212, 151, 61, 25);
		contentPane.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGender.setBounds(212, 205, 61, 25);
		contentPane.add(lblGender);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCity.setBounds(212, 260, 61, 25);
		contentPane.add(lblCity);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(266, 100, 127, 25);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(254, 154, 61, 25);
		contentPane.add(textFieldAge);
		
		textFieldGender = new JTextField();
		textFieldGender.setColumns(10);
		textFieldGender.setBounds(283, 205, 127, 25);
		contentPane.add(textFieldGender);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(254, 260, 127, 25);
		contentPane.add(textFieldCity);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(444, 69, 516, 325);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		table.setBackground(new Color(0, 128, 192));
		String[] columnNames = {"Name", "Age", "Gender", "City"};
		tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
						
				
        table = new JTable(tableModel);
        
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // get the selected row index
                int selectedRowIndex = table.getSelectedRow();

                // update the input fields with the selected row data
                textFieldName.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
                textFieldAge.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
                textFieldGender.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
                textFieldCity.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
           
                
            }
        });
		scrollPane.setViewportView(table);
	}
}
