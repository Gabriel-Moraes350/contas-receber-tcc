$(function(e) {

    $('#select-cliente-beneficio').change(function(e){
        e.preventDefault();

        const clienteId = $('#select-cliente-beneficio').val();

        $('#btn-beneficio-history-cliente').attr('href', 'http://localhost:8080/consultas?cliente=' + clienteId)
    });
});

$(document).ready(function () {
    $('#select-cliente-beneficio').change();
})