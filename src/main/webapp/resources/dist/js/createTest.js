
$("#view-generated-test").hide();

$(".js-add-question").on("click", function()
{
	var hasErrors = false;
	var test = new Object();
	
	test.questionTags = new Array();
	test.reviewers = new Array();
	
	test.candidateName =$(".js-candidate-name").val();
	if(test.candidateName.match("^[a-zA-Z ]+$"))
	{
		$('.js-candidate-name').parent().removeClass("has-error")
	}else
	{
		$('.js-candidate-name').parent().addClass("has-error")
    	hasErrors = true
	}
	
	test.numberOfQuestions =$(".js-number-of-questions").val();
	if(test.numberOfQuestions.match("^[1-9][0-9]*$"))
	{
		$('.js-number-of-questions').parent().removeClass("has-error")
	}else
	{
		$('.js-number-of-questions').parent().addClass("has-error")
    	hasErrors = true
	}
	
	
	test.candidateMail =$(".js-candidate-mail").val();
	if(test.candidateMail.match("^[a-zA-Z0-9.\]*@[a-zA-Z0-9.\]*"))
	{
		$('.js-candidate-mail').parent().removeClass("has-error")
	}else
	{
		$('.js-candidate-mail').parent().addClass("has-error")
    	hasErrors = true
	}
	
	
	$('.js-question-tags').children('button').each(function () {
		test.questionTags.push($(this).text().trim());
	});
	if(test.questionTags.length > 0)
	{
		$('.js-drop-down-add-to-list-tags').parent().removeClass("has-error")
	}else
	{
		$('.js-drop-down-add-to-list-tags').parent().addClass("has-error")
		hasErrors = true
	}


	$('.js-reviewers').children('button').each(function () {
		test.reviewers.push($(this).children()[0].tagName.toLowerCase());
	});
	if(test.reviewers.length > 0)
	{
		$('.js-drop-down-add-to-list-reviewers').parent().removeClass("has-error")
	}else
	{
		$('.js-drop-down-add-to-list-reviewers').parent().addClass("has-error")
		hasErrors = true
	}
	

	var myString = JSON.stringify(test);
	
	if(hasErrors)
	{
	    var obj = new Object();
	    obj.ressponseMessage = "Please fill in all fields in red with the required data";
	    obj.notoficationType = "ERROR";
	    obj.cleanDiv = false;
	    responsiveFeedback(obj);
	}else
	{
	$.ajax({
    	'url': 'createTest',
        'headers': { 
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'data': myString,
        'type': 'POST',
        success:function(data) {
        	
        	$("#view-generated-test").show();
        	$(".js-generate-test-panel").hide();
        	
        	var t = $('#dataTables-example').DataTable();
//        	cteateResultsTable(t, data);
        	
        	fillRow(data, t,false)
          }
    })
		}
	
//	}
})


function fillRow(data, t, isChangeQuestion)
{
	var arrayLength = data.length;
	for (var i = 0; i < arrayLength; i++) 
	{
		var asd = [];
		if(isChangeQuestion)
		{
			asd.push(data[i].questionId);
		}
		else
		{
			asd.push(data[i].number);
		}
		asd.push(data[i].questionText);
		asd.push(map[data[i].questionType]);
//		createList(data[i].questionTags)
		asd.push(createList(data[i].questionTags));
		t.row.add(asd).draw( false );
	}
	}



function cteateResultsTable(dataTable, data)
{
	for (var i in data) 
		{var asd = [];
		asd.push(i.questionText);
		asd.push(i.questionTags);
		asd.push(i.questionType);
		
			dataTable.row.add(asd).draw( false );
		}
	
	
}
var selectedRow ;
var changeQuestionNumber ;
//var mainTable ;


 $('#dataTables-example tbody').on('dblclick', 'tr', function () {
	 var table = $('#dataTables-example').DataTable();
//        var data = table.row( this ).data();
//	mainTable = table;
	 selectedRow = this 
	 changeQuestionNumber = table.row(this).data()[0]; 
	 
	 $.ajax({
	    	'url': 'allQuestions',
	        'headers': { 
	        	'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        },
	        'type': 'GET',
	        success:function(data) {
	        	var t = $('#dataTables-example23').DataTable(
//	        			{ "columnDefs": [{ "targets": [ 1 ], "visible": false }]}
	        			);
	        	t.clear();
	        	fillRow(data, t, true)
//	        	alert(data);
	        }
	    })
	 
        $('#myModal').modal({
            show: true
        });
    } );
 
 $('.js-change-question').on( 'click', function () {
	 
 
	 var newQuestion = $('#dataTables-example23').DataTable().row('.selected').data();
	 var objToSend = new Object();
	 objToSend.number = changeQuestionNumber;
	 objToSend.id = newQuestion[0];
	 $.ajax({
	    	'url': 'updateTestQuestion',
	        'headers': { 
	        	'Accept': 'application/json',
	            'Content-Type': 'application/json'
	        },
	        'data': JSON.stringify(objToSend),
	        'type': 'POST',
	        success:function() {

	        	$('#dataTables-example').DataTable().row(selectedRow).data([changeQuestionNumber, newQuestion[1], newQuestion[2], newQuestion[3]]).draw();
	        	$('#myModal').modal('hide');
	    }
	 
	 })
 })
//	 $('#myModal').modal('hide');
 
 $('#dataTables-example23').on( 'click', 'tr', function () {
	 
	 $(this).siblings('tr').each(function() {
		 $(this).removeClass('selected');
	 });
	 
	 var table = $('#example').DataTable();
     if ( $(this).hasClass('selected') ) {
         $(this).removeClass('selected');
     }
     else {
         table.$('tr.selected').removeClass('selected');
         $(this).addClass('selected');
     }
 } );
 
 
 $('.js-send-mail').on( 'click', function () {
	 
	 $.ajax({
	    	'url': 'sendEmail',
	        'type': 'GET',
	        success:function(data) {
	        	
	        	responsiveFeedback(data);
	          }
	    })
	 
 })

//# sourceURL=createTest.js