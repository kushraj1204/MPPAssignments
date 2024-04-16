package prob2.intfaces2;

public class Ellipse implements ClosedCurve {

	double semiaxis;
	double elateral;

	public Ellipse(double semiaxis, double elateral) {
		this.semiaxis = semiaxis;
		this.elateral = elateral;
	}

	public double getSemiaxis() {
		return semiaxis;
	}

	public void setSemiaxis(double semiaxis) {
		this.semiaxis = semiaxis;
	}

	public double getElateral() {
		return elateral;
	}

	public void setElateral(double elateral) {
		this.elateral = elateral;
	}

	@Override
	public double computePerimeter() {
		// TODO Auto-generated method stub
		return 4 * semiaxis * elateral;
	}

}
