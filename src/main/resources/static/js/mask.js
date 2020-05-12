$(function(e) {

    $(".date-inputmask").inputmask("99/99/9999", { "placeholder": "dd/mm/yyyy" });
    $(".currency").inputmask("decimal", { 'alias': 'numeric',
                'groupSeparator': ',',
                'autoGroup': true,
                'digits': 2,
                'radixPoint': ",",
                'digitsOptional': false,
                'allowMinus': false,
                'prefix': '',
                'placeholder': '0,00',
                unmaskAsNumber: true
     });
});
