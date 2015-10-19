package farmacia.utils;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.border.Border;

public class WallpaperBorder implements Border {
	
	private BufferedImage fondo;
	
	public WallpaperBorder(BufferedImage fondo){
		this.fondo=fondo;
	}

	@Override
	public Insets getBorderInsets(Component arg0) {
		return new Insets(0, 0, 0, 0);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y,
			int width, int height) {
		g.drawImage(fondo, 0, 0, width, height, null);
	}

}
