import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listening implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	public static int mx = 0, my = 0;

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();

	}

	public void mouseClicked(MouseEvent e) {
		int button = 0;
		if (e.getButton() == MouseEvent.BUTTON1)
			button = 1;
		else if (e.getButton() == MouseEvent.BUTTON2)
			button = 2;
		else if (e.getButton() == MouseEvent.BUTTON3)
			button = 3;

		Component.onClick(button);
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}
}
