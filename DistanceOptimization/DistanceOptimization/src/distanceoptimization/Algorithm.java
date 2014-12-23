package distanceoptimization;

public class Algorithm {

    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.saveDistance(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Distance parent1 = tournamentSelection(pop);
            Distance parent2 = tournamentSelection(pop);
            // Crossover parents
            Distance child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveDistance(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getDistance(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Distance crossover(Distance parent1, Distance parent2) {
        // Create new child tour
        Distance child = new Distance();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.tourSize());
        int endPos = (int) (Math.random() * parent1.tourSize());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.tourSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCoordinateLocation(i, parent1.getCoordinateLocation(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCoordinateLocation(i, parent1.getCoordinateLocation(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.tourSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCoordinateLocation(parent2.getCoordinateLocation(i))) {
                // Loop to find a spare position in the child's tour
                for (int ii = 0; ii < child.tourSize(); ii++) {
                    // Spare position found, add city
                    if (child.getCoordinateLocation(ii) == null) {
                        child.setCoordinateLocation(ii, parent2.getCoordinateLocation(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Distance tour) {
        // Loop through tour cities
        for (int tourPos1 = 0; tourPos1 < tour.tourSize(); tourPos1++) {
            // Apply mutation rate
            if (Math.random() < mutationRate) {
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.tourSize() * Math.random());

                // Get the cities at target position in tour
                CoordinateLocation city1 = tour.getCoordinateLocation(tourPos1);
                CoordinateLocation city2 = tour.getCoordinateLocation(tourPos2);

                // Swap them around
                tour.setCoordinateLocation(tourPos2, city1);
                tour.setCoordinateLocation(tourPos1, city2);
            }
        }
    }

    // Selects candidate tour for crossover
    private static Distance tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveDistance(i, pop.getDistance(randomId));
        }
        // Get the fittest tour
        Distance fittest = tournament.getFittest();
        return fittest;
    }
}
