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
        City city = new City(30,40, "kabul");
        TourManager.addCity(city);
        city = new City(5, 65, "yerevan");
        TourManager.addCity(city);
        city = new City(8, 63, "baku");
        TourManager.addCity(city);
        city = new City(10, 55, "manama");
        TourManager.addCity(city);
        city = new City(50, 55, "dhaka");
        TourManager.addCity(city);
        city = new City(47, 55, "thimphu");
        TourManager.addCity(city);
        city = new City(55, 42, "bandarSeriBegawanBrunei");
        TourManager.addCity(city);
        city = new City(52, 40, "phnomPenhCambodia");
        TourManager.addCity(city);
        city = new City(65, 70, "beijingChina");
        TourManager.addCity(city);
        city = new City(0, 60, "nicosiaCyprus");
        TourManager.addCity(city);
        city = new City(5, 75, "tbilisiGeorgia");
        TourManager.addCity(city);
        city = new City(45, 55, "newDelhiIndia");
        TourManager.addCity(city);
        city = new City(70, 25, "jakartaIndonesia");
        TourManager.addCity(city);
        city = new City(15, 60, "tehranIran");
        TourManager.addCity(city);
        city = new City(10, 60, "baghdadIraq");
        TourManager.addCity(city);
        city = new City(0, 60, "jerusalemIsrael");
        TourManager.addCity(city);
        city = new City(90, 65, "tokyoJapan");
        TourManager.addCity(city);
        city = new City(5, 52, "ammanJordan");
        TourManager.addCity(city);
        city = new City(33, 75, "astanaKazakhstan");
        TourManager.addCity(city);
        city = new City(85, 63, "pyongyangNorthKorea");
        TourManager.addCity(city);
        city = new City(83, 60, "seoulSouthKorea");
        TourManager.addCity(city);
        city = new City(10, 50, "kuwaitCityKuwait");
        TourManager.addCity(city);
        city = new City(43, 64, "bishkekKyrgyzstan");
        TourManager.addCity(city);
        city = new City(53, 48, "vientianeLaos");
        TourManager.addCity(city);
        city = new City(0 ,60, "beirutLebanon");
        TourManager.addCity(city);
        city = new City(55, 26, "kualaLumpurMalaysia");
        TourManager.addCity(city);
        city = new City(40,28, "maleMaldives");
        TourManager.addCity(city);
        city = new City(65, 80, "ulaanbaatarMongolia");
        TourManager.addCity(city);
        city = new City(55, 43, "naypyidawMyanmar");
        TourManager.addCity(city);
        city = new City(43, 53, "kathmanduNepal");
        TourManager.addCity(city);
        city = new City(16, 53, "muscatOman");
        TourManager.addCity(city);
        city = new City(23, 53, "islamabadPakistan");
        TourManager.addCity(city);
        city = new City(2,55, "jerusalemPalestine");
        TourManager.addCity(city);
        city = new City(68, 37, "manilaPhilippines");
        TourManager.addCity(city);
        city = new City(10, 49, "dohaQatar");
        TourManager.addCity(city);
        city = new City(12, 53, "riyadhSaudiArabia");
        TourManager.addCity(city);
        city = new City(51, 19, "singaporeSingapore");
        TourManager.addCity(city);
        city = new City(40, 33, "sriJayawardenapuraKotteSriLanka");
        TourManager.addCity(city);
        city = new City(7, 60, "damascusSyria");
        TourManager.addCity(city);
        city = new City(75, 50, "taipeiTaiwan");
        TourManager.addCity(city);
        city = new City(39, 76, "dushanbeTajikistan");
        TourManager.addCity(city);
        city = new City(54, 43, "bangkokThailand");
        TourManager.addCity(city);
        city = new City(0, 70, "ankaraTurkey");
        TourManager.addCity(city);
        city = new City(24, 73, "turkmenistanAshgabat");
        TourManager.addCity(city);
        city = new City(15, 50, "abudhabiUnitedArabEmirates");
        TourManager.addCity(city);
        city = new City(33, 70, "tashkentUzbekistan");
        TourManager.addCity(city);
        city = new City(63, 48, "hanoiVietnam");
        TourManager.addCity(city);
        city = new City(5, 45, "sanaaYemen");
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
            LineComponent lineComponent = new LineComponent(400,400);
            int scale = 5;
            for(int i = 0; i<tour.tourSize()-1;i++){
                lineComponent.addLine(tour.getCity(i).x*scale, tour.getCity(i).y*scale, tour.getCity(i+1).x*scale, tour.getCity(i+1).y*scale);
            }
            JOptionPane.showMessageDialog(null, lineComponent);
        };
        SwingUtilities.invokeLater(r);
    }
}