function logout(){
	alert("logout")
	
	$.get('http://localhost:8080/Cinema/LogOutServlet', {},function(data) {
		if(data.status == "success"){
				$(location).attr('href', 'http://localhost:8080/Cinema/login.html');
		}
	})
}


$( document ).ready(function() {
		
	ajaxGet();
	function ajaxGet() {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/FilmoviServlet",
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					
					var content="";
					content+='<td align="right">Filmovi:</td><td>';
					content+='<select id="filmoviInput" name="filmovi">';
						
					
					var options_str = "";
					list.forEach( function(film) {
						  options_str += '<option value="' + film.id + '">' + film.naziv + '</option>';
					});
					console.log("opcije " + options_str);
					content+=options_str;
					content+='<select></td>';
					
					console.log("content"+content)
					$("#film").append(content);
					
					var uloga = result.uloga;
					console.log("ucitana lista"+list);
					var ulogovaniKorisnik = result.ulogovaniKorisnik;
						
				}
			}
		});
	}
	
        $("#DodajProjekciju").submit(function(event) {
            
            event.preventDefault();
            ajaxPost();
        });


        function ajaxPost(){
        	var filmInput = $('#filmoviInput');
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
    
    
    