
$(".js-add-question").on("click", function(){
        $("#targetid").load("addQuestion");
});

$(".js-create-test").on("click", function(){
    $("#targetid").load("createTest");
});

$(".js-show-questions").on("click", function(){
    console.log("show all question");
    $("#targetid").load("showAllQuestions");
});

$(".js-create-test").on("click", function(){
    $("#targetid").load("createTest");
});

$(".js-create-user").on("click", function(){
    $("#targetid").load("createUser");
});

$(".js-add-tag").on("click", function(){
    $("#targetid").load("createTag");
});

//$.ajax({
//	'url': 'testsForReview',
//    'headers': {
//    	'Accept': 'application/json',
//        'Content-Type': 'application/json'
//    },
//    'type': 'GET',
//    success:function(result) {
//
//    	$.each(result, function(index, test) {
//    		$('.nav-second-level').append('<li><a class= "js-review-test" href="#" data= "' + test.key + '">' + test.value + '</a></li>');
//    	})
//
//    	$(".js-review-test").on("click", function(){
//    	    $("#targetid").load("reviewTest/" + $(this).attr('data'));
//    	});
//}
//})

$('#alert').hide();

var map = new Object();
map['INFO'] = 'alert-info';
map['WARNING'] = 'alert-warning';
map['ERROR'] = 'alert-danger';
map['PLAIN_TEXT'] = 'Plain Text';
map['MULTIPLE_CHOICE'] = 'Multiple Choice';


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

function validateInput()
{
$('.js-validate-not-empty').each(function () {
    	if($(this).text().trim().length <= 0)
    		{
    			$(this).addClass("has-error");
    		}
});

}

      




