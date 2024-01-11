let seats = document.getElementById("seatid");
let ticketfilmid = parseInt(document.getElementById("ticketfilmid").value);
let accid = parseInt(document.getElementById("taikhoan").value);

let seatArray = seats.value.split(',').map(item => parseInt(item, 10));

seatArray.forEach(function (seatId) {
    callInsertTicketAPI(accid, seatId, ticketfilmid);  
    console.log(accid);
    console.log(seatId);
    console.log(ticketfilmid);

});

let timeLeft = 90;
let countdownElement = document.getElementById("time");

let countdownInterval = setInterval(function () {
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
