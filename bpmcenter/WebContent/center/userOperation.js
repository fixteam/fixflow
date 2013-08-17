// Create variables (in this scope) to hold the API and image size
var jcrop_api,
    boundx,
    boundy;

jQuery(function($){
	
	    
	
	        // Grab some information about the preview pane
	    var $preview = $('#preview-pane'),
	        $pcnt = $('#preview-pane .preview-container'),
	        $pimg = $('#preview-pane .preview-container img'),
	
	        xsize = $pcnt.width(),
	        ysize = $pcnt.height();
	    
	    $('#target').Jcrop({
	      onChange: updatePreview,
	      onSelect: updatePreview,
	      aspectRatio: 1
	    },function(){
	      // Use the API to get the real image size
	      var bounds = this.getBounds();
	      boundx = bounds[0];
	      boundy = bounds[1];
	      // Store the API in the jcrop_api variable
	      jcrop_api = this;
	
	      // Move the preview into the jcrop container for css positioning
	      $preview.appendTo(jcrop_api.ui.holder);
	      
	      jcrop_api.setOptions({
	        setSelect:[100, 100, 50, 50]
	      });
	      jcrop_api.focus();
	    });
	
	    function updatePreview(c)
	    {
	      if (parseInt(c.w) > 0)
	      {
	        var rx = xsize / c.w;
	        var ry = ysize / c.h;
	
	        $pimg.css({
	          width: Math.round(rx * boundx) + 'px',
	          height: Math.round(ry * boundy) + 'px',
	          marginLeft: '-' + Math.round(rx * c.x) + 'px',
	          marginTop: '-' + Math.round(ry * c.y) + 'px'
	        });
	      }
	    };
	    
	    $("#fn_scaled").click(function(){
			//alert("111");
			//var a = "";
	    	$("#scaled").val(JSON.stringify(jcrop_api.tellScaled()));
	      });
	    
	  });
	  
	  