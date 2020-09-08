$(document).ready(function() {
	
	
	ajaxGet();
	function ajaxGet() {
		var idProjekcija = window.location.search.slice(1).split('?')[0].split('=')[1];
		alert("idProjekcija" + idProjekcija);
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Cinema/SlobodnaSedistaServlet?idProjekcija="+idProjekcija,
			success : function(result) {
				if (result.status == "success") {
					var list = result.dataList;
					
					var select = document.getElementById('sedista');
					for (var i = 0, size = list.length; i < size; i++) {
						var item = list[i];
						console.log(item.film);
						var opt = document.createElement('option');
					    opt.value = item.redniBroj;
					    opt.innerHTML = 'Sediste broj ' + item.redniBroj + ' u sali ' + item.sala;
					    select.appendChild(opt);
					}
					
						
				}
			}
		});
	}
	
	$("#odabirSedista").submit(function(event) {
        
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

    	
    	var sedistaInput = $('#sedista').val();
    	var idProjekcija = window.location.search.slice(1).split('?')[0].split('=')[1];
    	
    	console.log(sedistaInput + " <--- ");
        // DO POST
        $.ajax({
            type : "POST",
           
            url : "http://localhost:8080/Cinema/KupiKartuServlet",
            data : {
				'sedistaInput' : sedistaInput.toString(),
				'idProjekcija' : idProjekcija
				//,"idKorisnik" : idKorisnik
			},
           
            success : function(result) {
                if(result.status == "success"){
                    alert("ok");
                    $(location).attr('href', 'http://localhost:8080/Cinema/index.html')
                }else{
                   alert("Dogodila se greska prilikom kupovine, proverite da li su sedista odabrana za redom!");
                }
            },
            error : function(e) {
                alert("Error!");
                console.log("ERROR: ", e);
            }
        });

       

    }

});