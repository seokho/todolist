var API_URL = "/api/todos";

var ACTIVE_TODO_CODE = 0;
var COMPLETED_TODO_CODE = 1;
var REMOVED_TODO_CODE = 2;

(function (window) {
    //page load -> show todo list
    getTodosByStatus(ACTIVE_TODO_CODE);

    //show completed list
    getTodosByStatus(COMPLETED_TODO_CODE);

    // bind event
    bindEventAtInputActiveTodo();
})(window);

function bindEventAtInputActiveTodo() {
    $('.new-todo').on('keypress', function (e) {
        var $newTodoElement = $('.new-todo');
        if (e.which === 13 && $newTodoElement.val().length > 0) {
            inputActiveTodoAjax($newTodoElement.val());
            $newTodoElement.val('');
        }
    });
}

function drawActiveTodos(todos, $element) {
    for (var i in todos) {
        console.log('todos', todos);
        console.log('i', i);
        var html = '<div class="view">' +
            '<input class="toggle" type="checkbox">' +
            '<label id="' + todos[i].id + '">' + todos[i].title + '</label>' +
            '<button class="destroy" id="' + todos[i].id + '""></button>' +
            '</div>';
        $element.append(html);
    }

    $('.delete').click(function () {
        updateStatusAjax(this.id, 2);
    });
    $('label').click(function () {
        updateStatusAjax(this.id, 1);
        console.log("click : " + this.id);
    });
}

function drawTodo(todo, $element) {
    var html = '<div class="view">' +
        '<input class="toggle" type="checkbox">' +
        '<label id="' + todo.id + '">' + todo.title + '</label>' +
        '<button class="destroy" id="' + todo.id + '""></button>' +
        '</div>';
    $element.append(html);
}

function drawActiveTodosCount(count) {
    $('.todo-count').children('strong').text(count);
}

function getTodosByStatus(status) {
    var todos = [];
    var $element;
    $.ajax({
        url: API_URL + "/" + status,
        type: "get",
        success: function (data) {
            todos = data;
            if (status === 0) {
                $element = $('li.todo-list');
            } else {
                $element = $('li.completed-list');
            }
            drawActiveTodos(data, $element);
            setActiveTodosCount();
        }
    });
    return todos;
}

function inputActiveTodoAjax(data) {
    var todo = {
        title: data,
        desc: "null",
        status: 0
    };
    $.ajax({
        url: API_URL,
        dataType: "json",
        type: "post",
        data: JSON.stringify(todo),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert("Success registration todo");
            drawTodo(data, $('li.todo-list'));
            setActiveTodosCount();
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "error:" + error);
        }
    });
}

function setActiveTodosCount() {
    $.ajax({
        url: API_URL + "/count",
        dataType: "json",
        type: "get",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            drawActiveTodosCount(data);
        }
    })
}

function updateStatusAjax(id, status) {
    var todo = {
        id: id,
        status: status
    };

    $.ajax({
        url: API_URL + "/" + id,
        dataType: "json",
        type: "put",
        data: JSON.stringify(todo),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            setActiveTodosCount();
        }
    });
}
