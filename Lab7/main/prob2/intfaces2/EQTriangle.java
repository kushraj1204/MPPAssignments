package prob2.intfaces2;

public class EQTriangle implements Polygon {
	double side;

	public EQTriangle(double side) {
		this.side = side;
	}

	@Override
	public double computePerimeter() {
		// TODO Auto-generated method stub
		return 3 * side;
	}

	@Override
	public double[] getSides() {
		// TODO Auto-generated method stub
		return new double[] { side, side, side };
	}
}
