package ua.goit.java8.javadeveloper.controller.servlet;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by t.oleksiv on 20/12/2017.
 */
public class ManufacturerEditServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();

        if(request.getParameter("Save")!= null) {  //при натисканні на кнопку Save
            Manufacturer manufacturer = new Manufacturer(); //створюєм екземпляр класу моделі бази даних
            manufacturer.withId(UUID.fromString(request.getParameter("manufacturerId")))
                    .withName(request.getParameter("newName"));
            manufacturerDAO.update(manufacturer);   //оновлюєм виробника

            request.setAttribute("list",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("manufacturersList.jsp");  //перескакуєм на manufacturersList.jsp
            requestDispatcher.forward(request,response);
        }

        if(request.getParameter("Cancel")!= null) {  //при натисканні на кнопку Cancell
            request.setAttribute("list",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("manufacturersList.jsp");  //перескакуєм на manufacturersList.jsp
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
