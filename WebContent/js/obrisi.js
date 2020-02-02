
//delete
$(document).on('click',"obrisiFilm", function(event){
	var idFilm = $(this).attr('id');
	
	var json = {
			'status': 'delete',
			'id': idFilm
	}
	var x=confirm("Are you shure ?");
	if(x){
		$.get('ObrisiFilmServlet',json,function(data){
			window.location.replace("index.html");
		});
	}else{
		return;
	}
	
	
	event.preventDefault();
	return false;
});