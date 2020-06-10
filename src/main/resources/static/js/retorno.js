$(function(){

   $('#fileInput').on('change', (e) => {
        const form = $(e.target).closest('form');

        form.submit();
   });

   $('.btn-details-retorno').on('click', (e) => {
        const retornoId = $(e.target).data('id');

        $.get('/retorno/' + retornoId + '/details', (data) => {
            let body = '';
            data.map(item => {
                body += tableRow(item.retornoContaReceberId.contaReceberId, item.totalAmount);
            })
            $('.modal-data-table').DataTable().clear().destroy();

            $('.retorno-table-body').html(body);
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

const tableRow = (id, total) => {
   return `<tr >
                      <td >${id}</td>
                      <td >${Number(total).toLocaleString("pt-BR") || '0,00'}</td>
                      <td><a class="btn btn-secondary btn-small" href="/contas-receber/view?id=${id}">Visualizar</a>
                      </td>
                  </tr>`
  }