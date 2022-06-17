<?php

    $connect = new mysqli(
        'localhost',
        '', // <-- Colocar el username de base de datos
        '', // <-- Colocar la contraseña de base de datos
        'dummy',
        null,
        null
    );

    if($connect->connect_error) {
        die('Connection Not Established, '.mysqli_connect_error());
    }

?>
