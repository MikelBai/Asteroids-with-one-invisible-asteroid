import processing.core.PShape;

public class Ship {
    private PShape shi;
    private MainDraw p;
    private static float x;
    private static float y;
    private static float speed;
    private float rotationSpeed;
    private static float angle = 0;
    ;//HEYO
    public int color = 255;

    public Ship(MainDraw parent, float xpos, float ypos, float spd, float rotatSpd) {
        p = parent;
        x = xpos;
        y = ypos;
        speed = spd;
        rotationSpeed = rotatSpd;
//		angle = ang;
        makeShape();
    }

    public void move() {
        x += speed * Math.cos(Math.toRadians(angle));
        y += speed * Math.sin(Math.toRadians(angle));
    }

    public void draw() {
        teleBack();
        p.pushMatrix(); //store current plac e
        p.translate(x, y);
        p.rotate((float) Math.toRadians(angle));
        p.shape(shi);
        p.popMatrix(); //return to old pal e
//		rotationSpeed += Math.toRadians(Math.PI/rotationSpeed);
    }

    public void rotateLeft() {
        angle -= rotationSpeed;
//		p.rotate((float)Math.toRadians(angle));
    }

    public void rotateRight() {
        angle += rotationSpeed;
//		p.rotate((float)Math.toRadians(angle));
    }

    public void teleBack() {
        if (x > 800) {
            x = 0;
        }
        if (x < 0) {
            x = 800;
        }
        if (y > 800) {
            y = 0;
        }
        if (y < 0) {
            y = 800;
        }
    }

    public void makeShape() {
        shi = p.createShape();
        shi.beginShape();
//		ast.scale(makeRange((float)0.15,(float)1.25));
        shi.noFill();
        shi.stroke(color);
        shi.vertex(7, 0);//100
        shi.vertex(-7, 4);
        shi.vertex(-2, 0);
        shi.vertex(-7, -4);
        shi.vertex(7, 0);//need this to connect "final" line
        shi.endShape();
    }

    public void flashing(int clr) {
        color = clr;
    }

    public static float getAngle() {
        return angle;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

    public float getRotation() {
//		return (float)Math.toRadians(rotationSpeed);
        return angle;
    }

    public void respawn() {
        x = 400;
        y = 400;
        speed = 0;
    }

    public void newSpd(float spd) {
        speed = spd;
    }

    public void accelSpd(float inc) {
        speed += inc;
        if (speed > 12) {
            speed = 12;
        }
    }

    public PShape getShape() {
        return shi;
    }

    public void moveAway() {
        x = 2000;
    }
}
