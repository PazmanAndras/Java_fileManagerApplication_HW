package z_movies_app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Program extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private File file;
	private JTextField textId;
	private JTextField textReleaseYear;
	private JTextField textHuReleaseDate;
	private JTextField textOriginalTitle;
	private JTextField textImdb;
	private JTextField textHuTitle;
	private JTextField textDirectors;
	private JTextField textCategory;
	@SuppressWarnings("unused")
	private boolean loaded;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Program frame = new Program();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Program() {
		setTitle("Movies");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1515, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		file = new File("m_data/movies.csv");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		/*
		 * ---------------------------------------------------LOAD----------------------
		 * ---------------------------------------------------
		 */

		JButton btnNewButton = new JButton("Load/Refresh");
		btnNewButton.setBackground(new Color(128, 255, 159));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\refresh.png"));
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// FileManager.CsvReader();
				// A tabla "clearelese"
				// clear the table
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				tableModel.setRowCount(0);

				// read the data from the file and add it to the table
				try (BufferedReader reader = new BufferedReader(new FileReader("m_data/movies.csv"))) {
					String line;
					while ((line = reader.readLine()) != null) {
				

						String[] data = line.split(";");
						int id = Integer.parseInt(data[0]);
						String originalTitle = data[1];
						String huTitle = data[2];
						String category = data[3];
						int year = Integer.parseInt(data[4]);	
						LocalDate huRelaseDate = LocalDate.parse(data[5], formatter);
						String Director = data[6];
						double imdbScore = Double.parseDouble(data[7]);

						tableModel.addRow(new Object[] { id, originalTitle, huTitle, category, year, huRelaseDate,
								Director, imdbScore });
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(36, 123, 176, 40);
		contentPane.add(btnNewButton);

		/*
		 * ---------------------------------------------------ADD-------------------------------------------------------------------------
		 */

		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(0, 183, 183));
		btnAdd.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\add-button.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Get data from text fields
				// Add data to table

				String iD = textId.getText();
				DataCheck.idCheck(iD);
				String originalTitle = textOriginalTitle.getText();
				String huTitle = textHuTitle.getText();
				String category = textCategory.getText();
				String releaseYear = textReleaseYear.getText();
				String huReleaseDate = textHuReleaseDate.getText();
				String director = textDirectors.getText();
				String imdb = textImdb.getText();

				if (DataCheck.idCheck(iD) == true && DataCheck.isString(originalTitle) == true
						&& DataCheck.isString(huTitle) == true && DataCheck.isString(category) == true
						&& DataCheck.isDateFormatValid(huReleaseDate) == true
						&& DataCheck.isYearValid(releaseYear) == true && DataCheck.isNumber(imdb) == true) {

					String[] data = { iD, originalTitle, huTitle, category, releaseYear, huReleaseDate, director,
							imdb };
					tableModel.addRow(data);

					// Write data to file
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
						writer.write(iD + ";" + originalTitle + ";" + huTitle + ";" + category + ";" + releaseYear + ";"
								+ huReleaseDate + ";" + director + ";" + imdb + "\n");
						// writer.newLine();
						writer.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					// JOptionPane.showMessageDialog(null, "Hibás a beviteli formátum!.",
					// "Hiba",JOptionPane.ERROR_MESSAGE);

				}

				// Clear text fields
				textId.setText("");
				textOriginalTitle.setText("");
				textHuTitle.setText("");
				textCategory.setText("");
				textReleaseYear.setText("");
				textHuReleaseDate.setText("");
				textDirectors.setText("");
				textImdb.setText("");

			}
		});
		btnAdd.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnAdd.setBounds(36, 181, 176, 40);
		contentPane.add(btnAdd);

		/*
		 * ------------------------------------------------------MODIFY----------------------------------------------------------------------
		 */

		JButton btnModify = new JButton("Modify");
		btnModify.setBackground(new Color(134, 223, 249));
		btnModify.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\edit.png"));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected row index
				int selectedRowIndex = table.getSelectedRow();

				// check if there is a selected row
				if (selectedRowIndex != -1) {

					// update the selected row data with the input field values

					if (DataCheck.idCheck(textId.getText()) == true
							&& DataCheck.isString(textOriginalTitle.getText()) == true
							&& DataCheck.isString(textHuTitle.getText()) == true
							&& DataCheck.isString(textCategory.getText()) == true
							&& DataCheck.isDateFormatValid(textHuReleaseDate.getText()) == true
							&& DataCheck.isYearValid(textReleaseYear.getText()) == true
							&& DataCheck.isNumber(textImdb.getText()) == true) {

						tableModel.setValueAt(textId.getText(), selectedRowIndex, 0);
						tableModel.setValueAt(textOriginalTitle.getText(), selectedRowIndex, 1);
						tableModel.setValueAt(textHuTitle.getText(), selectedRowIndex, 2);
						tableModel.setValueAt(textCategory.getText(), selectedRowIndex, 3);
						tableModel.setValueAt(textReleaseYear.getText(), selectedRowIndex, 4);
						tableModel.setValueAt(textHuReleaseDate.getText(), selectedRowIndex, 5);
						tableModel.setValueAt(textDirectors.getText(), selectedRowIndex, 6);
						tableModel.setValueAt(textImdb.getText(), selectedRowIndex, 7);

						// write the updated data to the file
						try {
							FileWriter writer = new FileWriter("m_data/movies.csv");
							for (int i = 0; i < tableModel.getRowCount(); i++) {
								writer.write(tableModel.getValueAt(i, 0) + ";" + tableModel.getValueAt(i, 1) + ";"
										+ tableModel.getValueAt(i, 2) + ";" + tableModel.getValueAt(i, 3) + ";"
										+ tableModel.getValueAt(i, 4) + ";" + tableModel.getValueAt(i, 5) + ";"
										+ tableModel.getValueAt(i, 6) + ";" + tableModel.getValueAt(i, 7) + "\n");

							}
							writer.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}

					}

					textId.setText("");
					textOriginalTitle.setText("");
					textHuTitle.setText("");
					textCategory.setText("");
					textReleaseYear.setText("");
					textHuReleaseDate.setText("");
					textDirectors.setText("");
					textImdb.setText("");
				}
			}
		});
		btnModify.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnModify.setBounds(36, 236, 176, 40);
		contentPane.add(btnModify);

		/*
		 * ------------------------------------ROW
		 * REMOVE------------------------------------------------------
		 */
		JButton btnDelete = new JButton("Remove");
		btnDelete.setBackground(new Color(255, 111, 111));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Get selected row index
				int row = table.getSelectedRow();

				// Clear text fields
				textId.setText("");
				textOriginalTitle.setText("");
				textHuTitle.setText("");
				textCategory.setText("");
				textReleaseYear.setText("");
				textHuReleaseDate.setText("");
				textDirectors.setText("");
				textImdb.setText("");

				table.getSelectionModel().removeSelectionInterval(0, 0);

				System.out.println(row);

				if (row != -1) {

					// Remove data from table
					tableModel.removeRow(row + 1);

					// Write data to file
					try {

						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						for (int i = 0; i < tableModel.getRowCount(); i++) {
							writer.write(tableModel.getValueAt(i, 0) + ";" + tableModel.getValueAt(i, 1) + ";"
									+ tableModel.getValueAt(i, 2) + ";" + tableModel.getValueAt(i, 3) + ";"
									+ tableModel.getValueAt(i, 4) + ";" + tableModel.getValueAt(i, 5) + ";"
									+ tableModel.getValueAt(i, 6) + ";" + tableModel.getValueAt(i, 7) + "\n");
							// writer.newLine();
						}
						writer.close();
					} catch (IOException ex) {
						ex.printStackTrace();

					}

					// Clear text fields
					textId.setText("");
					textOriginalTitle.setText("");
					textHuTitle.setText("");
					textCategory.setText("");
					textReleaseYear.setText("");
					textHuReleaseDate.setText("");
					textDirectors.setText("");
					textImdb.setText("");
				}
			}

		});
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\remove.png"));

		btnDelete.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnDelete.setBounds(36, 291, 176, 40);
		contentPane.add(btnDelete);

		JLabel lblNewLabel_1 = new JLabel("DATA ELEMENTS ");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(336, 65, 170, 21);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(683, 65, 782, 439);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(0, 128, 192));
		String[] columnNames = { "ID", "Orginal title", "HU title", "Category", "Release year", "HU release date",
				"Director", "IMDB" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);

		/*
		 * ----> Ez jeleniti meg a beviteli mezőben az adatokat ha rákattintunk
		 * <----------
		 */

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				// get the selected row index
				int selectedRowIndex = table.getSelectedRow();

				// update the input fields with the selected row data
				textId.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
				textOriginalTitle.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
				textHuTitle.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
				textCategory.setText(tableModel.getValueAt(selectedRowIndex, 3).toString());
				textReleaseYear.setText(tableModel.getValueAt(selectedRowIndex, 4).toString());
				textHuReleaseDate.setText(tableModel.getValueAt(selectedRowIndex, 5).toString());
				textDirectors.setText(tableModel.getValueAt(selectedRowIndex, 6).toString());
				textImdb.setText(tableModel.getValueAt(selectedRowIndex, 7).toString());

			}
		});

		/*--------------------------------------------------------------------------------------*/

//		tableModel= new DefaultTableModel();
//		Object[] column = {"ID", "Orginal title", "HU title", "Category", "Release year", "HU release date", "Director", "IMDB"};
//		Object[] row = new Object[0];
//		tableModel.setColumnIdentifiers(column);
//		table.setModel(tableModel);
		scrollPane.setViewportView(table);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(new Color(191, 191, 191));
		inputPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		inputPanel.setBounds(250, 97, 362, 332);
		contentPane.add(inputPanel);

		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 16));

		textId = new JTextField();
		textId.setColumns(10);

		textReleaseYear = new JTextField();
		textReleaseYear.setColumns(10);

		textHuReleaseDate = new JTextField();
		textHuReleaseDate.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("Original title:");
		lblNewLabel_4_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		JLabel lblNewLabel_4_1_1 = new JLabel("HU title:");
		lblNewLabel_4_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Category:");
		lblNewLabel_4_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		textOriginalTitle = new JTextField();
		textOriginalTitle.setColumns(10);

		textImdb = new JTextField();
		textImdb.setColumns(10);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Release year:");
		lblNewLabel_4_1_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("HU release date:");
		lblNewLabel_4_1_1_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		textHuTitle = new JTextField();
		textHuTitle.setColumns(10);

		textDirectors = new JTextField();
		textDirectors.setColumns(10);

		JLabel lblNewLabel_4_1_1_1_1_1_1 = new JLabel("Director(s) :");
		lblNewLabel_4_1_1_1_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		JLabel lblNewLabel_4_1_1_1_1_1_1_1 = new JLabel("IMDB:");
		lblNewLabel_4_1_1_1_1_1_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));

		textCategory = new JTextField();
		textCategory.setColumns(10);
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_inputPanel
				.createSequentialGroup()
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputPanel.createSequentialGroup().addGap(30).addComponent(lblNewLabel_4).addGap(5)
								.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_inputPanel.createSequentialGroup().addGap(24)
								.addComponent(lblNewLabel_4_1_1_1_1_1_1).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textDirectors, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
						.addGroup(gl_inputPanel.createSequentialGroup().addGap(25).addGroup(gl_inputPanel
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_inputPanel.createSequentialGroup().addComponent(lblNewLabel_4_1_1)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(textHuTitle))
								.addGroup(gl_inputPanel.createSequentialGroup().addComponent(lblNewLabel_4_1)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(textOriginalTitle,
												GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_inputPanel.createSequentialGroup().addGap(22).addGroup(gl_inputPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inputPanel.createSequentialGroup().addComponent(lblNewLabel_4_1_1_1_1)
										.addGap(18)
										.addComponent(textReleaseYear, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
								.addGroup(gl_inputPanel.createSequentialGroup().addComponent(lblNewLabel_4_1_1_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textCategory, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
								.addGroup(gl_inputPanel.createSequentialGroup().addComponent(lblNewLabel_4_1_1_1_1_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textHuReleaseDate,
												GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
								.addGroup(gl_inputPanel.createSequentialGroup().addGap(32)
										.addComponent(lblNewLabel_4_1_1_1_1_1_1_1).addGap(18)
										.addComponent(textImdb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))))
				.addGap(26)));
		gl_inputPanel.setVerticalGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_inputPanel
				.createSequentialGroup().addGap(5)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_4)
						.addGroup(gl_inputPanel.createSequentialGroup().addGap(1).addComponent(textId,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4_1)
						.addComponent(textOriginalTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4_1_1)
						.addComponent(textHuTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(7)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4_1_1_1)
						.addComponent(textCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(9)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4_1_1_1_1)
						.addComponent(textReleaseYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_4_1_1_1_1_1)
						.addComponent(textHuReleaseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_4_1_1_1_1_1_1)
						.addComponent(textDirectors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_inputPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4_1_1_1_1_1_1_1).addComponent(textImdb, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(34, Short.MAX_VALUE)));
		inputPanel.setLayout(gl_inputPanel);

		ImageIcon openIcon = new ImageIcon("icons/icons/folder.png");

		// Mentés gomb
		ImageIcon saveIcon = new ImageIcon("icons/icons/floppy-disk.png");

		// Másolás gomb
		ImageIcon exportIcon = new ImageIcon("icons/icons/export.png");

		ImageIcon zipIcon = new ImageIcon("icons/icons/zip.png");

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1504, 40);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(-6, -15, 1516, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 15, 1610, 40);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(new Color(164, 164, 164));
		toolBar_1.setBounds(0, 0, 1610, 40);
		panel_2.add(toolBar_1);
		JButton openButton = new JButton(openIcon);
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileManagerGui filemanagerGui = new FileManagerGui();
				filemanagerGui.setVisible(true);

			}
		});
		openButton.setText("File Manager ");
		openButton.setToolTipText("open");
		toolBar_1.add(openButton);
		JButton saveButton = new JButton(saveIcon);
		saveButton.setText("Save");
		saveButton.setToolTipText("Save");
		toolBar_1.add(saveButton);

		JButton exportButton = new JButton(exportIcon);
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					PdfCreator.exportToPDF2(table, getName());
					JOptionPane.showMessageDialog(null, "Succes!");

				} catch (Exception e2) {

				}
			}
		});
		exportButton.setText("Export to PDF ");
		exportButton.setToolTipText("Export");
		toolBar_1.add(exportButton);

		JButton btnZip = new JButton(zipIcon);
		btnZip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ZipCreator.zipCreator();
					JOptionPane.showMessageDialog(null, "Succes!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Failed!");
					e1.printStackTrace();
				}
			}
		});
		btnZip.setToolTipText("Export");
		btnZip.setText("ZIP");
		toolBar_1.add(btnZip);

		// Ablak beállításai
		contentPane.setSize(400, 400);
		contentPane.setLayout(null);

		JLabel lblNewLabelMov = new JLabel("MOVIES");
		lblNewLabelMov.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabelMov.setBounds(1033, 40, 76, 21);
		contentPane.add(lblNewLabelMov);

		JButton btnActorsTable = new JButton("Open Actors");
		btnActorsTable.setBackground(new Color(255, 255, 185));
		btnActorsTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ActorGuiApp actorGui = new ActorGuiApp();
				// actorGui.setVisible(true);
				dispose();

			}
		});

		btnActorsTable.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\open .png"));
		btnActorsTable.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnActorsTable.setBounds(36, 389, 176, 40);
		contentPane.add(btnActorsTable);

		JButton btnOpenDiagram = new JButton("Open Diagram");
		btnOpenDiagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int[] data = { 2, 3, 2, 8, 2, 10, 11, 5, 6, 3 };
				@SuppressWarnings("unused")
				DiagramApp diagramApp = new DiagramApp(data);
				// diagramApp.setVisible(true);
				// dispose();
			}
		});
		btnOpenDiagram.setIcon(new ImageIcon("C:\\Users\\Bandy\\Desktop\\icons\\pie-chart.png"));
		btnOpenDiagram.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnOpenDiagram.setBackground(new Color(159, 159, 208));
		btnOpenDiagram.setBounds(36, 451, 176, 40);
		contentPane.add(btnOpenDiagram);
		contentPane.setVisible(true);

	}

	@SuppressWarnings("unused")
	private void loadDataFromFile() throws ParseException {
		String fileName = "m_data/movies.csv";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(";");
				int id = Integer.parseInt(data[0]);
				String originalTitle = data[1];
				String huTitle = data[2];
				String category = data[3];
				int year = Integer.parseInt(data[4]);
				LocalDate huRelaseDate = LocalDate.parse(data[5], formatter);
				String Director = data[6];
				double imdbScore = Double.parseDouble(data[7]);
				tableModel.addRow(
						new Object[] { id, originalTitle, huTitle, category, year, huRelaseDate, Director, imdbScore });
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void loadFile() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");
					if (data.length == 3) {
						addRow(data);
					} else {
						System.out.println("Invalid line: " + line);
					}
				}
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void addRow(String[] data) {
		tableModel.addRow(data);
		// inputLabel.setText("Added row: " + String.join(",", data));
	}

	@SuppressWarnings("unused")
	private void clearTable() {
		int rowCount = tableModel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}
}
