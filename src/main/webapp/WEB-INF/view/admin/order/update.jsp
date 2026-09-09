<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Update Product - Hỏi Dân IT</title>
                <link href="/css/styles.css" rel="stylesheet" />

                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        const orgImage = "${newProduct.image}";
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#avatarPreview").attr("src", urlImage);
                            $("#avatarPreview").css({ "display": "block" });
                        }

                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
            </head>


            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Đơn hàng</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Bảng điều khiển</a></li>
                                    <li class="breadcrumb-item"><a href="/admin/order">Đơn hàng</a></li>
                                    <li class="breadcrumb-item active">Cập nhật đơn hàng </li>
                                </ol>
                                <div class=" mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3>Cập nhật 1 đơn hàng</h3>
                                            <hr />
                                            <form:form method="post" action="/admin/order/update" class="row"
                                                enctype="multipart/form-data" modelAttribute="singleOrder">
                                                <div class="mb-3" style="display: none;">
                                                    <label class="form-label" disabled="true">Id:</label>
                                                    <form:input type="text" class="form-control" path="id" />
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label" >Tổng tiền:</label>
                                                    <form:input type="text" class="form-control" path="totalPrice"  disabled="true"/>
                                                </div>
                                             
                                                <div class="mb-3">
                                                    <label class="form-label">Tên người order:</label>
                                                    <form:input type="text" class="form-control" path="user.fullName" disabled="true"/>
                                                </div>

                                

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Trạng thái:</label>
                                                    <form:select class="form-select" path="status">
                                                        <form:option value="PENDING">CHỜ XÁC NHẬN</form:option>
                                                        <form:option value="SHIPPING">ĐANG GIAO
                                                        </form:option>
                                                        <form:option value="COMPLETE">HOÀN THÀNH
                                                        </form:option>
                                                        <form:option value="CANCEL">HỦY BỎ</form:option>
                                                    </form:select>
                                                </div>

                                            </div>
                                            <div class="col-12 mb-5">
                                                <button type="submit" class="btn btn-warning">Cập nhật</button>
                                            </div>

                                            </form:form>

                                        </div>

                                    </div>

                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>