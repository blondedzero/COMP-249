import java.io.Serializable;

public class Book implements Serializable{
    protected String title;
    protected String author;
    protected double price;
    protected String isbn;
    protected String genre;
    protected int year;
    

    public Book( String title, String author, double price, String isbn,String genre, int year){
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }


    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public double getPrice(){
        return this.price;
    }
    public String getISBN(){
        return this.isbn;
    }

    public String getGenre(){
        return this.genre;
    }
    public int getYear(){
        return this.year;
    }

    public String setTitle(){
        return this.title;
    }
    public String setAuthor(){
        return this.author;
    }
    public double setPrice(){
        return this.price;
    }
    public String setISBN(){
        return this.isbn;
    }
    public String setGenre(){
        return this.genre;
    }
    public int setYear(){
        return this.year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return Double.compare(book.price, price) == 0 && year == book.year &&title.equals(book.title) && author.equals(book.author) &&isbn.equals(book.isbn) && genre.equals(book.genre);
    }

    @Override
    public String toString() {
        return "Book: " + "title ='" + title + '\'' + ", author ='" + author + '\'' +", price =" + price + ", isbn ='" + isbn + '\'' + ", genre ='" + genre + '\'' +", year =" + year;
    }

}

