package prob2.intfaces2;

public class Rectangle implements Polygon {
    private double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }


    @Override
    public double[] getSides() {
        double[] sideArray = new double[4];
        for (int i = 0; i < sideArray.length; i++) {
            if (i % 2 == 0) {
                sideArray[i] = length;
            } else {
                sideArray[i] = width;
            }
        }
        return sideArray;
    }


    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
