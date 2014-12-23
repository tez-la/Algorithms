package distanceoptimization;

import java.util.ArrayList;
import java.util.Collections;


public class Distance {

    // Holds our tour of cities

    private ArrayList tour = new ArrayList<CoordinateLocation>();
    // Cache
    private double fitness = 0;
    private int distance = 0;

    // Constructs a blank tour
    public Distance() {
        for (int i = 0; i < PathManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public Distance(ArrayList tour) {
        this.tour = tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < PathManager.numberOfCities(); cityIndex++) {
            setCoordinateLocation(cityIndex, PathManager.getCoordinateLocation(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public CoordinateLocation getCoordinateLocation(int tourPosition) {
        return (CoordinateLocation) tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCoordinateLocation(int tourPosition, CoordinateLocation city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }

    // Gets the total distance of the tour
    public int getDistance() {
        if (distance == 0) {
            int tourDistance = 0;
            // Loop through our tour's cities
            for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
                // Get city we're travelling from
                CoordinateLocation fromCoordinateLocation = getCoordinateLocation(cityIndex);
                // CoordinateLocation we're travelling to
                CoordinateLocation destinationCoordinateLocation;
                // Check we're not on our tour's last city, if we are set our 
                // tour's final destination city to our starting city
                if (cityIndex + 1 < tourSize()) {
                    destinationCoordinateLocation = getCoordinateLocation(cityIndex + 1);
                } else {
                    destinationCoordinateLocation = getCoordinateLocation(0);
                }
                // Get the distance between the two cities
                tourDistance += fromCoordinateLocation.distanceTo(destinationCoordinateLocation);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCoordinateLocation(CoordinateLocation city) {
        return tour.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCoordinateLocation(i) + "|";
        }
        return geneString;
    }
}
