<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Estudiantes</title>
    </head>
    <body>
        <h1>Lista de Estudiantes</h1>
        <a href="MainControler?action=nuevo">Nuevo Estudiante</a>
        
        <table border="2">
            <tr>
                 <th>ID</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Email</th>
                <th>Fecha de Nacimiento</th>
                <th>Opcion</th>
                <th></th>
                <th></th>
                
            </tr>
            <c:forEach var="e" items="${lista}">
                <tr>
                    <td>${e.id}</td>
                    <td>${e.nombre}</td>
                    <td>${e.apellidos}</td>
                    <td>${e.email}</td>
                    <td>${e.fechaNacimiento}</td>
                    <td>
                        <a href="MainControler?action=editar&id=${e.id}">Editar</a>
                    
                        <a href="MainControler?action=eliminar&id=${e.id}" onclick="return(confirm('Esta seguro de la eliminacion?'))">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>