package Daos;

import Beans.Pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PeliculaDao extends DaoBase{

    public ArrayList<Pelicula> listarPeliculas (){
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        String sql = "select * from pelicula";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt(1));
                pelicula.setNombre(rs.getString(2));
                listaPeliculas.add(pelicula);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }



}
