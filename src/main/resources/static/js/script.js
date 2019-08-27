
var xtarget = 0;
var ytarget = 0;
var x = 0;
var y = 0;
//CONTROLS SPEED OF GAME | UPDATES BY MILLISECONDS (1000 MS = 1 SECOND)
var speed = 20;
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
	    dot.style.left = (div.x+200) + "px";
    }
    if (div.y != div.ytarget){
	    if (div.y < div.ytarget) {
	    	div.y++;
	    }
	    if (div.y > div.ytarget) {
	    	div.y--;
	    }
	    dot.style.top = (div.y+74) + "px";
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
				htmGo = htmGo + '<div id="div' + id + '" class="divvy" style="background-color: ' + color + '; border: 5px solid ' + outline + '; border-radius: ' + radius + 'px;">' + mood + '</div>';
				
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

function setDivs() {
	var htmGo = "";
	for (var i = 0; i < allDivs.length; i++){
		var current = allDivs[i];
		htmGo = htmGo + '<div class="divvy" id="' + current.id + '"></div>';
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

function updateSidebar(){
	
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
	          updateSidebar();
	          //UPDATE DIVS FUNCTION :: UPDATES DIVS WITHOUT ADDING NEW DIVS 
//	          updateDivs();
	          //GAME UPDATE FUNCTION
	          
	          //SIDEBAR UPDATE FUNCTION
	        }
	    })
}


//SPAWNS A NEW DIV AND ADDS TO JS OBJECTS :: DOES NOT GET ALL FROM DATABASE
function spawn(){
	$.ajax({
		type: "POST",
		url: "/spawn",
		success: function(data, result, jqXHR) {
			spawnData(data);
			setDivs();
		}
	})
}

//RUNS UPDATE FUNCTION ON LOAD
window.onload = function(){
	
	updateAll();
	if (allDivs.length < 4) {
//		for (i = 0; i < 4-allDivs.length; i++){
//			spawn();
//		}
	}
}


//RUNS AT SET INTERVALS :: DEPRECATED BUT POSSIBLY USEFUL FOR FUTURE
window.setInterval(function(){
	for (var i = 0; i < allDivs.length; i++){
		move(allDivs[i]);
	}
},speed)

$('#spawner').click(function(event) {
            event.stopImmediatePropagation()   
            event.stopPropagation()
            spawn();
        })
