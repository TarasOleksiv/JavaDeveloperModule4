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
import java.util.UUID;

/**
 * Created by t.oleksiv on 20/12/2017.
 */
public class ProductListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new HibernateProductDAOImpl();
        ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();

        if(request.getParameter("Add")!= null){  //при натисканні на кнопку Add
            Product product = new Product(); //створюєм екземпляр класу моделі бази даних
            product.withName(request.getParameter("name"))
                    .withPrice(new BigDecimal(request.getParameter("price")))
                    .withManufacturer(manufacturerDAO.getById(UUID.fromString(request.getParameter("manufacturerId"))));    //задаєм йому і'мя, ціну та виробника
            productDAO.create(product);   //створюєм новий виріб
        }

        if(request.getParameter("Delete")!= null) {  //при натисканні на кнопку Delete
            if (request.getParameter("productId") != null) {
                productDAO.delete(productDAO.getById(UUID.fromString(request.getParameter("productId"))));
            }
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
