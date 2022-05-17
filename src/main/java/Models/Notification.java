package Models;

public class Notification {
    private StringBuilder temp;

    public Notification() {
        this.temp = new StringBuilder();
    }
    public void updateNotification(String input) {
        this.temp.append(input).append("\n");
    }
    public void resetNotifiction() {
        this.temp = new StringBuilder();
    }
    public String getNotification() {
        return temp.toString();
    }

}
