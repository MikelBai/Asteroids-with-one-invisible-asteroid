
import processing.core.*;

import java.util.ArrayList;

public class MainDraw extends PApplet {
    int color = 0;
    PShape ast;
    PShape bul;
    PShape prj;
    int totAst = 10;
    int totBul = 0;
//    long elapsedTime;
    Ship ship;
    ArrayList<Asteroid> astArr;//need to specify what type of things go into list
    ArrayList<Bullet> bulArr;
    ArrayList<Projectile> prjArr;
    //	ArrayList<PVector> bulPoints = Collision.getPoints(bul, bulArr.get(i).getX(), bulArr.get(i).getY(), bulArr.get(i).getAngle());
    private PFont myFont;

    public void setup() {
        myFont = createFont("perpetua", 48, true);
        ship = new Ship(this, 400, 400, (float) 0.7, PI);
        astArr = new ArrayList<Asteroid>();
        bulArr = new ArrayList<Bullet>();
        prjArr = new ArrayList<Projectile>();
//		maindraw parent, float x, float y, float speed, float rotatSpd, float scale
        for (int i = 0; i < totAst; i++) {
            double astCr = Math.random();
            if (astCr < 0.25) {//small
                astArr.add(new Asteroid(this,
                        Asteroid.makeRange(0, 800),
                        Asteroid.makeRange(0, 800),
                        Asteroid.makeRange(1, 2),
                        Asteroid.makeRange((float) Math.toRadians(Math.PI / 10), 5),
                        Asteroid.makeRange(1, 360),
                        (float) 0.5));
            } else if (astCr < 0.6) {//med
                astArr.add(new Asteroid(this,
                        Asteroid.makeRange(0, 800),
                        Asteroid.makeRange(0, 800),
                        Asteroid.makeRange(1, 2),
                        Asteroid.makeRange((float) Math.toRadians(Math.PI / 10), 5),
                        Asteroid.makeRange(1, 360),
                        1));
            } else {//big
                astArr.add(new Asteroid(this,
                        Asteroid.makeRange(0, 800),
                        Asteroid.makeRange(0, 800),
                        Asteroid.makeRange(1, 2),
                        Asteroid.makeRange((float) Math.toRadians(Math.PI / 10), 5),
                        Asteroid.makeRange(1, 360),
                        2));
            }

        }
    }

    long prevTime = System.currentTimeMillis();
    boolean isRes = false;
    long deathTime;
    //	bulPoints = new ArrayList<PVector> Collision.getPoints(bul, bulArr.get(i).getX(), bulArr.get(i).getY(), bulArr.get(i).getAngle());
//	ArrayList<PVector> bulPoints = Collision.getPoints(bul, bulArr.get(i).getX(), bulArr.get(i).getY(), bulArr.get(i).getAngle()); THIS WORKS?
    public void draw() { //updateee
        background(color);

        //Bullet shooting
        if (e) {
            long bulletTime = System.currentTimeMillis() - prevTime;
            if (bulletTime > 300) {
                bulArr.add(new Bullet(this, ship.getX(), ship.getY(), ship.getSpeed() + 5));
                totBul = bulArr.size();
                prevTime = System.currentTimeMillis();
            }
        }
        //Bullet draw/physics
        for (int i = 0; i < bulArr.size(); i++) {
            bulArr.get(i).move();
            bulArr.get(i).draw();
			bulArr.get(i).teleBack();
            bulArr.get(i).accel((float) -0.03);
            if (bulArr.get(i).getSpeed() <= 0 || bulArr.get(i).isOut()) {
                bulArr.remove(i--);
                totBul = bulArr.size();
            }
        }
        //Asteroid draw/physics
        for (int i = 0; i < astArr.size(); i++) {
            astArr.get(i).draw();
            astArr.get(i).move();
//			astArr.get(i).smallDraw();
            if (Collision.areShipAsteroidColliding(ship, astArr.get(i))) {
//				boolean deathAni = true;
                for (int j = 0; j < 10; j++) {
                    prjArr.add(new Projectile(this, ship.getX(), ship.getY(),
                            Projectile.makeRange((float) 2, 5),
                            Projectile.makeRange((float) Math.toRadians(Math.PI / 10), 5),
                            Projectile.makeRange(1, 360)));
                }

                isRes = true;
                color += 1;
                deathTime = System.currentTimeMillis();
            }
        }
        long elapsedTime = System.currentTimeMillis() - deathTime;
        if (elapsedTime > 2500 && isRes) {
            isRes = false;
            ship.respawn();
        }

        for (int i = 0; i < bulArr.size(); i++) {
            Bullet temp = bulArr.get(i);
            for (int j = 0; j < astArr.size(); j++) {//i for bullet
                Asteroid dyingAst = astArr.get(j);
                ArrayList<PVector> astPoints = Collision.getPoints(dyingAst.getShape(), dyingAst.getX(),
                        dyingAst.getY(), dyingAst.getAngle(), dyingAst.getScale());//j for ast
                if (Collision.areBulletColliding(astPoints.size(), astPoints, temp)) {
                    System.out.println("WHAT THE HECK");
                    if (bulArr.size() > i){
                        bulArr.remove(i);

                    }

                    if (dyingAst.getScale() > 0.5){
                        float newScale;
                        if (dyingAst.getScale() == 1){
                            newScale = (float)0.5;
                        }
                        else{
                            newScale = 1;
                        }
                        astArr.add(new Asteroid(this,
                                dyingAst.getX(),
                                dyingAst.getY(),
                                dyingAst.getSpeed()*(float)1.25,
                                Asteroid.makeRange((float) Math.toRadians(Math.PI / 10), 5),
                                Asteroid.makeRange(1, 360),
                                newScale));

                        if (dyingAst.getScale() == 2){
                            astArr.add(new Asteroid(this,
                                    dyingAst.getX(),
                                    dyingAst.getY(),
                                    dyingAst.getSpeed()*(float)1.25,
                                    Asteroid.makeRange((float) Math.toRadians(Math.PI / 10), 5),
                                    Asteroid.makeRange(1, 360),
                                    newScale));
                        }

                    }


                    astArr.remove(j--);
                }
            }
        }

        for (int j = 0; j < prjArr.size(); j++) {
            prjArr.get(j).move();
            prjArr.get(j).draw();
            for (int k = 0; k < prjArr.size(); k++) {
                if (prjArr.get(k).isOut()) {
                    prjArr.remove(k);
                    k-=1;
                }
            }
        }
//		if(Collision.getPoints(bul, ))
        textFont(myFont);
        fill(255);
//        text(Ship.getAngle(), 400, 400);
//        text("Bullets: " + totBul, 600, 400);
        text("Speed: " + ship.getSpeed(), 100, 100);
        text("Asteroids: " + astArr.size(), 100, 400);
        stroke(255);


        if (!isRes){
            ship.move();
            ship.draw();
            if (w) {
                ship.accelSpd((float) 0.15);
            }
            if (!w && ship.getSpeed() > 0.8) {
                ship.accelSpd((float) -0.08);
            }
            if (a) {
                ship.rotateLeft();
            }
            if (d) {
                ship.rotateRight();
            }
        }
        else{
            ship.moveAway();
        }


//		if(Collision.areShipAsteroidColliding(ship, astArr.get(huh))){lol
//			ship.respawn();
//		}
    }

    private static boolean w;
    private static boolean a;
    private static boolean d;
    private static boolean e;

    public void keyPressed() {
        if (key == 'w') {
            w = true;
        } else if (key == 'a') {
            a = true;
        } else if (key == 'd') {
            d = true;
        }
        if (key == 'e') {
            e = true;
        }
    }

    public void keyReleased() {
        if (key == 'w') {
            w = false;
        } else if (key == 'a') {
            a = false;
        } else if (key == 'd') {
            d = false;
        }
        if (key == 'e') {
            e = false;
        }
    }

    public void settings() {
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"MainDraw"});
    }
//	if(Collision.areShapesColliding(bul, bulArr.get(i).getX(), bulArr.get(i).getY(), bulArr.get(i).getAngle(), 
//			ast, astArr.get(k).getX(), astArr.get(k).getX(), astArr.get(k).getAngle())){
//		astArr.remove(i);
//	}
}
