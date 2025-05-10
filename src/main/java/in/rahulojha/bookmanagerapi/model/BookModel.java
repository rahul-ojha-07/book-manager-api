package in.rahulojha.bookmanagerapi.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import in.rahulojha.bookmanagerapi.entity.Book;

@Data
@NoArgsConstructor
public class BookModel {
    private Long id;
    private String title;
    private String author;
    private Long isbn;
    private String publicationYear;


    public BookModel(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
    }

    public Book toEntity() {
        Book book = new Book();
        book.setId(this.id);
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setIsbn(this.isbn);
        book.setPublicationYear(this.publicationYear);
        return book;
    }

    public static BookModel fromEntity(Book book) {
        return new BookModel(book);
    }
}