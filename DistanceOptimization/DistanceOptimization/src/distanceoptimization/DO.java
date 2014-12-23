package distanceoptimization;

public class DO {

    public static void main(String[] args) {

        // Create and add our cities
        CoordinateLocation point = new CoordinateLocation(60, 200);
        PathManager.addCoordinateLocation(point);
        CoordinateLocation point2 = new CoordinateLocation(180, 200);
        PathManager.addCoordinateLocation(point2);
        CoordinateLocation point3 = new CoordinateLocation(80, 180);
        PathManager.addCoordinateLocation(point3);
        CoordinateLocation point4 = new CoordinateLocation(140, 180);
        PathManager.addCoordinateLocation(point4);
        CoordinateLocation point5 = new CoordinateLocation(20, 160);
        PathManager.addCoordinateLocation(point5);
        CoordinateLocation point6 = new CoordinateLocation(100, 160);
        PathManager.addCoordinateLocation(point6);
        CoordinateLocation point7 = new CoordinateLocation(200, 160);
        PathManager.addCoordinateLocation(point7);
        CoordinateLocation point8 = new CoordinateLocation(140, 140);
        PathManager.addCoordinateLocation(point8);
        CoordinateLocation point9 = new CoordinateLocation(40, 120);
        PathManager.addCoordinateLocation(point9);
        CoordinateLocation point10 = new CoordinateLocation(100, 120);
        PathManager.addCoordinateLocation(point10);
        CoordinateLocation point11 = new CoordinateLocation(180, 100);
        PathManager.addCoordinateLocation(point11);
        CoordinateLocation point12 = new CoordinateLocation(60, 80);
        PathManager.addCoordinateLocation(point12);
        CoordinateLocation point13 = new CoordinateLocation(120, 80);
        PathManager.addCoordinateLocation(point13);
        CoordinateLocation point14 = new CoordinateLocation(180, 60);
        PathManager.addCoordinateLocation(point14);
        CoordinateLocation point15 = new CoordinateLocation(20, 40);
        PathManager.addCoordinateLocation(point15);
        CoordinateLocation point16 = new CoordinateLocation(100, 40);
        PathManager.addCoordinateLocation(point16);
        CoordinateLocation point17 = new CoordinateLocation(200, 40);
        PathManager.addCoordinateLocation(point17);
        CoordinateLocation point18 = new CoordinateLocation(20, 20);
        PathManager.addCoordinateLocation(point18);
        CoordinateLocation point19 = new CoordinateLocation(60, 20);
        PathManager.addCoordinateLocation(point19);
        CoordinateLocation point20 = new CoordinateLocation(160, 20);
        PathManager.addCoordinateLocation(point20);

        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = Algorithm.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = Algorithm.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }
}
    