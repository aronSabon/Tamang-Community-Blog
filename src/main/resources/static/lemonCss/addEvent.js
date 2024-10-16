/**
 * load tabs
 * this script is not required for switching tabs to work
 */

document.addEventListener('DOMContentLoaded', function () {
  const nextButtons = document.querySelectorAll('.next-btn');
  
  nextButtons.forEach(button => {
    button.addEventListener('click', function () {
      const nextTab = this.getAttribute('data-next');
      const nextTabEl = document.querySelector(`#${nextTab}-tab`);
      
      if (nextTabEl) {
        const tab = new bootstrap.Tab(nextTabEl);
        tab.show();
      }
    });
  });
});

/**
 * dynamically insert add guest form
 */
const addGuestBtn = document.getElementById('addGuest');
   const guestContainer = document.getElementById('guestContainer');
   let guestCount = 0;

   addGuestBtn.addEventListener('click', function () {
       guestCount++;

       const guestSection = document.createElement('div');
       guestSection.classList.add('guest-section');
       guestSection.innerHTML = `
           <div class="row">
       	 <div class="form-group  title-group">
		<label for="guest${guestCount}">Title</label>
		<select name="guest[${guestCount}].title" id="guest${guestCount}" class="form-control">
			<option value="">Select</option>
			<option value="Mrs">Mrs</option>
			<option value="Mr">Mr</option>
			<option value="Miss">Miss</option>
		</select>
	</div>
               <div class="form-group">
                   <label for="firstName${guestCount}" >First Name</label>
                   <input id="firstName${guestCount}" type="text" placeholder="Guest First Name" name="guest[${guestCount}].firstName" class="form-control"/>
               </div>
               <div class="form-group">
               <label for="middleName${guestCount}" >Middle Name</label>
               <input id="middleName${guestCount}" type="text" placeholder="Guest Middle Name" name="guest[${guestCount}].middleName" class="form-control"/>
           	</div>
           </div>
           <div class="row">
               <div class="form-group">
				<label for="lastName${guestCount}">Last Name</label>
                   <input id="lastName${guestCount}" type="text" placeholder="Guest Last Name" name="guest[${guestCount}].lastName" class="form-control"/>
               </div>
           </div>
           <div class="row">
               <div class="form-group">
                   <label for="description${guestCount}">Description</label>
                   <input id="description${guestCount}" type="text" placeholder="Guest Description" name="guest[${guestCount}].description" class="form-control"/>
               </div>
           </div>
           <button type="button" class="remove-btn" onclick="removeGuest(this)">Remove</button>
       `;

       guestContainer.appendChild(guestSection);
   });

   function removeGuest(button) {
       const guestSection = button.parentElement;
       guestContainer.removeChild(guestSection);
   }