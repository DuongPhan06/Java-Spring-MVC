<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../layout/header.jsp"></jsp:include>
        <div id="layoutSidenav">
            <jsp:include page="../layout/sidebar.jsp"></jsp:include>
            <div id="layoutSidenav_content">
                <main>
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3>Delete Order</h3>
                            <hr />
                            <div class="col-12 mx-auto">
                                <div class="d-flex justify-content-between">
                                  <h3>Xóa Order có ID: ${id}</h3>
                                </div>
                              
                                <hr>
                              
                                <div class="alert alert-danger">
                                  Bạn có chắc chắn muốn xóa đặt hàng này không?
                                </div>
                              
                                <form:form method="post" action="/admin/order/delete" modelAttribute="newOrder">
                                    <div class="mb-3" >
                                        <label class="form-label">id:</label>
                                        <form:input value="${id}" type="text" class="form-control" path="id" />
                                    </div>
                                <button class="btn btn-danger">Xác nhận</button>
                                </form:form>
                              </div>
                        </div>
    
                    </div>    
                </main>
                <jsp:include page="../layout/footer.jsp"></jsp:include>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>