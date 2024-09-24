package unsafe;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.logging.Logger;

public class Test1 {
    private static final Logger log = Logger.getAnonymousLogger();
    private static Unsafe reflectGetUnsafe() throws NoSuchMethodException {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            log.throwing(Test1.class.getName(), Test1.class.getDeclaredMethod("reflectGetUnsafe").getName(),e);
            return null;
        }
    }



}
