public class LinearRegressionPolynomial {

	private int degree;
	private double[] coefficients;
	private Iterable<Point2D> pointsCollection;

	public LinearRegressionPolynomial(Iterable<Point2D> pointsCollection) {
		this.degree = 1;
		this.pointsCollection = pointsCollection;
		this.coefficients = new double[degree + 1];
		computeCoefficients();
	}

	public LinearRegressionPolynomial(int degree, Iterable<Point2D> pointsCollection) {
		this.degree = degree;
		this.pointsCollection = pointsCollection;
		this.coefficients = new double[degree + 1];
		computeCoefficients();
	}

	public void computeCoefficients() {
		double[][] A = new double[this.degree + 1][this.degree + 1];
		double[] B = new double[this.degree + 1];

		for (int i = 0; i <= this.degree; i++) {
			B[i] = 0;
			for (int j = 0; j <= this.degree; j++)
				A[i][j] = 0;
		}

		for (Point2D P : this.pointsCollection)
			for (int i = 0; i <= this.degree; i++) {
				B[i] += P.y() * Math.pow(P.x(), this.degree - i);
				for (int j = 0; j <= this.degree; j++)
					A[i][j] += Math.pow(P.x(), 2 * this.degree - i - j);
			}

		try {
			this.coefficients = GaussianElimination.lsolve(A, B);
		} catch (ArithmeticException err) {
			for (int i = 0; i <= degree; i++)
				this.coefficients[i] = 0;
		}
	}

	public int getDegree() {
		return this.degree;
	}

	public double valueAt(double x) {
		double y = 0.0;
		for (int i = 0; i <= degree; i++)
			y = x * y + this.coefficients[i];
		return y;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
