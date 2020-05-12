$(function(){

    $('.delete-contas-btn').click(function(e){
        e.preventDefault();
        if(!confirm('Deseja deletar esse item realmente?')){
            return;
        }

        const id = $(e.target).data('id')
        $.ajax({
            url: '/contas-receber/'+ id,
            type: 'DELETE',

        }).done(function(data){
            alert('Item deletado com sucesso');
            $(e.target).closest('tr').remove();
            return;
        }).fail(function(data){

            alert('Não foi possível deletar o item');
            return;
        });
    });
})