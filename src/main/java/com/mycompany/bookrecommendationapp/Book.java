public class Book {
    private String title;
    private String genre;
    private boolean myBook; // Menandai apakah buku masuk ke koleksi pribadi

    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.myBook = false;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isMyBook() {
        return myBook;
    }

    public void setMyBook(boolean myBook) {
        this.myBook = myBook;
    }
}
