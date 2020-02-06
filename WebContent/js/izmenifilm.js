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

$( document ).ready(function() {
	
	
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
					alert(item.naziv);
					
					 $("#nazivInput").val(item.naziv);
			         $("#reziserInput").val(item.reziser);
			         $("#glumciInput").val(item.glumci);
			         $("#zanroviInput").val(item.zanrovi);
			         $("#trajanjeInput").val(item.trajanje);
			         $("#distributerInput").val(item.distributer);
			         $("#zemljaPoreklaInput").val(item.zemljaPorekla);
			         $("#godinaInput").val(item.godinaProizvodnje);
			         $("#opisInput").val(item.opis);
					
					
						
				}
			}
		});
	}
	
	$("#DodajFilm").submit(function(event) {
        
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

    	var nazivInput = $('#nazivInput');
    	var reziserInput = $('#reziserInput');
    	var glumciInput = $('#glumciInput');
    	var zanroviInput = $('#zanroviInput');
    	var trajanjeInput = $('#trajanjeInput');
    	var distributerInput = $('#distributerInput');
    	var zemljaPoreklaInput = $('#zemljaPoreklaInput');
    	var godinaInput = $('#godinaInput');
    	var opisInput = $('#opisInput');
    	var idFilm = readCookie("idFilm");

        // DO POST
        $.ajax({
            type : "POST",
           
            url : "http://localhost:8080/Cinema/FilmoviServlet",
            data : {
            	'id' : idFilm,
				'naziv' : nazivInput.val(),
				'reziser' : reziserInput.val(),
				'glumci' : glumciInput.val(),
				'zanrovi' : zanroviInput.val(),
				'trajanje' : trajanjeInput.val(),
				'distributer' : distributerInput.val(),
				'zemljaPorekla' : zemljaPoreklaInput.val(),
				'godinaProizvodnje' : godinaInput.val(),
				'opis' : opisInput.val()
			},
           
            success : function(result) {
                if(result.status == "success"){
                    
                    $(location).attr('href', 'http://localhost:8080/Cinema/index.html')
                }else{
                    $("#postResultDiv").html("<strong>Dogodila se greska prilikom izmene!</strong>");
                }
            },
            error : function(e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }
       
});      
    
    
    
    

