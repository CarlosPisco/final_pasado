<%@ page import="Beans.Cartelera" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Beans.Cine" %>
<%@ page import="Beans.Pelicula" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27/11/2022
  Time: 11:15 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Cartelera cartelera = (Cartelera) request.getAttribute("cartelera");
  ArrayList<Cine> listaCines = (ArrayList<Cine>) request.getAttribute("listaCines");
  ArrayList<Pelicula> listaPeliculas = (ArrayList<Pelicula>) request.getAttribute("listaPeliculas");
%>

<html>
<head>
  <title>Title</title>
  <jsp:include page="/includes/bootstrap.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"></jsp:include>
<div class="container">
  <h1><%=cartelera == null ? "Nueva cartelera" : "Editar cartelera"%>
  </h1>
  <form action="<%=request.getContextPath()%>/CarteleraServlet" method="post"
        style="width:500px; margin-left:auto; margin-right:auto">
    <%if (cartelera != null) {%>
    <input type="text" class="form-control" name="idCartelera" value="<%=cartelera.getIdCartelera()%>" hidden>
    <%}%>
    <div class="mb-3">
      <label class="form-label">Cine</label>

      <select class="form-select" aria-label="Default select example" name="cine">
        <%for (Cine cine : listaCines) {%>
        <option value="<%=cine.getIdCine()%>"
                <%=cartelera!=null?(cartelera.getCine().getIdCine() == (cine.getIdCine())?"selected":""):""%>
        ><%=cine.getNombre()%>
        </option>
        <%}%>>
      </select>

    </div>
    <div class="mb-3">
      <label class="form-label">Nombre Peli</label>
      <select class="form-select" aria-label="Default select example" name="pelicula">
        <%for (Pelicula pelicula : listaPeliculas) {%>
        <option value="<%=pelicula.getIdPelicula()%>"
                <%=cartelera!=null?(cartelera.getPelicula().getIdPelicula() == (pelicula.getIdPelicula())?"selected":""):""%>
        ><%=pelicula.getNombre()%>
        </option>
        <%}%>>
      </select>
    </div>

    <div class="mb-3">
      <label class="form-label">3D</label>

      <select class="form-select" aria-label="Default select example" name="tresd">
        <option value="1"<%=cartelera!=null?(cartelera.getTresD()==1?"selected":""):""%>>si</option>
        <option value="2"<%=cartelera!=null?(cartelera.getTresD()==2?"selected":""):""%>>no</option>
      </select>

    </div>
    <div class="mb-3">
      <label class="form-label">Doblada</label>

      <select class="form-select" aria-label="Default select example" name="doblada">
        <option value="1"<%=cartelera!=null?(cartelera.getDoblada()==1?"selected":""):""%>>si</option>
        <option value="2"<%=cartelera!=null?(cartelera.getDoblada()==2?"selected":""):""%>>no</option>
      </select>

    </div>

    <div class="mb-3">
      <label class="form-label">Subtitulada</label>
      <select class="form-select" aria-label="Default select example" name="subtitulada">

        <option value="1"<%=cartelera!=null?(cartelera.getSubtitulada()==1?"selected":""):""%>>si</option>
        <option value="2"<%=cartelera!=null?(cartelera.getSubtitulada()==2?"selected":""):""%>>no</option>
      </select>
    </div>


    <%--        <input type="text" class="form-control" value="<%=e==null?"":e.getDepartment().getDepartmentId()%>">--%>


    <a class="btn btn-secondary" href="<%=request.getContextPath()%>/CarteleraServlet">Atras</a>
    <button type="submit" class="btn btn-primary">Submit</button>

  </form>
</div>
</body>
</html>
