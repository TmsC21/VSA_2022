package sk.stuba.fei.uim.vsa.zapa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private double price;

    public Publication(String isbn, Double price, String name) {
        this.isbn = isbn;
        this.price = price;
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
