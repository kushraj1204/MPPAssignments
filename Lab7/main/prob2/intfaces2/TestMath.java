package prob2.intfaces2;

public class TestMath {

	public static void main(String args[]) {
		Circle circle = new Circle(1.4);
		Ellipse ellipse = new Ellipse(1, 2);
		EQTriangle eqTriangle = new EQTriangle(3);
		Rectangle rec = new Rectangle(2, 4);

		
		System.out.println("Circle perimeter: "+circle.computePerimeter());
		System.out.println("Ellipse perimeter: "+ellipse.computePerimeter());
		System.out.println("Equilater Triangle perimeter: "+eqTriangle.computePerimeter());
		System.out.println("Rectangle perimeter: "+rec.computePerimeter());
		

	}
}
