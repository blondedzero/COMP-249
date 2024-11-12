
public class Book {
    protected String title;
    protected String author;
    protected String price;
    protected String isbn;
    protected String year;
    

    public Book( String title, String author, String price, String isbn, String year){
        this.title = title;
        this.author = author;
        this.price=price;
        this.isbn = isbn;
        this.year = year;
    }

    public Book(String title2, String authors, double price2, String isbn2, String genre, int year2) {
        //TODO Auto-generated constructor stub
    }

    public String getTitle(){
        return this.title;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getPrice(){
        return this.price;
    }
    public String getISBN(){
        return this.isbn;
    }
    public String getYear(){
        return this.year;
    }

    public String setTitle(){
        return this.title;
    }
    public String setAuthor(){
        return this.author;
    }
    public String setPrice(){
        return this.price;
    }
    public String setISBN(){
        return this.isbn;
    }
    public String setYear(){
        return this.year;
    }
}
