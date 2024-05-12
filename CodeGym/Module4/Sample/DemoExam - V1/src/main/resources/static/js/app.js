
console.log("File JS đã được nhúng!")

// Modal trang xóa
function openModal(idDelete){
    let modal = document.getElementById(`myModal${idDelete}`);
    console.log(idDelete);
    console.log(modal)
    modal.style.display = "block";
}

function closeModal(idDelete) {
    let modal = document.getElementById(`myModal${idDelete}`);
    console.log(idDelete);
    console.log(modal)
    modal.style.display = "none";
}
// Phân trang
document.addEventListener("DOMContentLoaded", function () {
    let itemsPerPage = 5;
    let totalItems = document.querySelectorAll(".table tbody tr").length;

    if (totalItems === 0) {
        document.getElementById("noDataMessage").style.display = "block";
        document.getElementById("nav-page").style.display = "none";
        document.getElementById("pagination").style.display = "none";
    }

    // Kiểm tra nếu chỉ có 1 trang thì không hiển thị phân trang
    if (totalItems <= itemsPerPage) {
        return;
    }
    let totalPages = Math.ceil(totalItems / itemsPerPage);
    let pagination = document.getElementById("pagination");

    for (let i = 1; i <= totalPages; i++) {
        let pageButton = document.createElement("button");
        pageButton.innerText = i;
        pageButton.addEventListener("click", function () {
            showPage(this.innerText);
        });
        pagination.appendChild(pageButton);
    }

    showPage(1);

    function showPage(pageNumber) {
        let start = (pageNumber - 1) * itemsPerPage;
        let end = start + itemsPerPage;

        let allRows = document.querySelectorAll(".table tbody tr");
        allRows.forEach(function (row) {
            row.style.display = "none";
        });

        for (let i = start; i < end; i++) {
            if (allRows[i]) {
                allRows[i].style.display = "table-row";
            }
        }

        let allButtons = document.querySelectorAll(".pagination button");
        allButtons.forEach(function (button) {
            button.classList.remove("selected");
        });

        let selectedButton = document.querySelector(".pagination button:nth-child(" + pageNumber + ")");
        if (selectedButton) {
            selectedButton.classList.add("selected");
        }
    }
});
// Hàm để xóa phần tử
function removeElement(elementId) {
    let element = document.getElementById(elementId);
    if (element) {
        element.parentNode.removeChild(element);
    }
}
// Thiết lập hẹn giờ để gọi hàm xóa phần tử sau 5 giây
// setTimeout(function() {
//     removeElement('modal-alert');
// }, 5000);

// Xử lý chức năng xóa nhiều sản phẩm cùng 1 lúc
let selectedValues = [];
function getSelectedValues() {
    let checkboxes = document.getElementsByName("deleteIdList");
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedValues.push({
                id: checkboxes[i].value,
                codeName: checkboxes[i].getAttribute("data-codeName")
            });
            // console.log(selectedValues);
        }
    }
    let modal = document.getElementById(`myModal`);
    modal.style.display = "block";
    if (selectedValues.length > 0) {
        // let resultString = selectedValues.map(item => item.id + " (" + item.codeName + ")").join(', ');
        let selectedCodeNames = selectedValues.map(item => item.codeName);
        document.getElementById("contentDel").innerText = "Bạn thật sự muốn xóa thông tin: " + selectedCodeNames + " ? Hành động này không thể hoàn tác !!!";
        document.getElementById("accept").style.display = "inline-block";
    } else {
        document.getElementById("contentDel").innerText = "Không có thông tin cần xóa !!!";
        document.getElementById("accept").style.display = "none";
    }
}

function deleteProduct(){
    let selectedIds = selectedValues.map(item => item.id);
    console.log(selectedIds)
    window.location.href="/product/delete?ids="+selectedIds;
    selectedValues = [];

}
function closeModal() {
    let modal = document.getElementById(`myModal`);
    modal.style.display = "none";
    selectedValues = [];
}