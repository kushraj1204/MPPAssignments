package prob2.intfaces2;

/**
 * @author kush
 */
public class Ellipse implements ClosedCurve {
    private double semiAxis;
    private double elateral;

    public Ellipse(double semiAxis, double elateral) {
        this.semiAxis = semiAxis;
        this.elateral = elateral;
    }

    public double getSemiAxis() {
        return semiAxis;
    }

    public double getElateral() {
        return elateral;
    }

    @Override
    public double computePerimeter() {
        return 4 * semiAxis * elateral;
    }
}
