import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextBoxLine extends GameObject {
	String text;
	int x, y, width, height;

	public TextBoxLine() {
		this("");
	}

	public TextBoxLine(String s) {
		this(s, 0, 0, 0, 0);
	}

	public TextBoxLine(String s, int x, int y, int width, int height) {
		text = s;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public TextBoxLine(String s, int x, int y) {
		text = s;
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		Font f = new Font("Arial", 1, 15);
		leftString(g, text, f, x, y, width, height);
	}

}
