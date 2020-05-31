$(function(e) {

    $('#tipo-select').on('change', function() {
        if(this.value === 'F') {
            $('.fisica').show();

            $('.juridica').hide();
        } else {
            $('.fisica').hide();

            $('.juridica').show();
        }
    });
});

$(document).ready(function () {
    if(document.getElementById('tipo-select').value === 'F') {
        $('.fisica').show();

        $('.juridica').hide();
    } else {
        $('.fisica').hide();

        $('.juridica').show();
    }

})