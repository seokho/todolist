(function (window) {

	//input todo script
	$('.new-todo').on('keypress', function(e) {
		if (e.which == 13) {
			alert("you pressed enter key"+$('.new-todo').val());
			inputTodoAjax($('.new-todo').val());
		}
	});
})(window);

function inputTodoAjax(data) {
	var todo = {
		title : data,
		desc : "null",
		task : 0
	};
	$.ajax({
		url : "api/todos",
		dataType : "json",
		type : "post",
		data : JSON.stringify(todo),
		contentType : "application/json; charset=UTF-8", 
		success : function(data) {
			alert("Success registration todo");
		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"error:"+error);
		}
	});
}