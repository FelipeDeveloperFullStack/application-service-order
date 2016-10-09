function changeMethod(){
	
	$('#cb_tipo_pessoa').on('change', function (event){
		var valor = $(this).val();
		return valor;
	});
	
	/*$('#mySelect').on('change', function (e) {
	    $('#myTab li a').eq($(this).val()).tab('show'); 
	});*/
}

$(document).ready(function (){
	changeMethod();
	
});	
