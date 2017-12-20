
$(document).ready(function () {

    function addCloseButtonToListElements() {
        var myNodelist = $("LI");
        for (var i = 0; i < myNodelist.length; i++) {
            myNodelist.append($("<SPAN></SPAN>").text("\u00D7").addClass("close"));
        }
    }

    function addListenerToAllCloseButton(close) {
        for (var i = 0; i < close.length; i++) {
            addListenerToOneCloseButton(close[i]);
        }
    }

    function addListenerToOneCloseButton(el) {
        $(el).click(function (event) {

            $.ajax('/rem-todo', {
                method: 'POST',
                data: JSON.stringify({id: $(el).attr("id")})

            }).then(function success(data) {
                    if (data) {
                        $(event.target).parent().hide();
                        console.log("->" + $(el).attr("id"));
                    }
                }, function fail(data, status) {
                    console.log('Request failed.  Returned status of ' + status);
                }
            );
        })
    }

    function addCheckedEventToList() {
        var list = $('ul');
        list.children().each(function (index) {
            $(this).click(function (event) {
                if (event.target.tagName === 'LI') {
                    event.target.classList.toggle('checked');
                }
            });

        });
    }

    function newElement() {
        var inputValue = $("#myInput").val();

        if (inputValue === '') {
            alert("You must write something!");
            return;
        }

        addTodoAjax(inputValue);

    }

    function createNewElement(inputId, inputValue) {
        var li = $("<li></li>").text(inputValue);
        li.attr("id", inputId);
        $("#myUL").append(li);

        li.append($("<SPAN></SPAN>").text("\u00D7").addClass("close"));
        addListenerToOneCloseButton(li);
        $("#myInput").val("");
    }

    function addTodoAjax(body) {

        $.ajax('/add-todo', {
            method: 'POST',
            data: JSON.stringify({body: body})

        }).then(function success(data) {
                if (data) {
                    console.log('Todo has been added with id ' + data.id);
                    createNewElement(data.id, data.body);
                }
            }, function fail(data, status) {
                console.log('Request failed.  Returned status of ' + status);
            }
        );
    }

    function getAllToDoAjax() {

        $.ajax('/getall', {
            method: 'POST'
        }).then(function success(data) {
                if (data) {
                    var todos = JSON.parse(data);
                    for (i = 0; i < todos.length; i++) {
                        createNewElement(todos[i].id, todos[i].body);
                    }
                    console.log('response' + data);
                }
            }, function fail(data, status) {
                console.log('response' + status);
            }
        );
    }

    $("#addButtonId").click(newElement)

    getAllToDoAjax();
    addCloseButtonToListElements();

    var close = $(".close");

    addListenerToAllCloseButton(close);
    /*addCheckedEventToList();*/

});


