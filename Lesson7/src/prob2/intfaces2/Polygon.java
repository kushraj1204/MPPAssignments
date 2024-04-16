package prob2.intfaces2;

/**
 * @author kush
 */
public interface Polygon extends ClosedCurve {
    default double computePerimeter() {
        double perimeter = 0;
        double[] sides = getSides();
        for (double side : sides) {
            perimeter += side;
        }
        return perimeter;
    }

    double[] getSides();

}
