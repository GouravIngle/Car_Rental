import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class Car {
	private String car_id;
	private String car_model;
	private String car_brand;
	//private double car_price;
	private double bestpriceperDay;
	private boolean available;

	public Car(String car_id, String car_brand, String car_model, double bestpriceperDay) {
		super();
		this.car_id = car_id;
		this.car_brand = car_brand;
		this.car_model = car_model;
		//this.car_price = d;
		this.bestpriceperDay = bestpriceperDay;
		available = true;

	}

	public String getCar_id() {
		return car_id;
	}

	public String getCar_model() {
		return car_model;
	}

	public String getCar_brand() {
		return car_brand;
	}

//	public double getCar_price() {
//		return car_price;
//	}

	public double calculatePrice(int rentalDays) {
		return bestpriceperDay * rentalDays;
	}
//	 public double calculatePrice(int rentalDays) {
//	        return basePricePerDay * rentalDays;
//	    }

	public boolean isAvailable() {
		return available;
	}

	public void rent() {
		boolean isAvailable = false;
	}

	public void returnCar() {
		boolean isAvailable = true;
	}
}

class Customer {
	private String id;
	private String name;

	public Customer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

class Rental {
	private Car car;
	private Customer customer;
	private int day;

	public Rental(Car car, Customer customer, int day) {
		super();
		this.car = car;
		this.customer = customer;
		this.day = day;
	}

	public Car getCar() {
		return car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getDay() {
		return day;
	}
}

class CarRentalSystem {
	private List<Car> cars = new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();
	private List<Rental> rentals = new ArrayList<>();

	public void addCar(Car car) {
		cars.add(car);
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void rentCar(Car car, Customer customer, int days) {
		if (car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car, customer, days));

		} else {
			System.out.println("Car is not available for rent.");
		}
	}

	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove = null;
		for (Rental rental : rentals) {
			if (rental.getCar() == car) {
				rentalToRemove = rental;
				break;
			}
		}
		if (rentalToRemove != null) {
			rentals.remove(rentalToRemove);

		} else {
			System.out.println("Car was not rented.");
		}
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			
			System.out.println("====Rental Portal====");
			System.out.println("1 Rent a car ");
			System.out.println("Return a car");
			System.out.println("3 Exit");
			int a = sc.nextInt();
			switch (a) {
			case 1: {
				System.out.println(" ENTER THE NAME 1 :");
				String customerName23 = sc.nextLine();
				System.out.println(" ENTER THE NAME :");
				String CustmerName15=sc.nextLine();
				System.out.println("Available Cars:");
				for (Car car : cars) {
					if (car.isAvailable()) {
						System.out.println(car.getCar_id() + " - " + car.getCar_brand() + " " + car.getCar_model());
					}
				}
				System.out.print("\nEnter the car ID you want to rent: ");
				String carId = sc.nextLine();

				System.out.print("Enter the number of days for rental: ");
				int rentalDays = sc.nextInt();
				sc.nextLine(); // Consume newline

				Customer customer1Name = new Customer("CUS" + (customers.size()), CustmerName15);
				addCustomer(customer1Name);
				Car selectcar = null;
				for (Car car : cars) {
					if (car.getCar_id().equals(carId) && car.isAvailable()) {
						selectcar = car;
						break;
					}
				}
				if (selectcar != null) {
					double total = selectcar.calculatePrice(rentalDays);
					System.out.println("======Rental Data=====");
					System.out.println("CustomerId = " + customer1Name.getId());
					System.out.println("Customer Name = " + customer1Name.getName());
					System.out.println("car Brand = " + selectcar.getCar_brand());
					System.out.println("car Model = " +selectcar.getCar_model());
					System.out.println("Reant Day = " + rentalDays);
					System.out.println("Total Price = " + total);

					System.out.println("Conform [Y/N]");
					String r = sc.next();
					if (r.equalsIgnoreCase("Y")) {
						rentCar(selectcar, customer1Name, rentalDays);
						System.out.println("Car rented successfully.");
					} else {
						System.out.println("Car Canceled");
					}
				} else {
					System.out.println("\nInvalid car selection or car not available for rent.");
				}

			}
			case 2: {
				System.out.println("\n== Return a Car ==\n");
				System.out.print("Enter the car ID you want to return: ");
				String carId = sc.nextLine();

				Car carToReturn = null;
				for (Car car : cars) {
					if (car.getCar_id().equals(carId) && car.isAvailable()) {
						carToReturn = car;
						break;
					}
				}

				if (carToReturn != null) {
					Customer customer12 = null;
					for (Rental rental : rentals) {
						if (rental.getCar() == carToReturn) {
							customer12 = rental.getCustomer();
							break;
						}
					}

					if (customer12 != null) {
						returnCar(carToReturn);
						System.out.println("Car returned successfully by " + customer12.getName());
					} else {
						System.out.println("Car was not rented or rental information is missing.");
					}
				} else {
					System.out.println("Invalid car ID or car is not rented.");
				}
			}

			case 3: {
				break;
			}
			default:
				System.out.println("Invalid choice. Please enter a valid option.");
			}
			System.out.println("\nThank you for using the Car Rental System!");
		}

	}

}

public class main {

	public static void main(String[] args) {
		CarRentalSystem rentalSystem = new CarRentalSystem();

		Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
		Car car2 = new Car("C002", "Honda", "Accord", 70.0);
		Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
		rentalSystem.addCar(car1);
		rentalSystem.addCar(car2);
		rentalSystem.addCar(car3);
		rentalSystem.menu();
	}
}
