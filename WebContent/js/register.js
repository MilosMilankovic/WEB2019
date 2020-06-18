

$(document).ready(function() {
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	
	


	var messageParagraph = $('#messageParagraph');

	$('#registerSubmit').on('click', function(event) {
		var userName = userNameInput.val();
		var password = passwordInput.val();
		
	
		
		console.log('userName: ' + userName);
		console.log('password: ' + password);
		
		
		var params = {
			'userName': userName, 
			'password': password
		}
		alert(userName)
		$.post('RegisterServlet', params, function(data) {
			console.log(data);

			if (data.status == 'failure') {
				messageParagraph.text(data.message);
				return;
			}
			if (data.status == 'success') {
				window.location.replace('index.html');
			}
		});

		event.preventDefault();
		return false;
	});
});
