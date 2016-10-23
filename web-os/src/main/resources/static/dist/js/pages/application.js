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
	formatarDatas();
});	

function isValidDate(str) {
    return str == 'dd/mm/yyyy' || ( /^\d{2}\/\d{2}\/\d{4}$/.test(str) && new Date(str).getTime() );
}

function formatarDatas(){
	$(".datemask").mask("99/99/9999");
	$(".cpf").mask("999.999.999-99");
	$(".cnpj").mask("99.999.999/9999-99");
	$(".cep").mask("99999-999");
	$(".fone_residencial").mask("(62) 9999-9999");
	$(".fone_celular").mask("(62) 9999-9999");
	$(".caixa_alta").keyup(function (){
		$(this).val($(this).val().toUpperCase());
	});
}

// Página de movimento de caixa - exclusão
$('#excluirMovimentoCaixaFinanceiro').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var id = button.data('id');
	
	var parceiro = button.data('parceiro');
	
	var contaCaixa = button.data('conta');
	
	var valorMovimento = button.data('valor');
	
	var modal = $(this);
	
	var form = modal.find('form');
	
	var valueAction = form.data('url-base');
	
	if(!valueAction.endsWith('/')){
		valueAction += '/';
	}
	
	form.attr('action', valueAction + id);
	
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

function desabilitarBotao(){
	$('.js-disabled').prop('disabled', true);
}
