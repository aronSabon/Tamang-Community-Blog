/**
 * 
 */
document.querySelectorAll('a').forEach(function(anchor) {
    anchor.addEventListener('click', function(e) {
        var button = this.querySelector('button');
        if (button.disabled) {
            e.preventDefault();  // Prevent link navigation if button is disabled
        }
    });
});