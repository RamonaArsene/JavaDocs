package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class Circle extends Shape {

    private int xPos;
    private int yPos;
    private int radius;

    public Circle(){
        setxPos(0);
        setyPos(0);
        setRadius(1);
    }

    public Circle(int radius){
        this.radius = radius;
    }
    public  Circle(int yPos, int xPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public  Circle(int yPos, int xPos, int radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    public void fillColor(){
        super.getColor();
    }

    public void fillColor(int c){

        super.setColor(c);
        System.out.println("Culoarea setata la " + c);

    }
    public void fillColor(float s2){

        super.setSaturation(s2);
        System.out.println("Saturatia setata la " + s2);

    }

    public String toString(){

        String s = "center = ( " + xPos + "," + yPos + ") and radius = " + radius;
        return s;
    }


    public double area(){
        return radius*radius*java.lang.Math.PI;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


}
