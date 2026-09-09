<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <div class="container-fluid px-4">
                      <div class="row">
                        <div class="col-12 mx-auto">
                          <div class="d-flex justify-content-between">
                            <h3>Chi tiết đơn hàng với id = ${id}</h3>
      
                          </div>
                          <hr/>
                  
                          <div class="card" style="width: 60%">
                              <div class="card-header">
                                Thông tin đơn hàng
                              </div>
                              <ul class="list-group list-group-flush">
                                <li class="list-group-item">ID: ${id}</li>
                                <li class="list-group-item">Email: ${product.name}</li>
                                <li class="list-group-item">Tên đầy đủ: ${product.price}</li>
                              </ul>
                            </div>
                            
                             <div class="col-12 mb-3">
    <img
        id="avatarPreview"
        src="/images/product/${product.image}"
        style="
            max-width: 100%;
            max-height: 400px;
            width: auto;
            height: auto;
            object-fit: contain;
        "
        alt="Product image"
    />
</div>
                            <a href="/admin/product" class="btn btn-success mt-3">Trở lại</a>
                        </div>

                        <div class="card-body text-center">
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