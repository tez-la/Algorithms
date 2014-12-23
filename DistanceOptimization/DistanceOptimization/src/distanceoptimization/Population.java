package distanceoptimization;

public class Population {

    // Holds population of tours

    Distance[] tours;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        tours = new Distance[populationSize];
        // If we need to initialise a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Distance newDistance = new Distance();
                newDistance.generateIndividual();
                saveDistance(i, newDistance);
            }
        }
    }

    // Saves a tour
    public void saveDistance(int index, Distance tour) {
        tours[index] = tour;
    }

    // Gets a tour from population
    public Distance getDistance(int index) {
        return tours[index];
    }

    // Gets the best tour in the population
    public Distance getFittest() {
        Distance fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getDistance(i).getFitness()) {
                fittest = getDistance(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return tours.length;
    }
}
