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

// Página de movimento de caixa - exclusão
$('#excluirMovimentoCaixaFinanceiro').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var id = button.data('id');
	var parceiro = button.data('parceiro');
	var contaCaixa = button.data('conta');
	var valorMovimento = button.data('valor');
	
	var modal = $(this);
	modal.find('.modal-body span').html('Deseja realmente apagar o movimento de caixa financeiro? '
			+'<table class="table table-hover">'+
				'<thead>'+
					'<th> ID </th>'+
					'<th> Parceiro </th>'+
					'<th> Conta Caixa </th>'+
					'<th> Valor do movimento </th>'+
				'</thead>'+
				'<tbody>'+
					'<tr>'+
						'<td>'+id+'</td>'+
						'<td>'+parceiro+'</td>'+
						'<td>'+contaCaixa+'</td>'+
						'<td class="js-currency">'+valorMovimento+'</td>'+
					'</tr>'+
				'</tbody>'+
			'</table>');
});


