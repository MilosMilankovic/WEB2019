$( document ).ready(function() {
       
        $("#RegisterForm").submit(function(event) {
            
            event.preventDefault();
            ajaxPost();
        });


        function ajaxPost(){

        	var userNameInput = $('#userNameInput');
        	var passwordInput = $('#passwordInput');

            // DO POST
            $.ajax({
                type : "POST",
               
                url : "http://localhost:8080/Cinema/RegisterServlet",
                data : {
    				'username' : userNameInput.val(),
    				'password' : passwordInput.val()
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
            $("#username").val("");
            $("#userPassword").val("");
        }
    })


