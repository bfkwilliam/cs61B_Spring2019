public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	static final double g = 6.67e-11;

	public Body(double xP, double yP, double xV, 
				double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		return Math.sqrt((this.xxPos - b.xxPos)*(this.xxPos - b.xxPos)+(this.yyPos - b.yyPos)*(this.yyPos - b.yyPos));
	}

	public double calcForceExertedBy(Body b){
		return g*(this.mass)*(b.mass)/(this.calcDistance(b)*this.calcDistance(b));
	}
	
	public double calcForceExertedByX(Body b){
		return this.calcForceExertedBy(b)*(b.xxPos - this.xxPos)/this.calcDistance(b);
	}

	public double calcForceExertedByY(Body b){
		return this.calcForceExertedBy(b)*(b.yyPos - this.yyPos)/this.calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] b){
		double NetForceByX = 0.0;
		for(int i = 0; i < b.length; i++){
			if (this.equals(b[i])){
				continue;
			}
			else{
				NetForceByX += this.calcForceExertedByX(b[i]);
			}
		}
		return NetForceByX;
	}

	public double calcNetForceExertedByY(Body[] b){
		double NetForceByY = 0.0;
		for(int i = 0; i < b.length; i++){
			if (this.equals(b[i])){
				continue;
			}
			else{
				NetForceByY += this.calcForceExertedByY(b[i]);
			}
		}
		return NetForceByY;
	}

	public void update(double dt, double fx, double fy){
		this.xxVel += dt*fx/this.mass;
		this.yyVel += dt*fy/this.mass;
		this.xxPos += dt*this.xxVel;
		this.yyPos += dt*this.yyVel;
	}

	/**
	*  Draw one body
	*/
	public String imagePath = "images/";


	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, imagePath+this.imgFileName);
	}
}