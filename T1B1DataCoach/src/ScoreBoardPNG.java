import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


	public  class ScoreBoardPNG
	{
		private BufferedImage bufferedImage; // Image itself
		private File imageFile; // File for image

		/**
		 * Creates a new image for you from the filename string given.
		 *
		 * @param fileName  The file name for the image.
		 */
		public ScoreBoardPNG(String fileName)
		{
			// Create file
			imageFile = new File(fileName);
			try
			{
				bufferedImage = ImageIO.read(imageFile);
			}
			catch(IOException e)
			{
				System.out.println("Error loading "+fileName);
				System.out.println(e.toString());
			}
		}
		/**
		 * Draws the image on a Graphics2D context.
		 *
		 * @param g  The Graphics2D object to draw this image to.
		 */
		public void draw(Graphics2D g,double x,double y)
		{
			// Draw (we just want to place it somewhere, so AffineTransform has this method)
			g.drawRenderedImage(bufferedImage,AffineTransform.getTranslateInstance(x,y));
		}
	}
