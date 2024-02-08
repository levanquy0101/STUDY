<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vannn
  Date: 18-Jan-24
  Time: 2:56 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <h2>Phần mềm quản lý</h2>
  </header>
  <main id="main">
    <aside class="aside">
      <h2>Dashboards</h2>
      <ul>
        <li><a href="/ProductServlet">Default</a></li>
        <li><a href="">Other</a></li>
      </ul>
      <h2>Manager</h2>
      <ul>
        <li><a href="">Mục 1</a></li>
        <li><a href="">Mục 2</a></li>
        <li><a href="">Mục 3</a></li>
        <li><a href="">Mục 4</a></li>
      </ul>
    </aside>
    <section class="section">
      <div class="main-form">
        <h2>Tạo dữ liệu mới</h2>
        <form action="" class="form" method="post">
<%--          <input type="hidden" class="input-q1" placeholder="Nhập" name="id">--%>
          <input type="text" class="input-q1" placeholder="Nhập tên" name="name" required>
          <input type="text" class="input-q1" placeholder="Nhập mã tên" name="codeName" required pattern="^DT-\d{4}$" oninvalid="this.setCustomValidity('Vui lòng nhập theo định dạng DT-XXXX')" oninput="setCustomValidity('')">
        <c:if test="${messError != null}">
            <div class="error">
                <small>${messError}</small>
            </div>
        </c:if>
          <select name="typeName" required>
            <option selected disabled style="font-weight: bold">Nhập loại</option>
          <c:forEach items="${typeList}" var="type">
            <option value="${type.idType}">
                ${type.typeName}
            </option>
          </c:forEach>
           </select>
          <input type="text" class="input-q1" placeholder="Nhập giá" name="price" required>
          <input type="date" class="input-q1" placeholder="Nhập ngày" name="date" required>
          <input type="text" class="input-q1" placeholder="Nhập thông tin" name="about" required>
          <button type="submit" class="submit">Tạo dữ liệu</button>
        </form>
      </div>
      <div class="back">
        <a href="/ProductServlet">&larr; Trở về danh sách</a>
      </div>
    </section>
  </main>
  <footer id="footer">

  </footer>
</div>
</body>
</html>