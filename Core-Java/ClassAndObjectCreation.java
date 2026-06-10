public class ClassAndObjectCreation {
    
    static class Car {
        String make, model;
        int year;
        
        Car(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }
        
        void displayDetails() {
            System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year);
        }
    }
    
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2022);
        Car car2 = new Car("Honda", "Civic", 2023);
        
        car1.displayDetails();
        car2.displayDetails();
    }
}
