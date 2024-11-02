$(document).ready(function() {
    var username_input = $('#username_login');
    var regex = /[^a-zA-Z0-9]/;

    username_input.on('input', function() {
        if (regex.test(username_input.val())) {
            $('#username-error').text('Username cannot contain symbols.');
        } else {
            $('#username-error').text(''); 
        }
    });

    var username_register_input = $('#username_register');
    var regex = /[^a-zA-Z0-9]/;

    username_register_input.on('input', function() {
        if (regex.test(username_register_input.val())) {
            $('#username-register-error').text('Username cannot contain symbols.');
        } else {
            $('#username-register-error').text(''); 
        }
    });

    var email_register_input = $('#email-register');

    email_register_input.on('input', function() {
        var email = email_register_input.val();

        if (!email.includes('@')) {
            $('#email-register-error').text('Incorrect email format. Email must contain @.');
        } else {
            $('#email-register-error').text('');
        }
    });

    var prePassword = $('#pre-password');
    var confirmPassword = $('#confirm-password');

    confirmPassword.on('input', function(){
        if(confirmPassword.val() !== prePassword.val()){
            $('#confirm-password-error').text('Password are not same.');
        }else{
            $('#confirm-password-error').text('');
        }
    });
});

    $('#register-btn').click(function() {
        $('#form-login').addClass('fadeOut').addClass('d-none');

        $('#form-register').removeClass('fadeOut').removeClass('d-none').addClass('fadeIn');
    });

    $('#btn-back').click(function() {
        $('#form-register').addClass('fadeOut').addClass('d-none');

        $('#form-login').removeClass('fadeOut').removeClass('d-none').addClass('fadeIn');
    });

    $('#btn-login').click(function (e){
       $.ajax({
          url: 'loginServlet',
          type: 'POST',
          data: {
              username: $('#username_login').val(),
              password: $('#password_login').val()
          },
          dataType: 'json',
          beforeSend: function (xhr) {
              $('#login-text').addClass('d-none');
              $('.container-loader').removeClass('d-none');
          },
          success: function (res) {
            if(res.status === 'success'){
                sessionStorage.setItem("userId", res.userId);
                
                $('#login-text').removeClass('d-none');
                $('.container-loader').addClass('d-none');
                
                window.location.href = 'protected/home.xhtml';
            }else{
                alert('Incorrect Username or Password');
            }
          },error: function(){
                alert('Incorrect Username or Password');
          }
       });
    });

    $('#btn-submit').click(function (e){
        $.ajax({
           url: 'registerServlet',
           type: 'POST',
           data: {
               username: $('#username_register').val(),
               email: $('#email-register').val(),
               password: $('#pre-password').val()
           },
            beforeSend: function () {
                alert('Submitting your registration...');
            },
           dataType: 'json',
           success: function (res) {
             if(res.status === 'success'){
                alert('Registration successful!');
                 window.location.reload();
             }else{
                 alert('Incorrect Username or Password');
             }
           }
        });
    });