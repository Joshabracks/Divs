
var xtarget = 0;
var ytarget = 0;
var x = 0;
var y = 0;
//CONTROLS SPEED OF GAME | UPDATES BY MILLISECONDS (1000 MS = 1 SECOND)
var speed = 10000;
var allDivs = [];
var tempMar = "";

function div(id, name, color, outline, mood, radius) {
	this.id = id;
	this.name = name;
	this.color = color;
	this.outline = outline;
	this.mood = mood;
	this.radius = radius;
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

function updateMarquis() {
	document.getElementById("marquis").innerHTML  = tempMar;
}

function move(){
    var dot = document.getElementById('dot')
    if (x < xtarget) {
        x++;
    }
    if (x > xtarget) {
        x--;
    }
    if (y < ytarget) {
        y++;
    }
    if (y > ytarget) {
        y--;
    }

    dot.style.left = x + "px";
    dot.style.top = y + "px";
}


//Trigger function
function trigger(){
	alert("Trigger!");
}

function updateSidebar(){
	
}

//Update function
function update(){
	$.ajax({
	      type: "POST",
	      url: "/update",
	      success: function(data, result, jqXHR) {
	    	  console.log(data);
	          //MARQUIS UPDATE FUNCTION
	    	  updateData(data);
	          updateMarquis();
	          updateSidebar();
	          //GAME UPDATE FUNCTION
	          
	          //SIDEBAR UPDATE FUNCTION
	        }
	    })
}



function spawn(){
	$.ajax({
		type: "POST",
		url: "/spawn",
		success: function(data, result, jqXHR) {
			spawnData(data);
		}
	})
}

//RUNS UPDATE FUNCTION ON LOAD
window.onload = function(){
	
	update();
	if (allDivs.length < 4) {
		for (i = 0; i < 4-allDivs.length; i++){
			spawn();
		}
	}
}



//RUNS UPDATE FUNCTION AT SET INTERVALS
window.setInterval(function(){
	update();
},speed)

$('#spawner').click(function(event) {
            event.stopImmediatePropagation()   
            event.stopPropagation()
            spawn();
        })
