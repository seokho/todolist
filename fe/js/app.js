(function (window) {
	url = "/api/todos";

	getTodosList(0);
	// getTOdosList(1);
	
	//input new-todo
	$('.new-todo').on('keypress', function(e) {
		var newTodo = $('.new-todo');
		if (e.which == 13 && newTodo.val().length > 0) {
			alert("you pressed enter key"+newTodo.val());
			inputTodoAjax(newTodo.val());
		}
	});


})(window);

var url;

function drowList(list, className) {
	console.log(list);
	for(var i in list) {
		console.log(list[i]);
		var html = '<div class="view" id="'+list[i].id+'"><input class="toggle" type="checkbox"><label>'+list[i].title+'</label><button class="destroy"></button></div>';
		className.append(html);
	}

}

function getTodosList(task) {
	var todoList = {};
	var className;
	$.ajax({
		url : url+"/"+task,
		type : "get",
		success: function(data) {
			todoList = data;
			if(task === 0) {
				className = $('li.todo-list');
			} else {
				className = $('li.completed-list');
			}
			drowList(data, className);
		}

	})
	return todoList;
}


function inputTodoAjax(data) {
	var todo = {
		title : data,
		desc : "null",
		task : 0
	};
	$.ajax({
		url : url,
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