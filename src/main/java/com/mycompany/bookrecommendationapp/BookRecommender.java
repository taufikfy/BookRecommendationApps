import java.util.ArrayList;
import java.util.List;

public class BookRecommender {
    private BookRepository repository;

    public BookRecommender(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> recommendBooks(String genre) {
        List<Book> recommendedBooks = new ArrayList<>();
        for (Book book : repository.getBooks()) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                recommendedBooks.add(book);
            }
        }
        return recommendedBooks;
    }
}
