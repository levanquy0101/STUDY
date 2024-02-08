<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div id="app">
  <header id="header">
    <h2>Phần mềm quản lý phòng trọ</h2>
  </header>
  <main id="main">
    <aside class="aside">
      <h2>Dashboards</h2>
      <ul>
        <li><a href="/PhongTroServlet">Default</a></li>
        <li><a href="">Other</a></li>
      </ul>
      <h2>Manager</h2>
      <ul>
        <li><a href="">Item 1</a></li>
        <li><a href="">Item 2</a></li>
        <li><a href="">Item 3</a></li>
        <li><a href="">Item 4</a></li>
      </ul>
    </aside>
    <section class="section">
      <div class="main-form">
        <h2>Tạo dữ liệu mới</h2>
        <form action="" class="form" method="post">
          <input type="text" class="input-q1" placeholder="Nhập tên của bạn" name="nameThue" required pattern="^[a-zA-Z\s]+$" oninvalid="this.setCustomValidity('Vui lòng nhập tên không chứa ký tự số và ký tự đặc biệt!')" oninput="setCustomValidity('')">
          <input type="text" class="input-q1" placeholder="Nhập số điện thoại" name="phone" required pattern="^\d{10}$" oninvalid="this.setCustomValidity('Vui lòng nhập SĐT (độ dài 10 ký tự)!')" oninput="setCustomValidity('')">
          <input type="date" class="input-q1" placeholder="Nhập ngày bắt đầu thuê" name="dateThue" required >
          <select name="typeThue" id="" class="input-q1">
            <option value="1">Theo Tháng</option>
            <option value="2">Theo Quý</option>
            <option value="3">Theo Năm</option>
          </select>
          <input type="text" class="input-q1" placeholder="Nhập ghi chú" name="aboutThue">
          <c:if test="${messError != null}">
            <div class="error">
              <small>${messError}</small>
            </div>
          </c:if>
          <button type="submit" class="submit">Tạo dữ liệu</button>
        </form>
      </div>
      <div class="back">
        <a href="/PhongTroServlet">&larr; Trở về danh sách</a>
      </div>
    </section>
  </main>
  <footer id="footer">
  </footer>
</div>
</body>
</html>
  