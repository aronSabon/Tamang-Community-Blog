/**
 * 
 */
$(document).ready(function () {
     var formIndex = 0;
     
     // Initialize Select2 for the first member field only
     $('#member-0').select2({
         placeholder: "Select Member",
         allowClear: true,
         tags: true
     });

     // Function to initialize Select2 only for the member select elements
     function initializeSelect2(selector) {
         selector.select2({
             placeholder: "Select Member",
             allowClear: true,
             tags: true
         });
     }
     
     //select date greater than active from on active to
     $('input[type="date"]').on('change', function () {
         const activeFromDate = $('#activeFrom-0').val(); // Get the selected date from "Active From"
         if (this.id.startsWith('activeFrom')) {
             // Check if "Active From" date is selected
             if (activeFromDate) {
                 // Create a new date object for the selected date
                 const activeFrom = new Date(activeFromDate);
                 // Set the minimum selectable date for "Active To" to be the day after "Active From"
                 activeFrom.setDate(activeFrom.getDate() + 1);
                 $('#activeTo-0').attr('min', activeFrom.toISOString().split('T')[0]);
             } else {
                 // If no date is selected, clear the minimum date for "Active To"
                 $('#activeTo-0').removeAttr('min');
             }
         }
     });
     
     

     // Function to check if date fields are filled
     function checkDateFields() {
         var isValid = true;

         // Check if any activeFrom or activeTo fields are empty
         $('input[type="date"]').each(function () {
             if ($(this).val() === "") {
                 isValid = false;
             }
         });

         // Enable/disable the add-member button based on date field values
         if (isValid) {
             $('#add-member').prop('disabled', false);
         } else {
             $('#add-member').prop('disabled', true);
         }
     }

     // Initial check for date fields on page load
     checkDateFields();

     // Trigger check when any date field changes
     $('input[type="date"]').on('input', function () {
         checkDateFields();
     });
     

     // Add new committee member form
     $('#add-member').on('click', function () {
         formIndex++;

         // Clone the first form
         var newForm = $('.committee-member-form').first().clone();

         // Remove any previous Select2 container (to avoid duplication)
         newForm.find('.select2-container').remove();

         // Clear the values of the cloned form (both select and input fields)
         newForm.find('select').val(null).trigger('change'); // Reset Select2 fields
        // newForm.find('input').val(''); // Reset date fields
        
                 // Make date fields read-only
         newForm.find('input[type="date"]').attr('hidden', true);
        
         // Hide only the labels for "Active From" and "Active To"
         newForm.find('label[for^="activeFrom"]').attr('hidden', true);
         newForm.find('label[for^="activeTo"]').attr('hidden', true);
         
         // Update the new form's fields with unique IDs
         newForm.attr('data-form-index', formIndex);
         newForm.find('select, input').each(function () {
             var fieldId = $(this).attr('id');
             var newId = fieldId.replace(/\d+/, formIndex); // Update id with new index
             $(this).attr('id', newId);
         });

         // Add a remove button to the new form
         newForm.append('<button type="button" class="btn btn-danger remove-member" >Remove</button>');

         // Append the new form
         $('#committee-member-forms').append(newForm);

         // Reinitialize Select2 only for the member select field
         initializeSelect2(newForm.find('select[name^="member"]'));  // Only initialize on member field

         // Attach remove button functionality
         attachRemoveButton(newForm);
     });

     // Attach remove functionality
     function attachRemoveButton(form) {
         form.find('.remove-member').on('click', function () {
             $(this).closest('.committee-member-form').remove();
         });
     }
 });