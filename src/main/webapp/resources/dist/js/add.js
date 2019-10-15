
    ////////////////////////////////////////  class ='js-multiple-choice-answer'
    

$(".js-save-question").on("click", function()
{
	var hasErrors = false;
    var question = new Object();
    question.questionTags = new Array();
    
    var questionText = $('textarea').val();
    if(questionText.length == 0 )
    {
    	$('textarea').parent().addClass("has-error")
    	hasErrors = true
    }else
    {
    	question.questionText =questionText;
    	$('textarea').parent().removeClass("has-error")
    }
    

     
    $('.js-selected-tags').children('button').each(function () {     
    	question.questionTags.push($(this).text().trim());   
    });
    if(question.questionTags.length == 0)
    {
    	$('.js-drop-down-add-to-list').parent().addClass("has-error");
    	hasErrors = true;
    }
    else
    {
    	$('.js-drop-down-add-to-list').parent().removeClass("has-error");
    }


    var myString = JSON.stringify(question);
    if(hasErrors){
    var obj = new Object();
    obj.ressponseMessage = "Please fill in all fields in red";
    obj.notoficationType = "ERROR";
    obj.cleanDiv = false;
    responsiveFeedback(obj);
    }
    else
    {
    $.ajax({
    	'url': 'addQuestionPost',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'data': myString,
        'type': 'POST',
        success:function(data) {
        	responsiveFeedback(data);
            }
            	
    })
}
    
})

//# sourceURL=add.js