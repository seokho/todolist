(function (window) {
	url = "/api/todos";
	todos = 0;
	completed = 1;

	//page load -> show todo list
	getTodosListAjax(todos);
	//show completed list
	getTodosListAjax(completed);
	//count todos
	countTodoAjax();


	//input new-todo
	$('.new-todo').on('keypress', function(e) {
		var newTodo = $('.new-todo');
		if (e.which == 13 && newTodo.val().length > 0) {
			alert("you pressed enter key"+newTodo.val());
			inputTodoAjax(newTodo.val());
		}
	});

	//todo -> completed




})(window);

var url;
var todos, completed;

function updateTaskAjax(id, task) {
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
			countTodoAjax();
		}

	})

}

function countTodoAjax() {
	$.ajax({
		url : url+"/count",
		type : "get",
		success: function(data) {
			$('.todo-count').children('strong').text(data);
		}
	})

}

function drowTodo(todo, className) {
	var html = '<div class="view"><input class="toggle" type="checkbox"><label id="'+todo.id+'">'+todo.title+'</label><button class="delete" id="'+todo.id+'"">x</button></div>';
	className.append(html);
}

function drowList(list, className) {
	for(var i in list) {
		var html = '<div class="view"><input class="toggle" type="checkbox"><label id="'+list[i].id+'">'+list[i].title+'</label><button class="delete" id="'+list[i].id+'"">x</button></div>';
		className.append(html);
		$('.delete').click(function(){
			updateTaskAjax(this.id, 2);
		});
		$('label').click(function() {
			updateTaskAjax(this.id, 1);
			console.log("click : "+this.id);

		});
	}

}

function getTodosListAjax(task) {
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
			countTodoAjax();
			drowTodo(data, $('li.todo-list'));

		},
		error : function(request,status,error){
			alert("code:"+request.status+"\n"+"error:"+error);
		}
	});
}