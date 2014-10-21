
var websocketUrl = "ws://localhost:8080/Learning/story/nofitications";
var socket = null;

function initialize () {
	
	/*
	var canvas = document.getElementById("board");
	var ctx = canvas.getContext("2d");
	var img = new Image();
	img.src="images/planets/space.jpg";	
	ctx.drawImage(img, 0, 0);
	*/
	
	socket = new WebSocket(websocketUrl);	
	socket.onmessage = onSocketMessage;
	socket.onerror = onSocketError;
}

function onSocketError(event) {
    logError("could not connect to: " + websocketUrl);
}

function onSocketMessage (event) {
	
	var receivedSticker = JSON.parse(event.data);
	var imageObj = new Image();
	
	imageObj.onload = function () {
		var canvas = document.getElementById("board");
		var ctx = canvas.getContext("2d");
		ctx.drawImage(img, receivedSticker.x, receivedSticker.y);
	}
	imageObj.src = "images/planets/" + receivedSticker.sticker;
	
	log("Receiving object: " + receivedSticker.sticker);
}

function drag (ev) {
}

function allowDrop(ev) {
	ev.preventDefault();
}

function drop (ev) {
	
	ev.preventDefault();	
	var canvas = document.getElementById("board").getBoundingClientRect();	
	var draggedText = ev.dataTransfer.getData("text");
	
	console.log("draggedText: " + draggedText);
	
	var draggedSticker = JSON.parse(draggedText);
	
	console.log("draggedSticker: " + draggedSticker);
	
	
	var stickerToSend = {
			action: "add", 
			x: ev.clientX - draggedSticker.offsetX - bounds.left,
			x: ev.clientY - draggedSticker.offsetY - bounds.top,
			sticker: draggedSticker.sticker
	};
	
	console.log("stickerToSend:" + JSON.stringify(stickerToSend));
	
	socket.send(JSON.stringify(stickerToSend));
	log("Sending object: " + draggedSticker.sticker);
}

function log(logstr) {
	var logElement = document.getElementById("log");
	logElement.innerHTML = "<span>Info: " + logstr + "</span>" + logElement.innerHTML;
}

function logError(errorMsg) {
	var logElement = document.getElementById("log");
	logElement.innerHTML = "<span style='color: red;'>ERROR: " + errorMsg + "</span>" + logElement.innerHTML;
}

window.onload = initialize;

