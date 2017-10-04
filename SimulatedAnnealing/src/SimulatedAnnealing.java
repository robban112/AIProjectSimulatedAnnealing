import javax.swing.*;
import java.awt.Graphics;

public class SimulatedAnnealing {

    // Calculate the acceptance probability
    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static void main(String[] args) {
        // Create and add our cities
        City city = new City(30,40, "Kabul");
        TourManager.addCity(city);
        city = new City(5, 65, "Yerevan");
        TourManager.addCity(city);
        city = new City(8, 63, "Baku");
        TourManager.addCity(city);
        city = new City(10, 55, "Manama");
        TourManager.addCity(city);
        city = new City(50, 55, "Dhaka");
        TourManager.addCity(city);
        city = new City(47, 55, "Thimphu");
        TourManager.addCity(city);
        city = new City(55, 42, "Bandar Seri Begawan");
        TourManager.addCity(city);
        city = new City(52, 40, "Phnom Penh");
        TourManager.addCity(city);
        city = new City(65, 70, "Beijing");
        TourManager.addCity(city);
        city = new City(0, 60, "Nicosia");
        TourManager.addCity(city);
        city = new City(5, 75, "Tbilisi");
        TourManager.addCity(city);
        city = new City(45, 55, "New Delhi");
        TourManager.addCity(city);
        city = new City(70, 25, "Jakarta");
        TourManager.addCity(city);
        city = new City(15, 60, "Tehran");
        TourManager.addCity(city);
        city = new City(10, 60, "Baghdad");
        TourManager.addCity(city);
        city = new City(0, 60, "Jerusalem");
        TourManager.addCity(city);
        city = new City(90, 65, "Tokyo");
        TourManager.addCity(city);
        city = new City(5, 52, "Amman");
        TourManager.addCity(city);
        city = new City(33, 75, "Astana");
        TourManager.addCity(city);
        city = new City(85, 63, "Pyong Yang");
        TourManager.addCity(city);
        city = new City(83, 60, "Seoul");
        TourManager.addCity(city);
        city = new City(10, 50, "Kuwait City");
        TourManager.addCity(city);
        city = new City(43, 64, "Bishkek");
        TourManager.addCity(city);
        city = new City(53, 48, "Vientiane");
        TourManager.addCity(city);
        city = new City(0 ,60, "Beirut");
        TourManager.addCity(city);
        city = new City(55, 26, "Kuala Lumpur");
        TourManager.addCity(city);
        city = new City(40,28, "Male");
        TourManager.addCity(city);
        city = new City(65, 80, "Ulaanbaatar");
        TourManager.addCity(city);
        city = new City(55, 43, "Naypyidaw");
        TourManager.addCity(city);
        city = new City(43, 53, "Kathmandu");
        TourManager.addCity(city);
        city = new City(16, 53, "Muscat");
        TourManager.addCity(city);
        city = new City(23, 53, "Islamabad");
        TourManager.addCity(city);
        city = new City(2,55, "Jerusalem");
        TourManager.addCity(city);
        city = new City(68, 37, "Manila");
        TourManager.addCity(city);
        city = new City(10, 49, "Doha");
        TourManager.addCity(city);
        city = new City(12, 53, "Riyadh");
        TourManager.addCity(city);
        city = new City(51, 19, "Singapore");
        TourManager.addCity(city);
        city = new City(40, 33, "Sri Jayawardenapura Kotte");
        TourManager.addCity(city);
        city = new City(7, 60, "Damascus");
        TourManager.addCity(city);
        city = new City(75, 50, "Taipei");
        TourManager.addCity(city);
        city = new City(39, 76, "Dushanbe");
        TourManager.addCity(city);
        city = new City(54, 43, "Bangkok");
        TourManager.addCity(city);
        city = new City(0, 70, "Ankara");
        TourManager.addCity(city);
        city = new City(24, 73, "Turkmenistan");
        TourManager.addCity(city);
        city = new City(15, 50, "Abudhabi");
        TourManager.addCity(city);
        city = new City(33, 70, "Tashkent");
        TourManager.addCity(city);
        city = new City(63, 48, "Hanoi");
        TourManager.addCity(city);
        city = new City(5, 45, "Sanaa");
        TourManager.addCity(city);


        // Set initial temp
        double temp = 10000;

        // Cooling rate
        double coolingRate = 0.000003;

        // Initialize intial solution
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();

        System.out.println("Initial solution distance: " + currentSolution.getDistance());

        // Set as current best
        Tour best = new Tour(currentSolution.getTour());

        // Loop until system has cooled
        while (temp > 1) {
            // Create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            // Get a random positions in the tour
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());

            // Get the cities at selected positions in the tour
            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);

            // Get energy of solutions
            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            // Decide if we should accept the neighbour
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            // Keep track of the best solution found
            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }

            // Cool system
            temp *= 1-coolingRate;
        }


        System.out.println("Final solution distance: " + best.getDistance());
        System.out.println("Tour: " + best);

        drawTour(best);
    }

    public static void drawTour(Tour tour) {
        Runnable r = () -> {
            LineComponent lineComponent = new LineComponent(500,400);
            lineComponent.setTour(tour);
            int scale = 5;
            for(int i = 0; i<tour.tourSize()-1;i++){
                lineComponent.addLine(tour.getCity(i).x*scale, tour.getCity(i).y*scale, tour.getCity(i+1).x*scale, tour.getCity(i+1).y*scale);
            }
            JOptionPane.showMessageDialog(null, lineComponent);
        };
        SwingUtilities.invokeLater(r);
    }
}