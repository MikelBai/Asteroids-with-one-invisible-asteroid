import processing.core.PShape;

public class Bullet {
    private PShape bul;
    private MainDraw p;
    private float x;
    private float y;
    private float speed;
    private float rotationSpeed;
    private float angle = Ship.getAngle();
//	private int color = 255;

    public Bullet(MainDraw parent, float xpos, float ypos, float spd) {
        p = parent;
        x = xpos;
        y = ypos;
        speed = spd;
        makeShape();
    }

    public void move() {
        x += speed * Math.cos(Math.toRadians(angle));
        y += speed * Math.sin(Math.toRadians(angle));
    }

    public void draw() {
        if (this.x > 800 || this.x < 0) {
            this.x = 2000;
        }
        if (this.y > 800 || this.y < 0) {
            this.y = 2000;
        }
        p.pushMatrix(); //store current plac e
        p.translate(x, y);
        p.rotate(rotationSpeed);
        p.shape(bul);
        p.popMatrix(); //return to old pal e
        rotationSpeed += Math.toRadians(Math.PI / 2);
    }

    public void makeShape() {
//		bul = p.createShape();
//		bul.beginShape();
//		bul.noFill();
//		bul.stroke(color);
//		bul.vertex(0, -2);
//		bul.vertex(2, 2);
//		bul.vertex(-2, 2);
//		bul.vertex(0, -2);
//		bul.endShape();

        bul = p.createShape();
        bul.beginShape();
        bul.noFill();
        bul.strokeWeight(1);
        bul.stroke(255);
        bul.vertex(0, -1);
        bul.vertex(1, 0);
        bul.vertex(0, 1);
        bul.vertex(-1, 0);
        bul.vertex(0, -1);

//        bul.vertex(0, -2);
//        bul.vertex(-(float)1.5, 2);
//        bul.vertex(2, -1);
//        bul.vertex(-2, -1);
//        bul.vertex((float)1.5, (float)1.5);
//        bul.vertex(0, -2);

        bul.endShape();
    }//		STAR
//	bul.vertex(0, -4);
//	bul.vertex(-3, 4);
//	bul.vertex(4, -1);
//	bul.vertex(-4, -1);
//	bul.vertex(3, 3);
//	bul.vertex(0, -4);


    public float getAngle() {
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

    public PShape getShape() {
        return bul;
    }

    public void accel(float inc) {
        speed += inc;
    }

    public boolean isOut() {
        if (x > 800 || x < 0 ||
                y > 800 || y < 0) {
            return true;
        }
        return false;
    }
	public void teleBack(){
		if(x > 800){
			x = 0;
		}
		if(x < 0){
			x = 800;
		}
		if(y > 800){
			y = 0;
		}
		if(y < 0){
			y = 800;
		}
	}
}
