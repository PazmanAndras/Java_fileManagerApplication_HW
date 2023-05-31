package z_movies_app;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DiagramApp extends JFrame {
	private int[] data; // Számadatok tömbje

	public DiagramApp(int[] data) {
		getContentPane().setBackground(new Color(128, 128, 128));
		this.data = data;

		setTitle("Diagram App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);

		DiagramPanel diagramPanel = new DiagramPanel();
		getContentPane().add(diagramPanel);

		setVisible(true);
	}

	public static void main(String[] args) {
		// int[] data = {1, 3, 5, 2, 4, 2, 10, 4, 3, 5};
		// SwingUtilities.invokeLater(() -> new DiagramApp(data));
	}

	private class DiagramPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			int padding = 30;
			int x0 = padding;
			int y0 = getHeight() - padding;
			int x1 = getWidth() - padding;
			int y1 = padding;

			int maxValue = getMaxValue(data);

			int xTickSpacing = (x1 - x0) / 10;
			int yTickSpacing = (y0 - y1) / (maxValue + 1);

			// x tengely
			g.drawLine(x0, y0, x1, y0);

			// y tengely
			g.drawLine(x0, y0, x0, y1);

			// x tengely címkék
			for (int i = 0; i < 10; i++) {
				int x = x0 + (i + 1) * xTickSpacing;
				int y = y0 + padding / 2;
				String label = String.valueOf(2005 + i); // kezdő dátum
				g.drawString(label, x, y);
			}

			// y tengely címkék és skálázás
			for (int i = 0; i <= maxValue; i++) {
				int x = x0 - padding / 2;
				int y = y0 - i * yTickSpacing;
				String label = String.valueOf(i);
				g.drawString(label, x, y);
			}

			// Diagram
			int barWidth = xTickSpacing / 2; // vastagság
			@SuppressWarnings("unused")
			int barSpacing = xTickSpacing / 3;

			for (int i = 0; i < data.length; i++) {
				int x = x0 + (i + 1) * xTickSpacing - barWidth / 2;
				int y = y0 - (int) (((double) data[i] / maxValue) * (y0 - y1));
				int barHeight = y0 - y;

				g.setColor(Color.BLUE);
				g.fillRect(x, y, barWidth, barHeight);

				g.setColor(Color.BLACK);
				g.drawRect(x, y, barWidth, barHeight);
			}
		}

		private int getMaxValue(int[] data) {
			int max = Integer.MIN_VALUE;

			for (int value : data) {
				if (value > max) {
					max = value;
				}
			}

			return max;
		}
	}
}
