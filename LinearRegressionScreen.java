import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class LinearRegressionScreen {

	private int canvasWidth = 512;
	private int canvasHeight = 512;
	private double XMIN = -100.0;
	private double YMIN = -100.0;
	private double XMAX = +100.0;
	private double YMAX = +100.0;
	private Color pointColor = StdDraw.BLUE;
	private double pointRadius = 0.0100;
	private Color polynomialColor = StdDraw.RED;
	private double polynomialRadius = 0.0025;
	private Color textColor = StdDraw.BLACK;
	private int textSize = 10;

	public LinearRegressionScreen() {
		StdDraw.setCanvasSize(this.canvasWidth, this.canvasHeight);
		StdDraw.setXscale(this.XMIN, this.XMAX);
		StdDraw.setYscale(this.YMIN, this.YMAX);
	}

	public void draw(int degree, ArrayList<Point2D> pointsCollection) {
		StdDraw.show(0);

		StdDraw.clear();
		plotPoints(pointsCollection);
		writeInstructions(degree, pointsCollection.size());

		StdDraw.show(0);
	}

	public void plotPoints(Iterable<Point2D> pointsCollection) {
		StdDraw.setPenColor(this.pointColor);
		StdDraw.setPenRadius(this.pointRadius);

		for (Point2D P : pointsCollection)
			P.draw();
	}

	public void plotPolynomial(LinearRegressionPolynomial polynomial) {
		double gap = 1.0;
		double lastx = this.XMIN - gap;
		double lasty = polynomial.valueAt(lastx);

		StdDraw.show(0);
		StdDraw.setPenColor(this.polynomialColor);
		StdDraw.setPenRadius(this.polynomialRadius);

		for (double x = this.XMIN; x <= this.XMAX + gap; x += gap) {
			double y = polynomial.valueAt(x);
			StdDraw.line(lastx, lasty, x, y);
			lastx = x;
			lasty = y;
		}

		StdDraw.show(0);
	}

	public void writeInstructions(int degree, int nrPoints) {
		StdDraw.setPenColor(this.textColor);
		StdDraw.setFont(new Font("Font", 0, this.textSize));

		double x = this.XMIN + this.textSize / 10;
		double y = this.YMAX + 1;
		double gap = this.textSize / 2;

		StdDraw.textLeft(x, y - 1 * gap, "Degree: " + degree);
		StdDraw.textLeft(x, y - 2 * gap, "Number of points: " + nrPoints);
		StdDraw.textLeft(x, y - 3 * gap, "Press F5 to reset.");
		StdDraw.textLeft(x, y - 4 * gap, "Press a key between 1 and 5 to set degree.");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
