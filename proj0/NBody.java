public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);

		int numofPlanets = in.readInt();
		double radiusofUniverse = in.readDouble();

		return radiusofUniverse;
	}
	/** Read input file name, Then return an array of Planet*/
	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int numofPlanets = in.readInt();
		double radiusofUniverse = in.readDouble();
		Planet[] planets = new Planet[numofPlanets];

		/*For loop to read lines and make constructors*/
		for(int i = 0; i < numofPlanets; i++){
			planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}

		return planets;
	}
	/** Main
	1. Collecting All Needed Input
	2. Drawing the Background
	3. Drawing One Planet
	4. Drawing More than One Planet
	*/
	public static void main(String[] args) {
		double T;
		double dt;
		String filename;
		String theBackground = "images/starfield.jpg";	
		int waitTimeMilliseconds = 10;

		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		filename = args[2];
		double radius = NBody.readRadius(filename);
		Planet[] bodies = NBody.readPlanets(filename);
		/** Create xForce yForce Array*/
		double[] xForce = new double[bodies.length];
		double[] yForce = new double[bodies.length];

		/** Sets up the universe so its scale match the radius of universe */
		StdDraw.setScale(-radius, radius);

		/** Clears the drawing window. */
		StdDraw.clear();
		StdDraw.picture(0, 0, theBackground);

		/** Draw the planets*/
		for(int i = 0; i < bodies.length; i++){
			bodies[i].draw();
		}

		/**Enable Double Buffering*/
		StdDraw.enableDoubleBuffering();

		for(double time = 0.0; time <= T; time += dt){
			for(int i = 0; i < bodies.length; i++){
				xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
				bodies[i].update(dt, xForce[i], yForce[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, theBackground);
			for(int i = 0; i < bodies.length; i++){
				bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
			bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
			bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}

	
}