$(function(){

   $('.btn-details-remessa').on('click', (e) => {
        const remessaId = $(e.target).data('id');

        $.get('/remessa/' + remessaId + '/details', (data) => {
            let body = '';
            data.map(item => {
                body += tableRow(item.remessaContaReceberId.contaReceberId, item.totalAmount);
            })
            $('.modal-data-table').DataTable().clear().destroy();

            $('.remessa-table-body').html(body);
            $('.modal-data-table').DataTable({
              lengthChange: false,
              order: [0, 'desc'],
              pageLength: 5,
              language: {
                paginate: {
                    previous: "Anterior",
                    next: "Próximo"
                },
                search: "Buscar",
                info: "Exibindo página _PAGE_ de _PAGES_"
              }
            });
        })
   });
});

