
$('.js-drop-down-add-to-list').on("change",function(  ){
//       $('#tags').show();
//       $('.tooltip-demo').append(" <button type='button' class='btn btn-default js-list-item' data-placement='left' title='Click to remove' >" + $(this).val() + "</button>");
	$(this).siblings('.js-selected-items-panel').show();
    $(this).siblings('.js-selected-items-panel').children('.js-selected-tags').append(" <button type='button' class='btn btn-default js-list-item' data-placement='left' title='Click to remove' >" + $(this).val() + "</button>");
    })

    
$("div.js-selected-tags").on("click","button.js-list-item", function(event){
    	$(this).remove();
    })
  
    function createList(data)
{
	var arrayLength = data.length;
	var list = "<ul>";
	for (var i = 0; i < arrayLength; i++)
	{
		list += "<li>" + data[i] + "</li>"; 
	}
	list += "</ul>";	
	return list;
}
    
//# sourceURL=common.js
    