<?php

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    require_once 'config.php';

    if(isset($_POST['methodCall'])) {
        switch($_POST['methodCall']) {
            case 'create':
                $nombre = $_POST['nombre'];
                $apellido = $_POST['apellido'];
                $nombre_usuario = $_POST['nombre_usuario'];
                $clave_usuario = $_POST['clave_usuario'];

                $query = "insert into prueba_usuario (nombre_usuario, password, nombre, apellido) values ('$nombre_usuario', '$clave_usuario', '$nombre', '$apellido')";
                $result = mysqli_query($connect, $query);

                if($result) {
                    $response['evento'] = "Success";
                } else {
                    $response['evento'] = "Insert process failed";
                }

                try {
                    echo json_encode($response, JSON_THROW_ON_ERROR);
                } catch (JsonException $e) {
                    echo json_last_error_msg();
                }

                mysqli_close($connect);
                break;
            case 'read':
                $nombre_usuario = $_POST['nombre_usuario'];
                $clave_usuario = $_POST['clave_usuario'];

                $query = "select * from prueba_usuario where nombre_usuario='$nombre_usuario' and password='$clave_usuario'";
                $result = mysqli_query($connect, $query);

                if($connect->affected_rows > 0) {
                    $response = null;

                    while($rows_content = $result->fetch_assoc()){
                        $response = $rows_content;
                    }
                    $response['evento'] = 'Success';
                } else {
                    $response['evento'] = "There are no entries.";
                }

                try {
                    echo json_encode($response, JSON_THROW_ON_ERROR);
                } catch (JsonException $e) {
                    echo json_last_error_msg();
                }

                mysqli_close($connect);
                break;
        }
    } else {
        $response['Evento'] = "Metodo Desconocido";
        try {
            echo json_encode($response, JSON_THROW_ON_ERROR);
        } catch (JsonException $e) {
            echo json_last_error_msg();
        }
    }
}

?>
