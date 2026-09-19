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
    public boolean equals(Object comparedObj) {
        if (this == comparedObj) {
            return false;
        }

        if (!(comparedObj instanceof SimilarityOfObjects)) {
            return false;
        }

        SimilarityOfObjects comparedBook = (SimilarityOfObjects) comparedObj;

        if (
            this.name == comparedBook.name &&
            this.published == comparedBook.published &&
            this.content == comparedBook.content
        ) {
            return true;
        }

        return false;
    }
    public  int hashCode() {
        if(this.name == null) {
            return this.published;
        }

        return this.published + this.name.hashCode();
    }
    public static void similarityComparetion() {
        SimilarityOfObjects book = new SimilarityOfObjects("Book", 10, "Test book");
        SimilarityOfObjects anotherBook = book;
        SimilarityOfObjects thirdBook = new SimilarityOfObjects("Book", 10, "Test book");

        if(book.equals(anotherBook)) {
            System.err.println("The books 1 and 2 are the same!");
        } else {
            System.err.println("The book 1 and 2 are not the same!");
        }
        if(book.equals(thirdBook)) {
            System.err.println("The Books 1 and 3 are the same!");
        }

        // anotherBook = new SimilarityOfObjects("Book", 10, "Test Book");
        // if(anotherBook.name.equals(book.name)) {
        //     System.err.println("OK");
        // }
        // if (book.equals(anotherBook)) {
        //     System.err.println("The book are the same!");
        // } else {
        //     System.err.println("The book are not the same!");
        // }
    } 

    public static void hashCodeAprox() {
        HashMap<SimilarityOfObjects, String> borrowers = new HashMap<>();
        SimilarityOfObjects bookObj = new SimilarityOfObjects("Book Object", 2000, "...");
        borrowers.put(bookObj, "pekka");
        borrowers.put(new SimilarityOfObjects("Test driven development", 1999, "..."), "Arto");

        System.err.println(borrowers.get(bookObj));
        System.err.println(borrowers.get(new SimilarityOfObjects("Book Object", 2000, "...")));
        System.err.println(borrowers.get(new SimilarityOfObjects("Test driven development", 1999, "...")));
        System.err.println(bookObj.hashCode());
    }

    public static void main(String[] args) {
        hashCodeAprox();
    }
    
}
