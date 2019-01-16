import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * Created by lxy on 24/02/2018.
 */
public class ThriftTest {

    public static void main(String[] args) {
        CallerClassTest tt =  new CallerClassTest();
        tt.testGetCallerClass();
    }

    public static class CallerClassTest {
        public void testGetCallerClass() {
            // System.out.println(Reflection.getCallerClass().getName());
        }
    }
}
