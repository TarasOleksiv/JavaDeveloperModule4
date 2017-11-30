package ua.goit.java8.javadeveloper.controller;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import java.util.List;
import java.util.Scanner;

/**
 * Created by t.oleksiv on 30/11/2017.
 */
public class ManufacturerController {

    private static ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();
    private Scanner sc = new Scanner(System.in);

    public void getAll() {
        List<Manufacturer> manufacturers = manufacturerDAO.getAll();

        System.out.println("********** Виробники ************");
        if (manufacturers != null){
            for (Manufacturer manufacturer: manufacturers){
                System.out.println(manufacturer);
            }
        } else {
            System.out.println("Виробники відсутні.");
        }
        System.out.println("**********************************");
    }

    public void getByName() {
        System.out.print("Введіть назву виробника: ");
        String name = sc.nextLine().trim();
        List<Manufacturer> manufacturers = manufacturerDAO.getByName(name);

        System.out.println("********** Виробники ************");
        if (manufacturers != null){
            for (Manufacturer manufacturer: manufacturers){
                System.out.println(manufacturer);
            }
        } else {
            System.out.println("Виробники відсутні.");
        }
        System.out.println("**********************************");
    }

    public void create() {
        System.out.println("Введіть назву виробника: ");
        String name = sc.nextLine().trim();

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.withName(name);
        manufacturerDAO.create(manufacturer);
    }

    public void update() {
        System.out.print("Введіть назву виробника, якого ви хочете змінити: ");
        String nameExists = sc.nextLine().trim();
        List<Manufacturer> manufacturers = manufacturerDAO.getByName(nameExists);  //перевірка чи виробник з такою назвою існує

        if (manufacturers != null && manufacturers.size() > 0){
            System.out.println("Введіть нову назву виробника: ");
            String name = sc.nextLine().trim();
            Manufacturer manufacturer = manufacturers.get(0);
            manufacturer.withId(manufacturer.getId())
                    .withName(name);
            manufacturerDAO.update(manufacturer);
        } else {
            System.out.println("Виробник з назвою " + nameExists + " відсутній.");
        }
    }

    public void delete() {
        System.out.print("Введіть назву виробника: ");
        String nameExists = sc.nextLine().trim();
        List<Manufacturer> manufacturers = manufacturerDAO.getByName(nameExists);  //перевірка чи виробник з такою назвою існує

        if (manufacturers != null && manufacturers.size() > 0){
            manufacturerDAO.delete(manufacturers.get(0));
        } else {
            System.out.println("Виробник з назвою " + nameExists + " відсутній.");
        }
    }

    // вивести всі товари виробника
    public void getProductsByManufacturer() {
        System.out.print("Введіть назву виробника: ");
        String nameExists = sc.nextLine().trim();
        List<Manufacturer> manufacturers = manufacturerDAO.getByName(nameExists);  //перевірка чи виробник з такою назвою існує

        System.out.println("********** Товари виробника ************");
        if (manufacturers != null && manufacturers.size() > 0){
            System.out.println(manufacturers.get(0).showManufacturerProducts());
        } else {
            System.out.println("Виробник з назвою " + nameExists + " відсутній.");
        }
        System.out.println("**********************************");
    }
}
