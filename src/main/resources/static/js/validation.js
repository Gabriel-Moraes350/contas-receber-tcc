$(function() {

    let form = $(".date-inputmask").closest('form');
    $(document).on('submit', form, (e) => {

        let dates = $('.date-inputmask');
        let error = false;
        dates.map(date => {
            const localDate = $('.date-inputmask')[date];
            if(localDate.value){
                if(!moment(localDate.value, 'DD/MM/YYYY').isValid()){
                    error = true;
                    return false;
                }
            }
            return true;
        });

        if(error){
            Swal.fire({
                title: 'Ops...',
                text: "Data inválida",
                icon: 'error',
            });
            e.preventDefault();
            return false;
        }
    });
});
