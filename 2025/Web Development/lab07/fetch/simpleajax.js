// file simpleajax.js
// var xhr = createRequest();
function getData(dataSource, divID, aName, aPwd)  {
  
	var place = document.getElementById(divID);
	var url = dataSource+"?name="+aName+"&pwd="+aPwd;

	const requestPromise = fetch(url);
	requestPromise.then(
		function (response){
			response.text().then(function(text) {
				place.innerHTML = text;
			});

		}
	);
} 
