$(document).ready(function() {

	ajaxGet();
	function ajaxGet() {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/ProjekcijaServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					var content="";
					content+='<table width="50%" border="1">';
					
					content+='<tr><td>film</td><td>datum i vreme</td><td>tip projekcije</td><td>sala</td><td>cena karte</td><td>Obrisi</td></tr>'
						
					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.film);
						content+='<tr>';
						content+='<td>' + item.film + '</td>';
						content+='<td>' + item.datumIvreme + '</td>';
						content+='<td>' + item.tipProjekcije + '</td>';
						content+='<td>' + item.sala + '</td>';
						content+='<td>' + item.cenaKarte + '</td>';
						content+='<td> ' + item.id + ' ' + item.film + ' <button id="' + item.id + '" onClick="obrisiProjekciju('+item.id+')">Obrisi projekciju</button></td>';
						content+='</tr>';
					}
					content+='</table>';
					$("#projekcije").append(content);
						
				}
			}
		});
	}

});
