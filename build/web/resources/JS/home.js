$(document).ready(function() {
    const carousel = new bootstrap.Carousel('#carouselExampleDark', {
        interval: 5000,
        ride: 'carousel' 
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


