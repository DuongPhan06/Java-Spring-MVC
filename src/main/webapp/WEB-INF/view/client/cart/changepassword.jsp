<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đổi mật khẩu</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>

<body>

<div class="container mt-5">

    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow">

                <div class="card-header">
                    <h4 class="mb-0">Đổi mật khẩu</h4>
                </div>

                <div class="card-body">

                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">
                            ${successMessage}
                        </div>
                    </c:if>

                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            ${errorMessage}
                        </div>
                    </c:if>

                    <form action="/account/change-password" method="post">

                        <div class="mb-3">
                            <label class="form-label">
                                Mật khẩu hiện tại
                            </label>

                            <input type="password"
                                   name="oldPassword"
                                   class="form-control"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Mật khẩu mới
                            </label>

                            <input type="password"
                                   name="newPassword"
                                   class="form-control"
                                   minlength="6"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">
                                Xác nhận mật khẩu mới
                            </label>

                            <input type="password"
                                   name="confirmPassword"
                                   class="form-control"
                                   minlength="6"
                                   required>
                        </div>

                        <button type="submit"
                                class="btn btn-primary w-100">
                            Đổi mật khẩu
                        </button>

                    </form>

                </div>
            </div>

        </div>
    </div>

</div>

</body>
</html>
```
