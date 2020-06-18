function ucitajFilm(idFilm){
			document.cookie = "idFilm="+idFilm;
			$(location).attr('href', 'http://localhost:8080/Cinema/film.html');
		}

function logout(){
	alert("logout")
	document.cookie = "ulogovaniKorisnik=0";
	document.cookie = "uloga="+""; 
	$.get('http://localhost:8080/Cinema/LogOutServlet', {},function(data) {
		if(data.status == "success"){
				$(location).attr('href', 'http://localhost:8080/Cinema/login.html');
		}
	})
}

$(document).ready(function() {
	function readCookie(name){
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0; i<ca.length;i++){
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if(c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
		}
		return null;
	}
	hideButtons();
	function hideButtons(){
		var uloga = readCookie("uloga");
		if(uloga!=="ADMIN"){
			var korisnikAdmin = $('#korisniciAdmin');
			var dodajFilmAdmin = $('#dodajFilmAdmin');
			korisnikAdmin.hide();
			dodajFilmAdmin.hide();
		}
		if(uloga!="KORISNIK"){
			var korisnikKorisnik = $('#korisnikKorisnik');
			korisnikKorisnik.hide();
		}
	}

	ajaxGet();
	function ajaxGet() {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/FilmoviServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>Akcija</td><td>naziv</td><td>zanrovi</td><td>trajanje</td><td>distributer</td><td>zemljaPorekla</td><td>godinaProizvodnje</td></tr>'
						
					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.naziv);
						content+='<tr>';
						content+='<td> <button id="' + item.id + '" onClick="ucitajFilm('+item.id+')">Ucitaj film!</button></td>';
						content+='<td>' + item.naziv + '</td>';
						content+='<td>' + item.zanrovi + '</td>';
						content+='<td>' + item.trajanje + '</td>';
						content+='<td>' + item.distributer + '</td>';
						content+='<td>' + item.zemljaPorekla + '</td>';
						content+='<td>' + item.godinaProizvodnje + '</td>';
						content+=' </tr>';
					}
					content+='</table>';
					$("#filmovi").append(content);
						
				}
			}
		});
	}
	
	

});



