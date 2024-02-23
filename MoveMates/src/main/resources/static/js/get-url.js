// get-url.js
console.log("hello1");
document.addEventListener('DOMContentLoaded', function() {
	console.log("hello2");
    var links = document.querySelectorAll('a[data-add-origin-url]');
    console.log("hello3")
    links.forEach(function(link) {
		console.log("hello4");
        link.addEventListener('click', function(event) {
            console.log("hello5");
            var currentUrl = window.location.href;
            var destinationUrl = link.getAttribute('href');
            var separator = destinationUrl.includes('?') ? '&' : '?';
            var originQueryParam = 'originUrl=' + encodeURIComponent(currentUrl);
            console.log("hello6");
            link.setAttribute('href', destinationUrl + separator + originQueryParam);
            console.log("hello7");
        });
    });
});
