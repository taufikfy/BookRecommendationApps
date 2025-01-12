import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JFrame frame;
    private JComboBox<String> genreDropdown;
    private JTextArea bookListArea;
    private JTextArea myBookListArea;
    private JTextField titleField;
    private JButton addButton;
    private JButton recommendButton;
    private JCheckBox myBookCheckbox;

    private final BookRepository bookRepository;

    public MainGUI() {
        bookRepository = new BookRepository();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Book Recommender");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel atas untuk input
        JPanel topPanel = new JPanel(new GridLayout(2, 3));
        JLabel titleLabel = new JLabel("Book Title:");
        titleField = new JTextField();
        genreDropdown = new JComboBox<>(new String[]{"Horror", "Fantasy", "Mystery", "Romance", "Adventure", "History"});
        myBookCheckbox = new JCheckBox("Add to My Books");

        addButton = new JButton("Add Book");
        recommendButton = new JButton("Recommend Books");

        topPanel.add(titleLabel);
        topPanel.add(titleField);
        topPanel.add(genreDropdown);
        topPanel.add(myBookCheckbox);
        topPanel.add(addButton);
        topPanel.add(recommendButton);

        // Panel tengah untuk daftar buku
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        myBookListArea = new JTextArea();
        myBookListArea.setEditable(false);

        centerPanel.add(new JScrollPane(bookListArea));
        centerPanel.add(new JScrollPane(myBookListArea));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Tombol aksi
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String genre = (String) genreDropdown.getSelectedItem();
                boolean addToMyBooks = myBookCheckbox.isSelected();

                if (!title.isEmpty() && genre != null) {
                    Book book = new Book(title, genre);
                    book.setMyBook(addToMyBooks);
                    bookRepository.addBook(book);
                    updateBookList();
                    if (addToMyBooks) {
                        updateMyBookList();
                    }
                }
            }
        });

        recommendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genre = (String) genreDropdown.getSelectedItem();
                if (genre != null) {
                    var books = bookRepository.getBooks();
                    StringBuilder recommendations = new StringBuilder("Recommendations for " + genre + ":\n");
                    for (Book book : books) {
                        if (book.getGenre().equalsIgnoreCase(genre)) {
                            recommendations.append("- ").append(book.getTitle());
                            if (book.isMyBook()) {
                                recommendations.append(" (My Book)");
                            }
                            recommendations.append("\n");
                        }
                    }
                    bookListArea.setText(recommendations.toString());
                }
            }
        });

        updateBookList();
        updateMyBookList();
        frame.setVisible(true);
    }

    private void updateBookList() {
        StringBuilder allBooks = new StringBuilder("All Books:\n");
        for (Book book : bookRepository.getBooks()) {
            allBooks.append("- ").append(book.getTitle()).append(" [").append(book.getGenre()).append("]");
            if (book.isMyBook()) {
                allBooks.append(" (My Book)");
            }
            allBooks.append("\n");
        }
        bookListArea.setText(allBooks.toString());
    }

    private void updateMyBookList() {
        StringBuilder myBooks = new StringBuilder("My Books:\n");
        for (Book book : bookRepository.getMyBooks()) {
            myBooks.append("- ").append(book.getTitle()).append(" [").append(book.getGenre()).append("]\n");
        }
        myBookListArea.setText(myBooks.toString());
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
