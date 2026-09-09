<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="vi">
  <head>
    <meta charset="UTF-8" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Đăng nhập - Laptop Shop</title>

    <!-- Bootstrap -->

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <!-- Font Awesome -->

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
    />

    <style>
      /* =====================================
           RESET
        ===================================== */

      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      body {
        font-family: Arial, Helvetica, sans-serif;

        background: #f5f7fb;

        min-height: 100vh;
      }

      /* =====================================
           MAIN LOGIN PAGE
        ===================================== */

      .login-page {
        width: 100%;

        min-height: 100vh;

        display: flex;
      }

      /* =====================================
           LEFT SIDE
        ===================================== */

      .login-left {
        width: 50%;

        min-height: 100vh;

        background: linear-gradient(135deg, #4e73df, #224abe);

        display: flex;

        align-items: center;

        justify-content: center;

        color: white;

        position: relative;

        overflow: hidden;
      }

      /*
           Decorative circles
        */

      .login-left::before {
        content: "";

        position: absolute;

        width: 400px;

        height: 400px;

        border-radius: 50%;

        background: rgba(255, 255, 255, 0.06);

        top: -150px;

        left: -150px;
      }

      .login-left::after {
        content: "";

        position: absolute;

        width: 500px;

        height: 500px;

        border-radius: 50%;

        background: rgba(255, 255, 255, 0.05);

        bottom: -250px;

        right: -200px;
      }

      .left-content {
        width: 80%;

        max-width: 500px;

        text-align: center;

        position: relative;

        z-index: 2;
      }

      /* =====================================
           LAPTOP ICON
        ===================================== */

      .laptop-icon {
        width: 150px;

        height: 150px;

        margin: 0 auto 30px;

        border-radius: 30px;

        background: rgba(255, 255, 255, 0.12);

        display: flex;

        align-items: center;

        justify-content: center;

        backdrop-filter: blur(5px);

        box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
      }

      .laptop-icon i {
        font-size: 75px;

        color: white;
      }

      /* =====================================
           LEFT TEXT
        ===================================== */

      .left-content h1 {
        font-size: 44px;

        font-weight: 700;

        margin-bottom: 15px;
      }

      .left-content .slogan {
        font-size: 22px;

        margin-bottom: 20px;

        opacity: 0.95;
      }

      .left-content .description {
        font-size: 15px;

        line-height: 1.8;

        opacity: 0.8;
      }

      /* =====================================
           FEATURES
        ===================================== */

      .features {
        margin-top: 35px;

        display: flex;

        justify-content: center;

        gap: 25px;

        flex-wrap: wrap;
      }

      .feature {
        display: flex;

        align-items: center;

        gap: 8px;

        font-size: 14px;

        opacity: 0.9;
      }

      .feature i {
        font-size: 16px;
      }

      /* =====================================
           RIGHT SIDE
        ===================================== */

      .login-right {
        width: 50%;

        min-height: 100vh;

        background: #f5f7fb;

        display: flex;

        align-items: center;

        justify-content: center;

        padding: 30px;
      }

      /* =====================================
           LOGIN BOX
        ===================================== */

      .login-box {
        width: 100%;

        max-width: 430px;

        background: white;

        padding: 45px;

        border-radius: 20px;

        box-shadow: 0 15px 45px rgba(0, 0, 0, 0.08);
      }

      /* =====================================
           LOGIN HEADER
        ===================================== */

      .login-header {
        margin-bottom: 30px;
      }

      .login-header h2 {
        font-size: 32px;

        color: #333;

        font-weight: 700;

        margin-bottom: 8px;
      }

      .login-header p {
        color: #999;

        font-size: 14px;
      }

      /* =====================================
           ALERT
        ===================================== */

      .alert {
        border-radius: 10px;

        font-size: 14px;

        margin-bottom: 20px;
      }

      /* =====================================
           FORM
        ===================================== */

      .form-group {
        margin-bottom: 20px;
      }

      .form-group label {
        display: block;

        font-size: 14px;

        font-weight: 600;

        color: #444;

        margin-bottom: 8px;
      }

      /* =====================================
           INPUT
        ===================================== */

      .input-box {
        width: 100%;

        height: 52px;

        border: 1px solid #ddd;

        border-radius: 10px;

        display: flex;

        align-items: center;

        padding: 0 15px;

        transition: 0.3s;

        background: white;
      }

      .input-box:focus-within {
        border-color: #4e73df;

        box-shadow: 0 0 0 3px rgba(78, 115, 223, 0.12);
      }

      .input-icon {
        color: #999;

        margin-right: 12px;
      }

      .input-box input {
        flex: 1;

        height: 100%;

        border: none;

        outline: none;

        font-size: 15px;

        color: #333;

        background: transparent;
      }

      .input-box input::placeholder {
        color: #aaa;
      }

      /* =====================================
           PASSWORD ICON
        ===================================== */

      .password-toggle {
        color: #999;

        cursor: pointer;

        transition: 0.2s;
      }

      .password-toggle:hover {
        color: #4e73df;
      }

      /* =====================================
           REMEMBER + FORGOT
        ===================================== */

      .login-options {
        display: flex;

        align-items: center;

        justify-content: space-between;

        margin-bottom: 25px;

        font-size: 14px;
      }

      .remember {
        display: flex;

        align-items: center;

        gap: 7px;

        color: #777;

        cursor: pointer;
      }

      .remember input {
        width: 15px;

        height: 15px;

        cursor: pointer;
      }

      .forgot-password {
        color: #4e73df;

        text-decoration: none;
      }

      .forgot-password:hover {
        text-decoration: underline;
      }

      /* =====================================
           LOGIN BUTTON
        ===================================== */

      .login-button {
        width: 100%;

        height: 52px;

        border: none;

        border-radius: 10px;

        background: linear-gradient(135deg, #4e73df, #224abe);

        color: white;

        font-size: 16px;

        font-weight: 600;

        cursor: pointer;

        transition: all 0.3s ease;
      }

      .login-button:hover {
        transform: translateY(-2px);

        box-shadow: 0 8px 20px rgba(78, 115, 223, 0.3);
      }

      .login-button:active {
        transform: translateY(0);
      }

      /* =====================================
           DIVIDER
        ===================================== */

      .divider {
        display: flex;

        align-items: center;

        gap: 15px;

        margin: 28px 0;

        color: #aaa;

        font-size: 12px;
      }

      .divider::before,
      .divider::after {
        content: "";

        flex: 1;

        height: 1px;

        background: #eee;
      }

      /* =====================================
           REGISTER
        ===================================== */

      .register {
        text-align: center;

        color: #888;

        font-size: 14px;
      }

      .register a {
        color: #4e73df;

        font-weight: 600;

        text-decoration: none;
      }

      .register a:hover {
        text-decoration: underline;
      }

      /* =====================================
           MOBILE
        ===================================== */

      @media (max-width: 900px) {
        .login-left {
          width: 40%;
        }

        .login-right {
          width: 60%;
        }

        .left-content h1 {
          font-size: 34px;
        }

        .left-content .slogan {
          font-size: 18px;
        }

        .features {
          flex-direction: column;

          align-items: center;

          gap: 12px;
        }
      }

      @media (max-width: 768px) {
        .login-left {
          display: none;
        }

        .login-right {
          width: 100%;

          min-height: 100vh;
        }

        .login-box {
          max-width: 450px;
        }
      }

      @media (max-width: 450px) {
        .login-right {
          padding: 20px;
        }

        .login-box {
          padding: 30px 22px;
        }

        .login-header h2 {
          font-size: 27px;
        }

        .login-options {
          flex-direction: column;

          align-items: flex-start;

          gap: 12px;
        }
      }
    </style>
  </head>

  <body>
    <!-- ==================================================
     LOGIN PAGE
================================================== -->

    <div class="login-page">
      <!-- ==================================================
         LEFT SIDE
    ================================================== -->

      <div class="login-left">
        <div class="left-content">
          <!-- LAPTOP ICON -->

          <div class="laptop-icon">
            <i class="fas fa-laptop"></i>
          </div>

          <!-- BRAND -->

          <h1>Chào mừng bạn đến với Laptop-Shop</h1>

          <p class="slogan">Công nghệ trong tầm tay bạn</p>

          <p class="description">
            Đăng nhập để khám phá hàng ngàn sản phẩm công nghệ mới
          </p>

          <!-- FEATURES -->

          <div class="features">
            <div class="feature">
              <i class="fas fa-circle-check"></i>

              <span> Hàng chính hãng </span>
            </div>

            <div class="feature">
              <i class="fas fa-shield-halved"></i>

              <span> Bảo hành uy tín </span>
            </div>

            <div class="feature">
              <i class="fas fa-truck"></i>

              <span> Giao hàng nhanh </span>
            </div>
          </div>
        </div>
      </div>

      <!-- ==================================================
         RIGHT SIDE
    ================================================== -->

      <div class="login-right">
        <div class="login-box">
          <!-- HEADER -->

          <div class="login-header">
            <h2>Đăng nhập</h2>

            <p>Đăng nhập vào tài khoản của bạn để tiếp tục.</p>
          </div>

          <!-- ==================================================
                 ERROR MESSAGE
            ================================================== -->

          <c:if test="${param.error != null}">
            <div class="alert alert-danger">
              <i class="fas fa-circle-exclamation me-2"> </i>

              Email hoặc mật khẩu không chính xác.
            </div>
          </c:if>

          <!-- ==================================================
                 LOGOUT MESSAGE
            ================================================== -->

          <c:if test="${param.logout != null}">
            <div class="alert alert-success">
              <i class="fas fa-circle-check me-2"> </i>

              Đăng xuất thành công.
            </div>
          </c:if>

          <!-- ==================================================
                 LOGIN FORM
            ================================================== -->

          <form method="post" action="${pageContext.request.contextPath}/login">
            <!-- EMAIL -->

            <div class="form-group">
              <label for="username"> Email </label>

              <div class="input-box">
                <i class="fas fa-envelope input-icon"> </i>

                <input
                  type="email"
                  id="username"
                  name="username"
                  placeholder="Nhập địa chỉ email"
                  autocomplete="email"
                  required
                />
              </div>
            </div>

            <!-- PASSWORD -->

            <div class="form-group">
              <label for="password"> Mật khẩu </label>

              <div class="input-box">
                <i class="fas fa-lock input-icon"> </i>

                <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Nhập mật khẩu"
                  autocomplete="current-password"
                  required
                />

                <span class="password-toggle" onclick="togglePassword()">
                  <i id="passwordIcon" class="fas fa-eye"> </i>
                </span>
              </div>
            </div>

            <!-- ==================================================
                     REMEMBER ME
                ================================================== -->

            <div class="login-options">
              <label class="remember">
                <input type="checkbox" name="remember-me" />

                <span> Ghi nhớ đăng nhập </span>
              </label>

              <a
                href="${pageContext.request.contextPath}/forgot-password"
                class="forgot-password"
              >
                Quên mật khẩu?
              </a>
            </div>

            <!-- ==================================================
                     CSRF
                ================================================== -->

            <input
              type="hidden"
              name="${_csrf.parameterName}"
              value="${_csrf.token}"
            />

            <!-- ==================================================
                     LOGIN BUTTON
                ================================================== -->

            <button type="submit" class="login-button">
              <i class="fas fa-right-to-bracket me-2"> </i>

              Đăng nhập
            </button>
          </form>

          <!-- DIVIDER -->

          <div class="divider">HOẶC</div>

          <!-- REGISTER -->

          <div class="register">
            Chưa có tài khoản?

            <a href="/register"> Đăng ký ngay </a>
          </div>
        </div>
      </div>
    </div>

    <!-- ==================================================
     JAVASCRIPT
================================================== -->

    <script>
      function togglePassword() {
        const password = document.getElementById("password");

        const icon = document.getElementById("passwordIcon");

        if (password.type === "password") {
          password.type = "text";

          icon.classList.remove("fa-eye");

          icon.classList.add("fa-eye-slash");
        } else {
          password.type = "password";

          icon.classList.remove("fa-eye-slash");

          icon.classList.add("fa-eye");
        }
      }
    </script>
  </body>
</html>
