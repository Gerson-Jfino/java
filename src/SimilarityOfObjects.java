import java.util.HashMap;

public class SimilarityOfObjects {
    private String name;
    private  int published;
    private String content;
    public SimilarityOfObjects(String name, int published, String content) {
        this.name = name;
        this.published = published;
        this.content = content;
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

    public static void main(String[] args) {
        SimilarityOfObjects book = new SimilarityOfObjects("Book", 10, "Test book");
        SimilarityOfObjects anotherBook = book;

        if(book.equals(anotherBook)) {
            System.err.println("The books are the same!");
        } else {
            System.err.println("The book are not the same!");
        }

        anotherBook = new SimilarityOfObjects("Book", 10, "Test Book");
        if(anotherBook.name.equals(book.name)) {
            System.err.println("OK");
        }
        if (book.equals(anotherBook)) {
            System.err.println("The book are the same!");
        } else {
            System.err.println("The book are not the same!");
        }
    }
    
}
