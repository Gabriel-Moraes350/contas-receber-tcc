$(document).ready( function () {
    moment().format();
    $.fn.dataTable.moment('DD/MM/YYYY HH:mm');
    $('.data-table').DataTable({
          lengthChange: false,
          pageLength: 5,
          columns: [
            { orderable: false },
            { orderable: false },
            null,
            { orderable: false },
            { orderable: false },
            null,
            null,
            null,
            { orderable: false }
          ],
          language: {
           paginate: {
                previous: "Anterior",
                next: "Próximo"
            },
            search: "Buscar",
            info: "Exibindo página _PAGE_ de _PAGES_"
         }
    });

    $('.data-table-default').DataTable({
          lengthChange: false,
          order: [0, 'desc'],
          pageLength: 5,
          language: {
            paginate: {
                previous: "Anterior",
                next: "Próximo"
            },
            search: "Buscar",
            info: "Exibindo página _PAGE_ de _PAGES_",
            sEmptyTable: "Preencha os campos acima/Consulta não encontrou dados.",
            sInfoEmpty: "",
         }
    });

    $('.select2').select2();
});
