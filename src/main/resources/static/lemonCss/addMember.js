/**
 * 1) js for switching tabs in add member
 * 2) js for adding family member dynamically in add member
 */


	
	/*
	* 1) js for switching tabs in add member
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
	 
	 /*
	 * 2) js for adding family member dynamically in add member
	 */
	 const addFamilyBtn = document.getElementById('addFamilyBtn');
	        const familyContainer = document.getElementById('familyContainer');
	        let familyMemberCount = 0;
	        addFamilyBtn.addEventListener('click', function () {
	            familyMemberCount++;
	            const familyMemberSection = document.createElement('div');
	            familyMemberSection.classList.add('family-member-section');
	            familyMemberSection.innerHTML = `
	                <div class="row">
	                    <div class="form-group">
	                        <label for="relation${familyMemberCount}" >Relation</label>
	                        <select id="relation${familyMemberCount}" name="family[${familyMemberCount}].relation" class="form-control" >
	                    	<option value="">Select Relation</option>
	                    	<option value="Son">Son</option>
	                    	<option value="Daughter">Daughter</option>
	                    	<option value="Son-In-Law">Son-In-Law</option>
	                    	<option value="Daughter-In-Law">Daughter-In-Law</option>
	                    	<option value="Grandfather">Grandfather</option>
	                    	<option value="GrandMother">GrandMother</option>
	                    	<option value="Father">Father</option>
	                    	<option value="Mother">Mother</option>
	                    	<option value="Reserve">Reserve</option>
	                    </select>
	                    </div>
	                </div>
	                <div class="row">
	                <div class="form-group  title-group">
	 			<label for="familyTitle${familyMemberCount}">Title</label>
	 			<select name="family[${familyMemberCount}].title" id="familyTitle${familyMemberCount}" class="form-control">
	 				<option value="">Select</option>
	 				<option value="Mrs">Mrs</option>
	 				<option value="Mr">Mr</option>
	 				<option value="Miss">Miss</option>
	 			</select>
	 		</div>
	                    <div class="form-group">
	                        <label for="familyFirstName${familyMemberCount}">First Name</label>
	                        <input id="familyFirstName${familyMemberCount}" type="text" placeholder="Member First Name" name="family[${familyMemberCount}].firstName" class="form-control"/>
	                        
	                        </div>
	                    <div class="form-group">
	                        <label for="familyMiddleName${familyMemberCount}">Middle Name</label>
	                    
	                        <input id="familyMiddleName${familyMemberCount}" type="text" placeholder="Member Middle Name" name="family[${familyMemberCount}].middleName" class="form-control"/>
	                        
	                             </div>
	                <div class="row">
	               		<div class="form-group">
	               		<label for="familyLastName${familyMemberCount}">Last Name</label>
	              		<input id="familyLastName${familyMemberCount}" type="text" placeholder="Member Last Name" name="family[${familyMemberCount}].lastName" class="form-control" />
	          		</div>
	                <div class="form-group">
	                      <label for="familyImage${familyMemberCount}">Image</label>
	                      <input id="familyImage${familyMemberCount}" type="file" placeholder="Select Image" name="familyImages" class="form-control"/>
	                  </div>
	              </div>
	             <div>
	                <button type="button" class="remove-btn" onclick="removeFamilyMember(this)">Remove</button>
	                </div>
	                
	            `;
	            
	              familyContainer.appendChild(familyMemberSection);
	        });
	        function removeFamilyMember(button) {
	        	console.log("what is happening");
	        	 const familyMemberSection = button.closest('.family-member-section'); // Get the closest family-member-section
	        	    if (familyMemberSection && familyContainer.contains(familyMemberSection)) {
	        	        familyContainer.removeChild(familyMemberSection); // Remove it from the container
	        	    } else {
	        	        console.error("The family member section is not found or not a child of familyContainer");
	        	    }
	        }