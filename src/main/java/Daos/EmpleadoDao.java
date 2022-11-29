package Daos;

import Beans.Cine;
import Beans.Empleado;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class EmpleadoDao extends DaoBase {


    public ArrayList<Empleado> listarEmpleados (){

        ArrayList<Empleado> listaEmpleados = new ArrayList<>();

        String sql = "select *\n" +
                "from empleado e\n" +
                "inner join rolempleado re on re.idempleado = e.idempleado\n" +
                "inner join rol r on r.idrol = re.idrol ";

        try(Connection conn = getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql)) {

            while(rs.next()){
                Empleado e = new Empleado();
                e.setIdEmpleado(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setApellido(rs.getString(3));
                e.setDni(rs.getString(4));
                e.setSalario(rs.getBigDecimal(5));
                e.setFechaContrato(rs.getString(6));
                e.setNombreUsuario(rs.getString(7));
                e.setEdad(rs.getInt(8));
                e.setActivo(rs.getBoolean(9));
                Cine cine = new Cine();
                cine.setIdCine(rs.getInt(10));
                e.setCine(cine);
                Empleado jefe = new Empleado();
                jefe.setIdEmpleado(rs.getInt(11));
                e.setJefe(jefe);
                e.setRol(rs.getString(15));
                listaEmpleados.add(e);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEmpleados;
    }



    public Empleado loginEmpleado(String dni, String password) {
        Empleado e = null;
        ArrayList<Empleado> listaEmpleados = listarEmpleados();
        String sql = "SELECT * FROM empleado where dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);


            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    for(Empleado e1 : listaEmpleados){
                        if(e1.getDni()!=null && e1.getSalario()!=null){
                            if(Integer.parseInt(e1.getDni()) - e1.getSalario().intValue() == Integer.parseInt(password)){
                                e = e1;
                                break;

                            }
                        }

                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return e;

    }
}
