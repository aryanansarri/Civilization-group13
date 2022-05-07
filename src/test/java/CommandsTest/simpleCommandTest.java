package CommandsTest;

import View.Menu;
import org.junit.Test;

import java.util.regex.Matcher;

public class simpleCommandTest {
    @Test
    public void simpleTester() {
        Matcher matcher;
        if ((matcher = Menu.getCommand("ok", "ok")) != null) {
            System.out.println("match!");
        }
        else {
            System.out.println("not match");
        }
    }
}
