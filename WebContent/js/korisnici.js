$(document).ready(function() {

	ajaxGet();
	function ajaxGet() {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/KorisnikServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>korisnicko ime</td><td>datum registracije</td><td>uloga</td></tr>'


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
