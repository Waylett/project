/**
 * 
 */
$.getJSON('../../json/mps.json',function(data){
  	$.each(data, function(index, datum){
  		if(datum.party == "Liberal Democrat"){
        var current_html = document.getElementById("placeholder").innerHTML;
        var html_to_add = "<a href='" + datum.url + "' >" + datum.mp + ' ' + datum.constituency + '</a><br/>';
  			document.getElementById("placeholder").innerHTML = current_html + html_to_add;
  		}
  	})
  });