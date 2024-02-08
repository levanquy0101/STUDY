
// Phân trang
document.addEventListener("DOMContentLoaded", function () {
    let itemsPerPage = 10;
    let totalItems = document.querySelectorAll(".table tbody tr").length;

    if (totalItems === 0) {
        document.getElementById("noDataMessage").style.display = "block";
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
