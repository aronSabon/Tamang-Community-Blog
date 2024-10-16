/**
 * 
 */
window.onload = function() {
     fetchImages(); // Fetch images when the page is loaded
 };
 function fetchImages() {
     const selectedType = document.getElementById('type').value;
     let src;
     if(selectedType=="Member"){
     	src="memberImages";
     }
     else{
     	src="eventImages";
     }
     const url = `/gallery/${selectedType}`; // Adjust the URL according to your backend endpoint
 	console.log(url);
     
 	 
     // Dynamically update the gallery title
     document.getElementById('galleryTitle').textContent = selectedType + " Images List";

     fetch(url)
         .then(response =>{
         	 if (!response.ok) {
                  return response.text().then(text => { throw new Error(text); });
              }
              return response.json();
         })
         .then(data => {
         	console.log(data);
             const tableBody = document.querySelector('#datatablesSimple tbody');
             tableBody.innerHTML = ''; // Clear previous results
             
             data.forEach(image => {
                 const row = document.createElement('tr');
                 row.innerHTML = `
                     <td><img src="/${src}/${image}" alt="No Image" /></td>
                     <td>
                         <button class="btn btn-danger btn-sm" onclick="deleteImage('${image}')">Delete</button>
                     </td>
                 `;

                 tableBody.appendChild(row);
             });
         })
         .catch(error => console.error('Error fetching images:', error));
 }
 
 function deleteImage(imageName) {
     const selectedType = document.getElementById('type').value;

     const url = `gallery/${selectedType}/${imageName}`; // Adjust the URL according to your backend endpoint
     fetch(url, { method: 'DELETE' })
         .then(response => {
             if (!response.ok) {
                 return response.text().then(text => { throw new Error(text); });
             }
             return response.text();
         })
         .then(data => {
             console.log("Delete response:", data); // Debugging: check response from delete operation
             fetchImages(); // Refresh the gallery after successful deletion
         })
         .catch(error => console.error('Error deleting image:', error));
 }