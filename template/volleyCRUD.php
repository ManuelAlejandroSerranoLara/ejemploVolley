<?php

require_once 'config.php';

$response = null;

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    if(isset($_POST['methodCall'])) {
        switch($_POST['methodCall']) {
            case 'create':
                $nombre = $_POST['nombre'];
                $apellido = $_POST['apellido'];
                $nombre_usuario = $_POST['nombre_usuario'];
                $clave_usuario = $_POST['clave_usuario'];

                $query = "INSERT INTO 
                            prueba_usuario 
                                (nombre_usuario, 
                                 password, 
                                 nombre, 
                                 apellido) 
                                    VALUES 
                                        ('$nombre_usuario', 
                                         '$clave_usuario', 
                                         '$nombre', 
                                         '$apellido')";
                $result = mysqli_query($connect, $query);

                if($result) {
                    $response['evento'] = "Success";
                } else {
                    $response['evento'] = "Insert process failed";
                }

                break;
            case 'readUser':
                $nombre_usuario = $_POST['nombre_usuario'];
                $clave_usuario = $_POST['clave_usuario'];

                $query = "SELECT 
                            * 
                                FROM
                                    prueba_usuario 
                                        WHERE 
                                            nombre_usuario='$nombre_usuario' 
                                            AND 
                                            password='$clave_usuario'";
                $result = mysqli_query($connect, $query);

                if($connect->affected_rows > 0) {
                    while($row_content = mysqli_fetch_assoc($result)){
                        $info = $row_content;
                        $response["user"][] = $info;
                    }
                    $response['evento'] = 'Success';
                } else {
                    $response['evento'] = "There are no entries.";
                }

                break;

            case 'readAll':
                $query = "SELECT 
	                        prueba_usuario.id_usuario AS 'ID Usuario', 
	                        prueba_usuario.nombre_usuario AS 'Nombre Usuario', 
	                        prueba_usuario.nombre AS 'Nombre del Usuario', 
	                        prueba_usuario.apellido AS 'Apellido del usuario' 
		                        FROM 
			                        prueba_usuario";
                $result = mysqli_query($connect, $query);

                if($result->num_rows > 0) {
                    while($row_content = $result->fetch_assoc()) {
                        $info = array();
                        $info['id'] = $row_content['ID Usuario'];
                        $info['nombreusuario'] = $row_content['Nombre Usuario'];
                        $info['nombre'] = $row_content['Nombre del Usuario'];
                        $info['apellido'] = $row_content['Apellido del usuario'];

                        $response["user"][] = $info;
                    }
                    $response['evento'] = 'Success';
                }

                break;

            default:
                $response['evento'] = "Opcion No Disponible";

                break;
        }

        try {
            echo json_encode($response, JSON_THROW_ON_ERROR);
        } catch (JsonException $e) {
            echo json_last_error_msg();
        }

        mysqli_close($connect);
    } else {
        $response['evento'] = "Metodo Desconocido";
        try {
            echo json_encode($response, JSON_THROW_ON_ERROR);
        } catch (JsonException $e) {
            echo json_last_error_msg();
        }
    }
}

?>
