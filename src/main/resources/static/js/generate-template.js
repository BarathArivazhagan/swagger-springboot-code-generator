

var GenerateTemplate={
		
		
		handleTemplate:function(element){
			console.log("handle template is called"+element);
			var text=element.innerText.toLowerCase();
			if( text.search(TemplateConstants.MICROSERIVCE) > 0){
				GenerateTemplate.getTemplate(TemplateConstants.MICROSERIVCETEMPLATE);
			}else if(text.search(TemplateConstants.CLASS) > 0){
				GenerateTemplate.getTemplate(TemplateConstants.CLASSTEMPLATE);
			}else if(text.search(TemplateConstants.METHOD) > 0){
				GenerateTemplate.getTemplate(TemplateConstants.METHODTEMPLATE);
			}
		},
		
		renderTemplate:function(html){
			$("#render-template").html(html);
		},


		getTemplate :function(name){
			console.log("getting the template");
			
			$.ajax({
			    url: "/html/"+name+".html",
			    cache: true,
			    success: function(data) {
			      source    = data;
			      template  = Handlebars.compile(source);
			      GenerateTemplate.renderTemplate(template);
			    }               
			  });  
		},
		
		findTemplateName: function(element){
			var id;
		},
		
		generateId: function(){			
			    var text = "";
			    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

			    for( var i=0; i < 5; i++ )
			        text += possible.charAt(Math.floor(Math.random() * possible.length));

			    return text;
			
		},
		
		validateModel: function(){
			console.log("validate model is called ");
			
			$.ajax({
			    url: "/html/"+name+".html",
			    cache: true,
			    success: function(data) {
			      source    = data;
			      template  = Handlebars.compile(source);
			      GenerateTemplate.renderTemplate(template);
			    }               
			  });  
			
		},
		
		saveTemplate: function(){
			console.log("save model is called ");
			$.ajax({
			    url: "/saveMsModel",
			    method: "POST",
			    contentType: "application/json",
			    data: GenerateTemplate.generateMicroserviceRequest(),
			    success: function(data) {
			    	if(data != null ){
			    		console.log("model saved successfully"+data);
			    		
			    	}
			    	alert("Model saved successfully");
			    	
			    	
			    } ,
			    error : function(error){
			    	console.log("error in saving the model");
			    }
			  }); 
		},
		
		clearTemplate:function(){
			console.log("clear model is called ");
		},
		
		generateMicroserviceRequest:function(){
			var request={
					
					"templateName": $("#templateName").val(),
					"models": GenerateTemplate.getModels()
					
			};
			$("#customTemplateName").text($("#templateName").val());
			console.log("JSON REQUEST IS "+JSON.stringify(request));
			
			return JSON.stringify(request);
		},
		
		getModels:function(){
			console.log("Getting the models ");
			var modelObj=$("#templateModel").val();
			var jsonObj=JSON.parse(modelObj).models;
			return jsonObj;
		}
		
		
		
};


var TemplateConstants={
		
		MICROSERIVCE: "microservice",
		CLASS: "class",
		METHOD: "method",
		MICROSERIVCETEMPLATE: "create-microservice",
		CLASSTEMPLATE: "create-class",
		METHODTEMPLATE: "create-method"
		

};




