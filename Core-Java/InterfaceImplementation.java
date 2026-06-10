public class InterfaceImplementation {
    
    interface Playable {
        void play();
    }
    
    static class Guitar implements Playable {
        public void play() {
            System.out.println("Strum strum...");
        }
    }
    
    static class Piano implements Playable {
        public void play() {
            System.out.println("Ding ding...");
        }
    }
    
    public static void main(String[] args) {
        Playable guitar = new Guitar();
        Playable piano = new Piano();
        
        guitar.play();
        piano.play();
    }
}
