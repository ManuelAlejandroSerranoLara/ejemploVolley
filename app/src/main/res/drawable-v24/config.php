<?php

    $connect = new mysqli(
        'localhost',
        'manuel',
        '1309MANO./@mariaDataBase',
        'dummy',
        null,
        null
    );

    if($connect->connect_error) {
        die('Connection Not Established, '.mysqli_connect_error());
    }

?>
