function ucitajKorisnika(idKorisnik){
			
			$(location).attr('href', 'http://localhost:8080/Cinema/korisnik.html?id='+idKorisnik);
		}


$(document).ready(function() {
	
	ajaxGet();
	function ajaxGet() {
		
	
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/KorisniciServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>korisnicko Ime</td><td>datum Registracije</td><td>uloga</td></tr>'


					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.korisnickoIme);
						content+='<tr>';
						content+='<td> ' + item.id + ' ' + item.korisnickoIme + ' <button id=" ' + item.id + '" onClick="ucitajKorisnika('+item.id+')">Ucitaj!</button></td>';
						content+='<td>' + new Date(item.datumRegistracije).toLocaleString() + '</td>';
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


