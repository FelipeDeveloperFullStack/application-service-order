function mascara_valores_decimais(){
	$(".js-currency").maskMoney({
		decimal: ',',
		thousands: '.',
		allowZero: true,
	});
}
//https://github.com/plentz/jquery-maskmoney

$(document).ready(function (){
	mascara_valores_decimais();
});	
