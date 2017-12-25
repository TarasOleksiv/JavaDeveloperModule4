package ua.goit.java8.javadeveloper.console.menu;

import ua.goit.java8.javadeveloper.console.controller.ProductController;

import java.util.Scanner;

/**
 * Created by Taras on 11.11.2017.
 */
public class ProductsMenu {

    private static ProductController productController = new ProductController();
    private Scanner sc = new Scanner(System.in);

    public ProductsMenu(){
        show();
    }

    private void show(){
        System.out.println();
        menu();

        System.out.print("Введіть символ: ");
        String n = sc.nextLine().trim();
        switch (n) {
            case "1":
                productController.getAll();
                break;
            case "2":
                productController.getByName();
                break;
            case "3":
                productController.create();
                break;
            case "4":
                productController.update();
                break;
            case "5":
                productController.delete();
                break;
            default:
                System.out.println("Повернення у Головне меню");
                return;
        }
        show();
    }

    private void menu() {
        System.out.println("Меню Товари");
        System.out.println("Які дії виконуєм? (" +
                "1 - Вивести всі товари, " +
                "2 - Вивести товар по назві, " +
                "3 - Створити товар, " +
                "4 - Оновити товар, " +
                "5 - Вилучити товар, " + "\n" +
                "інший символ - Повернутись у Головне меню)");
    }
}
