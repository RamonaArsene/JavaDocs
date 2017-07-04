package ro.teamnet.zerotohero.oop.runapp;
import com.sun.org.apache.xpath.internal.SourceTree;
import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class RunApp {
    public static void main(String[] args) {
        Circles cs = new Circles();

        System.out.println("The default circle area is" + cs.getAreaPub());
        cs.getAreaDef();

        Canvas c = new Canvas();
        //c.paint();

        Shape s =  new Circle(10);
        System.out.println("Shape-circle area is: " + s.area());

        ShapeBehaviour sb = new Square(10);
        System.out.println("ShapeBehaviour-square area is: " + sb.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);

        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));
    }
}
