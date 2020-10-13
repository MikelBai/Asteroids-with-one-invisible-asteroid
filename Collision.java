
import java.awt.geom.Line2D;
import java.util.ArrayList;
import processing.core.PShape;
import processing.core.PVector;

/**
 * @author Tom Nute
 *	Used to determine the collision between two 
 */

public class Collision {
	
	/**
	 * Create an ArrayList of PVectors where each one is the coordinate of a 
	 * vertex for the given PShape s located at coordinates (x,y) with a rotation
	 * of r radians
	 * 
	 * @param s The shape for which the vertices' cooridnates are calculated
	 * @param x The x coordinate of the shape
	 * @param y The y cooridnate of the shape
	 * @param r The rotation of the shape (in radians)
	 * @return an ArrayList of PVectors
	 */
	
	public static ArrayList<PVector> getPoints(PShape s, float x, float y, float r, float scale) {
		ArrayList<PVector> points = new ArrayList<PVector>();
		
		for (int i=0; i<s.getVertexCount(); i++) {
			float tempX = s.getVertex(i).x;
			float tempY = s.getVertex(i).y;
			
			
			float rotatedX = (float)(tempX * Math.cos(r) - tempY * Math.sin(r));
			float rotatedY = (float)(tempX * Math.sin(r) + tempY * Math.cos(r));

			points.add(new PVector((rotatedX*scale + x), (rotatedY*scale + y)));
		}
		
		return points;
	}
	
	private static boolean areColliding(ArrayList<PVector> aPoints, ArrayList<PVector> bPoints) {
		boolean toRet = false;
		for (int i=0; i<aPoints.size()-1; i++) {
			float Ax = aPoints.get(i).x;
			float Ay = aPoints.get(i).y;

			float Bx = aPoints.get(i+1).x;
			float By = aPoints.get(i+1).y;
			
			for (int j=0; j<bPoints.size()-1; j++) {
				float Cx = bPoints.get(j).x;
				float Cy = bPoints.get(j).y;
				
				float Dx = bPoints.get(j+1).x;
				float Dy = bPoints.get(j+1).y;
				
				if (Line2D.linesIntersect(Ax, Ay, Bx, By, Cx, Cy, Dx, Dy)) {					
					toRet = true;;
				}
			}
		}
		return toRet;
	}
//	int pnpoly(int nvert, float *vertx, float *verty, float testx, float testy)
//	{
//	  int i, j, c = 0;
//	  for (i = 0, j = nvert-1; i < nvert; j = i++) {
//	    if ( ((verty[i]>testy) != (verty[j]>testy)) &&
//		 (testx < (vertx[j]-vertx[i]) * (testy-verty[i]) / (verty[j]-verty[i]) + vertx[i]) )
//	       c = !c;
//	  }
//	  return c;
//	}
	public static boolean areBulletColliding(int numVert, ArrayList<PVector> bro, Bullet test){
		int i, j = 0;
		boolean c = false;
		for(i = 0, j = numVert-1; i < numVert; j = i++){
			if( ((bro.get(i).y > test.getY()) != (bro.get(j).y>test.getY())) &&
				(test.getX() < (bro.get(j).x - bro.get(i).x) * (test.getY()-bro.get(i).y) / (bro.get(j).y - bro.get(i).y) + bro.get(i).x) ){
				c = !c;
			}
		}
		return c;
	}

	/**
	 * This method will determine if two objects, one of type Ship and the other of type Asteroid.
	 * Both classes MUST contain the following methods with respective return types.
	 * 	PShape getShape()
	 * 	float getX()
	 * 	float getY()
	 * 	float getRotation()
	 * 
	 * @param ship Instance of a class with certain methods.  See description for requirements.
	 * @param ast Instance of a class with certain methods.  See description for requirements.
	 * @return true if the two objects have overlapping shapes.
	 */

	public static boolean areShipAsteroidColliding(Ship ship, Asteroid ast) {
		return areColliding(getPoints(ship.getShape(), ship.getX(), ship.getY(), ship.getRotation(), 1), getPoints(ast.getShape(),ast.getX(),ast.getY(),ast.getRotation(), ast.getScale()));
	}
	
	/**
	 * Given 2 PShapes and their respective orientation (x,y,rotation), determine if they overlap at all.
	 * 
	 * @param ps1 PShape for object 1
	 * @param x1 x-coordinate for object 1
	 * @param y1 y-coordinate for object 1
	 * @param r1 rotation (in radians) for object 1
	 * @param ps2 PShape for object 2
	 * @param x2 x-coordinate for object 2
	 * @param y2 y-coordinate for object 2
	 * @param r2 rotation (in radians) for object 2
	 * @return true if the lines forming object 1 and 2 intersect
	 */
	
//	public static boolean areShapesColliding(PShape ps1, float x1, float y1, float r1, PShape ps2, float x2, float y2, float r2) {
//		return areColliding(getPoints(ps1,x1,y1,r1),getPoints(ps2,x2,y2,r2));
//	}
}
