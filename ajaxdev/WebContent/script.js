var classmate = [
	{
		"no":"1",
		"name":"냠냠냠",
		"say":"먹어! 배고프니까~"
	},
	{
		"no":"2",
		"name":"배불",
		"say":"안머거"
	},
	{
		"no":"3",
		"name":"배거파",
		"say":"자바머그꺼야"
	}
];

var items = [];
$.each(classmate, function(key, val){
	items.push(
		'<li id="'+key+'">'+
		val['no']+": "+
		val['name']+": "+
		val['say']+"</li>");	
});
$('#msgTxt').append(items);