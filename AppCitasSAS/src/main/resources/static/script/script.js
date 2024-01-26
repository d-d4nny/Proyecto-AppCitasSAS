// Función para ocultar/mostrar elementos según el tipo de usuario
function toggleElements() {
    var userType = document.getElementById("user-type").value;
    var pacientesElements = document.querySelectorAll(".paciente");
    var empleadosElements = document.querySelectorAll(".empleado");

    // Oculta todos los elementos
    pacientesElements.forEach(function(element) {
        element.style.display = "none";
    });

    empleadosElements.forEach(function(element) {
        element.style.display = "none";
    });

    // Muestra elementos específicos según el tipo de usuario
    if (userType === "paciente") {
        pacientesElements.forEach(function(element) {
            element.style.display = "block";
        });
        document.getElementById("clientes").style.display = "block";
        document.getElementById("empleados").style.display = "none";
    } else if (userType === "empleado") {
        empleadosElements.forEach(function(element) {
            element.style.display = "block";
        });
        document.getElementById("clientes").style.display = "none";
        document.getElementById("empleados").style.display = "block";
    }
}

// Llama a la función al cargar la página y cada vez que cambia el tipo de usuario
window.onload = toggleElements;
document.getElementById("user-type").addEventListener("change", toggleElements);


function revisarContraseña() {
	var contraseña = document.getElementById('claveUsuario').value;
    var confContraseña = document.getElementById('confirmarClaveUsuario').value;
    var mensajeContraseña = document.getElementById('mensajeContrasenya');

	if (contraseña === "" && confContraseña === "") {
		mensajeContraseña.textContent = 'No puede dejar campos vacíos';
		document.getElementById("btnRegistro").disabled = true;//deshabilita el boton		
	} else if (contraseña === confContraseña) {
        mensajeContraseña.textContent = 'Las contraseñas coinciden';
        mensajeContraseña.style.color = 'green';
        document.getElementById("btnRegistro").disabled = false;//habilita el boton
    } else {
        mensajeContraseña.textContent = 'Las contraseñas no coinciden';
        mensajeContraseña.style.color = 'red';
        document.getElementById("btnRegistro").disabled = true;//deshabilita el boton
    }
}

function mostrarNotificacion(titulo, mensaje, tipo) {
    Swal.fire({
        title: titulo,
        text: mensaje,
        icon: tipo,
        confirmButtonText: 'OK'
    });
}

function confirmarLogout() {
    Swal.fire({
        title: '¿Estás seguro de que deseas cerrar sesión?',
        text: 'Serás redirigido a la página de bienvenida.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, cerrar sesión'
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById('logoutForm').submit();
        } else {
            console.log('Logout cancelado');
        }
    });
}
function confirmar() {
    return Swal.fire({
        title: '¿Estás seguro de que deseas eliminar?',
        text: 'Esta acción es irreversible.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar.'
    }).then((result) => {
        return result.isConfirmed;
    });
}

function confirmarEliminar(event) {
    const idUsuario = event.currentTarget.getAttribute("data-id");
    confirmar().then(function (confirmado) {
        if (confirmado) {
            window.location.href = 'http://localhost:8080/privada/eliminar/' + idUsuario;
        }
    });
}



