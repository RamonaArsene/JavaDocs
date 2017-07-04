package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by ramona.arsene on 7/4/2017.
 */
public class Shape extends AbstractShape implements ShapeBehaviour{

    protected int color;
    protected float saturation;

    public void setColor(int c){
        color = c;
    }

    public int getColor(){
        return color;
    }

    public void setSaturation(float s){
        saturation = s;
    }

    public float getSaturation(){
        return saturation;
    }
    public double area(){
        return 0.0;
    }
}


