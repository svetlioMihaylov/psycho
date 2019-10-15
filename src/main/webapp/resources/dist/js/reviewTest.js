
var currentQuestion = 1 ;
var init = true;

function loadGeneralTestInfo() { $.ajax({
    	'url': 'reviewTestdata',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        success:function(data) {
        	
        	$('.js-candidate-name').text("Candidate Name : " + data.candidateName);
        	$('.js-candidate-mail').text("Candidate Mail : " + data.candidateMail);
        	$('.js-test-tags').replaceWith(createList(data.questionTags));
        	
        	if(init)
        	{
        		init = false;
        		var questionSelectorDropDown = $(".js-qustion-selector");
        		for (i = 1; i <= data.numberOfQuestions; i++) { 
        			questionSelectorDropDown.append($("<option />").val(i).text(i));
        			}
        	}
        	
        	
        	
          }
    })
}
    
 function loadComments() { $.ajax({
            	'url': 'reviewQuesiton/' + currentQuestion,
                'headers': { 
                	'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'type': 'GET',
                success:function(data) {
                	
                	$('.js-multiple-choise').empty();
                	
                	$('.js-question-text').text(data.questionText);
                	if(data.questionType == "PLAIN_TEXT")
                	{
                		$('.js-question-answer').show();
                		$('.js-question-answer').text(data.answer);
                	}else
                	{
                		$('.js-question-answer').hide();
                		
                		$.each(data.multipleChoiseMap, function(answer, isChecked) { 
                			if(isChecked)
                			{
                				$('.js-multiple-choise').append('<input type="checkbox" value="' + answer + '" checked disabled="disabled">' + answer + '</input>');
                			}
                			else
                			{
                				$('.js-multiple-choise').append('<input type="checkbox" value="' + answer + '" disabled="disabled">' + answer + '</input>');
                			}
                			$('.js-multiple-choise').append('<br />');
                    	})
                    	
                	}
                	
                	$('.js-comment-area').empty();
                $.each(data.comments, function(index, comment) { 
                	$('.js-comment-area').append('<label>' + comment.author + ' commented on ' + comment.date + '</label>');
            		$('.js-comment-area').append('<textarea class="form-control disabled" style="cursor: default">' + comment.commentText + '</textarea>');
            		$('.js-comment-area').append('<br/>');
            		});
                $('.disabled').attr("disabled","disabled"); 
        	}
    })
}
loadComments();
loadGeneralTestInfo();

    $('select').change(function() {
    	$('.js-comment-area').empty();
    currentQuestion = $(this).val();
    loadComments();

});
        	
        	
$('.disabled').attr("disabled","disabled"); 

$('.js-inout-checkbox').attr("disabled","disabled");

$('.js-inout-checkbox').attr("checked","checked");

$('textarea').attr("resize", "none");


$(".js-add-comment").on("click", function()
{
	var request = new Object();
	var textarea = $(this).siblings('textarea');
	request.comment = textarea.val();
	request.numberId = currentQuestion;
	
	$.ajax({
    	'url': 'addComment',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'data': JSON.stringify(request),
        success:function(output) {
        	textarea.val("");
        	loadComments();
        	loadGeneralTestInfo();
        	
        }
	})
	
	
})

//# sourceURL=reviewTest.js
