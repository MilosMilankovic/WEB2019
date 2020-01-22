/*
$(document).ready(function() {
	

  	var nazivInput = $('#nazivInput');
	var reziserInput = $('#reziserInput');
	var glumciInput = $('#glumciInput');
	var trajanjeInput = $('#trajanjeInput');
	var zanroviInput = $('#trajanjeInput');
	var distributerInput = $('#distributerInput');
	var zemljaporeklaInput = $('#zemljaporeklaInput');
	var godinaproizvodnjeInput = $('#godinaproizvodnjeInput');
	var opisInput = $('#opisInput');
	
	
	
	$('#dodajfilmSubmit').on('click', function(event) { 
		var naziv = nazivInput.val();
		var reziser = reziserInput.val();
		var glumci = glumciInput.val();
		var zanrovi = zanroviInput.val();
		var trajanje = trajanjeInput.val();
		var distributer = distributerInput.val();
		var zemljaporekla = zemljaporeklaInput.val();
		var godinaproizvodnje = godinaproizvodnjeInput.val();
		var opis = opisInput.val();
		alert(naziv);
		
		$.post('DodajFilmServlet', {'naziv': naziv, 'reziser': reziser, 'glumci': glumci, 'zanrovi': zanrovi, 'trajanje': trajanje, 'distributer': distributer, 'zemljaporekla': zemljaporekla, 'godinaproizvodnje': godinaproizvodnje, 'opis': opis,}, function(data) {
			console.log(data);
			if (data.status == 'success') {
				window.location.replace('index.html');
			}
			if (data.status == 'failure') {
				alert("Selected incorrect data!");
			}
		});
		

		event.preventDefault();
		return false;
	});
});


*/



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
                        $("#postResultDiv").html("<strong>Dogodila se greska prilikom registracije ili takav korisnik vec postoji!</strong>");
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

        function resetData(){
            $("#naziv").val("");
            $("#reziser").val("");
            $("#glumci").val("");
            $("#zanrovi").val("");
            $("#trajanje").val("");
            $("#distributer").val("");
            $("#zemljaPorekla").val("");
            $("#godina").val("");
            $("#opis").val("");
            
        }
    })
    
    
    
    
    
    
