angular.module('graph', [])

.controller('graphController', ['$scope',
function($scope){

    /*try{
        $scope.data = JSON.parse(Parcela.getData().toString().replace(/\"/g, "").replace(/\\/g, '"'));
    }catch(error){
        console.log("[Error in Graph]: " + error);
    }*/
    $scope.data = {"nombre": "terreno","children": [{"nombre": "Bloque 1","children": [{"nombre": "Parcela 1", "size": 70},{"nombre": "Parcela 2", "size": 50},{"nombre": "Parcela 3", "size": 100}]},{"nombre": "Bloque 2","children": [{"nombre": "Parcela 1", "size": 120},{"nombre": "Parcela 2", "size": 70},{"nombre": "Parcela 3", "size": 80}]},{"nombre": "Bloque 3","children":[{"nombre": "Parcela 1", "size": 70},{"nombre": "Parcela 2", "size": 90},{"nombre": "Parcela 3", "size": 100},{"nombre": "Parcela 4", "size": 50}]}]};

    graph($scope.data);
}]);

function graph(data){
    console.log("Generate Graph");
	var margin = {top: 10, bottom: 10, left: 10, right: 10};
	var width = window.innerWidth - margin.left - margin.right - 200;
	var height = window.innerHeight - margin.top - margin.bottom;
	var x = d3.scale.linear().range([0, width]);
	var y = d3.scale.linear().range([0, height]);
	var color = d3.scale.category20();
	var root = null;
	var node = null;

	var treemap = d3.layout.treemap()
		.size([width, height])
		.sticky(true)
		.value(function(d){ return d.size; });

	var svg = d3.select('#graph')
		.append('div')
		.style('position', 'relative')
		.style('width', width + 'px')
		.style('height', height + 'px')
		.style('left', margin.left + 'px')
		.style('top', margin.top + 'px')
		.append("svg")
			.attr("width", width)
			.attr("height", height)
			.append("g")
				.attr("transform", "translate(0.5, 0.5)");

    root = data;
    node = root;

    var nodes = treemap.nodes(data)
        .filter(function(d){ return !d.children; });

    var cell = svg.selectAll("g")
        .data(nodes).enter()
        .append("g")
            .attr("class", "cell")
            .attr("transform", function(d){ return "translate("+d.x+","+d.y+")"; })
            .on("click", function(d){
                if(node == d.parent) return zoom(d);
                else return zoom(d.parent); });
            //return zoom(node == d.parent ? root : d.parent); }

    cell.append("rect")
        .attr("width", function(d){ return d.dx - 1; })
        .attr("height", function(d){ return d.dy - 1; })
        .style("fill", function(d){ return color(d.parent.nombre); });

    cell.append("text")
        .attr("x", function(d){ return d.dx / 2; })
        .attr("y", function(d){ return d.dy / 2;})
        .attr("dy", ".35em")
        .attr("text-anchor", "middle")
        .text(function(d){ return d.nombre + " ("+d.size+"m)"; });

    d3.select(window).on("click", function(){ zoom(root); });

    function zoom(d){
        var kx = width / d.dx;
        var ky = height / d.dy;
        x.domain([d.x, d.x + d.dx]);
        y.domain([d.y, d.y + d.dy]);

        var t = svg.selectAll("g.cell")
            .transition().duration(700)
            .attr("transform", function(d){ return "translate("+x(d.x)+", "+y(d.y)+")"; });

        t.select("rect")
            .attr("width", function(d){ return kx * d.dx - 1; })
            .attr("height", function(d){ return ky * d.dy - 1; });

        t.select("text")
            .attr("x", function(d){ return kx * d.dx / 2; })
            .attr("y", function(d){ return ky * d.dy / 2; });

        node = d;
        d3.event.stopPropagation();
    }

    /*document.querySelector("btnRoot").onClick = function(){
        zoom(root);
    };*/
    d3.select("#btnRoot")
        .on('click', function(){ zoom(root); });
}
