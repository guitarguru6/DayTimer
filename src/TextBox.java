import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class TextBox extends GameObject {

	public ArrayList<TextBoxLine> text;
	int x, y, width, height;

	public TextBox(int x, int y) {
		this(x, y, 0, 0);
	}

	public TextBox(int x, int y, int w, int h) {
		text = new ArrayList<TextBoxLine>();
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}

	public void addLine(String s) {
		text.add(new TextBoxLine(s, x, y + text.size() * 30));
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x - 10, y - 15, width + 20, height + 20);
		for (TextBoxLine t : text) {
			t.render(g);
		}
	}

}
