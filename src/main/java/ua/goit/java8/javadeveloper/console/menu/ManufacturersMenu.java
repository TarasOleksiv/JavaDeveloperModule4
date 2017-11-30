package ua.goit.java8.javadeveloper.console.menu;

import ua.goit.java8.javadeveloper.controller.ManufacturerController;

import java.util.Scanner;

/**
 * Created by Taras on 11.11.2017.
 */
public class ManufacturersMenu {

    private static ManufacturerController manufacturerController = new ManufacturerController();
    private Scanner sc = new Scanner(System.in);

    public ManufacturersMenu(){
        show();
    }

    private void show(){
        System.out.println();
        menu();

        System.out.print("Введіть символ: ");
        String n = sc.nextLine().trim();
        switch (n) {
            case "1":
                manufacturerController.getAll();
                break;
            case "2":
                manufacturerController.getByName();
                break;
            case "3":
                manufacturerController.create();
                break;
            case "4":
                manufacturerController.update();
                break;
            case "5":
                manufacturerController.delete();
                break;
            case "6":
                manufacturerController.getProductsByManufacturer();
                break;
            default:
                System.out.println("Повернення у Головне меню");
                return;
        }
        show();
    }

    private void menu() {
        System.out.println("Меню Виробники");
        System.out.println("Які дії виконуєм? (" +
                "1 - Вивести всіх виробників, " +
                "2 - Вивести виробника по назві, " +
                "3 - Створити виробника, " +
                "4 - Оновити виробника, " +
                "5 - Вилучити виробника, " + "\n" +
                "6 - Вивести всі товари виробника, " +
                "інший символ - Повернутись у Головне меню)");
    }
}
