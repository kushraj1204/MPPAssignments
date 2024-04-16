package prob2.intfaces2;

/**
 * @author kush
 */
public class EquilateralTriangle implements Polygon {

    private double length;

    public EquilateralTriangle(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public double[] getSides() {
        double[] sideArray=new double[3];
        for (int i = 0; i < sideArray.length; i++) {
            sideArray[i]=length;
        }
        return sideArray;
    }
}
