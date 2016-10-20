function mascara_valores_decimais(){
	$(".js-currency").maskMoney({
		decimal: ',',
		thousands: '.',
		allowZero: true,
	});
}
//https://github.com/plentz/jquery-maskmoney

// Página de movimento de caixa - exclusão
$('#excluirMovimentoCaixaFinanceiro').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var id = button.data('id');
	var parceiro = button.data('parceiro');
	var contaCaixa = button.data('conta');
	var valorMovimento = button.data('valor');
	
	var modal = $(this);
	modal.find('.modal-body span').html('Deseja realmente apagar o movimento de caixa financeiro? <p></p> Código: <strong>'
			+id+'</strong> Parceiro: <strong>'+parceiro+'</strong> <p></p> Conta Caixa: <strong>'+contaCaixa+'</strong> Valor do movimento: <strong>'+valorMovimento+'</strong>');
	
});

$(document).ready(function (){
	mascara_valores_decimais();
});	
