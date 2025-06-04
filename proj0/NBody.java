class NBody{
    public static double readRadius(String f){
        In in = new In(f);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String f){
        In in = new In(f);
        int num_planets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num_planets];
        for(int n = 0; n < num_planets; n++){
            planets[n] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble()
                    , in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        String imageToDraw = "images/starfield.jpg";
        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();


//        StdDraw.pause(2000);
        StdDraw.enableDoubleBuffering();

        for(double time = 0; time < T; time += dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dt,xForces[i],yForces[i]);
                StdDraw.picture(-1,1,imageToDraw);
                planets[i].draw();
                StdDraw.show();
            }
        }

    }
}