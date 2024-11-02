$(document).ready(function(){
    let concertId = localStorage.getItem('concertId');
    
    $.ajax({
        url: '../PreviewServlet',
        type: 'GET',
        data: { concertId: concertId },
        dataType: 'json',
        success: function (response) {
            var dataConcert = response.concert;
            console.log(dataConcert.name);
            var dataSession = response;
            var path = dataConcert.concertTrailer.trailer;

            $('#video').attr('src', "../resources/VID/" + path);
            $('video')[0].load();
            
            $('#concertName').text(dataConcert.name);
            $('#description').text(dataConcert.desc);
            
            $('#userId').val(dataSession.userId);
            $('#concertId').val(dataConcert.id);
            $('#concertNameVal').val(dataConcert.name);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("AJAX Error: ", textStatus, errorThrown);
            console.log("Response Text: ", jqXHR.responseText);
        }
    });
});

$('#btn-toBook').click(function() {
    $('#description').addClass('fadeOut').addClass('d-none');

    $('#booking-form').removeClass('fadeOut').removeClass('d-none').addClass('fadeIn');
});

$('#btn-back').click(function() {
    $('#booking-form').addClass('fadeOut').addClass('d-none');

    $('#description').removeClass('fadeOut').removeClass('d-none').addClass('fadeIn');
});

$('#btn-booking').click(function(){
    $.ajax({
       url: '../PreviewServlet',
        type: 'POST',
        data: {
            userId: $('#userId').val(),
            concertId: $('#concertId').val(),
            concertName: $('#concertNameVal').val(),
            fullname: $('#fullname').val(),
            phoneNumber: $('#phoneNumber').val(),
            idCard: $('#idCard').val()
        },
        dataType: 'json',
        beforeSend: function () {
            alert('Processing request...');
        },
        success: function (response) {
            if(response.status === 'success'){
                alert(response.message);
                window.location.reload();
            }else{
                alert(response.message);
                window.location.reload();
            }
        }
    });
});

$('#logout-btn').on('click', function (){
    $.ajax({
        url: '../logoutServlet',
        type: 'GET',
        success: function() {
            window.location.href = 'index.xhtml';
        },
        error: function(xhr, status, error) {
            console.error('Logout failed:', error);
            alert('Logout failed. Please try again.');
        }
    });
});