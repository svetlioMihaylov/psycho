$('textarea').hide();


var currentQuestion;
var currentQuestionType;

$('#myModal').on('shown', function (e) {
//  $('body').on('click', function(e) {
    e.stopPropagation();
//  });
})

$('#myModal').on('hidden', function () {
  $('body').off('click');
});


$('#myModal').modal({
    show: true
});

$('.js-validate-user').on('click', function() {
	var url = document.URL;
	var obj = new Object();
	if(url.endsWith('#'))
	{
		obj.id = url.slice(url.indexOf('test/') + 5, -1);
	}
	else{
		obj.id = url.substr(url.indexOf('test/') + 5);
	}
	obj.mail = $('.modal-body input').val();
	$.ajax({
    	'url': '/my-artifactId/validatemail',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'data': JSON.stringify(obj),
        'type': 'POST',
        success:function(data) {
        	
        	$('#myModal').modal('hide');
        	
        	for (i = 1; i <= data.questionCount; i++) { 
        		$("#side-menu").append('<li><a href="#">' + i + '</a></li>');
        	}
        	
        	displayQuestion(data);
        	currentQuestion = 1;
        	$('h2').html("Question : "+ currentQuestion);
          }
    })
	
})

function displayQuestion(data) {
	
	currentQuestionType = data.questionType;
	$('textarea').hide();
	$('.js-multiple-choice').empty();
	$('label').text(data.qustionText);
	if(data.questionType == "PLAIN_TEXT")
	{
		$('textarea').val(data.questionAnswer);
		$('textarea').show();
	}
	else{
		$.each(data.multipleChoice, function(answer, isChecked) { 
			if(isChecked)
			{
				$('.js-multiple-choice').append('<input type="checkbox" value="' + answer + '" checked>' + answer + '</input>');
			}
			else
			{
				$('.js-multiple-choice').append('<input type="checkbox" value="' + answer + '">' + answer + '</input>');
			}
			$('.js-multiple-choice').append('<br />');
    	})
		
	}
}

$('.js-save-answer').on('click', function() {
	
	
	var obj = new Object();
	if(currentQuestionType == "PLAIN_TEXT")
	{
		obj.answer = $(this).siblings('textarea').val();
	}
	else
	{
		obj.multipleChoise = new Array();
		
		$('.js-multiple-choice').children('input:checked').each(function () {
			obj.multipleChoise.push($(this).attr('value'));
		});
	}
	obj.number = currentQuestion;
		
	
	
	$.ajax({
    	'url': '/my-artifactId/answer',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'data': JSON.stringify(obj),
        'type': 'POST',
        success:function(data) {
        	responsiveFeedback(data);
          }
    })
})

$('body').on('click', 'a', function() {
	var answerNumber = $(this).text();
	
	$.ajax({
    	'url': '/my-artifactId/getquestion/' + answerNumber ,
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        success:function(data) {
        	
        	displayQuestion(data);
        	currentQuestion = answerNumber;
        	$('h2').html("Question : "+ currentQuestion);
          }
    })
	
})

$('.js-finish-test').on('click', function() {

	$('#completeTestModal').modal('show');
	
})

$('.js-complete-test').on('click', function() {
	
	$.ajax({
    	'url': 'complete' ,
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        success:function(data) {
        	$('#dashboard').empty();
        	$('button').prop('disabled', true);
        	$('#side-menu').empty();
        	$('h2').html("Thank you for submitting the test. Our HR team will get in touch with you with the results.");
        	$('#completeTestModal').modal('hide');
          }
    })
	
})

$('#alert').hide();

var map = new Object();
map['INFO'] = 'alert-info';
map['WARNING'] = 'alert-warning';
map['ERROR'] = 'alert-danger';


function responsiveFeedback(data) {
	
	$('#alert').show();
	$('#alert').addClass(map[data.notoficationType]);
	$('#aler-label').html(data.ressponseMessage);
	
	if(data.cleanDiv)
	{
		 $("#targetid").empty();
	}
	
	$('.alert-dismissable').fadeTo(2000, 500).slideUp(500, function(){
        $('.alert-dismissable').slideUp(500);
    }); 

}

//# sourceURL=resolveTest.js