package ua.goit.java8.javadeveloper.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

/**
 * Created by t.oleksiv on 30/11/2017.
 */

@Entity
@Table(name = "manufacturers")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "manufacturerCache")
public class Manufacturer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer", fetch = FetchType.EAGER)
    private Set<Product> products;

    public Manufacturer(){

    }

    public Manufacturer(String name){
        this.name = name;
    }

    public Manufacturer withId(UUID id){
        this.id = id;
        return this;
    }

    public Manufacturer withName(String name){
        this.name = name;
        return this;
    }

    @Override
    public String toString(){
        return "Manufacturer{" +
                "id=" + id.toString() +
                ", name='" + name + '\'' +
                "}";
    }

    private String showProducts(){
        String result = "";
        for (Product product: products){
            result += product + ",\n    ";
        }
        return result;
    }

    public String showManufacturerProducts(){
        return "Manufacturer{" +
                "id=" +id + " " +
                "name=" + name + ";\n    " +
                "{Products:\n    " +
                showProducts() +
                "}}";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
