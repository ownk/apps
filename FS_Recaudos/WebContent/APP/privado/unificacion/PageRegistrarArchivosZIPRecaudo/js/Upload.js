 $(document).ready(function(){

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
						alert("Enviando archivos a servidor");
                    });
                    this.on("successmultiple", function(files, response) {
						alert("Archivos almacenados en servidor");
						$('#btn_unificarArchivos').removeAttr('disabled');
                    });
                    this.on("errormultiple", function(files, response) {
						alert("Error enviando archivos al servidor");
                    });
                }

            }

       });