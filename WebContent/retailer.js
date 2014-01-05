  var chan = {};
  chan.retailer = {
		  
		   applyTemplate: function(template, data) {    	
		    	for (var d in data) {
		    		if (typeof data[d] === "object") {
		    			template = this.applyTemplate(template, data[d]);
		    		}
		    		var re = new RegExp("{{" + this.escapeSpecialChars(d) + "}}", "g");
		    		template = template.replace(re, data[d]); 		
		    	}
		    	return template;
		    },

		    escapeSpecialChars: function (str) {
		    	return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
		    },
		    
		    formSubmit: function (formId, successHandler, errorHandler) {
		    	$("#"+formId).submit(function(e) {
					 
				    var formObj = $(this);
				    var formURL = formObj.attr("action");
				 
				    if(window.FormData !== undefined) {				 
				        var formData = new FormData(this);
				        $.ajax({
				            url: formURL,
				            type: 'POST',
				            data:  formData,
				            mimeType:"multipart/form-data",
				            contentType: false,
				            cache: false,
				            processData:false,
				            success: function(data, textStatus, jqXHR) {				
				            	if (successHandler && typeof successHandler === 'function') {
				            		successHandler.apply(this, arguments)
				            	}
				            },
				            error: function(jqXHR, textStatus, errorThrown){
				            	if (errorHandler && typeof errorHandler === 'function') {
				            		errorHandler.apply(this, arguments)
				            	}
				            }           
				       });
				        e.preventDefault();
				   }
				});
		    	
				$("#"+formId).submit();
		    },
		    
		    sendRequest: function (url, data, successHandler, errorHandler, type) {
		    	type = type || "GET";
		    	
		    	var request = $.ajax({
								url: url,
								type: type,
								data: data,
								dataType: "html"
							});
		    	
		    	request.done(function() {
		    		if (successHandler && typeof successHandler === "function") {
		    			successHandler.apply(this, arguments);
		    		}
		    	});
		    	request.fail(function() {
		    		if (errorHandler && typeof errorHandler === "function") {
		    			errorHandler.apply(this, arguments);
		    		}
		    	});
		    }
			
  };
  

  chan.retailer.grocery = {
	grocerySubmit : function() {
		var handler = function(response) {
			var template = $("#groceryTemplate").html();
			var jObj = JSON.parse(response);
			$("#preview").append(applyTemplate(template, jObj));
			$('#grocery-image').elevateZoom();
		};
		chan.retailer.formSubmit("groceryform", handler);
		
	},
	
	getAllGroceries: function() {
		var templatestr = $("#groceryTemplate").html();
		var retailer = chan.retailer,
			url = "core/grocery/getAllGroceries",
			data = undefined;
		
		var handler = function (response) {
			var jObj = JSON.parse(response);				
			for (var i=0; i < jObj.length; i++) {
				$("#preview").append(retailer.applyTemplate(templatestr, jObj[i]));
				$('#' + jObj[i]["name"] + "image").elevateZoom(); 
			}				
		};			
		retailer.sendRequest(url, data, handler);		
	}
  };
  
  chan.retailer.basket = {
	DEFAULT_URL: "core/basket/",
	
	  addToBasket: function (data) {
		  var retailer = chan.retailer;		
		  retailer.sendRequest(this.DEFAULT_URL + "insert", data, function () {
			  alert("addded to basket");
		  }, null, "POST");
	  },
	  
	  removeFromBasket: function (data) {
		  alert("soon functionality will be implemented..");
	  },
	  
	  getAllGroceries: function () {
		  var templatestr = $("#basketTemplate").html();
			var retailer = chan.retailer,
				url = this.DEFAULT_URL + "getAllGroceries",
				data = undefined;
			
			var handler = function (response) {
				var jObj = JSON.parse(response);				
				for (var i=0; i < jObj.length; i++) {
					$("#preview").append(retailer.applyTemplate(templatestr, jObj[i]));
					$('#' + jObj[i]["name"] + "image").elevateZoom(); 
				}				
			};
			
		  chan.retailer.sendRequest(url, data, handler);
	  }
  };
  