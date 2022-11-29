package Daos;

import Beans.Cine;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

public class CineDao extends DaoBase {

    public ArrayList<Cine> listaCines (){

        ArrayList<Cine> listaCines = new ArrayList<>();

        String sql = "select * from cine";

        try(Connection conn = getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {

            while(rs.next()){
                Cine cine = new Cine();
                cine.setIdCine(rs.getInt(1));
                cine.setNombre(rs.getString(2));

                listaCines.add(cine);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCines;
    }


}
