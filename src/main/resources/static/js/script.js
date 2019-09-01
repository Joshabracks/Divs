var spotlight; //CONTAINS DIV THAT IS SHOWN ON THE SIDEBAR
var speed = 20; //CONTROLS MOVE SPEED OF DIVS | UPDATES BY MILLISECONDS (1000 MS = 1 SECOND)
var allDivs = [];//CONTAINS ALL DIVS TO BE DRAWN ON SCREEN
var tempMar = "";//USED TO STORE MARQUEE INFO
//TAKES SINGLE DIV AND MOVES IT IN THE DIRECTION OF ITS x and y TARGET
function move(div) {
	var dot = document.getElementById(div.id);
	if (div.x != div.xtarget) {
		if (div.x < div.xtarget) {
			div.x++;
		}
		if (div.x > div.xtarget) {
			div.x--;
		}
		dot.style.left = (div.x) + "px";
	}
	if (div.y != div.ytarget) {
		if (div.y < div.ytarget) {
			div.y++;
		}
		if (div.y > div.ytarget) {
			div.y--;
		}
		dot.style.top = (div.y) + "px";
	}
}
//PROXIMITY AND SPACE MAKING BEHAVIOUR
function xProx(x1, x2) {
	return Math.abs(x1 - x2);
}
function yProx(y1, y2) {
	return Math.abs(y1 - y2);
}
function makeSpace(div) {
	for (var i = 0; i < allDivs.length; i++) {
		if (allDivs[i] != div) {
			var other = allDivs[i].name;
			var yprox = yProx(div.y, allDivs[i].y);
			var xprox = xProx(div.x, allDivs[i].x);
			var distance = ((div.x.size + allDivs[i].size) / 2);
			if ((xprox == 0) && (yprox == 0)) {
				div.xtarget = (div.x + Math.floor(Math.random() * 100) - 50);
				div.ytarget = (div.y + Math.floor(Math.random() * 100) - 50);

			}
			else if ((xprox < 50) && (yprox < 50)) {

				if (xprox >= yprox) {
					if (div.x == allDivs[i].x) {
						div.xtarget = (div.x + Math.floor(Math.random() * 100) - 50);
					}
					else if (div.x < allDivs[i].x) {
						div.xtarget = (allDivs[i].x - 50);
					}
					else {
						div.xtarget = (allDivs[i].x + 50);
					}
				}
				else {
					if (div.y == allDivs[i].y) {
						div.ytarget = (div.y + Math.floor(Math.random() * 100) - 50);
					}
					else if (div.y < allDivs[i].y) {
						div.ytarget = (allDivs[i].y - 50);
					}
					else {
						div.ytarget = (allDivs[i].y + 50);
					}
				}
			}
		}
	}
}
//CREATES A NEW DIV || MUST BE ADDED TO allDivs
function div(id, name, color, outline, mood, radius, x, y, love, like, dislike, hate, action, size) {
	this.id = id;
	this.name = name;
	this.color = color;
	this.outline = outline;
	this.mood = mood;
	this.radius = radius;
	this.xtarget = 0;
	this.ytarget = 0;
	this.x = x;
	this.y = y;
	this.hate = hate;
	this.love = love;
	this.like = like;
	this.dislike = dislike;
	this.action = action;
	this.size = size;
}
//PARSES THROUGH DATA ON UPDATE FUNCTION
function updateData(data) {
	tempMar = "";
	var setting = "";
	var command = "";
	var value = "";
	//setting new div object
	var id = 0;
	var name = "";
	var color = "";
	var outline = "";
	var mood = "";
	var radius = 0;
	var x = 0;
	var y = 0;
	var hate = "";
	var love = "";
	var like = "";
	var dislike = "";
	var action = "";
	var size = "";

	var htmGo = "";
	for (var i = 0; i < data.length; i++) {
		//settings
		if (data.charAt(i) == "!") {
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?") {
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#") {
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				htmGo = htmGo + '<button id="' + id + '" class="divvy" style=" top: ' + y + '; left: ' + x + ';background-color: ' + color + '; border: 5px solid ' + outline + '; border-radius: ' + radius + 'px; height: ' + size + 'px; width: ' + size + 'px;">' + mood + '</button>';

				tempMar = tempMar + name + ", ";

				id = 0;
				name = "";
				color = "";
				outline = "";
				mood = "";
				radius = 0;
				x = 0;
				y = 0;
				command = "";
				setting = "";
				var hate = "";
				var love = "";
				var like = "";
				var dislike = "";
				var action = "";
				var size = "";
			}
			else {
				command = "";
				setting = "";
			}
		}
		else {
			if (setting == "command") {
				command = command + data.charAt(i);
			}
			if (setting == "value") {
				if (command == "id") {
					id = id + data.charAt(i);
				}
				if (command == "name") {
					name = name + data.charAt(i);
				}
				if (command == "color") {
					color = color + data.charAt(i);
				}
				if (command == "outline") {
					outline = outline + data.charAt(i);
				}
				if (command == "mood") {
					mood = mood + data.charAt(i);
				}
				if (command == "radius") {
					radius = radius + data.charAt(i);
				}
				if (command == "x") {
					x = x + data.charAt(i);
				}
				if (command == "y") {
					y = y + data.charAt(i);
				}
				if (command == "hate") {
					hate = hate + data.charAt(i);
				}
				if (command == "love") {
					love = love + data.charAt(i);
				}
				if (command == "like") {
					like = like + data.charAt(i);
				}
				if (command == "dislike") {
					dislike = dislike + data.charAt(i);
				}
				if (command == "action") {
					action = action + data.charAt(i);
				}
				if (command == "size") {
					size = size + data.charAt(i);
				}

			}
		}
	}
	document.getElementById("gamer").innerHTML = htmGo;
}
//PARSES THROUGH DATA ON SPAWN FUNCTION AND CREATES A DIV OBJECT
function spawnData(data) {
	var setting = "";
	var command = "";
	var value = "";
	//setting new div object
	var id = "";
	var name = "";
	var color = "";
	var outline = "";
	var mood = "";
	var radius = "";
	var x = "";
	var y = "";
	var hate = "";
	var love = "";
	var like = "";
	var dislike = "";
	var action = "";
	var size = "";
	for (var i = 0; i < data.length; i++) {
		//settings
		if (data.charAt(i) == "!") {
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?") {
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#") {
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				var newDiv = new div(id, name, color, outline, mood, radius, x, y, love, like, dislike, hate, action, size);
				allDivs.push(newDiv);
				var id = "";
				var name = "";
				var color = "";
				var outline = "";
				var mood = "";
				var radius = "";
				var x = "";
				var y = "";
				var hate = "";
				var love = "";
				var like = "";
				var dislike = "";
				var action = "";
				var size = "";
			}
			else {
				command = "";
				setting = "";
			}
		}
		else {
			if (setting == "command") {
				command = command + data.charAt(i);
			}
			if (setting == "value") {
				if (command == "id") {
					id = id + data.charAt(i);
				}
				if (command == "name") {
					name = name + data.charAt(i);
				}
				if (command == "color") {
					color = color + data.charAt(i);
				}
				if (command == "outline") {
					outline = outline + data.charAt(i);
				}
				if (command == "mood") {
					mood = mood + data.charAt(i);
				}
				if (command == "radius") {
					radius = radius + data.charAt(i);
				}
				if (command == "x") {
					x = x + data.charAt(i);
				}
				if (command == "y") {
					y = y + data.charAt(i);
				}
				if (command == "hate") {
					hate = hate + data.charAt(i);
				}
				if (command == "love") {
					love = love + data.charAt(i);
				}
				if (command == "like") {
					like = like + data.charAt(i);
				}
				if (command == "dislike") {
					dislike = dislike + data.charAt(i);
				}
				if (command == "action") {
					action = action + data.charAt(i);
				}
				if (command == "size") {
					size = size + data.charAt(i);
				}
			}
		}
	}
}
//SPAWNS A SINGLE DIV FROM A DATA STRING
function spawnDivData(data) {
	var setting = "";
	var command = "";
	var value = "";
	//setting new div object
	var id = "";
	var name = "";
	var color = "";
	var outline = "";
	var mood = "";
	var radius = "";
	var x = "";
	var y = "";
	var hate = "";
	var love = "";
	var like = "";
	var dislike = "";
	var action = "";
	var size = "";
	for (var i = 0; i < data.length; i++) {
		//settings
		if (data.charAt(i) == "!") {
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?") {
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#") {
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				var newDiv = new div(id, name, color, outline, mood, radius, x, y);
				allDivs.push(newDiv);
				setDiv(newDiv);
				var id = "";
				var name = "";
				var color = "";
				var outline = "";
				var mood = "";
				var radius = "";
				var x = "";
				var y = "";
				var hate = "";
				var love = "";
				var like = "";
				var dislike = "";
				var action = "";
				var size = "";
			}
			else {
				command = "";
				setting = "";
			}
		}
		else {
			if (setting == "command") {
				command = command + data.charAt(i);
			}
			if (setting == "value") {
				if (command == "id") {
					id = id + data.charAt(i);
				}
				if (command == "name") {
					name = name + data.charAt(i);
				}
				if (command == "color") {
					color = color + data.charAt(i);
				}
				if (command == "outline") {
					outline = outline + data.charAt(i);
				}
				if (command == "mood") {
					mood = mood + data.charAt(i);
				}
				if (command == "radius") {
					radius = radius + data.charAt(i);
				}
				if (command == "x") {
					x = x + data.charAt(i);
				}
				if (command == "y") {
					y = y + data.charAt(i);
				}
				if (command == "hate") {
					hate = hate + data.charAt(i);
				}
				if (command == "love") {
					love = love + data.charAt(i);
				}
				if (command == "like") {
					like = like + data.charAt(i);
				}
				if (command == "dislike") {
					dislike = dislike + data.charAt(i);
				}
				if (command == "action") {
					action = action + data.charAt(i);
				}
				if (command == "size") {
					size = size + data.charAt(i);
				}
			}
		}
	}
}
//UPDATES ALL DIV BEHAVIORS | |  KEEP
function updateBehaviors(data) {
	var setting = "";
	var command = "";
	var value = "";
	//setting new div object
	var id = "";
	var name = "";
	var color = "";
	var outline = "";
	var mood = "";
	var radius = "";
	var x = "";
	var y = "";
	var hate = "";
	var love = "";
	var like = "";
	var dislike = "";
	var action = "";
	var size = "";
	for (var i = 0; i < data.length; i++) {
		//settings
		if (data.charAt(i) == "!") {
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?") {
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#") {
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				length = allDivs.length;
				var checker = false;
				for (var j = 0; j < allDivs.length; j++) {
					if (allDivs[j].id == id) {
						checker = true;
						allDivs[j].name = name;
						allDivs[j].color = color;
						allDivs[j].outline = outline;
						allDivs[j].mood = mood;
						document.getElementById(id).innerHTML = mood;
						allDivs[j].radius = radius;
						allDivs[j].xtarget = x;
						allDivs[j].ytarget = y;
						allDivs[j].like = like;
						allDivs[j].dislike = dislike;
						allDivs[j].love = love;
						allDivs[j].hate = hate;
						allDivs[j].action = action;
						allDivs[j].size = size;
					}
				}
				if (checker == false) {
					var newDiv = new div(id, name, color, outline, mood, radius, x, y, love, like, dislike, hate, action, size);
					allDivs.push(newDiv);
					setDiv(newDiv);
				}
				id = "";
				name = "";
				color = "";
				outline = "";
				mood = "";
				radius = "";
				x = "";
				y = "";
				like = "";
				dislike = "";
				love = "";
				hate = "";
				action = "";
				size = "";

			}
			else {
				command = "";
				setting = "";
			}
		}
		else {
			if (setting == "command") {
				command = command + data.charAt(i);
			}
			if (setting == "value") {
				if (command == "id") {
					id = id + data.charAt(i);
				}
				if (command == "name") {
					name = name + data.charAt(i);
				}
				if (command == "color") {
					color = color + data.charAt(i);
				}
				if (command == "outline") {
					outline = outline + data.charAt(i);
				}
				if (command == "mood") {
					mood = mood + data.charAt(i);
				}
				if (command == "radius") {
					radius = radius + data.charAt(i);
				}
				if (command == "x") {
					x = x + data.charAt(i);
				}
				if (command == "y") {
					y = y + data.charAt(i);
				}
				if (command == "hate") {
					hate = hate + data.charAt(i);
				}
				if (command == "love") {
					love = love + data.charAt(i);
				}
				if (command == "like") {
					like = like + data.charAt(i);
				}
				if (command == "dislike") {
					dislike = dislike + data.charAt(i);
				}
				if (command == "action") {
					action = action + data.charAt(i);
				}
				if (command == "size") {
					size = size + data.charAt(i);
				}
			}
		}
	}
}
//ADDS A SINGLE DIV TO THE GAME DISPLAY
function setDiv(div) {
	var htmGo = '<button class="divvy" id="' + div.id + '"></button>';
	var newDiv = document.createElement('button');
	newDiv.id = div.id;
	newDiv.className = "divvy";
	newDiv.style.left = '0px';
	newDiv.style.top = '0px';
	newDiv.style.backgroundColor = div.color;
	newDiv.style.border = '5px solid ' + div.outline;
	newDiv.style.borderRadius = div.radius + "px";
	newDiv.innerHTML = div.mood;
	document.getElementById("gamer").appendChild(newDiv);
}
//UPDATES ALL ON-SCREEN DIVS
function updateDivs() {
	for (var i = 0; i < allDivs.length; i++) {
		current = allDivs[i];
		target = document.getElementById(allDivs[i].id);
		target.style.backgroundColor = current.color;
		target.style.border = '5px solid ' + current.outline;
		target.style.borderRadius = current.radius + "px";
		target.innerHTML = current.mood;
		target.style.top = current.y;
		target.style.left = current.x;
		target.style.height = current.size + "px";
		target.style.width = current.size + "px";
	}
}
//UPDATES MARQUIS AT BOTTOM OF PAGE WITH DIVVY NAMES AND ACTIONS
function updateMarquis() {
	tempMar = "";
	for (var i = 0; i < allDivs.length; i++) {
		tempMar = tempMar + " | " + allDivs[i].name + " " + allDivs[i].action;
	}
	document.getElementById("marquis").innerHTML = tempMar;
}
//RUNS UPDATE FUNCTION ON LOAD
window.onload = function () {
	updateAll();
}
//GETS ALL DIVS FROM SERVER :: RUN ON PAGE LOAD ONLY
function updateAll() {
	$.ajax({
		type: "POST",
		url: "/update",
		success: function (data, result, jqXHR) {
			spawnData(data);
			setDivs();
			updateDivs();
			updateMarquis();
		}
	})
}
//ADDS BLANK DIV OBJECTS TO PAGE ON PAGE :: RUN ON PAGE LOAD ONLY
function setDivs() {
	var htmGo = "";
	for (var i = 0; i < allDivs.length; i++) {
		var current = allDivs[i];
		htmGo = htmGo + '<button class="divvy" id="' + current.id + '"></button>';
	}
	document.getElementById("gamer").innerHTML = htmGo;
}
//PROMPTS NEW DIV CREATION ON SERVER AND RETURN DATA FOR A SINGLE DIV :: DOES NOT GET ALL FROM DATABASE
function spawn() {
	$.ajax({
		type: "POST",
		url: "/spawn",
		success: function (data, result, jqXHR) {
			spawnDivData(data);
		}
	})
}
//UPDATES GETS INFORMATION FROM PAGE AT SET INTERVALS AND UPDATES MARQUIS/BEHAVIORS
window.setInterval(function () {
	$.ajax({
		type: "POST",
		url: "/update",
		success: function (data, result, jqXHR) {
			for (var i = 0; i < allDivs.length; i++) {
				updateBehaviors(data);
				updateMarquis();
				updateDivs();
			}
		}
	})
}, 1000)
//ENACTS MOVEMENT OF DIVS AT SET INTERVALS
window.setInterval(function () {
	for (var i = 0; i < allDivs.length; i++) {
		move(allDivs[i]);
	}
}, speed)
//ENACTS COLLISION DETECTION "MAKE SPACE" FUNCTION AT SET INTERVALS
window.setInterval(function () {
	for (var i = 0; i < allDivs.length; i++) {
		makeSpace(allDivs[i]);
	}
}, 50)
//UPDATES SIDEBAR INFORMATION AT SET INTERVALS
window.setInterval(function () {
	if (spotlight == undefined) {
		spotlight = allDivs[0];
		updateSpotlight(spotlight.id);
	}
	document.getElementById("spotlight").innerHTML = "<h3>" + spotlight.action + "</h3>" + "<h3>Loves: " + spotlight.love + "</h3>" + "<h3>Likes: " + spotlight.like + "</h3>" + "<h3>Dislikes: " + spotlight.dislike + "</h3>" + "<h3>Hates: " + spotlight.hate + "</h3>";
	document.getElementById("spawner").innerHTML = "<h2>" + spotlight.size + "</h2>";
}, 1000)
//RUNS SPAWN FUNCTION UPON CLICKING OBJECT WITH #spawner id
$('#spawner').click(function (event) {
	spawn();
})
//TAKES A DIV ID AND SETS IT AS THE "SPOTLIGHT" AND DISPLAYS ITS INFORMATION ON THE SIDEBAR
function updateSpotlight(id) {
	var thisDiv;
	for (var i = 0; i < allDivs.length; i++) {
		if (allDivs[i].id == id) {
			thisDiv = allDivs[i];
			spotlight = thisDiv;
		}
	}
	var target = document.getElementById("display");
	target.style.backgroundColor = thisDiv.color;
	target.style.border = '5px solid ' + thisDiv.outline;
	target.style.borderRadius = thisDiv.radius + "px";
	target.innerHTML = thisDiv.mood;
	target = document.getElementById("infobox");
	target.innerHTML = '<br><h2>' + thisDiv.name + '</h2>';
	document.getElementById("spotlight").innerHTML = "<h3>" + spotlight.action + "</h3>" + "<h3>Loves: " + spotlight.love + "</h3>" + "<h3>Likes: " + spotlight.like + "</h3>" + "<h3>Dislikes: " + spotlight.dislike + "</h3>" + "<h3>Hates: " + spotlight.hate + "</h3>";
	document.getElementById("spawner").innerHTML = "<h2>" + spotlight.id + "</h2>";
}
//ENACTS "updateSpotlight" function upon clicking on a Divvy
$('#gamer').click(function (event) {
	updateSpotlight(event.target.id);
})