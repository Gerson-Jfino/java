# Hashing and object comparison

This note explains the code in `src/BookHash.java` and
`src/SimilarityOfObjects.java`.

## 1. `BookHash`: using a `HashMap` with a string key

`BookHash` represents a book with three fields:

- `name`: the book title
- `published`: the publication year
- `content`: a short description of the book

The constructor receives these values and stores them in the object. The
getters read the fields, and the setters replace their values. The `toString`
method creates a readable text representation of a book, which is why printing
a `BookHash` object displays its field values instead of a memory address.

### `findBook`

```java
public static BookHash findBook(HashMap<String, BookHash> directory, String name)
```

This method searches a map whose:

- key is a `String` containing the book name
- value is a `BookHash` object

First, `containsKey(name)` checks whether the requested title is present. If it
is present, `get(name)` returns the corresponding book. If it is absent, the
method returns `null`.

The map uses the hash of the string key to find the appropriate bucket quickly.
In normal conditions, `containsKey` and `get` take constant average time,
$O(1)$. The map does not use the `BookHash` object's fields for this lookup;
only the title stored as the key matters.

### `main`

The example creates two books and an empty `HashMap`. Each book is inserted
using its title as the key:

```java
directory.put(senseAndSensibility.getName(), senseAndSensibility);
directory.put(prideAndPrejudice.getName(), prideAndPrejudice);
```

`findBook` then retrieves *Sense and Sensibility*. Afterwards,
`containsKey` checks whether that title exists and `remove` deletes the entry.
The final print shows the map with only the remaining book.

## 2. `SimilarityOfObjects`: comparing objects and generating hash codes

This class has the same kind of book data, but it uses the object itself as a
`HashMap` key. For that to work correctly, a class normally needs compatible
implementations of `equals` and `hashCode`.

### `equals`

The method is intended to decide whether two `SimilarityOfObjects` instances
represent the same book.

1. `this == comparedObj` checks whether both variables refer to the exact same
	 object.
2. `instanceof` rejects `null` and objects of another type.
3. The cast allows the other object's fields to be read.
4. The name, publication year, and content are compared.

However, the current implementation has two problems:

- `if (this == comparedObj) { return false; }` makes an object unequal to
	itself. The usual behavior should be `return true` for the same reference.
- `this.name == comparedBook.name` and the equivalent content comparison use
	`==`. For strings, `==` compares references, not text. Two separately created
	strings containing the same characters can therefore compare as different.
	`Objects.equals(this.name, comparedBook.name)` should be used for value
	comparison instead.

Because of these issues, `book.equals(anotherBook)` in
`similarityComparetion` is false even though both variables refer to the same
object, and `book.equals(thirdBook)` is usually false even though all three
field values appear identical.

### `hashCode`

The current method returns:

```java
published + name.hashCode()
```

If `name` is `null`, it returns `published` instead, avoiding a
`NullPointerException`. The result is an integer used by `HashMap` to choose a
bucket. Objects with the same hash code are still allowed to be different;
`HashMap` uses `equals` as the final comparison inside a bucket.

The method includes the publication year and name, but not `content`. That is
allowed only if the equality definition also ignores content. If `equals` is
fixed to compare content, the hash code must include content as well, or two
equal objects could have different hash codes. A typical implementation is:

```java
return Objects.hash(name, published, content);
```

with `import java.util.Objects;`.

### `similarityComparetion`

This method creates three objects:

- `book` is the original object.
- `anotherBook = book` points to the same object, so it is an alias.
- `thirdBook` is a different object with the same visible field values.

The method demonstrates the difference between object identity and object
value equality. With a correct `equals` implementation, the first and second
objects would be equal because they are the same reference, and the first and
third would also be equal because their fields have the same values.

### `hashCodeAprox`

This method uses `SimilarityOfObjects` objects as keys in a
`HashMap<SimilarityOfObjects, String>`:

```java
borrowers.put(bookObj, "pekka");
```

Looking up with `borrowers.get(bookObj)` works because it uses the exact key
object that was inserted. Looking up with a newly created object depends on
both `hashCode` and `equals`. With the current `equals` implementation, the
new book objects are not reliably considered equal to the inserted key, so
those lookups can return `null` even when their field values match.

The last line prints the hash code of `bookObj`, which is useful for observing
the integer generated from its name and publication year.

## Main lesson

When an object is used as a `HashMap` key:

1. `hashCode` narrows the search to a bucket.
2. `equals` identifies the matching key in that bucket.
3. Every pair of objects that are equal must return the same hash code.

For this example, the comparison methods should use the same fields and
`Objects.equals`/`Objects.hash` so that object lookup is predictable.
