package ua.goit.java8.javadeveloper.console.controller;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.ProductDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.java8.javadeveloper.model.Manufacturer;
import ua.goit.java8.javadeveloper.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Created by t.oleksiv on 30/11/2017.
 */
public class ProductController {

    private static ProductDAO productDAO = new HibernateProductDAOImpl();
    private static ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();
    private Scanner sc = new Scanner(System.in);

    public void getAll() {
        List<Product> products = productDAO.getAll();

        System.out.println("********** Товари ************");
        if (products != null){
            for (Product product: products){
                System.out.println(product);
            }
        } else {
            System.out.println("Товари відсутні.");
        }
        System.out.println("**********************************");
    }

    public void getByName() {
        System.out.print("Введіть назву товару: ");
        String name = sc.nextLine().trim();
        List<Product> products = productDAO.getByName(name);

        System.out.println("********** Товари ************");
        if (products != null){
            for (Product product: products){
                System.out.println(product);
            }
        } else {
            System.out.println("Товари відсутні.");
        }
        System.out.println("**********************************");
    }

    public void create() {
        System.out.println("Введіть через крапку з комою наступні значення: ");
        System.out.println("Назва Товару;Назва Виробника;Ціна");
        String delims = "[;]";
        String[] line;
        line = sc.nextLine().split(delims);
        String nameProduct = line[0];
        String nameManufacturer = line[1];
        BigDecimal price = new BigDecimal(line[2]);

        List<Manufacturer> manufacturers = manufacturerDAO.getByName(nameManufacturer);
        // перевіряєм чи виробник існує
        if (manufacturers == null || manufacturers.size() == 0){
            System.out.println("Виробник з назвою " + nameManufacturer + " відсутній.");
            return;
        }

        Manufacturer manufacturer = manufacturers.get(0);
        Product product = new Product();
        product.withName(nameProduct)
                .withManufacturer(manufacturer)
                .withPrice(price);
        productDAO.create(product);
    }

    public void update() {
        System.out.print("Введіть назву товару, який ви хочете змінити: ");
        String nameExists = sc.nextLine().trim();
        List<Product> products = productDAO.getByName(nameExists);  //перевірка чи товар з такою назвою існує

        if (products != null && products.size() > 0){
            System.out.println("Введіть через крапку з комою наступні значення: ");
            System.out.println("Назва Товару;Назва Виробника;Ціна");
            String delims = "[;]";
            String[] line;
            line = sc.nextLine().split(delims);
            String nameProduct = line[0];
            String nameManufacturer = line[1];
            BigDecimal price = new BigDecimal(line[2]);

            List<Manufacturer> manufacturers = manufacturerDAO.getByName(nameManufacturer);
            // перевіряєм чи виробник існує
            if (manufacturers == null || manufacturers.size() == 0){
                System.out.println("Виробник з назвою " + nameManufacturer + " відсутній.");
                return;
            }

            Manufacturer manufacturer = manufacturers.get(0);

            Product product = products.get(0);

            product.withId(product.getId())
                    .withName(nameProduct)
                    .withManufacturer(manufacturer)
                    .withPrice(price);
            productDAO.update(product);
        } else {
            System.out.println("Товар з назвою " + nameExists + " відсутній.");
        }
    }

    public void delete() {
        System.out.print("Введіть назву товару: ");
        String nameExists = sc.nextLine().trim();
        List<Product> products = productDAO.getByName(nameExists);  //перевірка чи товар з такою назвою існує

        if (products != null && products.size() > 0){
            productDAO.delete(products.get(0));
        } else {
            System.out.println("Товар з назвою " + nameExists + " відсутній.");
        }
    }

}
