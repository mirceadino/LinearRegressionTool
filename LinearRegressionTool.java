import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LinearRegressionTool {

	private ArrayList<Point2D> pointsCollection = new ArrayList<Point2D>();
	private LinearRegressionPolynomial polynom;
	private LinearRegressionScreen screen = new LinearRegressionScreen();
	private int degree = 1;

	private Point2D getPoint() throws Exception {
		if (StdDraw.mousePressed()) {
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			return new Point2D(x, y);
		}

		throw new Exception("No point.");
	}

	private boolean addPoint() {
		try {
			Point2D newPoint = getPoint();
			StdDraw.show(100);
			if (pointsCollection.size() < 1000)
				pointsCollection.add(newPoint);
			draw();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean refresh() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_F5)) {
			degree = 1;
			pointsCollection.clear();
			draw();
			return true;
		}

		return false;
	}

	private boolean setDegree() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
			if (degree == 1)
				return false;
			degree = 1;
			draw();
			return true;
		}

		if (StdDraw.isKeyPressed(KeyEvent.VK_2)) {
			if (degree == 2)
				return false;
			degree = 2;
			draw();
			return true;
		}

		if (StdDraw.isKeyPressed(KeyEvent.VK_3)) {
			if (degree == 3)
				return false;
			degree = 3;
			draw();
			return true;
		}

		if (StdDraw.isKeyPressed(KeyEvent.VK_4)) {
			if (degree == 4)
				return false;
			degree = 4;
			draw();
			return true;
		}

		if (StdDraw.isKeyPressed(KeyEvent.VK_5)) {
			if (degree == 5)
				return false;
			degree = 5;
			draw();
			return true;
		}

		return false;
	}

	private void draw() {
		try {
			screen.draw(degree, pointsCollection);
			int actualDegree = Math.min(degree, pointsCollection.size() - 1);
			if (actualDegree >= 1) {
				polynom = new LinearRegressionPolynomial(actualDegree, pointsCollection);
				screen.plotPolynomial(polynom);
			}
		} catch (ArithmeticException err) {
		}
	}

	public void run() {
		screen.draw(degree, pointsCollection);

		while (true) {
			addPoint();
			refresh();
			setDegree();
		}
	}

	public static void main(String[] args) {
		LinearRegressionTool T = new LinearRegressionTool();
		T.run();
	}
}
