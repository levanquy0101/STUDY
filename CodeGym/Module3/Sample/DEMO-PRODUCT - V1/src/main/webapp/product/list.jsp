<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Phầm mềm quản lý</title>
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
                <li><a href="">Item 1</a></li>
                <li><a href="">Item 2</a></li>
                <li><a href="">Item 3</a></li>
                <li><a href="">Item 4</a></li>
            </ul>
        </aside>
        <section class="section">
            <div class="section-top">
                <h2>Danh sách</h2>
                <form action="/ProductServlet" class="main-form">
                    <input type="hidden" name="action" value="search">
                    <input type="text" placeholder="Tìm kiếm" class="input-q1" name="name">
                    <button type="submit" class="button-search">Tìm kiếm</button>
                </form>
                <button class="button-add" onclick="window.location.href='?action=create'">Thêm mới</button>
                <select name="" id="" class="select-q1">
                    <option value="" style="font-weight: bold" selected disabled>Sắp xếp theo</option>
                    <option value="">Tên A-Z</option>
                    <option value="">Tên Z-A</option>
                </select>
            </div>
            <div class="main-table">
                <table class="table">
                    <thead>
                    <th>Item 1</th>
                    <th>Item 2</th>
                    <th>Item 3</th>
                    <th>Item 4</th>
                    <th>Hành động</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${productList}" var="product">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.about}</td>
                        <td>
                            <button class="button-edit" onclick="window.location.href='?action=update&id=${product.id}'"><img src="/static/img/edit.png" alt="" class="icon-size"></button>
                            <button class="button-delete" id="openModalBtn${product.id}" onclick="openModal(${product.id})"><img src="/static/img/delete.png" alt="" class="icon-size"></button>
                            <div id="myModal${product.id}" class="modal">
                                <div class="modal-content">
                                    <!-- <span class="close" id="closeModalBtn">&times;</span> -->
                                    <p>Bạn thật sự muốn xóa ID = ${product.id}? Hành động này không thể hoàn tác !!!</p>
                                    <button id="closeModalBtn${product.id}" class="button-close" onclick="closeModal(${product.id})">Trở lại</button>
                                    <button class="button-delconfirm" onclick="window.location.href='?action=delete&id=${product.id}'">Xác nhận</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="pagination" class="pagination"></div>

            <div class="modal-alert" id="modal-alert">
                <div class="modal-content-alert">
                    <h2>Đã cập nhật lại danh sách</h2>
                    <p class="tick">&#10003;</p>
                </div>
            </div>

        </section>
    </main>
    <footer id="footer">

    </footer>
</div>
<script src="../static/js/app.js" defer></script>
<script>
    let params = new URLSearchParams(window.location.search);
    let ms = params.get('ms');
    if (ms == 1) {
        let displayModal = document.getElementById("modal-alert");
        displayModal.style.display= "flex";
    }
</script>
</body>
</html>