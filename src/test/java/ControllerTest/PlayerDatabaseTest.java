package ControllerTest;

import Controller.PlayerDatabase;
import Controller.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerDatabaseTest {

    @Test
    public void loadDataTest() {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        User u1 = new User("aryan", "Arywn", "12345ab");
        User u2 = new User("Mrx", "MrXX", "456789");
        User u3 = new User("aryan2", "ary", "12345");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3));
        Assert.assertEquals(playerDatabase.getUsers().size(), users.size());
    }
}
