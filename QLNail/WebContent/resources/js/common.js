//Testing functions
function t(text){
    alert(text);
}


// Default setup
$(function () {
    // auto select has to be done when page ready
    $('input').on("focus", function() {
        $(this).select();
    });
})

// Get back number from currency string
$.fn.getCurrencyNumber = function() {
	return Number(this.text().replace(/[^0-9\.-]+/g,""));
}



// Retrieve GET parameter
function getParam(name){
   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
      return decodeURIComponent(name[1]);
}

// Show Accordions for w3
function showAccordions(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}

// Check selectors exist
$.fn.exists = function () {
    return this.length !== 0;
}