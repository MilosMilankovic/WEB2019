$(document).ready(function() {
	
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	
	
	$('#registerSubmit').on('click', function(event) { 
		var userName = userNameInput.val();
		var password = passwordInput.val();
		
			$.post('RegisterServlet', {'username': userName, 'password': password}, function(data) {
				console.log(userName + password);
				if (data.status == 'success') {
					window.location.replace('index.html');
				}
				if (data.status == 'failure') {
					message.fadeIn();
					message.text("Selected incorrect data!");
				}
			
		
			
		
		
			});
	
	});

});
			
