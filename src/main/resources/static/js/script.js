
var allDivs = "[[${allDivs}]]";
var xtarget = 0;
var ytarget = 0;
var x = 0;
var y = 0;
//CONTROLS SPEED OF GAME | UPDATES BY MILLISECONDS (1000 MS = 1 SECOND)
var speed = 10000;
var allDivs = "";

function updateMarquis() {
	document.getElementById("marquis").innerHTML  = allDivs;
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

//Update function
function update(){
	$.ajax({
	      type: "POST",
	      url: "/update",
	      success: function(data, result, jqXHR) {
	    	  allDivs = (data);
	          //MARQUIS UPDATE FUNCTION
	          updateMarquis();
	          //GAME UPDATE FUNCTION
	          
	          //SIDEBAR UPDATE FUNCTION
	        }
	    })
}


window.setInterval(function(){
	update();
},speed)

