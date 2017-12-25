package ua.goit.java8.javadeveloper.controller.servlet;

import ua.goit.java8.javadeveloper.dao.ManufacturerDAO;
import ua.goit.java8.javadeveloper.dao.hibernate.HibernateManufacturerDAOImpl;
import ua.goit.java8.javadeveloper.model.Manufacturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Taras on 19.12.2017.
 */

@WebServlet("/showManufacturers")
public class ManufacturerListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacturerDAO manufacturerDAO = new HibernateManufacturerDAOImpl();

        if(request.getParameter("Edit")!= null) {  //при натисканні на кнопку Edit
            if (request.getParameter("manufacturerId") != null) {
                request.setAttribute("manufacturer",manufacturerDAO.getById(UUID.fromString(request.getParameter("manufacturerId"))));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("manufacturerEdit.jsp");  //перескакуєм на manufacturerEdit.jsp
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

            // Якщо немає помилок, втілюєм бізнес-логіку
            if (messages.isEmpty()) {
                Manufacturer manufacturer = new Manufacturer(); //створюєм екземпляр класу моделі бази даних
                manufacturer.withName(request.getParameter("name"));    //задаєм йому і'мя
                manufacturerDAO.create(manufacturer);   //створюєм нового виробника
            }
        }

        if(request.getParameter("Delete")!= null) {  //при натисканні на кнопку Delete
            if (request.getParameter("manufacturerId") != null) {
                manufacturerDAO.delete(manufacturerDAO.getById(UUID.fromString(request.getParameter("manufacturerId"))));
            }
        }

        request.setAttribute("list",manufacturerDAO.getAll());  //створюєм атрибут який виводить список всіх виробників
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("manufacturersList.jsp");  //перескакуєм на manufacturersList.jsp
        requestDispatcher.forward(request,response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
