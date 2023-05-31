package z_movies_app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.awt.Color;

public class FileView extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton chooseFileButton;
	private JTextArea textArea;
	private JFileChooser fileChooser;

	public FileView() {
		setTitle("File Chooser Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(453, 425);

		textArea = new JTextArea();

		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt, *.csv)", "txt", "csv");
		fileChooser.setFileFilter(filter);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(0, 0, 443, 379);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		chooseFileButton = new JButton("Choose File");
		chooseFileButton.setBackground(new Color(0, 255, 64));
		chooseFileButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setColumnHeaderView(chooseFileButton);
		chooseFileButton.addActionListener(this);
		getContentPane().setLayout(null);

		getContentPane().add(panel);

		setVisible(true);
	}

	public static void main(String[] args) {
		new FileView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chooseFileButton) {
			int returnVal = fileChooser.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();

				if (file.getName().endsWith(".txt") || file.getName().endsWith(".csv")) {
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line;
						StringBuilder sb = new StringBuilder();

						while ((line = reader.readLine()) != null) {
							sb.append(line);
							sb.append("\n");
						}

						textArea.setText(sb.toString());
						reader.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					textArea.setText("Invalid file type. Please choose a .txt or .csv file.");
				}
			}
		}
	}
}
