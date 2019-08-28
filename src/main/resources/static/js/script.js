
var xtarget = 0;
var ytarget = 0;
var x = 0;
var y = 0;
var spotlight;

var speed = 20; //CONTROLS MOVE SPEED OF DIVS | UPDATES BY MILLISECONDS (1000 MS = 1 SECOND)
var allDivs = [];
var tempMar = "";


function move(div){
    var dot = document.getElementById(div.id);
    
    if (div.x != div.xtarget){
	    if (div.x < div.xtarget) {
	    	div.x++;
	    }
	    if (div.x > div.xtarget) {
	    	div.x--;
	    }
	    dot.style.left = (div.x) + "px";
    }
    if (div.y != div.ytarget){
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
function xProx(x1, x2){
	return Math.abs(x1 - x2);
}
function yProx(y1, y2){
	return Math.abs(y1 - y2);
}
function makeSpace(div){
	for (var i = 0; i < allDivs.length; i++){
		if (allDivs[i] != div){
			var other = allDivs[i].name;
			var yprox = yProx(div.y, allDivs[i].y);
			var xprox = xProx(div.x, allDivs[i].x);
			if (div.x < 0) {
				div.xtarget = 10;
				div.status = "Out of bounds. Returning";
			}
			else if (div.y < 0){
				div.ytarget = 10;
				div.status = "Out of bounds. Returning";
			}
			else if ((xprox == 0) && (yprox == 0)){
				div.xtarget = (div.x + Math.floor(Math.random() * 100) - 50);
				div.ytarget = (div.y + Math.floor(Math.random() * 100) - 50);
				div.status = "Giving " + other + " some space."
			}
			else if ((xprox < 50) && (yprox < 50)) {
				div.status = "Giving " + other + " some space."
				if (xprox >= yprox) {
					if (div.x == allDivs[i].x) {
						div.xtarget = (div.x + Math.floor(Math.random() * 100) - 50);
					}
					else if (div.x < allDivs[i].x){
						div.xtarget = (allDivs[i].x-50);
						}
					else {
						div.xtarget = (allDivs[i].x+50);
					}
				}
				else {
					if (div.y == allDivs[i].y) {
						div.ytarget = (div.y + Math.floor(Math.random() * 100) - 50);
					}
					else if (div.y < allDivs[i].y){
						div.ytarget = (allDivs[i].y-50);
						}
					else {
						div.ytarget = (allDivs[i].y+50);
					}
				}
			}
		}
	}
}


 
function div(id, name, color, outline, mood, radius) {
	this.id = id;
	this.name = name;
	this.color = color;
	this.outline = outline;
	this.mood = mood;
	this.radius = radius;
	this.xtarget = 0;
	this.ytarget = 0;
	this.x = 0;
	this.y = 0;
}

//PARSES THROUGH DATA ON UPDATE FUNCTION
function updateData(data){
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
	
	var htmGo = "";
	for (var i = 0; i < data.length; i++){
		//settings
		if (data.charAt(i) == "!"){
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?"){
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#"){
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				htmGo = htmGo + '<button id="' + id + '" class="divvy" style="background-color: ' + color + '; border: 5px solid ' + outline + '; border-radius: ' + radius + 'px;">' + mood + '</button>';
				
				tempMar = tempMar + name + ", ";
				
				id = 0;
				name = "";
				color = "";
				outline = "";
				mood = "";
				radius = 0;
				command = "";
				setting = "";
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
					if (command == "id"){
						id = id + data.charAt(i);
					}
					if (command == "name") {
						name = name + data.charAt(i);
					}
					if (command == "color"){
						color = color + data.charAt(i);
					}
					if (command == "outline"){
						outline = outline + data.charAt(i);
					}
					if (command == "mood"){
						mood = mood + data.charAt(i);
					}
					if (command == "radius"){
						radius = radius + data.charAt(i);
					}
					
			}
		}
	}
	document.getElementById("gamer").innerHTML = htmGo;
	
}
//PARSES THROUGH DATA ON SPAWN FUNCTION AND CREATES A DIV OBJECT
function spawnData(data){
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
	
//	var htmGo = "";
	for (var i = 0; i < data.length; i++){
		//settings
		if (data.charAt(i) == "!"){
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?"){
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#"){
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				var newDiv = new div(id, name, color, outline, mood, radius);
				allDivs.push(newDiv);
//				var htmGo = '<div id="' + id + '" class="divvy" style="background-color: ' + color + '; border: 5px solid ' + outline + '; border-radius: ' + radius + 'px;">' + mood + '</div>';
//				document.getElementById("gamer").appendChild(htmGo);
				
				
				
				
				
				var id = "";
				var name = "";
				var color = "";
				var outline = "";
				var mood = "";
				var radius = "";
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
					if (command == "id"){
						id = id + data.charAt(i);
					}
					if (command == "name") {
						name = name + data.charAt(i);
					}
					if (command == "color"){
						color = color + data.charAt(i);
					}
					if (command == "outline"){
						outline = outline + data.charAt(i);
					}
					if (command == "mood"){
						mood = mood + data.charAt(i);
					}
					if (command == "radius"){
						radius = radius + data.charAt(i);
					}
					
			}
		}
	}
}

function spawnDivData(data){
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
	for (var i = 0; i < data.length; i++){
		//settings
		if (data.charAt(i) == "!"){
			setting = "command";
			command = "";
		}
		else if (data.charAt(i) == "?"){
			//execute or set command
			setting = "value";
		}
		else if (data.charAt(i) == "#"){
			//compile current set of variables and add them to htmGo variable
			if (command == "end") {
				var newDiv = new div(id, name, color, outline, mood, radius);
				allDivs.push(newDiv);;
				setDiv(newDiv);
				var id = "";
				var name = "";
				var color = "";
				var outline = "";
				var mood = "";
				var radius = "";
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
					if (command == "id"){
						id = id + data.charAt(i);
					}
					if (command == "name") {
						name = name + data.charAt(i);
					}
					if (command == "color"){
						color = color + data.charAt(i);
					}
					if (command == "outline"){
						outline = outline + data.charAt(i);
					}
					if (command == "mood"){
						mood = mood + data.charAt(i);
					}
					if (command == "radius"){
						radius = radius + data.charAt(i);
					}
					
			}
		}
	}
}

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

function setDivs() {
	var htmGo = "";
	for (var i = 0; i < allDivs.length; i++){
		var current = allDivs[i];
		htmGo = htmGo + '<button class="divvy" id="' + current.id + '"></button>';
	}
	document.getElementById("gamer").innerHTML = htmGo;
}
function updateDivs() {
	for (var i = 0; i < allDivs.length; i++){
		current = allDivs[i];
		target = document.getElementById(allDivs[i].id);
		target.style.backgroundColor = current.color;
		target.style.border = '5px solid ' + current.outline;
		target.style.borderRadius = current.radius + "px";
		target.innerHTML = current.mood;
	}
}

function updateMarquis() {
	document.getElementById("marquis").innerHTML  = tempMar;
}

//Trigger function
function trigger(){
	alert("Trigger!");
}



//GETS ALL DIVS FROM SERVER :: RUN ON PAGE LOAD ONLY
function updateAll(){
	$.ajax({
	      type: "POST",
	      url: "/update",
	      success: function(data, result, jqXHR) {
	    	  spawnData(data);
	    	  setDivs();
	    	  updateDivs();
	          updateMarquis();
	          //UPDATE DIVS FUNCTION :: UPDATES DIVS WITHOUT ADDING NEW DIVS 
//	          updateDivs();
	          //GAME UPDATE FUNCTION
	          
	        }
	    })
}


//SPAWNS A NEW DIV AND ADDS TO JS OBJECTS :: DOES NOT GET ALL FROM DATABASE
function spawn(){
	$.ajax({
		type: "POST",
		url: "/spawn",
		success: function(data, result, jqXHR) {
		spawnDivData(data);
			
		}
	})
}

//RUNS UPDATE FUNCTION ON LOAD
window.onload = function(){
	
	updateAll();
	
}


//RUNS AT SET INTERVALS :: DEPRECATED BUT POSSIBLY USEFUL FOR FUTURE
window.setInterval(function(){
	for (var i = 0; i < allDivs.length; i++){
		move(allDivs[i]);
	}
},speed)

window.setInterval(function(){
	for (var i = 0; i < allDivs.length; i++){

		makeSpace(allDivs[i]);
	}
}, 500)

window.setInterval(function(){
	if (spotlight == undefined) {
		spotlight = allDivs[0];
		updateSpotlight(spotlight.id);
	}
	document.getElementById("spotlight").innerHTML = "<h3>" + spotlight.status + "</h3>";
	document.getElementById("spawner").innerHTML = "<h2>" + spotlight.id + "</h2>";
}, 500)

$('#spawner').click(function(event) {
            
            spawn();
})

function updateSpotlight(id){
	var thisDiv;
	for (var i = 0; i < allDivs.length; i++) {
		if (allDivs[i].id == id){
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
}

$('#gamer').click(function(event){
	updateSpotlight(event.target.id);
})


        

        

        
//$("#things").ajaxForm({url: '/thing/new', type: 'post'})
