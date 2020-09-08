function obrisiKorisnika(idKorisnik) {
	$.get('http://localhost:8080/Cinema/ObrisiKorisnikaServlet', {
		"idKorisnik" : idKorisnik
	}, function(data) {
		if (data.status == "obrisan") {
			alert("proslo brisanje");
			$(location).attr('href', 'http://localhost:8080/Cinema/index.html')
		} else {
			alert("Dogodila se neka greska");
		}
	})
}

function izmeniKorisnika(idKorisnik) {

	
	$(location).attr('href', 'http://localhost:8080/Cinema/izmeniKorisnika.html?id='+idKorisnik)
};

$(document)
		.ready(
				function() {

					
					ajaxGet();
					function ajaxGet() {
						var idKorisnik = window.location.search.slice(1).split('?')[0].split('=')[1];
						
						alert("idKorisnik" + idKorisnik);
						$
								.ajax({
									type : "GET",
									url : "http://localhost:8080/Cinema/KorisnikServlet?idKorisnik="
											+ idKorisnik,
									success : function(result) {
										if (result.status == "success") {
											var item = result.data;
											var ulogovaniKorisnik = result.ulogovaniKorisnik;
											var uloga = result.uloga;
											var content = "";
											content += '<table width="50%" border="1">';

											content += '<tr><td>korisnicko Ime</td><td>datum Registracije</td><td>uloga</td><td>Izmeni</td>'
											if(uloga === "ADMIN"){
												content+='<td>Obrisi</td>';
											}
											content+='</tr>';
										
											console.log(item.korisnickoIme);
											content += '<tr>';
											content += '<td>'
													+ item.korisnickoIme
													+ '</td>';
											content += '<td>'
													+ new Date(
															item.datumRegistracije)
															.toLocaleString()
													+ '</td>';
											content += '<td>' + item.uloga
													+ '</td>';
											
											
											content += '<td> <button id="'
													+ item.id
													+ '" onClick="izmeniKorisnika('
													+ item.id
													+ ')">Izmeni korisnika</button></td>';
											
											if(uloga === "ADMIN"){
												
												if (Number(ulogovaniKorisnik) !== Number(item.id)){
													content += '<td> <button id="'
														+ item.id
														+ '" onClick="obrisiKorisnika('
														+ item.id
														+ ')">Obrisi korisnika</button></td>';
												}else{
													content+='<td></td>';
												}
												
											}
											
											content += '</tr>';
										
											content += '</table>';
											$("#korisnik").append(content);

										}
									}
								});
					}

				});
