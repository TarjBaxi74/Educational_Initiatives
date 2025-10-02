package patterns.creational;

// Simple singleton: Configuration
public class SingletonDemo {
    private static volatile SingletonDemo instance;

    private SingletonDemo() {
    }
    
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null)
                    instance = new SingletonDemo();
            }
        }
        return instance;
    }

    public void show() {
        System.out.println("Singleton instance: " + this.hashCode());
    }

    public static void main(String[] args) {
        SingletonDemo.getInstance().show();
    }
}