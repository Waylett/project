/**
 * 
 */

var members;

function create_table(id) {
    var mp_data = members[id];
    
   
    
    table_string = "<table id= myTable>";

    table_string += "<tr><td>" + "MP: " + "</td><td>" + mp_data.mp + "</td></tr>";
    table_string += "<tr><td>" + "Party: " + "</td><td>" + mp_data.party + "</td></tr>";
    table_string += "<tr><td>" + "Constituency: " + "</td><td>" + mp_data.constituency + "</td></tr>";
    table_string += "<tr><td>" + "URL: " + "</td><td>" + mp_data.url + "</td></tr>";

    table_string += "</table>";

    console.log(table_string);
    return table_string;
}

function load_data(mps_filename) {
	
	var mps = mps_filename;
	 d3.json(mps, function(error, m) {
         if (error) return console.error(error);
         members = m;
	   });
}




load_data("json/uk.json", "json/mps.json");