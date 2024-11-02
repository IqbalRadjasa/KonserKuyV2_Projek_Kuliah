$(document).ready(function(){

   $.ajax({
        url: '/KonserKuy_v2/PaymentHistoryServlet',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let html = '';
            
            $.each(data, function(index, history) {
                let formattedDate = history.bookingDate;
                
                html += `
                    <tr>
                        <td>${index + 1}</td> 
                        <td>${history.concertName}</td>
                        <td>${formattedDate}</td>
                        <td><button class="btn btn-danger btn-remove" data-id="${history.id}">Remove</button></td>
                    </tr>
                `;
            });
            
            $('#tbody').html(html);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching payment history data:", error);
        }
    });
    
    $(document).on('click', '.btn-remove', function(){
        let historyId = $(this).data('id');
        
        $.ajax({
            url: '/KonserKuy_v2/PaymentHistoryServlet',
            type: 'POST',
            data: {historyId: historyId},
            dataType: 'json',
            success: function (response) {
                if (response.status === "success") {
                alert(response.message);
                $(`button[data-id='${historyId}']`).closest('tr').remove();
            } else {
                alert(response.message);
            }
            }
        });
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
