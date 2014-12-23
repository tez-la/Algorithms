package distanceoptimization;

import java.util.ArrayList;

public class PathManager {

    // Holds our cities

    private static ArrayList destinationCities = new ArrayList<CoordinateLocation>();

    // Adds a destination city
    public static void addCoordinateLocation(CoordinateLocation city) {
        destinationCities.add(city);
    }

    // Get a city
    public static CoordinateLocation getCoordinateLocation(int index) {
        return (CoordinateLocation) destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities() {
        return destinationCities.size();
    }
}
