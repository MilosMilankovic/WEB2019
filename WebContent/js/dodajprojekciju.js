

$( document ).ready(function() {
       
        $("#DodajProjekciju").submit(function(event) {
            
            event.preventDefault();
            ajaxPost();
        });


        function ajaxPost(){

        	var filmInput = $('#filmInput');
        	var tipProjekcijeInput = $('#tipProjekcijeInput');
        	var salaInput = $('#salaInput');
        	var datumIvremeInput = $('#datumIvremeInput');
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
    				'datumIvreme' : datumIvremeInput.val(),
    				'cenaKarte' : cenaKarteInput.val(),
    				
    				
    			},
               
                success : function(result) {
                    if(result.status == "success"){
                        
                        $(location).attr('href', 'http://localhost:8080/Cinema/projekcije.html')
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
            $("#film").val("");
            $("#tipProjekcije").val("");
            $("#sala").val("");
            $("#datumIvreme").val("");
            $("#cenaKarte").val("");
            $("#administrator").val("");
            
        }
    })
    
    
    