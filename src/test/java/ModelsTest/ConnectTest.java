package ModelsTest;

import Models.Connect;
import org.junit.Test;

public class ConnectTest {
    private Connect<Integer, Integer> c = new Connect<>(4, 5);

    @Test
    public void Test() {
        c.getFirst();
        c.getSecond();
        c.setFirst(5);
        c.setSecond(6);
    }
}
