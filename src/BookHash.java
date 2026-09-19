import java.util.HashMap;


public class BookHash {
    private String name;
    private String content;
    private int published;

    public BookHash(String name, int published, String content) {
        this.name = name;
        this.content = content;
        this.published = published;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPublished() {
        return published;
    }
    public void setPublished(int published) {
        this.published = published;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", content:'" + content + '\'' +
                ", published:" + published +
                '}';
    }
    public static BookHash findBook(HashMap<String, BookHash> directory, String name) {
        if (directory.containsKey(name)) {
            return directory.get(name);
        }
        return null;
    }
    public static void main(String[] args) {
        
        BookHash senseAndSensibility = new BookHash("Sense and Sensibility", 1811, "A novel by Jane Austen.");
        BookHash prideAndPrejudice = new BookHash("Pride and Prejudice", 1813, "A novel by Jane Austen.");
        HashMap<String, BookHash> directory = new HashMap<>();
        directory.put(senseAndSensibility.getName(), senseAndSensibility);
        directory.put(prideAndPrejudice.getName(), prideAndPrejudice);
        BookHash book = findBook(directory, "Sense and Sensibility");
        System.out.println(book);
        if(directory.containsKey("Sense and Sensibility")) {
            directory.remove("Sense and Sensibility");
        }
        System.out.println(directory);
        // System.out.println();
        // System.out.println(directory.get("Pride and Prejudice"));

        
    }


}
