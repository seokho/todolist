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
    bindEventAtStatusFilter();
    bindEventAtClearButton();

})(window);

function bindEventAtClearButton() {
    $('.clear-completed').click(function() {
        deleteStatusAjax(COMPLETED_TODO_CODE);
    });
}

function bindEventAtInputActiveTodo() {
    $('.new-todo').on('keypress', function (e) {
        var $newTodoElement = $('.new-todo');
        if (e.which === 13 && $newTodoElement.val().length > 0) {
            inputActiveTodoAjax($newTodoElement.val());
            $newTodoElement.val('');
        }
    });
}

function bindEventAtStatusFilter() {
    var $active = $('li.todo-list');
    var $completed = $('li.completed-list');

    $('a.all').click(function () {
        $active.show();
        $completed.show();
        bindCSSAtStatusFilter($('a.all'));
    });

    $('a.active').click(function () {
        $active.show();
        $completed.hide();
        bindCSSAtStatusFilter( $('a.active'));

    });

    $('a.completed').click(function () {
        $active.hide();
        $completed.show();
        bindCSSAtStatusFilter( $('a.completed'));
    });
}

function bindCSSAtStatusFilter($element) {
    $('.filters').find('a').css('font-weight', 'normal');
    $element.css('font-weight' ,'bold');
}

function bindEventAtElementClick() {

    $('.destroy').off('click').on('click', function () {
        deleteElementAjax(this.id);
    });
    $('#active label').off('click').on('click', function () {
        var todo = {
            id : this.id,
            title : $('#'+this.id).text(),
            status : COMPLETED_TODO_CODE
        };

        updateStatusAjax(todo);
    });
}

function drawTodosByStatus(todos, $element) {
    for (var i in todos) {
        var html = '<div class="view">' +
            '<input class="toggle" type="checkbox">' +
            '<label id="' + todos[i].id + '">' + todos[i].title + '</label>' +
            '<button class="destroy" id="' + todos[i].id + '""></button>' +
            '</div>';
        $element.append(html);
    }
    bindEventAtElementClick();

}

function drawTodo(todo, $element, status) {
    var html = '<div class="view">' +
        '<input class="toggle" type="checkbox">' +
        '<label id="' + todo.id + '">' + todo.title + '</label>' +
        '<button class="destroy" id="' + todo.id + '""></button>' +
        '</div>';
    if (status === ACTIVE_TODO_CODE) {
        $element.append(html);
    } else {
        $element.prepend(html);
    }

    bindEventAtElementClick();
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
            drawTodosByStatus(data, $element);
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

function deleteStatusAjax(status) {
    var $element;
    if(status === ACTIVE_TODO_CODE) {
        $element = $('.todo-list');
    } else {
        $element = $('.completed-list');
    }
    $.ajax({
        url: API_URL + "/status/" + status,
        type: "delete",
        success: function() {
            $element.children().remove();
        }
    })
}

function deleteElementAjax(id) {
    var $element = $('#'+id);
    $.ajax({
        url: API_URL + "/" +id,
        type: "delete",
        success: function() {
            $element.parent().remove();
        }
    })
}

function updateStatusAjax(todo) {
    var $element = $('#' + todo.id);

    $.ajax({
        url: API_URL + "/" + todo.id,
        dataType: "json",
        type: "put",
        data: JSON.stringify(todo),
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            setActiveTodosCount();
            $element.parent().remove();
            drawTodo(todo, $('li.completed-list'), COMPLETED_TODO_CODE);

        }
    });
}

