$(document).ready(function() {
	
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	
	
	$('#loginSubmit').on('click', function(event) { 
		var userName = userNameInput.val();
		var password = passwordInput.val();
		alert(userName);
		
		$.post('LoginServlet', {'username': userName, 'password': password}, function(data) {
			console.log(data);
			if (data.status == 'success') {
				document.cookie = "ulogovaniKorisnik="+data.ulogovaniKorisnik;
				document.cookie = "uloga="+data.uloga;
				window.location.replace('index.html');
			}
			if (data.status == 'failure') {
				alert("Selected incorrect data!");
			}
		});
		

		event.preventDefault();
		return false;
	});
});