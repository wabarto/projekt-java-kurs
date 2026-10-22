import java.util.List;

public class SolidDemo {
    public static void main(String[] args) {
        List<Bird> birds = List.of(new Penguin());
        for (Bird b : birds) {
            b.eat();
        }
    }
}
