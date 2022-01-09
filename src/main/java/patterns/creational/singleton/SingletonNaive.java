package patterns.creational.singleton;

public final class SingletonNaive {
    private static SingletonNaive instance;
    public String value;

    private SingletonNaive(String value) {
        //emulate slow initialization
        try {
            Thread.sleep(1000L);
            this.value = value;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SingletonNaive getInstance(String value) {
        if (instance == null) {
            instance = new SingletonNaive(value);
        }
        return instance;
    }
}
