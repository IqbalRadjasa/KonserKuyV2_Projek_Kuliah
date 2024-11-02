$(document).ready(function() {
    $.ajax({
        url: '../EventServlet', // Ensure the URL is correct
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let html = ''; 
            $.each(data, function(index, concert) {
                let imageUrl = concert.concertView.image || 'https://via.placeholder.com/200x300'; 
                let title = concert.name || 'Concert Title';
                let description = concert.desc || 'Some quick example text to build on the card title and make up the bulk of the card\'s content.'; // Ensure description is provided
                
                // Format the concert date
                let formattedDate = formatDate(concert.dateOfConcert);
                
                html += `
                    <div class="col-4 mb-4">
                        <div class="card hover-card" style="width: 30rem; display: flex; flex-direction: row; background-color: #FFF5E4;">
                            <img src="../resources/IMG/concert/${imageUrl}" class="card-img-top hover-image" alt="${title}" style="width: 200px; height: 300px; object-fit: cover; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px;"/>
                            <div class="card-body" style="width: 12.5rem;">
                              <h5 class="card-title">${title}</h5>
                              <p class="card-text">Play Date: ${formattedDate}</p>
                              <button type="button" class="btn btn-dark btn-detail" data-concert="${concert.id}">See Detail</button>
                            </div>
                        </div>
                    </div>
                `;
            });
            $('#upcoming').html(html); // Insert dynamically created cards into the page
        },
        error: function(xhr, status, error) {
            console.error('Error fetching concert data:', error);
        }
    });
 
    $(document).on('click', '.btn-detail', function() {
        let concertId = $(this).data('concert');
        localStorage.setItem('concertId', concertId);
        
        window.location.href = "../protected/preview.xhtml";
    });
});
 
$('#logout-btn').on('click', function() {
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

// Function to convert date to a readable format (Month Name, Day, Year)
function formatDate(dateString) {
    var date = new Date(dateString); // Convert to a Date object
    
    // Array of month names
    var monthNames = [
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];
    
    var day = date.getDate(); // Get the day of the month (1-31)
    var monthIndex = date.getMonth(); // Get the month index (0-11)
    var year = date.getFullYear(); // Get the year (YYYY)
    
    // Format: Month Name Day, Year (e.g., October 1, 2024)
    return monthNames[monthIndex] + ' ' + day + ', ' + year;
}
