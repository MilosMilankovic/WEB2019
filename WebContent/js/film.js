function obrisiFilm(idFilm){
		$.get('http://localhost:8080/Cinema/ObrisiFilmServlet', {"idFilm":idFilm},function(data) {
			if(data.status == "obrisan"){
				alert("proslo brisanje");
				$(location).attr('href','http://localhost:8080/Cinema/index.html')
			}else{
				alert("Dogodila se neka greska");
			}
		})
	}

function logout(){
	alert("logout")
	
	$.get('http://localhost:8080/Cinema/LogOutServlet', {},function(data) {
		if(data.status == "success"){
				$(location).attr('href', 'http://localhost:8080/Cinema/login.html');
		}
	})
}



function izmeniFilm(idFilm){
	
	
	$(location).attr('href','http://localhost:8080/Cinema/izmeniFilm.html?id='+idFilm)
};

function projekcijeZaFilm(idFilm){
	
	
	$(location).attr('href','http://localhost:8080/Cinema/projekcijeZaFilm.html?id='+idFilm)
};



$(document).ready(function() {	
	
	//hideButtons();
	function hideButtons(uloga){
		
		
		if(uloga!=="ADMIN"){
			var dodajFilmAdmin = $('#dodajFilmAdmin');
			dodajFilmAdmin.hide();
		}
	}
	
	
	ajaxGet();
	function ajaxGet() {
		//alert("ajaxget");
		
		var idFilm = window.location.search.slice(1).split('?')[0].split('=')[1];
		console.log("id film from href -> " + idFilm);
		
		//alert("idFilm" + idFilm);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/FilmServlet?idFilm="+idFilm,
			success : function(result) {
				if (result.status == "success") {
					var item = result.data;
					var uloga = result.uloga;
					hideButtons(uloga);
					var ulogovaniKorisnik = result.ulogovaniKorisnik;
					var content="";
					content+='<table width="50%" border="1">';
					
					if(uloga==="ADMIN"){
						content+='<tr><td>naziv</td><td>zanrovi</td><td>glumci</td><td>reziser</td><td>trajanje</td><td>distributer</td><td>zemljaPorekla</td><td>godinaProizvodnje</td><td>Obrisi</td><td>Menjaj</td><td>Projekcije</td></tr>'
					}else{
						content+='<tr><td>naziv</td><td>zanrovi</td><td>glumci</td><td>reziser</td><td>trajanje</td><td>distributer</td><td>zemljaPorekla</td><td>godinaProizvodnje</td><td>Projekcije</td></tr>'
							
					}	
				
						console.log(item.naziv);
						content+='<tr>';
						content+='<td>' + item.naziv + '</td>';
						content+='<td>' + item.zanrovi + '</td>';
						content+='<td>' + item.glumci + '</td>';
						content+='<td>' + item.reziser + '</td>';
						content+='<td>' + item.trajanje + '</td>';
						content+='<td>' + item.distributer + '</td>';
						content+='<td>' + item.zemljaPorekla + '</td>';
						content+='<td>' + item.godinaProizvodnje + '</td>';
						/*content+='<td> ' + item.id + ' ' + item.naziv + ' <button id="' + item.id + '" onClick="obrisiFilm('+item.id+')">Obrisi film</button></td>';*/
						
						if(uloga==="ADMIN"){
							content+='<td> <button id="' + item.id + '" onClick="obrisiFilm('+item.id+')">Obrisi film</button></td>';
							content+='<td> <button id="' + item.id + '" onClick="izmeniFilm('+item.id+')">Izmeni film</button></td>';							
							
						}
						
						content+='<td> <button id="' + item.id + '" onClick="projekcijeZaFilm('+item.id+')">Ucitaj projekcije</button></td>';
						content+=' </tr>';
					//}
					content+='</table>';
					$("#film").append(content);
						
				}
			}
		});
	}

});
