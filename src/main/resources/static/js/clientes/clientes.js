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
    if($('#tipo-select').val() === 'F') {
        $('.fisica').show();

        $('.juridica').hide();
    } else {
        $('.fisica').hide();

        $('.juridica').show();
    }

})