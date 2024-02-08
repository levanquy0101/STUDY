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
            <div class="section-top">
                <h2>Danh sách</h2>
                <form action="/PhongTroServlet" class="main-form">
                    <input type="hidden" name="action" value="search">
                    <input type="text" placeholder="Tìm kiếm" class="input-q1" name="nameSearch">
                    <button type="submit" class="button-search">Tìm kiếm</button>
                </form>
                <button class="button-add" onclick="window.location.href='?action=create'">Thêm mới</button>
                <select name="sortType" id="sortSelect" class="select-q1" onchange="updateSortType()">
                    <option style="font-weight: bold" selected disabled>Sắp xếp theo</option>
                    <option value="name">Tên A-Z</option>
                    <option value="name desc">Tên Z-A</option>
                    <option value="typeName">Loai A-Z</option>
                    <option value="typeName desc">Loại Z-A</option>
                    <option value=id>Mặc định</option>
                </select>
            </div>
            <div class="main-table">
                <table class="table">
                    <thead>
                    <th>STT</th>
                    <th>Mã phòng trọ</th>
                    <th>Tên người thuê trọ</th>
                    <th>Số điện thoại</th>
                    <th>Ngày bắt đầu thuê</th>
                    <th>Hình thức thanh toán</th>
                    <th>Ghi chú</th>
                    <th>Hành động</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dataList}" var="data">
                    <tr>
                        <td>${data.idPT}</td>
                        <td>${data.codeThue}</td>
                        <td>${data.nameThue}</td>
                        <td>${data.phone}</td>
                        <td>${data.dateThue}</td>
                        <td>${data.typeThue}</td>
                        <td>${data.about}</td>
                        <td>
                            <input type="checkbox" class="button-delete" name="deleteList" value="${data.codeThue}"></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div id="noDataMessage" style="display: none;">
                <p style="text-align: center;padding: 10px">Không tìm thấy dữ liệu nào!</p>
            </div>
            <div>
                <button onclick="getSelectedValues()" id="openModalBtn" style="padding: 8px;color: orangered;border-radius: 4px;border: none;cursor: pointer;margin: 10px 0">Xóa</button>
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <p id="contentDel"></p>
                        <button id="closeModalBtn" class="button-close" onclick="closeModal()">Trở lại</button>
                        <button class="button-delconfirm" onclick="deleteProduct()" id="accept">Xác nhận</button>
                    </div>
                </div>
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

    let selectedValues = [];
    function getSelectedValues() {
        let checkboxes = document.getElementsByName("deleteList");
        for (let i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selectedValues.push(checkboxes[i].value);
                console.log(selectedValues)
            }
        }
        let modal = document.getElementById(`myModal`);
        modal.style.display = "flex";
        if(selectedValues.length>0){
            let resultString = selectedValues.join(', ');
        document.getElementById("contentDel").innerText = "Bạn thật sự muốn xóa thông tin thuê trọ = "+resultString+" ? Hành động này không thể hoàn tác !!!";
        document.getElementById("accept").style.display= "inline-block";
        }else {
        document.getElementById("contentDel").innerText = "Không có thông tin cần xóa !!!";
        document.getElementById("accept").style.display= "none";
        }
    }
    function deleteProduct(){
        window.location.href="?action=delete&deleteList="+selectedValues;
        selectedValues = [];

    }
    function closeModal() {
        let modal = document.getElementById(`myModal`);
        modal.style.display = "none";
        selectedValues = [];
    }
</script>
</body>
</html>