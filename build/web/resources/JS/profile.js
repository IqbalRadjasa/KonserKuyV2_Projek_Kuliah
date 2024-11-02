$(document).ready(function(){
   $('#phone_number').on('input', function (){
      var phoneNumber = $(this).val();
      var isValid = /^[0-9]*$/.test(phoneNumber);

      if (phoneNumber.length > 12 || phoneNumber.length < 12 || !isValid) {
          $('#phone_error').removeClass('d-none'); 
      } else {
          $('#phone_error').addClass('d-none'); 
      }
   });
   
    $.ajax({
        url: '../UserServlet',
        type: 'GET',
        dataType: 'json',
        beforeSend: function (){
                $('#userId').val('Fetching data...');
                $('#username').val('Fetching data...');
                $('#email').val('Fetching data...');
                $('#password').val('Fetching data...');
                $('#address').val('Fetching data...');
                $('#phone_number').val('Fetching data...');
                $('#substrict').val('Fetching data...');
                $('#ward').val('Fetching data...');
        },
        success: function(response) {
            if (response.status === 'success') {
                $('#userId').val(response.userId);
                $('#username').val(response.username);
                $('#email').val(response.email);
                $('#password').val(response.password);
                
                let address = response.address !== null ? response.address : '';
                let phoneNumber = response.phoneNumber !== null ? response.phoneNumber : '';
                let substrict = response.substrict !== null ? response.substrict : '';
                let ward = response.ward !== null ? response.ward : '';

                $('#address').val(address);
                $('#phone_number').val(phoneNumber);
                $('#substrict').val(substrict);
                $('#ward').val(ward);
            } else {
                console.log(response.message); 
            }
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch session data:', error);
        }
    });
});

$('#btn-update').click(function(){
    $.ajax({
        url: '../UserServlet',
        type: 'POST',
        data: {
            userId: $('#userId').val(),
            address: $('#address').val(),
            phoneNumber: $('#phone_number').val(),
            subdistrict: $('#substrict').val(),
            ward: $('#ward').val()
        },
        beforeSend: function (xhr) {
            $('#update-text').addClass('d-none');
            $('#wait-text').removeClass('d-none');
        },
        success: function (response) {
            $('#wait-text').addClass('d-none');
            $('#update-text').removeClass('d-none');
            alert(response); 
            window.location.reload();
        },
        error: function (xhr, status, error) {
            alert('Error updating user: ' + error);
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
