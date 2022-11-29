package Servlets;

import Beans.Empleado;
import Daos.EmpleadoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;
        String accion = request.getParameter("accion") == null ? "login" : request.getParameter("accion");
        RequestDispatcher view;

        switch (accion){

            case "login":
                session = request.getSession();
                if (session.getAttribute("EmpLogueo") == null) {
                    view = request.getRequestDispatcher("/index.jsp");
                    view.forward(request, response);
                } else {
                    Empleado empleado = (Empleado) session.getAttribute("EmpLogueo");
                    if (empleado.getRol().equals("gestor") || empleado.getRol().equals("vendedor")){ //compara mayu y minu
                        response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                    } else if (empleado.getRol().equals("admin")){
                        response.sendRedirect(request.getContextPath() + "/ReportesServlet");
                    }
                }
                break;
            case "logout":
                session = request.getSession();

                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/LoginServlet");
                break;



        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String dni = request.getParameter("dni");
        String password = request.getParameter("password");
        EmpleadoDao empleadoDao = new EmpleadoDao();
        HttpSession session = request.getSession();

        Empleado empleado = empleadoDao.loginEmpleado(dni,password);



        if(empleado!=null){
            System.out.println("hola");
            session.setAttribute("EmpLogueo",empleado);
            //session.setMaxInactiveInterval(10*60);

            if(empleado.getRol().equals("gestor") || empleado.getRol().equals("vendedor")){
                response.sendRedirect(request.getContextPath() + "/CarteleraServlet");

            }else if (empleado.getRol().equals("admin")){
                response.sendRedirect(request.getContextPath() + "/ReportesServlet");

            }



        }else {

            session.setAttribute("msg","La credenciales son incorrectas");
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request,response);

        }


    }
}
