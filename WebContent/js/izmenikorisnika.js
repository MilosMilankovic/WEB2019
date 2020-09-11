


$( document ).ready(function() {
	
	
	ajaxGet();
	function ajaxGet() {
		var idKorisnik = window.location.search.slice(1).split('?')[0].split('=')[1];
		//alert("idKorisnik" + idKorisnik);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/KorisnikServlet?idKorisnik="+idKorisnik,
			success : function(result) {
				if (result.status == "success") {
					var item = result.data;
					var ulogaRes = result.uloga;
					//alert(item.ime);
					
					 $("#imeInput").val(item.korisnickoIme);
			         $("#lozinkaInput").val(item.lozinka);
			         var uloga = item.uloga;
			         var ulogaInput = $('#ulogaInput');
			         if(ulogaRes === "ADMIN"){
			        	 ulogaInput.css("display","block");
			        	 $("#ulogaLabel").css("display","block");
			        	 //ulogaInput.show();
			        	 //$('#ulogaLabel').show();
			         }
			         ulogaInput.val(item.uloga);
						
				}
			}
		});
	}
	
	
	$("#IzmeniKorisnika").submit(function(event) {
        
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

    	
    	var imeInput = $('#imeInput');
    	var lozinkaInput = $('#lozinkaInput');
    	var ulogaInput = $('#ulogaInput');
    	var idKorisnik = window.location.search.slice(1).split('?')[0].split('=')[1];

        // DO POST
        $.ajax({
            type : "POST",
           
            url : "http://localhost:8080/Cinema/KorisniciServlet",
            data : {
            	'id' : idKorisnik,
				'ime' : imeInput.val(),
				'lozinka' : lozinkaInput.val(),
				'uloga' : ulogaInput.val(),
			},
           
            success : function(result) {
                if(result.status == "success"){
                    
                    $(location).attr('href', 'http://localhost:8080/Cinema/korisnici.html')
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