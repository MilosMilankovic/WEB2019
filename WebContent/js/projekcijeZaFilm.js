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
					var filmoviList = result.filmoviSaProjekcijaList;
					var tipoviProjekcijaList =  result.tipoviProjekcija;
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
						//content+='<td>' + item.film + '</td>';
						for(var j=0, sizeOfList=filmoviList.length; j < sizeOfList; j++){
							var film = filmoviList[j];
							if(film.id === item.film){
								content+='<td><a href="http://localhost:8080/Cinema/film.html?id=' +  item.film +'"> '+ film.naziv+ '</a></td>';
								break;
							}
						}
						var date = new Date(item.datumIvreme);
						

						content+='<td>' + date.toLocaleString() + '</td>';
						for(var j=0, sizeOfList=tipoviProjekcijaList.length; j < sizeOfList; j++){
							var tipProjekcije = tipoviProjekcijaList[j];
							if(tipProjekcije.id === item.tipProjekcije){
								content+='<td>' + tipProjekcije.naziv + '</td>';
								break;
							}
						}
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