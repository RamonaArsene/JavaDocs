package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class Square extends Shape {

    private int side;

    public Square(int s){
        this.side = s;
    }

    public double area(){
        return side*side;
    }


}
