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
    $(".cnpj-inputmask").inputmask("99.999.999/9999-99", { "placeholder": "00.000.000/0000-00" });
    $(".cpf-inputmask").inputmask("999.999.999-99", { "placeholder": "000.000.000-00" });
    $(".insc-estadual-inputmask").inputmask("999.999.999.999", { "placeholder": "000.000.000.000" });
    $(".cep-inputmask").inputmask("99.999-999", { "placeholder": "00.000-000" });
});
