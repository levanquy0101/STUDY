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
        <li><a href="/BenhAnServlet">Default</a></li>
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
        <h2>Sửa dữ liệu</h2>
        <form action="" class="form" method="post">
          <input type="hidden" class="input-q1" name="idBn" value="${data.idBn}">
          <input type="text" class="input-q1" placeholder="Nhập" name="codeBa" value="${data.codeBa}">
          <input type="text" class="input-q1" placeholder="Nhập" name="codeBn" value="${data.codeBn}">
          <input type="text" class="input-q1" placeholder="Nhập" name="nameBn" value="${data.nameBn}">
          <input type="date" class="input-q1" placeholder="Nhập" name="dateIn" value="${data.dateIn}">
          <input type="date" class="input-q1" placeholder="Nhập" name="dateOut" value="${data.dateOut}">
          <input type="text" class="input-q1" placeholder="Nhập" name="reason" value="${data.reason}">
          <button type="submit" class="submit">Sửa dữ liệu</button>
        </form>
      </div>
      <div class="back">
        <a href="/BenhAnServlet">&larr; Trở về danh sách</a>
      </div>
    </section>
  </main>
  <footer id="footer">
      <p>Web phát triển bởi Le Van Quy</p>
  </footer>
</div>
</body>
</html>