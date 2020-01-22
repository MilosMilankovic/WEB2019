$(document).ready(function() {

	
	
	
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
					
					content+='<tr><td>naziv</td><td>zanrovi</td><td>trajanje</td><td>distributer</td><td>zemljaPorekla</td><td>godinaProizvodnje</td></tr>'
						
					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.naziv);
						content+='<tr>';
						content+='<td> ' + item.id + ' ' + item.naziv + ' <button id="' + item.id + '" onClick="ucitajFilm('+item.id+')">Click Me!</button></td>';
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



