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
		var idKorisnik = readCookie("idKorisnik");
		alert("idKorisnik" + idKorisnik);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/KorisnikServlet?idKorisnik="+idKorisnik,
			success : function(result) {
				if (result.status == "success") {
					var item = result.data;
					//alert(item.ime);
					
					 $("#imeInput").val(item.korisnickoIme);
			         $("#lozinkaInput").val(item.lozinka);
			         $("#ulogaInput").val(item.uloga);
						
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
    	var idKorisnik = readCookie("idKorisnik");

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