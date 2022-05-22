package ModelsTest;

import Models.Notification;
import org.junit.Test;

public class NotificatonsTest {
    private Notification notification = new Notification();

    @Test
    public void Test() {
        notification.resetNotifiction();
        notification.updateNotification("hello");
        notification.getNotification();
    }
}
