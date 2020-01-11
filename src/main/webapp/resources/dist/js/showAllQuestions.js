$("#showAllQuestions").show();

//$.ajax({
//    	'url': 'showAllQuestions',
//        'headers': {
//        	'Accept': 'application/json',
//            'Content-Type': 'application/json'
//        },
//        'type': 'GET',
//        success:function(data) {
//            console.log("ajax show ");
//        	$("#show-questions").show();
//
//        	var t = $('#dataTables-example').DataTable((
//                        { "columnDefs": [{ "targets": [ 1 ], "visible": false }]}
//  			            );
//            t.clear();
//        	fillRow(data, t,false);
//        }
//})

var currentQuestion = 1 ;
var init = true;

function loadGeneralTestInfo() { $.ajax({
    	'url': 'showTestdata',
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
            	'url': 'showQuesiton/' + currentQuestion,
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

function fillRow(data, t, isChangeQuestion)
{
    console.log("fill row");
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

 $('#dataTables-example23').on( 'click', 'tr', function () {
    console.log("dataTables-example23");
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