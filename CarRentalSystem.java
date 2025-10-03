import java.util.*;

// Represents a car in the rental system
class Car {
    private final String registrationNumber;
    private final String model;
    private boolean isAvailable;

    // Constructor to initialize car details
    public Car(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.isAvailable = true; // Car is available by default
    }

    // Rent the car if it's available
    public boolean rentCar() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    // Return the car (make it available again)
    public void returnCar() {
        isAvailable = true;
    }

    // Getters for car attributes
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Display car details
    @Override
    public String toString() {
        return model + " (" + registrationNumber + ") - " + (isAvailable ? "Available" : "Rented");
    }
}

// Represents a customer in the system
class Customer {
    private final String name;
    private final String licenseNumber;

    // Constructor to initialize customer details
    public Customer(String name, String licenseNumber) {
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    // Getters for customer attributes
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }
}

// Manages cars and customers in the rental agency
class RentalAgency {
    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    // Add a car to the agency
    public void addCar(Car car) {
        cars.add(car);
    }

    // Add a customer to the agency
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Display all available cars
    public void showAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car);
            }
        }
    }

    // Rent a car to a customer based on registration number and license
    public boolean rentCar(String regNumber, String licenseNumber) {
        for (Car car : cars) {
            if (car.getRegistrationNumber().equalsIgnoreCase(regNumber) && car.isAvailable()) {
                car.rentCar();
                System.out.println("✅ Car rented successfully to license: " + licenseNumber);
                return true;
            }
        }
        System.out.println("❌ Car not available or invalid registration number.");
        return false;
    }
}

// Main class that runs the program
public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Hardcoded login credentials
        String correctUsername = "admin";
        String correctPassword = "pass123";

        boolean loggedIn = false;

        // Allow up to 3 login attempts
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Display password as asterisks
            String masked = "*".repeat(password.length());
            System.out.println("Password received: " + masked);

            // Validate credentials
            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("🎉 Login successful!\n");
                loggedIn = true;
                break;
            } else {
                System.out.println("⚠️ Incorrect credentials. Attempt " + attempt + " failed.\n");
            }
        }

        // Exit if login fails after 3 attempts
        if (!loggedIn) {
            System.out.println("🚫 Maximum login attempts reached. Exiting program.");
            return;
        }

        // Initialize rental agency and sample data
        RentalAgency agency = new RentalAgency();
        agency.addCar(new Car("KAA123A", "Toyota Corolla"));
        agency.addCar(new Car("KBB456B", "Honda Civic"));
        agency.addCustomer(new Customer("Leah", "DL123456")); // Sample customer

        // Show available cars
        agency.showAvailableCars();

        // Prompt user to rent a car
        System.out.print("\nEnter registration number of car to rent: ");
        String regNumber = scanner.nextLine();
        System.out.print("Enter your license number: ");
        String license = scanner.nextLine();

        // Attempt to rent the car
        agency.rentCar(regNumber, license);
    }
}