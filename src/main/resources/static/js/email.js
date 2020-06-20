$(document).ready(function (e) {
    doRequest($('#cliente-select').val(), $('#motivo-select').val());
});

$(function (e) {
    var contas = [];

    $('#cliente-select').on('change', function () {
        doRequest($('#cliente-select').val(), $('#motivo-select').val());
    });

    $('#motivo-select').on('change', function () {
        doRequest($('#cliente-select').val(), $('#motivo-select').val());
    });

    $('#btn-enviar').on('click', function () {
        $.each($("input[name='teste']:checked"), function(){
            contas.push($(this).attr('id'));
        });
        if (typeof contas === 'undefined' || contas.length <= 0) {
            Swal.fire(
                'Falha!',
                'Falta selecionar a conta!',
                'error'
            )
            $(e.target).closest('tr').remove();
            return;
        } else {
            let motivo = $('#motivo-select').val();

            doSendMailRequest(contas, motivo);
        }
    });
});

const doSendMailRequest = (contasList, motivo) => {
console.log(contasList);
console.log(typeof (contasList));
$.ajax({
    type: "POST",
    url: "/comunicacao-cliente/sendMail",
    data: JSON.stringify({ contas : contasList,
                                motivo}),
    contentType: "application/json",
    dataType: 'json',
    done : function(id) {
        Swal.fire(
            'Enviado!',
            'Email enviado com sucesso!',
            'success'
        )
        $(e.target).closest('tr').remove();
        return;
    },
    error : function(e) {
        Swal.fire(
            'Error!',
            'Não foi possível enviar o email!',
            'error'
        )
        return;
    },

});
};

const doRequest = (clientSelected, motivoSelected) => {
    let body = '';

    $.get('/comunicacao-cliente/' + clientSelected + '/' + motivoSelected +'/tabela-contas'  , (data) => {
        console.log("entrou no get => " + data);

        if (data !== "") {
            data.map(item => {
                if (typeof(item.valorTotalRecebido) === "undefined") {
                    item.valorTotalRecebido = 0;
                }
                body += contasRow(item.id, item.cliente.cnpj, item.dataVencimento, item.valorTotal, item.status);
            })
        }
        $('.contas-data-table').DataTable().clear().destroy();

        $('.contas-table-body').html(body);
        $('.contas-data-table').DataTable({
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
}

const contasRow = (id, cnpj, dataVencimento, valorTotal, status)  => {
    return `<tr>
                     <td>
                        <label class="custom-control custom-checkbox">
                            <input class="custom-control-input" id="${id}" name="teste" type="checkbox">
                            <span class="custom-control-label"></span>
                        </label>
                    </td>
                      <td >${id}</td>
                      <td >${cnpj}</td>
                      <td >${new Date(dataVencimento).toLocaleString("pt-BR") || 'dd-mm-yyyy'}</td>
                      <td >${Number(valorTotal).toLocaleString("pt-BR") || '0,00'}</td>
                      <td >${status}</td>
                  </tr>`

}