var hasErrors = false;

$(".js-create-user").on("click", function()
{
	var obj = new Object();
	obj.firstName = $(".js-first-name").val();
	if(obj.firstName.match("^[a-zA-Z ]+$"))
	{
		$('.js-first-name').parent().removeClass("has-error")
	}else
	{
		$('.js-first-name').parent().addClass("has-error")
    	hasErrors = true
	}
	
	obj.lastName = $(".js-last-name").val();
	if(obj.lastName.match("^[a-zA-Z ]+$"))
	{
		$('.js-last-name').parent().removeClass("has-error")
	}else
	{
		$('.js-last-name').parent().addClass("has-error")
    	hasErrors = true
	}
	
	obj.mail= $(".js-mail").val();
	if(obj.mail.match("^[a-zA-Z0-9.\]*@[a-zA-Z0-9.\]*"))
	{
		$('.js-mail').parent().removeClass("has-error")
	}else
	{
		$('.js-mail').parent().addClass("has-error")
    	hasErrors = true
	}
	
	obj.role= $(".js-role").find(":selected").val();
	if(obj.role.length > 0)
	{
		$(".js-role").parent().removeClass("has-error")
	}else
	{
		$(".js-role").parent().addClass("has-error")
    	hasErrors = true
	}
	
	obj.password= $(".js-password").val();
	if(obj.password.length  > 0)
	{
		$('.js-password').parent().removeClass("has-error")
	}else
	{
		$(".js-password").parent().addClass("has-error")
    	hasErrors = true
	}
	
	
	if(hasErrors){
	    var obj = new Object();
	    obj.ressponseMessage = "Please fill in all fields in red";
	    obj.notoficationType = "ERROR";
	    obj.cleanDiv = false;
	    responsiveFeedback(obj);
	    }
	    else
	    {
	var myString = JSON.stringify(obj);
	$.ajax({
    	'url': 'createUser',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'data': myString,
        'type': 'POST',
        success:function(data) {
        	responsiveFeedback(data)
        	
          }
    })
	    }
})


//# sourceURL=createUser.js