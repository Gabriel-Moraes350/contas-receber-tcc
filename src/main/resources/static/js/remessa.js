$(function(){

   $('.btn-details-remessa').on('click', (e) => {
        const remessaId = $(e.target).data('id');

        $.get('/remessa/' + remessaId + '/details', (data) => {
            let body = '';
            data.map(item => {
                body += tableRowRemessa(item.remessaContaReceberId.contaReceberId, item.totalAmount);
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


const tableRowRemessa = (id, total) => {
   return `<tr >
                      <td >${id}</td>
                      <td >${Number(total).toLocaleString("pt-BR") || '0,00'}</td>
                      <td>
                      <a class="btn btn-secondary btn-small" href="/contas-receber/view?id=${id}">Visualizar</a>
                      <a class="btn btn-outline-primary btn-small" href="/boleto/${id}">Gerar boleto</a>
                      </td>
                  </tr>`
  }
