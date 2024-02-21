// get-url.js
document.addEventListener('DOMContentLoaded', function() {
    var links = document.querySelectorAll('a[data-add-origin-url]');
    links.forEach(function(link) {
        link.addEventListener('click', function(event) {
            var currentUrl = window.location.href;
            var destinationUrl = link.getAttribute('href');
            var separator = destinationUrl.includes('?') ? '&' : '?';
            var originQueryParam = 'originUrl=' + encodeURIComponent(currentUrl);
            link.setAttribute('href', destinationUrl + separator + originQueryParam);
        });
    });
});
