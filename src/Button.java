import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Button extends GameObject implements Drawable {
	public int x, y, width, height;

	public Font font = new Font("Arial", 2, 20);
	private String text = "";
	private Color color = Color.YELLOW;
	public char alignment = 'c';

	public Button() {
		this(0, 0, 0, 0, "");
	}

	public Button(int x, int y, int w, int h) {
		this(x, y, w, h, "");
	}

	public Button(int x, int y, int w, int h, String t) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		setText(t);
	}

	public Button(Rectangle r, String t, Color c) {
		this(r.x, r.y, r.width, r.height, t);
		color = c;
	}

	public Button(int x, int y, int w, int h, String t, Color c) {
		this(x, y, w, h, t);
		color = c;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public char getAlignment() {
		return alignment;
	}

	public void setAlignment(char c) {
		this.alignment = c;
	}

	public boolean containsCursor() {
		Rectangle r2 = new Rectangle(x, y, width, height);
		return r2.contains(Listening.mx, Listening.my);
	}

	public abstract void onClick();

	public boolean isButton() {
		return true;
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		if (containsCursor())
			g.setColor(Color.WHITE);

		g.fillRoundRect(x, y, width, height, 5, 5);

		g.setColor(color);
		g.fillRoundRect(x + 1, y + 1, width - 2, height - 2, 5, 5);

		g.setColor(Color.BLACK);
		Rectangle r2 = new Rectangle(x, y, width, height);
		if (alignment == 'c') {
			centerString(g, text, font, r2);
		} else if (alignment == 'l') {
			leftString(g, text, font, r2);
		}

	}

}
