<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard - Hỏi Dân IT</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(()=>{
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e){
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src",imgURL);
                $("#avatarPreview").css({"display": "block"});
            });
        });
    </script>
</head>

<body class="sb-nav-fixed">
<%--header--%>
<jsp:include page="../layout/header.jsp"/>
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp"/>
    <div id="layoutSidenav_content">
        <main>
            <h1>hello product</h1>
            <div class="container mt-5">
                <div class = "row">
                    <div class = "col-md-6 col-12 mx-auto">
                        <h3>create user</h3>
                        <hr/>
                        <%--@elvariable id="newUser" type="java"--%>
                        <form:form action="/admin/user/create"  method="post" modelAttribute="newUser" class="row"
                            enctype="multipart/form-data">
                            <div class = "mb-3">
                                <label class="form-label">Email:</label>
                                <form:input type="email" class="form-control is-invalid" path = "email"/>
                                <form:errors path="email" cssClass="invalid-feedback"/>
                            </div>
                            <div class = "mb-3">
                                <label class="form-label">Fullname:</label>
                                <form:input type="text" class="form-control is-invalid" path = "fullName"/>
                                <form:errors path="fullName" cssClass="invalid-feedback"/>
                            </div>
                            <div class = "mb-3">
                                <label class="form-label">Password:</label>
                                <form:input type="password" class="form-control is-invalid" path = "password"/>
                                <form:errors path="password" cssClass="invalid-feedback"/>
                            </div>
                            <div class = "mb-3">
                                <label class="form-label">Address:</label>
                                <form:input type="text" class="form-control is-invalid" path = "address"/>
                            </div>

                            <div class = "mb-3">
                                <label class="form-label">Phone:</label>
                                <form:input type="text" class="form-control" path = "phone"/>
                            </div>

                            <div class="mb-3 col-12 col-md-6">
                                <label class="form-label">Role:</label>
                                <form:select class="form-select" path="role.name">
                                    <form:option value="ADMIN">ADMIN</form:option>
                                    <form:option value="USER">USER</form:option>
                                </form:select>
                            </div>

                            <div class="mb-3 col-12 col-md-6">
                                <label for="avatarFile" class="form-label">Avatar:</label>
                                <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg" name = "avatarFile"/>
                            </div>

                            <div class="col-12 mb-3">
                                <img style = "max-height:250px; display: none;" id="avatarPreview" alt="avatar preview"/>
                            </div>

                            <div class="col-12 mb-5">
                                <button type="submit" class="btn btn-primary">Create</button>
                            </div>


                        </form:form>
                    </div>
                </div>
            </div>

        </main>

        <jsp:include page="../layout/footer.jsp"/>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="demo/chart-area-demo.js"></script>
<script src="demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>

</html>

