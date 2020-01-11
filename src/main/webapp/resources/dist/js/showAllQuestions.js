$("#show-questions").show();

$.ajax({
    	'url': 'showAllQuestions',
        'headers': {
        	'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        success:function(data) {
            console.log("ajax show ");
        	$("#show-questions").show();

        	var t = $('#dataTables-example').DataTable((
                        { "columnDefs": [{ "targets": [ 1 ], "visible": false }]}
  			            );
            t.clear();
        	fillRow(data, t,false);
          }
})

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