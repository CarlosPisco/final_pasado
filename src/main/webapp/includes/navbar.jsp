<%@ page import="Beans.Empleado" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/CarteleraServlet">Cinema</a>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <% if (session.getAttribute("EmpLogueo") != null) {
                    Empleado usuario = (Empleado) session.getAttribute("EmpLogueo");
                %>
                <li class="nav-item">
                    <a class="nav-link"
                       style="color: darkgreen;">| <%=usuario.getNombre() + " " + usuario.getApellido()%> |
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: #0d6efd"
                       href="<%=request.getContextPath()%>/LoginServlet?accion=logout">(Logout)</a>
                </li>
                <%}%>
            </ul>

        </div>
    </div>
</nav>
