package z_movies_app;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;

public class FileManagerGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPath;
	private JList<File> fileList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileManagerGui frame = new FileManagerGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FileManagerGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 476);
		setTitle("File Manager");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.GRAY);
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelTop, BorderLayout.NORTH);

		JLabel lblPath = new JLabel("Path:");
		panelTop.add(lblPath);

		txtPath = new JTextField();
		panelTop.add(txtPath);
		txtPath.setColumns(40);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBackground(Color.LIGHT_GRAY);
		panelTop.add(btnBrowse);

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(1, 0, 0, 0));

		fileList = new JList<File>();
		fileList.setBackground(new Color(215, 215, 215));
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelCenter.add(new JScrollPane(fileList));

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(160, 160, 160));
		FlowLayout fl_panelBottom = (FlowLayout) panelBottom.getLayout();
		fl_panelBottom.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelBottom, BorderLayout.SOUTH);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 66, 66));
		panelBottom.add(btnDelete);

		JButton btnNewFolder = new JButton("New Folder");
		btnNewFolder.setBackground(new Color(149, 149, 255));
		panelBottom.add(btnNewFolder);

		JButton btnOpen = new JButton("Open");
		btnOpen.setBackground(new Color(0, 236, 0));
		panelBottom.add(btnOpen);

		JButton btnFileDetails = new JButton("File Details");
		btnFileDetails.setBackground(new Color(255, 128, 128));
		panelBottom.add(btnFileDetails);

		txtPath.setText(System.getProperty("user.home"));

		JButton btnTextFiles = new JButton("File data view");
		btnTextFiles.setBackground(Color.LIGHT_GRAY);
		panelTop.add(btnTextFiles);
		btnTextFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileView fView = new FileView();
				fView.setVisible(true);
			}
		});
		loadFiles();

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(FileManagerGui.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					txtPath.setText(selectedFile.getAbsolutePath());
					loadFiles();
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = fileList.getSelectedIndex();
				if (index != -1) {
					File selectedFile = fileList.getSelectedValue();
					int response = JOptionPane.showConfirmDialog(FileManagerGui.this,
							"Are you sure you want to delete " + selectedFile.getName() + "?", "Confirm Delete",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
						if (selectedFile.delete()) {
							JOptionPane.showMessageDialog(FileManagerGui.this,
									selectedFile.getName() + " has been deleted.", "Delete Successful",
									JOptionPane.INFORMATION_MESSAGE);
							loadFiles();
						} else {
							JOptionPane.showMessageDialog(FileManagerGui.this,
									"Failed to delete " + selectedFile.getName() + ".", "Delete Failed",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});

		btnNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folderName = JOptionPane.showInputDialog(FileManagerGui.this, "Enter folder name:");
				if (folderName != null) {
					File newFolder = new File(txtPath.getText() + File.separator + folderName);
					if (newFolder.mkdir()) {
						JOptionPane.showMessageDialog(FileManagerGui.this, newFolder.getName() + " has been created.",
								"New Folder Successful", JOptionPane.INFORMATION_MESSAGE);
						loadFiles();
					} else {
						JOptionPane.showMessageDialog(FileManagerGui.this,
								"Failed to create " + newFolder.getName() + ".", "New Folder Failed",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = fileList.getSelectedIndex();
				if (index != -1) {
					File selectedFile = fileList.getSelectedValue();
					if (selectedFile.isDirectory()) {
						txtPath.setText(selectedFile.getAbsolutePath());
						loadFiles();
					} else {
						// Open file
					}
				}
			}
		});

		fileList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = fileList.getSelectedIndex();
				if (index != -1) {
					File selectedFile = fileList.getSelectedValue();
					btnOpen.setEnabled(selectedFile.isDirectory() ? true : false);
					btnFileDetails.setEnabled(true);
				} else {
					btnOpen.setEnabled(false);
					btnFileDetails.setEnabled(false);
				}
			}
		});

		btnFileDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = fileList.getSelectedIndex();
				if (index != -1) {
					File selectedFile = fileList.getSelectedValue();
					String message = "Name: " + selectedFile.getName() + "\n" + "Path: "
							+ selectedFile.getAbsolutePath() + "\n" + "Size: " + selectedFile.length() + " bytes\n"
							+ "Last Modified: " + new Date(selectedFile.lastModified());

					JOptionPane.showMessageDialog(null, message, "File Details", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private void loadFiles() {
		File currentFolder = new File(txtPath.getText());
		File[] files = currentFolder.listFiles();
		fileList.setListData(files);
	}

}
