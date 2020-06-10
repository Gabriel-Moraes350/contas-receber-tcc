$(function(){

    $('.delete-clientes-btn').click(function(e){
        e.preventDefault();

        Swal.fire({
          title: 'Realmente deseja deletar este item?',
          text: "Você não poderá desfazer essa ação.",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          cancelButtonText: 'Cancelar',
          confirmButtonText: 'Sim, deletar!'
        }).then((result) => {
          if (result.value) {
            deletar(e);
          }
        })
    });
})

function deletar(e){

 const id = $(e.target).data('id')
        $.ajax({
            url: '/cliente/'+ id,
            type: 'DELETE',

        }).done(function(data){
            Swal.fire(
              'Deletado!',
              'Item deletado com sucesso!',
              'success'
            )
            $(e.target).closest('tr').remove();
            return;
        }).fail(function(data){

            Swal.fire(
              'Error!',
              'Não foi possível deletar o item!',
              'error'
            )
            return;
        });
}