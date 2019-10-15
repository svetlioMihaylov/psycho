


$(".js-create-tag").on("click", function()
		{
	var tagName = $("input").val();
	
	$.ajax({
    	'url': 'createTag/' + tagName,
        'type': 'GET',
        success:function(data) {
        	
        	responsiveFeedback(data);
          }
    })
	
	
		})

//# sourceURL=createTag.js