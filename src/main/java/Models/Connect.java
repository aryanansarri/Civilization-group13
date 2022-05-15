package Models;

public class Connect<A,B> {
    private A first;
    private B second;

    public Connect(A a, B b) {
        this.first = a;
        this.second = b;
    }
    public void setFirst(A a) {
        this.first = a;
    }
    public A getFirst() {
        return first;
    }
    public void setSecond(B b) {
        this.second = b;
    }
    public B getSecond() {
        return second;
    }
}