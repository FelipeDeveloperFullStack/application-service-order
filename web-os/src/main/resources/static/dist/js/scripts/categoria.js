/**
 * @Autor Felipe Miguel dos Santos
 * @version 1.0
 */

$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	var codigo = button.data('codigo');
	var modal = $(this);
	var form = modal.find('form');
	var valueAction = form.data('url-base');
	
	if(!valueAction.endsWith('/')){
		valueAction += '/';
	}
	form.attr('action', valueAction + codigo);
	
	var descricao = button.data('categoria');
	modal.find('.modal-body span').html('Deseja realmente apagar a categoria? <strong>'+descricao+'</strong>');
	
});

$('#editarCategoriaModal').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var codigo = button.data('codigo');
	var categoria = button.data('categoria');
	
	var modal = $(this);
	var form = modal.find('form');
	var valueAction = form.data('url-base');
	
	if(!valueAction.endsWith('/')){
		valueAction += "/";
	}
	
	form.attr('action', valueAction + codigo);
	modal.find('.modal-body input').val(categoria);	
});
	
