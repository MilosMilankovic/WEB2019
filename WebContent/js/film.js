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

function izmeniFilm(idFilm){
	
	document.cookie = "idFilm="+idFilm;
	$(location).attr('href','http://localhost:8080/Cinema/izmeniFilm.html')
};

function projekcijeZaFilm(idFilm){
	
	document.cookie = "idFilm="+idFilm;
	$(location).attr('href','http://localhost:8080/Cinema/projekcijeZaFilm.html')
};

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
			var dodajFilmAdmin = $('#dodajFilmAdmin');
			dodajFilmAdmin.hide();
		}
	}
	
	
	ajaxGet();
	function ajaxGet() {
		var idFilm = readCookie("idFilm");
		var uloga = readCookie("uloga");
		alert("idFilm" + idFilm);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/FilmServlet?idFilm="+idFilm,
			success : function(result) {
				if (result.status == "success") {
					var item = result.data;
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



