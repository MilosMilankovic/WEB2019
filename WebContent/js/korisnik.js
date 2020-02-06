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

	document.cookie = "idKorisnik=" + idKorisnik;
	$(location).attr('href', 'http://localhost:8080/Cinema/izmeniKorisnika.html')
};

$(document)
		.ready(
				function() {

					function readCookie(name) {
						var nameEQ = name + "=";
						var ca = document.cookie.split(';');
						for (var i = 0; i < ca.length; i++) {
							var c = ca[i];
							while (c.charAt(0) == ' ')
								c = c.substring(1, c.length);
							if (c.indexOf(nameEQ) == 0)
								return c.substring(nameEQ.length, c.length);
						}
						return null;
					}

					ajaxGet();
					function ajaxGet() {
						var idKorisnik = readCookie("idKorisnik");
						alert("idKorisnik" + idKorisnik);
						$
								.ajax({
									type : "GET",
									url : "http://localhost:8080/Cinema/KorisnikServlet?idKorisnik="
											+ idKorisnik,
									success : function(result) {
										if (result.status == "success") {
											var item = result.data;
											var content = "";
											content += '<table width="50%" border="1">';

											content += '<tr><td>korisnicko Ime</td><td>datum Registracije</td><td>uloga</td><td>Obrisi</td><td>Izmeni</td></tr>'

										
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
													+ '" onClick="obrisiKorisnika('
													+ item.id
													+ ')">Obrisi korisnika</button></td>';
											content += '<td> <button id="'
													+ item.id
													+ '" onClick="izmeniKorisnika('
													+ item.id
													+ ')">Izmeni korisnika</button></td>';
											content += '</tr>';
										
											content += '</table>';
											$("#korisnik").append(content);

										}
									}
								});
					}

				});
