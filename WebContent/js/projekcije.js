function kupiKartu(idProjekcija){
	
	document.cookie = "idProjekcija="+idProjekcija;
	$(location).attr('href','http://localhost:8080/Cinema/kupiKartu.html')
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
			var dodajProjekcijuAdmin = $('#dodajProjekcijuAdmin');
			dodajProjekcijuAdmin.hide();
		}
	}
	ajaxGet();
	function ajaxGet() {
		var uloga = readCookie("uloga");
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/ProjekcijaServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
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
