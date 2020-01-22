$(document).ready(function() {

	ajaxGet();
	function ajaxGet() {
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/Cinema/KorisnikServlet",
			success : function(result) {
				if (result.status == "success") {
					var userId = $(this).attr('id');
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>korisnicko Ime</td><td>datum Registracije</td><td>uloga</td></tr>'


					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.korisnickoIme);
						content+='<tr>';
						content+='<td>' + item.korisnickoIme + '</td>';
						content+='<td>' + item.datumRegistracije + '</td>';
						content+='<td>' + item.uloga + '</td>';
						content+='</tr>';
					}
					content+='</table>';
					$("#korisnici").append(content);
						
				}
			}
		});
	}

});

$(document).on('click',".deleteButton", function(event){
	var userId = $(this).attr('id');
	
	var json = {
			'status': 'delete',
			'id': userId
	}
	var x=confirm("Are you shure ?");
	if(x){
		$.post('UserServlet',json,function(data){
			window.location.replace("users.html");
		});
	}else{
		return;
	}
	
	
	event.preventDefault();
	return false;
});