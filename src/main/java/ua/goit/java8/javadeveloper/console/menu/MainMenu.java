package ua.goit.java8.javadeveloper.console.menu;

import java.util.Scanner;

/**
 * Created by t.oleksiv on 09/11/2017.
 */
public class MainMenu {
    private Scanner sc = new Scanner(System.in);

    public MainMenu(){
        show();
    }

    private void show(){
        System.out.println();
        System.out.println("Головне меню.");
        System.out.println("З яким об'єктом працюєм? (" +
                "1 - Виробники, " +
                "2 - Товари, " +
                "інший символ - Вихід з програми)");
        System.out.print("Введіть символ: ");
        String n = sc.nextLine().trim();
        switch (n) {
            case "1":   //Manufacturers
                new ManufacturersMenu();
                break;
            case "2":   //Products
                new ProductsMenu();
                break;
            default:
                System.out.println("Ви закінчили виконання програми.");
                return;
        }
        show();
    }
}
