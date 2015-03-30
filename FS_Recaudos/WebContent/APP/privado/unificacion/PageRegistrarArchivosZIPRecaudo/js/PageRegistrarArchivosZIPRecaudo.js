 $(document).ready(function(){
			
			$('#form_iniciarProceso .input-daterange').datepicker({
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
				format: 'dd/mm/yyyy' 
            });
			
            Dropzone.options.myAwesomeDropzone = {

                autoProcessQueue: false,
                uploadMultiple: true,
                parallelUploads: 100,
                maxFiles: 100,

                // Dropzone settings
                init: function() {
                    var myDropzone = this;

                    this.element.querySelector("button[type=submit]").addEventListener("click", function(e) {
                        e.preventDefault();
                        e.stopPropagation();
                        myDropzone.processQueue();
                    });
                    this.on("sendingmultiple", function() {
						
                    });
                    this.on("successmultiple", function(files, response) {
						$('#btn_unificarArchivos').removeAttr('disabled');
						$('#btn_unificarArchivos').removeClass('btn-outline');
					
                    });
                    this.on("errormultiple", function(files, response) {
						
                    });
                }

            }

       });