$(document).ready(function() {
		
	
	
		//PageLoader
		var _href;
		var pageWrap = document.getElementById( 'pagewrap' ),
			
			triggerLoading = [].slice.call( pageWrap.querySelectorAll( 'a.pageload-link' ) ),
			loader = new SVGLoader( document.getElementById( 'loader' ), { speedIn : 300, easingIn : mina.easeinout } );

		
		function init() {
			
			triggerLoading.forEach( function( trigger ) {
				trigger.addEventListener( 'click', function( ev ) {
					ev.preventDefault();
					
					_href = ev.currentTarget.href;
					
					$( ".contenedor" ).removeClass( "show" );
					$( ".contenedor" ).addClass( "hide" );
					loader.show();
					// after some time hide loader
					setTimeout( function() {
						loader.hide();
						
						location.href = _href;

					}, 2000 );
					
					
				} );
			} );	
		}
		

		init();
});

