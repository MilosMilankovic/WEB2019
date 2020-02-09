$( document ).ready(function() {
       
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
        	

            // DO POST
            $.ajax({
                type : "POST",
               
                url : "http://localhost:8080/Cinema/FilmoviServlet",
                data : {
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
                        alert("Dogodila se greska prilikom kreiranja ili update!");
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
    
    
    
    
    
    
