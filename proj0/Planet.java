public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, 
				double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos)+(this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		double g = 6.67e-11;
		return g*(this.mass)*(p.mass)/(this.calcDistance(p)*this.calcDistance(p));
	}
	
	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p)*(p.xxPos - this.xxPos)/this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p)*(p.yyPos - this.yyPos)/this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] p){
		double NetForceByX = 0.0;
		for(int i = 0; i < p.length; i++){
			if (this.equals(p[i])){
				continue;
			}
			else{
				NetForceByX += this.calcForceExertedByX(p[i]);
			}
		}
		return NetForceByX;
	}

	public double calcNetForceExertedByY(Planet[] p){
		double NetForceByY = 0.0;
		for(int i = 0; i < p.length; i++){
			if (this.equals(p[i])){
				continue;
			}
			else{
				NetForceByY += this.calcForceExertedByY(p[i]);
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

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}
}