
let total = 0;
let seatIdsArray = [];

function totalBill(tdElement) {
    const inputValue = parseFloat(tdElement.querySelector('.seat-value').value);
    const seatID = tdElement.querySelector('.seatID').value;
    const seatNumber = tdElement.textContent.trim();

    if (tdElement.classList.contains('selected-seat')) {
        tdElement.classList.remove('selected-seat');
        total -= inputValue;

        // Loại bỏ seatID khỏi mảng khi ghế bị bỏ chọn
        const index = seatIdsArray.indexOf(seatID);
        if (index > -1) {
            seatIdsArray.splice(index, 1);
        }

    } else {
        tdElement.classList.add('selected-seat');
        total += inputValue;

        // Thêm seatID vào mảng khi ghế được chọn
        seatIdsArray.push(seatID);
    }

    const selectedSeats = document.querySelectorAll('.selected-seat');
    const seatNumbers = Array.from(selectedSeats).map(seat => seat.textContent.trim());

    document.getElementById('selectedSeatsDisplay').textContent = seatNumbers.join(', ');
    document.getElementById('totalPrice').textContent = total;

    // truyen du lieu
    document.getElementById('seat').value = seatNumbers.join(', ');
    document.getElementById('money').value = total;
    document.getElementById('seatIdd').value = seatIdsArray.join(', ');

    //xu li hold and remove
    const ticketfilmid = parseInt(document.getElementById("ticketfilmid").value);
    const accid = parseInt(document.getElementById("taikhoan").value);
    console.log(accid);
    console.log(seatID);
    console.log(ticketfilmid);


    callInsertTicketAPI(accid, seatID, ticketfilmid);


}

function checkSeatsBeforeSubmit() {
    let selectedSeats = document.getElementById("selectedSeatsDisplay").textContent;
    if (selectedSeats.trim() === "") {
        alert("Vui lòng chọn ít nhất một ghế trước khi tiến hành thanh toán!");
        return false;
    }
    return true;
}

let timeLeft = 90;
let countdownElement = document.getElementById("time");


let countdownInterval = setInterval(function () {
    const accid = parseInt(document.getElementById("taikhoan").value);
    timeLeft--;
    let minutes = Math.floor(timeLeft / 60);
    let seconds = timeLeft % 60;

    countdownElement.textContent = (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;

    if (timeLeft <= 0) {
        clearInterval(countdownInterval);
        callDeleteTicketsWithStatusZeroAPI(accid);
        window.location.href = "Home";
    }
}, 1000);


function callInsertTicketAPI(accountId, seatId, ticketFilmId) {

    const apiUrl = '/Project_PRJ301/HoldSeatServlet';

    $.ajax({
        type: "POST",
        url: apiUrl,
        data: JSON.stringify({
            accountId: accountId,
            seatId: seatId,
            ticketFilmId: ticketFilmId
        }),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            console.log('Success:', data);
        },
        error: function (error) {
            console.error('Error:', error);
        }
    });
}


function callDeleteTicketsWithStatusZeroAPI(taikhoanid) {
    const apiUrl = '/Project_PRJ301/RemoveTicketStatus0Servlet';

    $.ajax({
        type: "POST",
        url: apiUrl,
        data: JSON.stringify({taikhoanid: taikhoanid}),
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            console.log('Successfully deleted tickets:', data);
        },
        error: function (error) {
            console.error('Error during deletion:', error);
        }
    });
}

window.addEventListener('beforeunload', function (e) {
    const accid = parseInt(document.getElementById("taikhoan").value);
    callDeleteTicketsWithStatusZeroAPI(accid);
});














