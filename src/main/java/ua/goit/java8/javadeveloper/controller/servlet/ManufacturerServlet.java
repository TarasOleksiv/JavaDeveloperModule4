package ua.goit.java8.javadeveloper.controller.servlet;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Taras on 19.12.2017.
 */
public class ManufacturerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();
        request.setAttribute("list",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("manufacturersList.jsp");  //перескакуєм на manufacturersList.jsp
        requestDispatcher.forward(request,response);
    }
}
