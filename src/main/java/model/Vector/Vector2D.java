package model.vector;

/**
 * Created by Guido on 05.05.2016.
 */
public class Vector2D {
    private double X;
    private double Y;

    public Vector2D(double x, double y) {
        this.X = x;
        this.Y = y;
    }

    public Vector2D(Vector2D vector2D) {
        this.X = vector2D.getX();
        this.Y = vector2D.getY();
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double length() {

        double length = Math.sqrt(Math.pow(this.X, 2) + Math.pow(this.Y, 2));

        return length;
    }

    public Vector2D normalized() {
        double length = length();

        Vector2D normalizedVector2D = new Vector2D(this.X / length, this.Y / length);

        return normalizedVector2D;
    }

    public Vector2D substract(Vector2D Position) {

        double X = this.X - Position.getX();
        double Y = this.Y - Position.getY();

        Vector2D substractedVector2D = new Vector2D(X, Y);

        return substractedVector2D;
    }

    public Vector2D add(Vector2D Position) {
        double X = this.X + Position.getX();
        double Y = this.Y + Position.getY();

        Vector2D addedVector2D = new Vector2D(X, Y);

        return addedVector2D;
    }

    public Vector2D add(double summand) {

        Vector2D addedVector2D = new Vector2D(this.X + summand, this.Y + summand);

        return addedVector2D;
    }

    public Vector2D multipliedBy(double multiplier) {
        Vector2D multipliedVector2D = new Vector2D(this.X * multiplier, this.Y * multiplier);

        return multipliedVector2D;
    }

    @Override
    public String toString() {

        String stringed = "(X = " + this.X + " | Y = " + this.Y + ")";

        return stringed;
    }
}
