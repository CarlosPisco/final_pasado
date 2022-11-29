<%@ page import="Beans.Cartelera" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Empleado" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27/11/2022
  Time: 03:59 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Empleado empleado = (Empleado) session.getAttribute("EmpLogueo");%>
<% ArrayList<Cartelera> carteleraList = (ArrayList<Cartelera>) request.getAttribute("listaCartelera");%>
<html>
<head>
    <title>Cartelera</title>
    <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="/includes/navbar.jsp"></jsp:include>


    <% if (session.getAttribute("estado")!=null){%>
    <div class="alert alert-success" role="alert">
        <%=session.getAttribute("estado")%>
    </div>

    <%
            session.removeAttribute("estado");
        }
    %>



<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col">Cadena</th>
        <th scope="col">Cine</th>
        <th scope="col">Formato</th>
        <th scope="col">Horario</th>
        <% if(empleado.getRol().equals("gestor")){%>
            <th></th>
            <th></th>
        <%}%>
    </tr>
    </thead>

    <tbody>


        <%for(Cartelera c : carteleraList){%>
        <tr>
            <td><%=c.getCadena().getNombreComercial()%></td>
            <td><%=c.getCine().getNombre()%></td>
            <%String formato = c.getPelicula().getNombre();
                if(c.getTresD()==1){
                    formato = formato + " 3D";
                }
                if(c.getSubtitulada()==1){
                    formato = formato + " Subtitulado";
                }
                if(c.getDoblada()==1){
                    formato = formato + " Doblada";
                }
            %>

            <td><%=formato%></td>
            <td><%=c.getHorario()%></td>

            <% if(empleado.getRol().equals("gestor")){%>
            <td>
                <a href="<%=request.getContextPath()%>/CarteleraServlet?accion=editar&id=<%=c.getIdCartelera()%>">Editar</a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/CarteleraServlet?accion=guardar">Crear</a>
            </td>
            <%}%>
        </tr>
        <%}%>


    </tbody>



</table>



</body>
</html>
