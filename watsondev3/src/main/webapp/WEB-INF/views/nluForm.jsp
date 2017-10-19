<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<title>Insert title here</title>
<link rel="shortcut icon" href="/favicon.ico" />
<script type="text/javascript">
$(document).ready(function(){
	$('#btnOk').click(function(){
		console.log('step 1');
		var jqxhr = $.getJSON("nluProcess?&" + 
		$('#myform').serialize(), 
		  function(data) {
 	  		var emotion = data.emotion.document.emotion;
 	  		var display = '<table>';
 	  		display += '<tr><th>분노</th><td>' + emotion.anger + '</td><td>' + barchart(emotion.anger) + '</td></tr>';
 	  		display += '<tr><th>거부감&nbsp;</th><td>' + emotion.disgust + '</td><td>' + barchart(emotion.disgust) + '</td></tr>';
 	  		display += '<tr><th>두려움</th><td>' + emotion.fear + '</td><td>' + barchart(emotion.fear) + '</td></tr>';
 	  		display += '<tr><th>즐거움</th><td>' + emotion.joy + '</td><td>' + barchart(emotion.joy) + '</td></tr>';
 	  		display += '<tr><th>슬픔</th><td>' + emotion.sadness + '</td><td>' + barchart(emotion.sadness) + '</td></tr>';
 	  		display += '</table>'
 	  		$('#display').html(display);
		  })
		  .fail(function() {
		    console.log( "error" );
		  })
		  .always(function() {
		    console.log( "complete" );
		  }
		);

	});
});
function barchart(val) {
	var w = parseInt(val * 100);
	return '<img src="<c:url value="/resources/img/point_red.png" />" height="7" width="' + w + '" />';
}
</script>
</head>
<body>
<form id="myform">
<h1>Natural Language Understanding</h1>
<a href="list">감성분석하기</a>
<center>
<textarea id="statement" name="statement" rows="5" cols="40">
It’s good to be home.
Thank you.
We’re on live TV here, I’ve got to move.
You can tell that I’m a lame duck, because nobody is following instructions.Continue reading the main storyRELATED COVERAGE
Obama, Saying Goodbye, Warns of Threats to National Unity JAN. 11, 2017
For Many on Chicago’s South Side, Obama’s Farewell Will Be Personal JAN. 9, 2017
Everybody have a seat.

My fellow Americans, Michelle and I have been so touched by all the well-wishes that we’ve received over the past few weeks. But tonight it’s my turn to say thanks.
Whether we have seen eye-to-eye or rarely agreed at all, my conversations with you, the American people — in living rooms and in schools; at farms and on factory floors; at diners and on distant military outposts — those conversations are what have kept me honest, and kept me inspired, and kept me going. And every day, I have learned from you. You made me a better president, and you made me a better man.
So I first came to Chicago when I was in my early twenties, and I was still trying to figure out who I was; still searching for a purpose to my life. And it was a neighborhood not far from here where I began working with church groups in the shadows of closed steel mills.

It was on these streets where I witnessed the power of faith, and the quiet dignity of working people in the face of struggle and loss.
(CROWD CHANTING “FOUR MORE YEARS”)
I can’t do that.
Now this is where I learned that change only happens when ordinary people get involved, and they get engaged, and they come together to demand it.
After eight years as your president, I still believe that. And it’s not just my belief. It’s the beating heart of our American idea — our bold experiment in self-government.
It’s the conviction that we are all created equal, endowed by our creator with certain unalienable rights, among them life, liberty, and the pursuit of happiness.
It’s the insistence that these rights, while self-evident, have never been self-executing; that We, the People, through the instrument of our democracy, can form a more perfect union.
What a radical idea, the great gift that our Founders gave to us. The freedom to chase our individual dreams through our sweat, and toil, and imagination — and the imperative to strive together as well, to achieve a common good, a greater good.
For 240 years, our nation’s call to citizenship has given work and purpose to each new generation. It’s what led patriots to choose republic over tyranny, pioneers to trek west, slaves to brave that makeshift railroad to freedom.
It’s what pulled immigrants and refugees across oceans and the Rio Grande. It’s what pushed women to reach for the ballot. It’s what powered workers to organize. It’s why GIs gave their lives at Omaha Beach and Iwo Jima; Iraq and Afghanistan — and why men and women from Selma to Stonewall were prepared to give theirs as well.
So that’s what we mean when we say America is exceptional. Not that our nation has been flawless from the start, but that we have shown the capacity to change, and make life better for those who follow.
Yes, our progress has been uneven. The work of democracy has always been hard. It has been contentious. Sometimes it has been bloody. For every two steps forward, it often feels we take one step back. But the long sweep of America has been defined by forward motion, a constant widening of our founding creed to embrace all, and not just some.
If I had told you eight years ago that America would reverse a great recession, reboot our auto industry, and unleash the longest stretch of job creation in our history — if I had told you that we would open up a new chapter with the Cuban people, shut down Iran’s nuclear weapons program without firing a shot, take out the mastermind of 9-11 — if I had told you that we would win marriage equality and secure the right to health insurance for another 20 million of our fellow citizens — if I had told you all that, you might have said our sights were set a little too high.
</textarea>
<br/>
<button type="button" id="btnOk">클릭</button>
<br/><br/>
<div id="display"></div>
</center>
</form>
</body>
</html>