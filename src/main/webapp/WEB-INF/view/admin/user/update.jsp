<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp"></jsp:include>
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp"></jsp:include>
      <div id="layoutSidenav_content">
        <main>
          <div class="container mt-5">
            <div class="row">
              <div class="col-md-6 col-12 mx-auto">
                <h3>Cập nhật người dùng</h3>
                <hr />
                <form:form
                  method="post"
                  action="/admin/user/update/${newUser.id}"
                  modelAttribute="newUser"
                >
                  <div class="mb-3" style="display: none">
                    <label class="form-label">id:</label>
                    <form:input type="text" class="form-control" path="id" />
                  </div>

                  <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <form:input
                      type="email"
                      class="form-control"
                      path="email"
                      disabled="true"
                    />
                  </div>

                  <div class="mb-3">
                    <label class="form-label">Số điện thoại:</label>
                    <form:input type="text" class="form-control" path="phone" />
                  </div>
                  <div class="mb-3">
                    <label class="form-label">Tên đầy đủ:</label>
                    <form:input
                      type="text"
                      class="form-control"
                      path="fullName"
                    />
                  </div>
                  <div class="mb-3">
                    <label class="form-label">Địa chỉ:</label>
                    <form:input
                      type="text"
                      class="form-control"
                      path="Address"
                    />
                  </div>
                  <div class="mb-3">
                    <label class="form-label">Role</label>
                    <form:input
                      type="text"
                      class="form-control"
                      path="Role.name"
                    />
                  </div>

                  <button
                    href="/admin/user"
                    type="submit"
                    class="btn btn-primary"
                  >
                    Cập nhật
                  </button>
                </form:form>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="../layout/footer.jsp"></jsp:include>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="js/scripts.js"></script>
  </body>
</html>
