var stompClient = null;

function setConnected(connected) {
    $("#connect1").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}
function connect1() {
	
    var socket = new SockJS('/websocket');
    var cat = $("#categorySelect" ).val();
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/'+cat, function (books) {
            showGreeting(books.body);
        },{ category: cat });
    });
	document.getElementById('headerCategory').innerHTML = '';
	$("#headerCategory").append("<span>Books published from: </span><span class='badge badge-success'>"+$("#categorySelect" ).val()+"</span>");
	$('#subs').removeClass('d-none');
	$('#conversation').removeClass('d-none');
}
function disconnect() {
	$('#subs').addClass('d-none');
	$('#conversation').addClass('d-none');

    if (stompClient !== null) {
    	var cat = $("#categorySelect" ).val();
        stompClient.disconnect({},{ category:cat});
    }
    setConnected(false);
    console.log("Disconnected");
}

function showGreeting(books) {				
	var booksToprint = JSON.parse(books);
	document.getElementById('greetings').innerHTML = '';
	document.getElementById('statistics').innerHTML = '';
	for (var i = 0; i < booksToprint[0].length; i++) {
		
		
		$("#greetings").append("<tr><td class='d-flex justify-content-between'><img style='height:60px;width:60px' src="+booksToprint[0][i].cover+"/><span>" + booksToprint[0][i].name +"</span><span>"+booksToprint[0][i].description+"</span><span>" + booksToprint[0][i].category +"</span><a href="+booksToprint[0][i].url+">download</a></td></tr>");				
	}
	for (var i = 0; i < booksToprint[1].length; i++) {
		
		
		$("#statistics").append("<td><span class='badge badge-danger'>"+booksToprint[1][i].cantidad+"</span></td>");				
	}
 
}

$(function () {
    $("#websocketForm").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect1" ).click(function() { connect1(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

