package ua.goit.java8.javadeveloper.controller.servlet;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.ProductDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.java8.javadeveloper.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by t.oleksiv on 20/12/2017.
 */

@WebServlet("/showProducts")
public class ProductListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new HibernateProductDAOImpl();
        ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();

        if(request.getParameter("Edit")!= null) {  //при натисканні на кнопку Edit
            if (request.getParameter("productId") != null) {
                request.setAttribute("product",productDAO.getById(UUID.fromString(request.getParameter("productId"))));
                request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("productEdit.jsp");  //перескакуєм на productEdit.jsp
                requestDispatcher.forward(request,response);
            }
        }

        if(request.getParameter("Add")!= null){  //при натисканні на кнопку Add

            // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

            // підговка повідомлення.
            Map<String, String> messages = new HashMap<String, String>();
            request.setAttribute("messages", messages);

            // Отримуємо імя та перевіряєм чи воно непорожнє.
            String name = request.getParameter("name");
            if (name == null || name.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
                messages.put("name", "Please enter name");
            }

            // Перевіряєм ціну.
            String price = request.getParameter("price");
            if (price == null || price.trim().isEmpty()) {
                messages.put("price", "Please enter price");
            } else if (!price.matches("^[0-9]*[.]?[0-9]+$")) {
                messages.put("price", "Please enter digits and/or dot only");
            }

            // Якщо немає помилок, втілюєм бізнес-логіку
            if (messages.isEmpty()) {
                Product product = new Product(); //створюєм екземпляр класу моделі бази даних
                product.withName(request.getParameter("name"))
                        .withPrice(new BigDecimal(request.getParameter("price")))
                        .withManufacturer(manufacturerDAO.getById(UUID.fromString(request.getParameter("manufacturerId"))));    //задаєм йому і'мя, ціну та виробника
                productDAO.create(product);   //створюєм новий виріб
            }

        }

        if(request.getParameter("Delete")!= null) {  //при натисканні на кнопку Delete
            if (request.getParameter("productId") != null) {
                productDAO.delete(productDAO.getById(UUID.fromString(request.getParameter("productId"))));
            }
        }

        if(request.getParameter("ShowAll")!= null) {  //при натисканні на кнопку ShowAll показуєм всі вироби

        }

        if(request.getParameter("Show")!= null) {  //при натисканні на кнопку Show показуєм лише вироби вказаного виробника
            request.setAttribute("listProducts",
                    manufacturerDAO.getById(UUID.fromString(request.getParameter("manufacturerForProductId"))).getProducts());
                    //створюєм атрибут який виводить список всіх виробів вибраного виробника
            request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("productsList.jsp");  //перескакуєм на productsList.jsp
            requestDispatcher.forward(request,response);
        }

        if(request.getParameter("filterProducts")!= null) {  //редірект з виробника: показуєм лише вироби вказаного виробника
            request.setAttribute("listProducts",
                    manufacturerDAO.getById(UUID.fromString(request.getParameter("idManufacturer"))).getProducts());
            //створюєм атрибут який виводить список всіх виробів вибраного виробника
            request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("productsList.jsp");  //перескакуєм на productsList.jsp
            requestDispatcher.forward(request,response);
        }

        request.setAttribute("listProducts",productDAO.getAll());  //створюєм атрибут який виводить список всіх виробів
        request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("productsList.jsp");  //перескакуєм на productsList.jsp
        requestDispatcher.forward(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
