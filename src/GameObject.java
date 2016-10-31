import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public abstract class GameObject implements Drawable {

	public void tick() {

	}

	public void tick(int l) {

	}

	public boolean containsCursor() {
		return false;
	}

	public boolean isButton() {
		return false;
	}

	public boolean isTimer() {
		return false;
	}

	public void resetLocation(int i) {

	}

	public void onClick(int button) {

	}

	public void centerString(Graphics g, String text, Font font, int x, int y, int width, int height) {
		FontRenderContext frc = new FontRenderContext(null, true, true);

		Rectangle2D r2D = font.getStringBounds(text, frc);
		int rWidth = (int) Math.round(r2D.getWidth());
		int rHeight = (int) Math.round(r2D.getHeight());
		int rX = (int) Math.round(r2D.getX());
		int rY = (int) Math.round(r2D.getY());

		int a = (width / 2) - (rWidth / 2) - rX;
		int b = (height / 2) - (rHeight / 2) - rY;

		g.setFont(font);
		g.drawString(text, x + a, y + b);
	}

	public void leftString(Graphics g, String text, Font font, int x, int y, int width, int height) {
		FontRenderContext frc = new FontRenderContext(null, true, true);

		Rectangle2D r2D = font.getStringBounds(text, frc);
		int rHeight = (int) Math.round(r2D.getHeight());
		int rY = (int) Math.round(r2D.getY());

		int b = (height / 2) - (rHeight / 2) - rY;

		g.setFont(font);
		g.drawString(text, x, y + b);
	}

	public void centerString(Graphics g, String text, Rectangle r) {
		Font font = new Font("Arial", 2, 20);
		centerString(g, text, font, r.x, r.y, r.width, r.height);
	}

	public void leftString(Graphics g, String text, Rectangle r) {
		Font font = new Font("Arial", 2, 20);
		leftString(g, text, font, r.x, r.y, r.width, r.height);
	}

	public void centerString(Graphics g, String text, Font font, Rectangle r) {
		centerString(g, text, font, r.x, r.y, r.width, r.height);
	}

	public void leftString(Graphics g, String text, Font font, Rectangle r) {
		leftString(g, text, font, r.x, r.y, r.width, r.height);
	}

	public abstract void render(Graphics g);

	public String toString() {
		return "";
	}
}
