package aSEROIDS;
import processing.core.PShape;
public class Asteroid {
	private PShape ast;
	private MainDraw p;
	private float x;
	private float y;
	private float speed;
	private float rotationSpeed;
	private float angle;//need this for MOVE;//HEYO
	private float scale;
	
	public Asteroid(MainDraw parent, float xpos, float ypos, float spd, float rotatSpd, float ang, float scl){
		p = parent;
		x = xpos;
		y = ypos;
		speed = spd;
		rotationSpeed = rotatSpd;
		angle = ang;
		scale = scl;
		if(Math.random() > 0.49){
			rotationSpeed *= -1;
		}
		
//		int dog = (int)(Math.random() * 3 + 1);
		//(int)(Math.random() * (y - x)|size + x|starting point);
//		scale = makeRange((float)0.2, (float)4);
		makeShape();
	}
	public void draw(){
		teleBack();
		p.pushMatrix(); //store current plac e
		p.translate(x,y);
		p.rotate(rotationSpeed);
		//p.scale(scale);
		p.shape(ast);
		p.popMatrix(); //return to old pal e
		rotationSpeed += Math.toRadians(Math.PI/rotationSpeed);
//		System.out.println(x+".."+y);
	}
//	public void smallDraw(){
//		teleBack();
//		p.pushMatrix(); //store current plac e
//		p.translate(x,y);
//		p.rotate(rotationSpeed);
//		p.scale((float)0.6);
//		p.shape(ast);
//		p.popMatrix(); //return to old pal e
//		rotationSpeed += Math.toRadians(Math.PI/rotationSpeed);
//	}
	public void move(){
		x += speed * Math.cos(Math.toRadians(angle));
		y += speed * Math.sin(Math.toRadians(angle));
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
	public void makeShape(){
		ast = p.createShape();
		ast.beginShape();
//		ast.scale(makeRange((float)0.15,(float)1.25));
		ast.noFill();
		ast.scale(scale);
		ast.stroke(0xff11DBFF);
		if(Math.random()<= 0.51){
			ast.stroke(0xffFF7AD1);
		}
		ast.strokeWeight(2);
		ast.vertex(0,-25);//100
		ast.vertex(makeRange(5,20),makeRange(5,10));
		ast.vertex(makeRange(5,20),makeRange(15,20));
		ast.vertex(makeRange(-15,15),makeRange(21,25));
		ast.vertex(makeRange(-20,-5),makeRange(15,20));
		ast.vertex(makeRange(-20,-5),makeRange(5,10));
		ast.vertex(0,-25);//need this to connect "final" line
		ast.endShape();
	}
	public static int makeRange(float start, float size){//makes a random in a set size and starting point
		return (int)(Math.random() * size + start);
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getRotation(){
//		return (float)Math.toRadians(rotationSpeed);
		return angle;
	}
	public PShape getShape(){
		return ast;
	}
	public static void main(String[]args){
		
	}
	public float getAngle() {
		return angle;
	}
	public float getScale(){
		return scale;
	}
}
