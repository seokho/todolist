(function (window) {
	url = "/api/todos";
	todos = 0;
	completed = 1;

	//page load -> show todo list
	getTodosList(todos);
	//show completed list
	getTodosList(completed);
	//count todos
	countTodo();

	
	//input new-todo
	$('.new-todo').on('keypress', function(e) {
		var newTodo = $('.new-todo');
		if (e.which == 13 && newTodo.val().length > 0) {
			alert("you pressed enter key"+newTodo.val());
			inputTodoAjax(newTodo.val());
		}
	});

	//todo -> completed
	updateTask(34, 2);
	$('.delete').click(function() {
		alert("asdasd");
	});
	


})(window);

var url;
var todos, completed;

function updateTask(id, task) {
	var todo = {
		id : id,
		task : task,
	}
	$.ajax({
		url : url+"/"+id,
		dataType : "json",
		type : "put",
		data : JSON.stringify(todo),
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			countTodo();
		}

	})
	
}

function countTodo() {
	$.ajax({
		url : url+"/count",
		type : "get",
		success: function(data) {
			$('.todo-count').children('strong').text(data);
		}
	})

}

function drowList(list, className) {
	for(var i in list) {
		var html = '<div class="view"><input class="toggle" type="checkbox"><label>'+list[i].title+'</label><button class="delete" id="'+list[i].id+'"">x</button></div>';
		className.append(html);
		$('.delete').click(function(){
			updateTask(this.id, 2);
		});
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
			countTodo();

		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"error:"+error);
		}
	});
}