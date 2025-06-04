class Planet
{
    static final double G = 6.67e-11;

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
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
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.pow(dx*dx + dy*dy,0.5);
    }

    public double calcForceExertedBy(Planet p){
        return G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double totalNetForce = 0.0;
        for(int i = 0; i < ps.length ; i++){
            if(!this.equals(ps[i])){
                totalNetForce += calcForceExertedByX(ps[i]);
            }
        }
        return totalNetForce;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double totalNetForce = 0.0;
        for(int i = 0; i < ps.length ; i++){
            if(!this.equals(ps[i])){
                totalNetForce += calcForceExertedByY(ps[i]);
            }
        }
        return totalNetForce;
    }

    public void update(double secs, double x_force, double y_force){
        double x_acceleration = x_force / mass;
        double y_acceleration = y_force / mass;

        xxVel += x_acceleration * secs;
        yyVel += y_acceleration * secs;

        xxPos += xxVel * secs;
        yyPos += yyVel * secs;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
        StdDraw.show();
    }
}