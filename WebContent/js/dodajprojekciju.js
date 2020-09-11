

$( document ).ready(function() {
	var idFilm = window.location.search.slice(1).split('?')[0].split('=')[1];
	//alert(idFilm);
	$('#filmInput').val(idFilm);
        $("#DodajProjekciju").submit(function(event) {
            
            event.preventDefault();
            ajaxPost();
        });


        function ajaxPost(){
        	
        	//var idFilm = window.location.search.slice(1).split('?')[0].split('=')[1];
        	//alert(idFilm);
        	var filmInput = $('#filmInput');
        	//$('#filmInput').val(idFilm);
        	var tipProjekcijeInput = $('#tipProjekcijeInput');
        	var salaInput = $('#salaInput');
        	
        	var datum = $('#datum');
        	var vreme = $('#vreme');
        	var cenaKarteInput = $('#cenaKarteInput');
        	var administratorInput = $('#administratorInput');
        	
        	

            // DO POST
            $.ajax({
                type : "POST",
               
                url : "http://localhost:8080/Cinema/DodajProjekcijuServlet",
                data : {
    				'film' : filmInput.val(),
    				'tipProjekcije' : tipProjekcijeInput.val(),
    				'sala' : salaInput.val(),
    				'datumIvreme' : datum.val() + 'T' + vreme.val(),
    				'cenaKarte' : cenaKarteInput.val(),
    				
    				
    			},
               
                success : function(result) {
                    if(result.status == "success"){
                        
                        $(location).attr('href', 'http://localhost:8080/Cinema/projekcije.html')
                    }else{
                    	 alert("Dogodila se greska prilikom kreiranja!");
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
            $("#film").val("");
            $("#tipProjekcije").val("");
            $("#sala").val("");
            $("#datumIvreme").val("");
            $("#cenaKarte").val("");
            $("#administrator").val("");
            
        }
        
       
    	
    })
    
    
    