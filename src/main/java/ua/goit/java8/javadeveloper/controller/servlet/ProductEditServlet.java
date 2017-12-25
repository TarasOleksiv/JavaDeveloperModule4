package ua.goit.java8.javadeveloper.controller.servlet;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.ProductDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateProductDAOImpl;
import ua.goit.java8.javadeveloper.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 20.12.2017.
 */
public class ProductEditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new HibernateProductDAOImpl();
        ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();

        if(request.getParameter("Save")!= null) {  //при натисканні на кнопку Save

            // Обробка реквесту: перевіряємо введені дані і виводимо результат в тому самому JSP

            // підговка повідомлення.
            Map<String, String> messages = new HashMap<String, String>();
            request.setAttribute("messages", messages);

            // Отримуємо імя та перевіряєм чи воно непорожнє.
            String newName = request.getParameter("newName");
            if (newName == null || newName.trim().isEmpty()) {    // посилаємо повідомлення про недобре введені дані
                messages.put("newName", "Please enter name");
            }

            // Перевіряєм ціну.
            String newPrice = request.getParameter("newPrice");
            if (newPrice == null || newPrice.trim().isEmpty()) {
                messages.put("newPrice", "Please enter price");
            } else if (!newPrice.matches("^[0-9]*[.]?[0-9]+$")) {
                messages.put("newPrice", "Please enter digits and/or dot only");
            }

            if (messages.isEmpty()) {   // Якщо немає помилок, втілюєм бізнес-логіку
                Product product = new Product(); //створюєм екземпляр класу моделі бази даних
                product.withId(UUID.fromString(request.getParameter("productId")))
                        .withPrice(new BigDecimal(request.getParameter("newPrice")))
                        .withManufacturer(manufacturerDAO.getById(UUID.fromString(request.getParameter("manufacturerId"))))
                        .withName(request.getParameter("newName"));
                productDAO.update(product);   //оновлюєм виріб

                request.setAttribute("listProducts",productDAO.getAll());  //створюєм атрибут який виводить список всіх виробів
                request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("productsList.jsp");  //перескакуєм на productsList.jsp
                requestDispatcher.forward(request,response);
            } else {
                if (request.getParameter("productId") != null) {
                    request.setAttribute("product",productDAO.getById(UUID.fromString(request.getParameter("productId"))));
                    request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("productEdit.jsp");  //перескакуєм на productEdit.jsp
                    requestDispatcher.forward(request,response);
                }
            }

        }

        if(request.getParameter("Cancel")!= null) {  //при натисканні на кнопку Cancel
            request.setAttribute("listProducts",productDAO.getAll());  //створюєм атрибут який виводить список всіх виробів
            request.setAttribute("listManufacturers",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("productsList.jsp");  //перескакуєм на productsList.jsp
            requestDispatcher.forward(request,response);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
