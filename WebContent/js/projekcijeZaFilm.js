function kupiKartu(idProjekcija){
	
	
	$(location).attr('href','http://localhost:8080/Cinema/kupiKartu.html?id='+idProjekcija)
};

$(document).ready(function() {
	
	
	ajaxGet();
	function ajaxGet() {
		var idFilm = window.location.search.slice(1).split('?')[0].split('=')[1];
		
		alert("idFilm" + idFilm);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/ProjekcijeZaFilm?idFilm="+idFilm,
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					var uloga = result.uloga;
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>film</td><td>datum i vreme</td><td>tip projekcije</td><td>sala</td><td>cena karte</td>'
					
					if (uloga==="KORISNIK") {
						content+='<td>Kupi kartu</td>'
					}
					content+='</tr>';
					
					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.film);
						content+='<tr>';
						content+='<td>' + item.film + '</td>';
						var date = new Date(item.datumIvreme);
						

						content+='<td>' + date.toLocaleString() + '</td>';
						content+='<td>' + item.tipProjekcije + '</td>';
						content+='<td>' + item.sala + '</td>';
						content+='<td>' + item.cenaKarte + '</td>';
						if (uloga==="KORISNIK"){
							content+='<td> <button id="' + item.id + '" onClick="kupiKartu('+item.id+')">Kupi kartu</button></td>';
						}
						content+='</tr>';
					}
					content+='</table>';
					$("#projekcije").append(content);
						
				}
			}
		});
	}

});