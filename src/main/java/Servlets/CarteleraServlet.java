package Servlets;

import Beans.Cadena;
import Beans.Cartelera;
import Beans.Cine;
import Beans.Pelicula;
import Daos.CarteleraDao;
import Daos.CineDao;
import Daos.PeliculaDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(name = "CarteleraServlet", value = "/CarteleraServlet")
public class CarteleraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion") == null ? "lista" : request.getParameter("accion");
        CarteleraDao cdao = new CarteleraDao();
        CineDao cineDao = new CineDao();
        PeliculaDao peliculaDao = new PeliculaDao();
        ArrayList<Cartelera> listaCartelera = cdao.listarCartelera();
        RequestDispatcher view;

        HttpSession session = request.getSession();
        if(session.getAttribute("EmpLogueo")==null){
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
        }else{
            switch (accion){

                case "lista":

                    request.setAttribute("listaCartelera",listaCartelera);
                    view = request.getRequestDispatcher("cartelera.jsp");
                    view.forward(request, response);

                    break;

                case "editar":

                    String id = request.getParameter("id");
                    Cartelera cartelera = cdao.obtenerCartelera(Integer.parseInt(id));

                    request.setAttribute("cartelera",cartelera);
                    ArrayList<Cine> listaCines = cineDao.listaCines();
                    request.setAttribute("listaCines",listaCines);
                    ArrayList<Pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                    request.setAttribute("listaPeliculas",listaPeliculas);

                    view = request.getRequestDispatcher("editarcartelera.jsp");
                    view.forward(request, response);
                    break;

                case "guardar":
                    ArrayList<Cine> listaCines1 = cineDao.listaCines();
                    request.setAttribute("listaCines",listaCines1);
                    ArrayList<Pelicula> listaPeliculas1 = peliculaDao.listarPeliculas();
                    request.setAttribute("listaPeliculas",listaPeliculas1);

                    view = request.getRequestDispatcher("editarcartelera.jsp");
                    view.forward(request, response);
                    break;


            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarteleraDao cdao = new CarteleraDao();
        String idCartelera = request.getParameter("idCartelera");
        HttpSession session = request.getSession();

        Cartelera cartelera = new Cartelera();

        Cine cine = new Cine();

        cine.setIdCine(Integer.parseInt(request.getParameter("cine")));
        cartelera.setCine(cine);




        Pelicula pelicula = new Pelicula();
        pelicula.setIdPelicula(Integer.parseInt(request.getParameter("pelicula")));
        cartelera.setPelicula(pelicula);

        cartelera.setTresD(Integer.parseInt(request.getParameter("tresd")));
        cartelera.setDoblada(Integer.parseInt(request.getParameter("doblada")));
        cartelera.setSubtitulada(Integer.parseInt(request.getParameter("subtitulada")));

        if(idCartelera!=null){
            cartelera.setIdCartelera(Integer.parseInt(idCartelera));
            cdao.actualizarCartelera(cartelera);
            session.setAttribute("estado","cartelera actualizada correctamente");
        }else{
            cdao.guardarCartelera(cartelera);
            session.setAttribute("estado","cartelera añadida correctamente");
        }

        response.sendRedirect(request.getContextPath()+"/CarteleraServlet");
    }
}
