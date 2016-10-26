import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;

	public static final String TITLE = "My Timer Pre-Alpha V0.1.0";

	public static int width = 800;
	public static int height = 600;

	public Window() {

		setTitle(TITLE);
		setSize(width + 6, height + 29);
		setLocationRelativeTo(null);
		setResizable(false);
		JFrame.setDefaultLookAndFeelDecorated(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image img = null;
		try {
			img = ImageIO.read(new File("res//timerIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(img);
		setVisible(true);
		addWindowListener(this);
	}

	public void tick() {
		boolean b = false;
		if (width != getWidth() - 6) {
			width = getWidth() - 6;
			b = true;
		}
		if (height != getHeight() - 29) {
			height = getHeight() - 29;
			b = true;
		}
		if (b)
			Component.resized();
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	public void windowClosing(WindowEvent e) {
		Component.scene.dump();
		System.out.println("closing");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}
}
