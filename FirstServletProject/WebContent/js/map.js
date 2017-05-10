
var width = 600
var height = 800;

// variables to draw map
var projection, svg, path, g;
var boundaries, members;

// init map
init(width, height);

// remove any data when we lose selection of a map unit
function deselect() {
    d3.select("#data_table");
      
}

function init(width, height) {

    // pretty boring projection
    projection = d3.geo.albers()
        .rotate([0, 0]);

    path = d3.geo.path()
        .projection(projection);

    // create the svg element for drawing onto
    svg = d3.select("#map").append("svg")
        .attr("width", width)
        .attr("height", height);

    // graphics go here
    g = svg.append("g");

    // add a white rectangle as background to enable us to deselect a map selection
    g.append("rect")
        .attr("x", 0)
        .attr("y", 0)
        .attr("width", width)
        .attr("height", height)
        .style("fill", "#fff")
        .on('mouseover', deselect);
}

// create a HTML table to display any properties about the selected item
function create_table(id) {
    var mp_data = members[id];
    
   
    
    table_string = "<table id= myTable>";

    table_string += "<tr><td>" + "MP: " + "</td><td>" + mp_data.mp + "</td></tr>";
    table_string += "<tr><td>" + "Party: " + "</td><td>" + mp_data.party + "</td></tr>";
    table_string += "<tr><td>" + "Constituency: " + "</td><td>" + mp_data.constituency + "</td></tr>";

    table_string += "</table>";

    console.log(table_string);
    return table_string;
}

// select a map area
function select(d) {
    // get the id of the selected map area
    var id = d.id;
    // add the area properties to the data_table section
    d3.select("#data_table")
        .html(create_table(id));
}

// draw our map on the SVG element
function draw(boundaries) {

    projection
        .scale(1)
        .translate([0,0]);

    // compute the correct bounds and scaling from the topoJSON
    var b = path.bounds(topojson.feature(boundaries, boundaries.objects["wpc"]));
    var s = .95 / Math.max((b[1][0] - b[0][0]) / width, (b[1][1] - b[0][1]) / height);
    var t = [(width - s * (b[1][0] + b[0][0])) / 2, (height - s * (b[1][1] + b[0][1])) / 2];

    projection
        .scale(s)
        .translate(t);

    // add an area for each feature in the topoJSON
    g.selectAll(".area")
        .data(topojson.feature(boundaries, boundaries.objects["wpc"]).features)
        .enter().append("path")
        .attr("class", "area")
        .attr("id", function(d) {return d.id})
        .attr("d", path)
        .on("mouseover", function(d){ return select(d)})

    // add a boundary between areas
    g.append("path")
        .datum(topojson.mesh(boundaries, boundaries.objects["wpc"], function(a, b){ return a !== b }))
        .attr('d', path)
        .attr('class', 'boundary');
}

// colours map with party colour
function colour_map() {
    var areas = d3.selectAll(".area"); // returs object of all colours and how to access, changes css class of the area 
    areas.attr("class", function(d) { // d stores data.
      return get_colour_class(d.id); // gets id of the area then color coded by 
    })
    .on("click", function(d) {
      window.location = get_url(d.id);
    });
}

// gets party colour from id
function get_colour_class(id) {
  return strip_whitespace(members[id].party);
}

// strip whitespace from string
function strip_whitespace(string) {
  return string.replace(/[^a-zA-Z]/g, '');
}

// gets url from mp data file
function get_url(id) {
  return members[id].url;
}

// called to redraw the map - removes map completely and starts from scratch
function redraw() { // 

    d3.select("svg").remove();

    init(width, height);
    draw(boundaries);
    colour_map();
}

// loads data from the given file and redraws the map
function load_data(map_filename, mps_filename) {
    // clear any selection
    deselect();

    var map = map_filename;
    var mps = mps_filename;

    d3.json(map, function(error, b) { // loads boundaries 
        d3.json(mps, function(error, m) { // loads mps
          if (error) return console.error(error);
          boundaries = b; // sets to b 
          members = m; // sets to m
          redraw();
        });
    });
}


load_data("../json/uk.json", "../json/mps.json");
