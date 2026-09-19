import java.util.HashMap;

public class Hash {
    public static void main(String[] args) {
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 30);
        ages.put("Bob", 25);
        ages.put("Bob", 26);

        Integer ageOfBob = ages.get("Bob");
        System.out.println("Age of Bob: " + ageOfBob);
    }
}
