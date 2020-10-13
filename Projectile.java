import processing.core.PShape;

public class Projectile {
    private PShape prj;
    private MainDraw p;
    private float x;
    private float y;
    private float speed;
    private float rotationSpeed;
    private float angle;//need this for MOVE;//HEYO

    public Projectile(MainDraw parent, float xpos, float ypos, float spd, float rotatSpd, float ang) {
        p = parent;
        x = xpos;
        y = ypos;
        speed = spd;
        rotationSpeed = rotatSpd;
        angle = ang;
        makeShape();
    }

    public void draw() {
        p.pushMatrix();
        p.translate(x, y);
        p.rotate(rotationSpeed);
        p.shape(prj);
        p.popMatrix();
        rotationSpeed += Math.toRadians(Math.PI / rotationSpeed);
    }

    public void move() {
        x += speed * Math.cos(Math.toRadians(angle));
        y += speed * Math.sin(Math.toRadians(angle));
    }

    public void makeShape() {
        prj = p.createShape();
        prj.beginShape();
        prj.noFill();
        prj.scale(makeRange((float) 0.5, 3));
        prj.strokeWeight(1);
        prj.stroke(255, 248, 7);
        prj.vertex(0, -4);
        prj.vertex(-3, 4);
        prj.vertex(4, -1);
        prj.vertex(-4, -1);
        prj.vertex(3, 3);
        prj.vertex(0, -4);
        prj.endShape();
    }

    public boolean isOut() {
        if (x > 800) {
            return true;
        }
        if (x < 0) {
            return true;
        }
        if (y > 800) {
            return true;
        }
        if (y < 0) {
            return true;
        }
        return false;
    }

    public static int makeRange(float start, float size) {//makes a random in a set size and starting point
        return (int) (Math.random() * size + start);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
