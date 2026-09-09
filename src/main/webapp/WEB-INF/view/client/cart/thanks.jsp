<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <title>Đặt hàng thành công</title>

    <style>
      body {
        margin: 0;
        font-family: Arial, sans-serif;
        background: #f5f5f5;
      }

      .thanks-container {
        width: 500px;
        margin: 100px auto;
        padding: 40px;
        text-align: center;
        background: white;
        border-radius: 10px;
        box-shadow: 0 3px 15px rgba(0, 0, 0, 0.1);
      }

      .success-icon {
        width: 70px;
        height: 70px;
        margin: 0 auto 20px;
        border-radius: 50%;
        background: #28a745;
        color: white;
        font-size: 45px;
        line-height: 70px;
      }

      h1 {
        color: #28a745;
        margin-bottom: 15px;
      }

      p {
        color: #666;
        font-size: 16px;
        margin: 10px 0;
      }

      .btn-home {
        display: inline-block;
        margin-top: 25px;
        padding: 12px 25px;
        background: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
      }

      .btn-home:hover {
        background: #0056b3;
      }
    </style>
  </head>

  <body>
    <div class="thanks-container">
      <div class="success-icon">✓</div>

      <h1>Đặt hàng thành công!</h1>

      <p>Cảm ơn bạn đã mua hàng tại Laptop Shop.</p>

      <p>Đơn hàng của bạn đã được tiếp nhận và đang được xử lý.</p>

      <a href="/" class="btn-home"> Tiếp tục mua hàng </a>
    </div>
  </body>
</html>
```
