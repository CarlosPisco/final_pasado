package Daos;

import Beans.Cadena;
import Beans.Cartelera;
import Beans.Cine;
import Beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;

public class CarteleraDao extends DaoBase{

    public ArrayList<Cartelera> listarCartelera (){
        ArrayList<Cartelera> listaCartelera = new ArrayList<>();


        String sql = "select ct.idCartelera,cd.nombre_comercial as `cadena`, c.nombre as `cine`, p.nombre as `nombre peli`, 3d, ct.doblada, ct.subtitulada, ct.horario\n" +
                "from cartelera ct\n" +
                "inner join pelicula p on p.idpelicula = ct.idpelicula\n" +
                "inner join cine c on c.idcine = ct.idcine\n" +
                "inner join  cadena cd on cd.idcadena = c.idcadena";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                Cartelera cartelera = new Cartelera();
                cartelera.setIdCartelera(rs.getInt(1));
                Cadena cadena = new Cadena();
                cadena.setNombreComercial(rs.getString(2));
                cartelera.setCadena(cadena);
                Cine cine = new Cine();
                cine.setNombre(rs.getString(3));
                cartelera.setCine(cine);
                Pelicula pelicula = new Pelicula();
                pelicula.setNombre(rs.getString(4));
                cartelera.setPelicula(pelicula);
                cartelera.setTresD(rs.getInt(5));
                cartelera.setDoblada(rs.getInt(6));
                cartelera.setSubtitulada(rs.getInt(7));
                cartelera.setHorario(rs.getString(8));
                listaCartelera.add(cartelera);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCartelera;
    }

    public Cartelera obtenerCartelera (int idCartelera){
        Cartelera cartelera = null;

        String sql = "select cd.nombre_comercial as `cadena`, c.nombre as `cine`, p.nombre as `nombre peli`, 3d, ct.doblada, ct.subtitulada, ct.horario\n" +
                "                from cartelera ct\n" +
                "                inner join pelicula p on p.idpelicula = ct.idpelicula\n" +
                "                inner join cine c on c.idcine = ct.idcine\n" +
                "                inner join  cadena cd on cd.idcadena = c.idcadena\n" +
                "                where idCartelera = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)){

            pstm.setInt(1,idCartelera);

            try(ResultSet rs = pstm.executeQuery()){
                if(rs.next()){
                    cartelera = new Cartelera();

                    Cadena cadena = new Cadena();
                    cadena.setNombreComercial(rs.getString(1));
                    cartelera.setCadena(cadena);
                    Cine cine = new Cine();
                    cine.setNombre(rs.getString(2));
                    cartelera.setCine(cine);
                    Pelicula pelicula = new Pelicula();
                    pelicula.setNombre(rs.getString(3));
                    cartelera.setPelicula(pelicula);
                    cartelera.setTresD(rs.getInt(4));
                    cartelera.setDoblada(rs.getInt(5));
                    cartelera.setSubtitulada(rs.getInt(6));
                    cartelera.setHorario(rs.getString(7));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cartelera;
    }


    public void actualizarCartelera (Cartelera cartelera){

        String sql = "update cartelera set idpelicula=?, idcine= ?, 3d=?, doblada=?, subtitulada=? where idcartelera=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,cartelera.getPelicula().getIdPelicula());
            pstmt.setInt(2,cartelera.getCine().getIdCine());
            pstmt.setInt(3,cartelera.getTresD());
            pstmt.setInt(4,cartelera.getDoblada());
            pstmt.setInt(5,cartelera.getSubtitulada());

            pstmt.setInt(6,cartelera.getIdCartelera());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void guardarCartelera (Cartelera cartelera){

        String sql = "insert into cartelera (idpelicula,idcine,3d,doblada,subtitulada) values (?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,cartelera.getPelicula().getIdPelicula());
            pstmt.setInt(2,cartelera.getCine().getIdCine());
            pstmt.setInt(3,cartelera.getTresD());
            pstmt.setInt(4,cartelera.getDoblada());
            pstmt.setInt(5,cartelera.getSubtitulada());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
