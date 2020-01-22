$(document).ready(function() {

	ajaxGet();
	function ajaxGet() {
		var idFilm = readCookie("idFilm");
		alert("idFilm" + idFilm);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/FilmServlet?idFilm="+idFilm,
			success : function(result) {
				if (result.status == "success") {
					var item = result.data;
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>naziv</td><td>zanrovi</td><td>glumci</td><td>reziser</td><td>trajanje</td><td>distributer</td><td>zemljaPorekla</td><td>godinaProizvodnje</td><td>Obrisi</td><td>Menjaj</td></tr>'
						
					/*for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];*/
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
						content+='<td> ' + item.id + ' ' + item.naziv + ' <button id="' + item.id + '" onClick="obrisiFilm('+item.id+')">Obrisi film</button></td>';
						content+='<td> ' + item.id + ' ' + item.naziv + ' <button id="' + item.id + '" onClick="izmeniFilm('+item.id+')">Izmeni film</button></td>';
						content+=' </tr>';
					//}
					content+='</table>';
					$("#film").append(content);
						
				}
			}
		});
	}

});



