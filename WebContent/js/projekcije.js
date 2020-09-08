function kupiKartu(idProjekcija){
	
	
	$(location).attr('href','http://localhost:8080/Cinema/kupiKartu.html?id='+idProjekcija)
};

function obrisiProjekciju(idProjekcija){
		$.get('http://localhost:8080/Cinema/ObrisiProjekcijuServlet', {"idProjekcija":idProjekcija},function(data) {
			if(data.status == "obrisan"){
				alert("proslo brisanje");
				$(location).attr('href','http://localhost:8080/Cinema/projekcije.html')
			}else{
				alert("Dogodila se neka greska");
			}
		})
	}

$(document).ready(function() {

	
	//hideButtons();
	function hideButtons(uloga){
		
		if(uloga!=="ADMIN"){
			var dodajProjekcijuAdmin = $('#dodajProjekcijuAdmin');
			dodajProjekcijuAdmin.hide();
		}
	}
	ajaxGet();
	function ajaxGet() {
		
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/ProjekcijaServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					var uloga = result.uloga;
					hideButtons(uloga);
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>film</td><td>datum i vreme</td><td>tip projekcije</td><td>sala</td><td>cena karte</td>'
					if(uloga==="ADMIN"){
						content+='<td>Obrisi</td>'
					}
					if(uloga==="KORISNIK"){
						content+='<td>Kupi</td>'
					}
					content+='</tr>'
						
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
						if(uloga==="ADMIN"){
							content+='<td> <button id="' + item.id + '" onClick="obrisiProjekciju('+item.id+')">Obrisi projekciju</button></td>';
							
						}
						if(uloga==="KORISNIK"){
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
