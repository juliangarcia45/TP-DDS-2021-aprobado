let guardarDatos = () => {

    $('#login form').submit(function(e) {
        e.preventDefault();
        var nombre = $('#nameUser').val();
        console.log(nombre.length);

        localStorage.setItem("form_name", nombre);
        //MOSTRAR DATOS

        if (localStorage.getItem('form_name').length != 0 && localStorage.getItem('form_name') != 'undefined') {

            mostrarData();
        } else {
            alert('Ingrese su Usuario Nuevamente');
            guardarDatos();

        }

        return false;
    })
}