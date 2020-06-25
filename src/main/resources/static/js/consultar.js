$(function(e) {

    $('.tipo-filtro').unbind('click').click(function(e){
        e.preventDefault();

        $('.tipo-filtro').removeClass('active');
        $(this).addClass('active');
        $('#dataTableConta').DataTable().clear().draw();
        $('#inicio').val('');
        $('#fim').val('');
    });

    $('.consultar-btn').click(function(e){
        e.preventDefault();

        const idCliente = $('#input-select').val();
        const inicio = $('#inicio').val();
        const fim = $('#fim').val();
        const status = $('.tipo-filtro.active')[0].id;
        const url = $(e.target).data('url');

        const tipoServicoPrestado = {};
        tipoServicoPrestado[tipoServicoPrestado["SERVICO"] = "Prestação de Serviço"];
        tipoServicoPrestado[tipoServicoPrestado["MANUT"] = "Prestação de Serviço"];
        tipoServicoPrestado[tipoServicoPrestado["IMPLANT"] = "Implantação de Sistema"];

        $.ajax({
            url:  `${url}/buscar`,
            type: 'POST',
            data: {idCliente, inicio, fim, status},
            success: function (data) {
                console.log(data);
                $('#dataTableConta').DataTable().clear().draw();
                data && data.length > 0 && data.map(item => {
                    console.log('item ', item);
                    $('#dataTableConta').DataTable().row.add([item.id, item.status.charAt(0).toUpperCase() + item.status.slice(1), item.cliente.nomeFantasia,
                        formatarCNPJ(item.cliente.cnpj, item.cliente.tipo), tipoServicoPrestado[item.tipoServicoPrestado], formatarValor(item.valorTotal),
                        formatarData(item.dataVencimento), gerarAcao(item.id)]).draw();
                })

                $(".cnpj-inputmask").inputmask("99.999.999/9999-99", { "placeholder": "00.000.000/0000-00" });
                $(".cpf-inputmask").inputmask("999.999.999-99", { "placeholder": "000.000.000-00" });
                $(".currency").inputmask("decimal", { 'alias': 'numeric',
                    'groupSeparator': ',',
                    'autoGroup': true,
                    'digits': 2,
                    'radixPoint': ",",
                    'digitsOptional': false,
                    'allowMinus': false,
                    'prefix': 'R$ ',
                    'placeholder': '0,00',
                    unmaskAsNumber: true
                });
            }
        }).done(function(data){
            return data;
        }).fail(function(data){

            Swal.fire(
                'Error!',
                'Não foi possível encontrar resultado para o filtro selecionado!',
                'error'
            )
            return;
        });
    });

    function gerarAcao(id) {
        return `<a class="btn btn-secondary btn-small"
                 href="/contas-receber/view?id=${id}"
                 style="background-color: #0998b0; border-color: #0998b0;">Visualizar</a>`
    }

    function formatarData(dataVencimento) {
        const dia = dataVencimento.slice(8);
        const mes = dataVencimento.slice(5, 7);
        const ano = dataVencimento.slice(0, 4);
        return `<span class="date-inputmask">${dia}/${mes}/${ano}</span>`
    }

    function formatarCNPJ(cnpj, tipo) {
        return `<span class="${tipo === 'F' ? 'cpf-inputmask' : 'cnpj-inputmask'}">${cnpj}</span>`
    }

    function formatarValor(valor) {
        return `<span class="currency">${valor}</span>`
    }
});

$(document).ready(function () {
    let searchParams = new URLSearchParams(window.location.search)
    const cliente = searchParams.get('cliente')

    if(cliente){
        $('#input-select').val(cliente).change();
        $('#inicio').val('01/06/2020')
        $('#fim').val('30/06/2020')
    }
})