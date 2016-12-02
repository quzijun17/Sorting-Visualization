import java.awt.EventQueue;


import javax.swing.JFrame;


public class Launcher {
	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame f1 = new MainFrame();
				f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				f1.setVisible(true);
			}
		});
	}
}
