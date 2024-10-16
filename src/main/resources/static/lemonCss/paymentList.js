/**
 * 
 */

	function showImageModal(imgElement) {
		var imageUrl = imgElement.src;
		document.getElementById('modalImage').src = imageUrl;
		$('#imageModal').modal('show'); // Trigger Bootstrap modal
	}
