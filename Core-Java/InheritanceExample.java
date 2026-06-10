public class InheritanceExample {
    
    static class Animal {
        void makeSound() {
            System.out.println("Some sound");
        }
    }
    
    static class Dog extends Animal {
        @Override
        void makeSound() {
            System.out.println("Bark");
        }
    }
    
    static class Cat extends Animal {
        @Override
        void makeSound() {
            System.out.println("Meow");
        }
    }
    
    public static void main(String[] args) {
        Animal animal = new Animal();
        Dog dog = new Dog();
        Cat cat = new Cat();
        
        animal.makeSound();
        dog.makeSound();
        cat.makeSound();
    }
}
