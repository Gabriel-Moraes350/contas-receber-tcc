$(function(e) {

    $('#select-cliente').change(function(e){
        e.preventDefault();

        const clienteId = $('#select-cliente').val();

       $.ajax({
            url:  `http://localhost:8080/beneficios/` + clienteId,
            type: 'GET',
        }).done(function(data){
            $("label[for='select-parcela']").css("color", "#71748d");
            $("label[for='valorDesconto']").css("color", "#71748d");
            if (data && $('#select-parcela').length === 1) {
                for (i = 2; i <= 6; i++) {
                    $('#select-parcela').append(new Option(i, i));
                }
//                    $('#valorDesconto').val(defaultDesconto);
            }

            data && data.length > 0 && data.map(item => {

                if (item.descricao === "BLOCK") {
                    $('#select-parcela option').each(function () {
                        if ($(this).val() > '1') {
                            $(this).remove();
                        }
                    });
                    $("label[for='select-parcela']").css("color", "red");
                }

                if (item.descricao === "DESCONTO") {
                    $('#valorDesconto').val(item.valorDesconto);
                    $("label[for='valorDesconto']").css("color", "green");
                }
            })
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
});

$(document).ready(function () {
    $('#select-cliente').change();
})