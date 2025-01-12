import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books;
    private static final String FILE_NAME = "books.txt";

    public BookRepository() {
        books = new ArrayList<>();
        loadBooksFromFile(); // Muat data buku dari file saat aplikasi dimulai
        if (books.isEmpty()) {
            initializeBooks(); // Inisialisasi default jika file kosong
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToFile(); // Simpan data buku ke file setiap kali ada buku baru
    }

    public List<Book> getBooks() {
        return books;
    }

    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.getTitle() + ";" + book.getGenre());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gagal menyimpan buku ke file.");
        }
    }

    private void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    books.add(new Book(parts[0], parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            // Jika file tidak ditemukan, buat file baru saat diperlukan
            System.out.println("File buku tidak ditemukan. Membuat file baru.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Gagal memuat buku dari file.");
        }
    }

    private void initializeBooks() {
        books.add(new Book("The Shining", "Horror"));
        books.add(new Book("It", "Horror"));
        books.add(new Book("Dracula", "Horror"));
        books.add(new Book("The Haunting of Hill House", "Horror"));
        books.add(new Book("Frankenstein", "Horror"));

        books.add(new Book("The Hobbit", "Fantasy"));
        books.add(new Book("Harry Potter", "Fantasy"));
        books.add(new Book("The Lord of the Rings", "Fantasy"));
        books.add(new Book("The Witcher", "Fantasy"));
        books.add(new Book("Percy Jackson", "Fantasy"));

        books.add(new Book("Sherlock Holmes", "Mystery"));
        books.add(new Book("Gone Girl", "Mystery"));
        books.add(new Book("The Girl with the Dragon Tattoo", "Mystery"));
        books.add(new Book("Big Little Lies", "Mystery"));
        books.add(new Book("The Da Vinci Code", "Mystery"));

        books.add(new Book("Pride and Prejudice", "Romance"));
        books.add(new Book("The Notebook", "Romance"));
        books.add(new Book("Me Before You", "Romance"));
        books.add(new Book("Twilight", "Romance"));
        books.add(new Book("Outlander", "Romance"));

        books.add(new Book("The Adventures of Huckleberry Finn", "Adventure"));
        books.add(new Book("Treasure Island", "Adventure"));
        books.add(new Book("The Call of the Wild", "Adventure"));
        books.add(new Book("Into the Wild", "Adventure"));
        books.add(new Book("Life of Pi", "Adventure"));

        books.add(new Book("Sapiens: A Brief History of Humankind", "History"));
        books.add(new Book("Guns, Germs, and Steel", "History"));
        books.add(new Book("The Silk Roads", "History"));
        books.add(new Book("The Diary of a Young Girl", "History"));
        books.add(new Book("A People's History of the United States", "History"));

        saveBooksToFile();
    }

    public List<Book> getMyBooks() {
        List<Book> myBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isMyBook()) {
                myBooks.add(book);
            }
        }
        return myBooks;
    }
}
