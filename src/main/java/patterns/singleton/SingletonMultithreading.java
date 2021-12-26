package patterns.singleton;

/**
 * To read more https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
 */
public class SingletonMultithreading {

    // The volatile keyword now ensures that multiple threads handle the singleton instance correctly.
    private static volatile SingletonMultithreading instance;
    public String value;

    private SingletonMultithreading(String value) {
        //emulate slow initialization
        try {
            Thread.sleep(1000L);
            this.value = value;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The approach taken here is called double-checked locking (DCL). It
     * exists to prevent race condition between multiple threads that may
     * attempt to get singleton instance at the same time, creating separate
     * instances as a result.
     * <p>
     * It may seem that having the `result` variable here is completely
     * pointless. There is, however, a very important caveat when
     * implementing double-checked locking in Java, which is solved by
     * introducing this local variable.
     * <p>
     * You can read more info DCL issues in Java here:
     * https://refactoring.guru/java-dcl-issue
     *
     * @param value
     * @return
     */
    public static SingletonMultithreading getInstance(String value) {
        // use local reference can improve the method's overall performance by as much as 40 percent!
        SingletonMultithreading localRef = instance;
        if (localRef != null) {
            return localRef;
        }
        synchronized (SingletonMultithreading.class) {
            localRef = instance;
            if (localRef == null) {
                instance = localRef = new SingletonMultithreading(value);
            }
            return localRef;
        }
    }
    //TODO Using Reflection to destroy Singleton Pattern
}
