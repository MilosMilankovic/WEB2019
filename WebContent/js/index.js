function ucitajFilm(idFilm){
			
			$(location).attr('href', 'http://localhost:8080/Cinema/film.html?id='+idFilm);
		}

function logout(){
	alert("logout")
	
	$.get('http://localhost:8080/Cinema/LogOutServlet', {},function(data) {
		if(data.status == "success"){
				$(location).attr('href', 'http://localhost:8080/Cinema/login.html');
		}
	})
}

$(document).ready(function() {
	//hideButtons();
	function hideButtons(uloga){
		console.log("Uloga je " + uloga)
		if(uloga!=="ADMIN"){
			var korisnikAdmin = $('#korisniciAdmin');
			var dodajFilmAdmin = $('#dodajFilmAdmin');
			var dodajProjekcijuAdmin = $('#dodajProjekcijuAdmin');
			korisnikAdmin.hide();
			dodajFilmAdmin.hide();
			dodajProjekcijuAdmin.hide();
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
					var uloga = result.uloga;
					hideButtons(uloga);
					var ulogovaniKorisnik = result.ulogovaniKorisnik;
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



