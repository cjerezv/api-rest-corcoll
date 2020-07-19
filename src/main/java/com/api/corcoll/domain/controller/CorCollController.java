package com.api.corcoll.domain.controller;

import com.api.corcoll.domain.data.model.*;
import com.api.corcoll.domain.services.Categoria.ICategoriaSevice;
import com.api.corcoll.domain.services.Coleccion.IColeccionService;
import com.api.corcoll.domain.services.Noticia.INoticiaService;
import com.api.corcoll.domain.services.Usuario.IUsuarioService;
import com.api.corcoll.domain.services.Util.IUtilService;
import com.api.corcoll.domain.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api/corcoll")
public class CorCollController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICategoriaSevice categoriaSevice;

    @Autowired
    private IColeccionService coleccionService;

    @Autowired
    private INoticiaService noticiaService;

    @Autowired
    private IUtilService utilService;

    @Autowired
    private Utils utils;

    private final Logger log = LoggerFactory.getLogger(CorCollController.class);

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/noticias", produces = "application/json")
    public ResponseEntity<?> obtenerNoticias() throws SQLException {
        List<DTONoticia> listaNoticias = noticiaService.obtenerNoticias();
        Map<String, Object> response = new HashMap<>();
        if (listaNoticias.size() > 0) {
            response.put("noticias", listaNoticias);
            response.put("message", "Noticias obtenidas correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("message", "No se encontraron noticias");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }
    }

    /* Usuarios */


    @GetMapping(path = "/usuarios", produces = "application/json")
    public ResponseEntity<?> obtenerUsuarios() throws SQLException {
        List<DTOUsuario> listaUsuarios = usuarioService.obtenerUsuarios();
        Map<String, Object> response = new HashMap<>();
        if (listaUsuarios.size() > 0) {
            response.put("usuarios", listaUsuarios);
            response.put("message", "Usuarios obtenidos correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("message", "No se encontraron usuarios");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/registro", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> agregarUsuario(@RequestBody DTOUsuario dtoUsuario) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuarioResp = null;
        Boolean existeUsername = utilService.existeUsername(dtoUsuario.getUsername());
        Boolean existeEmail = utilService.existeCorreo(dtoUsuario.getEmail());

        if (dtoUsuario == null || dtoUsuario.getUsername() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar valores requeridos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getNombre() == null || dtoUsuario.getNombre() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar nombre del nuevo usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getApellido() == null || dtoUsuario.getApellido() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar apellido del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (existeUsername) {
            response.put("usuario", dtoUsuario );
            response.put("message", "El username ingresado ya está en uso, ingrese uno distinto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getUsername() == null || dtoUsuario.getUsername() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar username del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } /*else if (dtoUsuario.getEdad() == null || dtoUsuario.getEdad() < 1) {
            response.put("message", "Debe ingresar edad del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }*/ else if (dtoUsuario.getSexo() == null || dtoUsuario.getSexo() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar sexo del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (existeEmail) {
            response.put("usuario", dtoUsuario );
            response.put("message", "El email ingresado ya está en uso, ingrese uno distinto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getEmail() == null || dtoUsuario.getEmail() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar email del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getPassword() == null || dtoUsuario.getPassword() == "") {
            response.put("usuario", dtoUsuario );
            response.put("message", "Debe ingresar password del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

        dtoUsuarioResp = usuarioService.registrarUsuario(dtoUsuario);
        response.put("usuario", dtoUsuarioResp);
        response.put("message", "Se ha registrado un nuevo usuario con username " + dtoUsuarioResp.getUsername());

        DTOUsuario dtoUsuarioId = utilService.obtenerIdUsuario(dtoUsuarioResp.getUsername());

        utilService.agregarRolUsuario(dtoUsuarioId.getId());
        System.out.println("se agregó el rol correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    /*
    @PostMapping(path = "/registro", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> agregarUsuario(@RequestBody DTOUsuario dtoUsuario) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuarioResp = null;
        Boolean existeUsername = utilService.existeUsername(dtoUsuario.getUsername());
        Boolean existeEmail = utilService.existeCorreo(dtoUsuario.getEmail());
        if (dtoUsuario == null) {
            response.put("message", "Debe ingresar valores requeridos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getNombre() == null || dtoUsuario.getNombre() == "") {
            response.put("message", "Debe ingresar nombre del nuevo usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getApellido() == null || dtoUsuario.getApellido() == "") {
            response.put("message", "Debe ingresar apellido del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (existeUsername) {
            response.put("message", "El username ingresado ya está en uso, ingrese uno distinto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getUsername() == null || dtoUsuario.getUsername() == "") {
            response.put("message", "Debe ingresar username del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getEdad() == null || dtoUsuario.getEdad() < 1) {
            response.put("message", "Debe ingresar edad del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getSexo() == null || dtoUsuario.getSexo() == "") {
            response.put("message", "Debe ingresar sexo del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (existeEmail) {
            response.put("message", "El email ingresado ya está en uso, ingrese uno distinto");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getEmail() == null || dtoUsuario.getEmail() == "") {
            response.put("message", "Debe ingresar email del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else if (dtoUsuario.getPassword() == null || dtoUsuario.getPassword() == "") {
            response.put("message", "Debe ingresar password del usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

        dtoUsuarioResp = usuarioService.registrarUsuario(dtoUsuario);
        response.put("usuario", dtoUsuarioResp);
        response.put("message", "Se ha registrado un nuevo usuario con username " + dtoUsuarioResp.getUsername());

        DTOUsuario dtoUsuarioId = utilService.obtenerIdUsuario(dtoUsuarioResp.getUsername());

        utilService.agregarRolUsuario(dtoUsuarioId.getId());
        System.out.println("se agregó el rol correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    */


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/miPerfil", produces = "application/json")
    public ResponseEntity<?> verMiPerfil(@PathVariable("idUsuario") int idUsuario) throws SQLException {
        DTOUsuario dtoUsuario = usuarioService.obtenerPerfil(idUsuario);
        Map<String, Object> response = new HashMap<>();
        if (dtoUsuario.getId() != null && dtoUsuario.getId() > 0) {
            response.put("perfil", dtoUsuario);
            response.put("message", "Perfil de usuario " + dtoUsuario.getUsername() + " obtenido correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            System.out.println("No se encontro el perfil");
            response.put("message", "El usuario no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario1}/amigos/{idUsuario2}", produces = "application/json")
    public ResponseEntity<?> obtenerPerfilAmigo(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        Map<String, Object> response = new HashMap<>();

        //DTOUsuario amigo = usuarioService.obtenerPerfil(username);
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        if (existeAmistad == true) {
            DTOUsuario dtoUsuario = usuarioService.obtenerPerfil(idUsuario2);
            if (dtoUsuario.getId() != null && dtoUsuario.getId() > 0) {
                response.put("perfilUsuario", dtoUsuario);
                response.put("message", "Perfil amigo obtenido correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
            } else {
                System.out.println("No se encontro el perfil");
                response.put("message", "El amigo no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "No puede ver el perfil de un usuario que no es su amigo");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario1}/usuario/{idUsuario2}", produces = "application/json")
    public ResponseEntity<?> obtenerPerfilUsuario(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        Map<String, Object> response = new HashMap<>();

        //DTOUsuario amigo = usuarioService.obtenerPerfil(username);
        //Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);

            DTOUsuario dtoUsuario = usuarioService.obtenerPerfil(idUsuario2);
            if (dtoUsuario.getId() != null && dtoUsuario.getId() > 0) {
                response.put("perfilUsuario", dtoUsuario);
                response.put("message", "Perfil usuario obtenido correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
            } else {
                System.out.println("No se encontro el perfil de usuario");
                response.put("message", "El usuario no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/solicitudes", produces = "application/json")
    public ResponseEntity<?> verSolicitudesAmistad(@PathVariable(value = "idUsuario") int idUsuario) throws SQLException {
        List<DTOUsuario> listaUsuarios = usuarioService.obtenerSolicitudesAmistad(idUsuario);
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario);
        if (listaUsuarios.size() > 0) {
            response.put("usuarios", listaUsuarios);
            response.put("message", "Solicitudes de amistad recibidas de usuario " + dtoUsuario.getUsername() + " obtenidas correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("usuarios", listaUsuarios);
            response.put("message", "El usuario " + dtoUsuario.getUsername() + " no ha recibido solicitudes de amistad");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/amigos", produces = "application/json")
    public ResponseEntity<?> obtenerUsuariosAmigos(@PathVariable(value = "idUsuario") int idUsuario) throws SQLException {
        List<DTOUsuario> listaUsuarios = usuarioService.obtenerUsuariosAmigos(idUsuario);
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario);
        if (listaUsuarios.size() > 0) {
            response.put("usuarios", listaUsuarios);
            response.put("message", "Amigos de usuario " + dtoUsuario.getUsername());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("usuarios", listaUsuarios);
            response.put("message", "Actualmente no tienes amigos, envía solicitudes de amistad a otros usuarios para formar amistad");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/usuariosNoAmigos", produces = "application/json")
    public ResponseEntity<?> obtenerUsuariosNoAmigos(@PathVariable(value = "idUsuario") int idUsuario) throws SQLException {
        List<DTOUsuario> listaUsuarios = usuarioService.obtenerUsuariosNoAmigos(idUsuario);
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario);
        if (listaUsuarios.size() > 0) {
            response.put("usuarios", listaUsuarios);
            response.put("message", "Lista de usuarios para enviar solicitudes por el usuario " + dtoUsuario.getUsername());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } else {
            response.put("usuarios", listaUsuarios);
            response.put("message", "Actualmente no tienes a quien enviar solicitudes de amistad");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @Secured({"ROLE_USER"})
    @PostMapping(path = "/usuarios/{idUsuario1}/solicitudes/{idUsuario2}", produces = "application/json")
    public ResponseEntity<?> enviarSolicitudAmistad(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                    @PathVariable(value = "idUsuario2") int idUsuario2
    ) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        System.out.println("obtener los usuarios");
        DTOUsuario dtoUsuario1 = usuarioService.obtenerPerfil(idUsuario1);
        DTOUsuario dtoUsuario2 = usuarioService.obtenerPerfil(idUsuario2);
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        Boolean existeSolicitud = utilService.existeSolicitudAmistad(idUsuario1, idUsuario2);
        if (dtoUsuario1.getId() == null || dtoUsuario1.getId() == 0
                || dtoUsuario2.getId() == null || dtoUsuario2.getId() == 0) {
            response.put("message", "Ingrese usuarios existentes para formar la nueva amistad");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (dtoUsuario1.getId().equals(dtoUsuario2.getId())) {
            response.put("message", "Error, debe ingresar usuarios distintos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (existeAmistad == true) {
            response.put("message", "Error, ambos usuarios ya son amigos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (existeSolicitud == true) {
            response.put("message", "Error, ya se ha enviado una solicitud de amistad a este usuario");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        try {
            usuarioService.enviarSolicitudAmistad(idUsuario1, idUsuario2);
            response.put("message", "Solicitud de amistad enviada correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Error al enviar la solicitud de amistad");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @PutMapping(value = "/usuarios/{idUsuario1}/solicitudes/{idUsuario2}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> aceptarSolicitudAmisatd(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                     @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        Boolean existeSolicitud = utilService.existeSolicitudAmistad(idUsuario1, idUsuario2);
        Boolean tengoSolicitud = utilService.tengoSolicitudPendiente(idUsuario1, idUsuario2);
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario2);
        if (existeSolicitud == true) {
            if (tengoSolicitud == true) {
                try {
                    usuarioService.aceptarSolicitudAmistad(idUsuario1, idUsuario2);
                    response.put("message", dtoUsuario.getUsername() + " y tú ahora son amigos");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                } catch (Exception e) {
                    response.put("message", "Error al aceptar solicitud de amistad");
                    System.out.println("Error : " + e.getMessage());
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }
            } else {
                response.put("message", "El usuario " + dtoUsuario.getUsername() + " no ha respondido a tu solicitud de amistad");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "No hay solicitud de amistad pendiente entre estos usuarios");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping(value = "/usuarios/{idUsuario1}/solicitudes/{idUsuario2}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> eliminarSolicitudAmisatd(@PathVariable(value = "idUsuario1") int idUsuario1, @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        Boolean existeSolicitud = utilService.existeSolicitudAmistad(idUsuario1, idUsuario2);
        if (existeSolicitud == true) {
            try {
                usuarioService.eliminarSolicitudAmistad(idUsuario1, idUsuario2);
                response.put("message", "Solicitud de amistad rechazada correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } catch (Exception e) {
                response.put("message", "Error al rechazar solicitud de amistad");
                System.out.println("Error : " + e.getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "No hay solicitud de amistad pendiente entre estos usuarios");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping(value = "/usuarios/{idUsuario1}/amigos/{idUsuario2}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> eliminarAmigo(@PathVariable(value = "idUsuario1") int idUsuario1,
                                           @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario2);
        if (idUsuario1 == idUsuario2) {
            response.put("message", "Debe ingresar usuarios distintos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (existeAmistad == true) {
            try {
                usuarioService.eliminarAmigo(idUsuario1, idUsuario2);
                response.put("message", dtoUsuario.getUsername() + " y tú ya no son amigos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } catch (Exception e) {
                response.put("message", "Error al eliminar amigo");
                System.out.println("Error : " + e.getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "No hay amistad entre estos usuarios");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/usuarios/upload")
    public ResponseEntity<?> cambiarFotoPerfil(@RequestParam("archivo") MultipartFile archivo,
                                               @RequestParam("id") int id) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(id);
        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            log.info(rutaArchivo.toString());
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
                response.put("mensaje", "Error al subir la imagen del usuario " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = dtoUsuario.getFotoPerfil();
            if (nombreFotoAnterior != "profile.png" && nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    // archivoFotoAnterior.delete();
                    System.out.println("Se ha eliminado la imagen anterior del usuario " + dtoUsuario.getUsername());
                }
            }
            usuarioService.actualizarFotoPerfil(dtoUsuario.getId(), nombreArchivo);
            DTOUsuario dtoUsuarioFotoModificada = usuarioService.obtenerPerfil(id);

            response.put("perfil", dtoUsuarioFotoModificada);
            response.put("message", "Usuario " + dtoUsuario.getUsername() + " cambió su foto de perfil");
            //return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/detalleColeccion/upload")
    public ResponseEntity<?> cambiarFotoDetalleColeccion(@RequestParam("archivo") MultipartFile archivo,
                                                         @RequestParam("id") int id) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads/detallesColeccion").resolve(nombreArchivo).toAbsolutePath();
            log.info(rutaArchivo.toString());
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
                coleccionService.actualizarFotoDetalleColeccionUsuario(id, nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
                response.put("mensaje", "Error al subir la imagen del detalle coleccion usuario " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            int idColeccionUsuario = utilService.obtenerIdColeccionUsuario(id);
            DTODetalleColeccionUsuario dtoDetalleColeccionUsuario = coleccionService.obtenerDetalleColeccionUsuario(id);

            System.out.println("al cambiar imagen se imprime nuevo detalle");
            System.out.println(dtoDetalleColeccionUsuario.getId() + ", " + dtoDetalleColeccionUsuario.getImagen());


            response.put("detalleColeccion", dtoDetalleColeccionUsuario);
            response.put("message", "Se actualizó la foto del detalle coleccion id " + id);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    @GetMapping("/usuarios/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> mostrarFotoPerfil(@PathVariable String nombreFoto) {
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            System.out.println("El error es : " + e.getCause());
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error no se pudo cargar la imagen de perfil: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @GetMapping("/noticias/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> mostrarFotoNoticia(@PathVariable String nombreFoto) {
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            System.out.println("El error es : " + e.getCause());
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error no se pudo cargar la imagen de perfil: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


    @GetMapping("/colecciones/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> mostrarFotoColeccion(@PathVariable String nombreFoto) {
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            System.out.println("El error es : " + e.getCause());
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error no se pudo cargar la imagen de coleccion: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @GetMapping("/detallesColeccion/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> mostrarFotoDetalleColeccion(@PathVariable String nombreFoto) {
        Path rutaArchivo = Paths.get("uploads/detallesColeccion").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            System.out.println("El error es : " + e.getCause());
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error no se pudo cargar la imagen de coleccion: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }





    /* Colecciones */

    @Secured({"ROLE_USER"}) //
    @GetMapping(path = "/verColeccionEnMiColeccion/{idColeccion}/{idUsuario}", produces = "application/json")
    public ResponseEntity<?> obtenerIdColeccionEnMisColecciones(@PathVariable(value = "idColeccion") int idColeccion,
                                                                @PathVariable(value = "idUsuario") int idUsuario) throws SQLException {

        Map<String, Object> response = new HashMap<>();
        int idColeccionUsuario = coleccionService.obtenerIdColeccionEnMisColecciones(idColeccion,idUsuario);
        response.put("idColeccionUsuario", idColeccionUsuario);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //

    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/colecciones", produces = "application/json")
    public ResponseEntity<?> obtenerColecciones(@RequestParam(value = "$filter", required = false) String filter) throws SQLException {
        List<DTOColeccion> listaColecciones = null;
        Map<String, Object> response = new HashMap<>();
        String idSubCategoria = StringUtils.EMPTY;
        if (filter != null && !filter.equals("") && !filter.equals("()")) {
            idSubCategoria = utils.valorDesdeFiltro(filter, "idSubCategoria");
        }
        listaColecciones = coleccionService.obtenerColecciones(idSubCategoria);
        if (listaColecciones.size() > 0) {
            response.put("colecciones", listaColecciones);
            response.put("message", "Lista colecciones obtenidas correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            response.put("message", "No se encontraron colecciones");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/colecciones/{idColeccion}", produces = "application/json")
    public ResponseEntity<?> obtenerColeccion(@PathVariable(value = "idColeccion") int idColeccion) throws SQLException {
        DTOColeccion dtoColeccion = coleccionService.obtenerColeccion(idColeccion);
        Map<String, Object> response = new HashMap<>();
        if (dtoColeccion.getId() != null && dtoColeccion.getId() > 0) {
            response.put("coleccion", dtoColeccion);
            response.put("message", "Coleccion obtenida correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            response.put("message", "No existe en la base de datos una coleccion con el id " + idColeccion);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/colecciones/{idColeccion}/{idUsuario}", produces = "application/json")
    public ResponseEntity<?> obtenerColeccion2(@PathVariable(value = "idColeccion") int idColeccion,
                                               @PathVariable(value = "idUsuario") int idUsuario) throws SQLException {
        DTOColeccion dtoColeccion = coleccionService.obtenerColeccion2(idColeccion, idUsuario);
        Map<String, Object> response = new HashMap<>();
        if (dtoColeccion.getId() != null && dtoColeccion.getId() > 0) {
            response.put("coleccion", dtoColeccion);
            response.put("message", "Coleccion obtenida correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            response.put("message", "No existe en la base de datos una coleccion con el id " + idColeccion);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/colecciones/{idColeccionUsuario}", produces = "application/json")
    public ResponseEntity<?> obtenerColeccionUsuario(@PathVariable(value = "idUsuario") int idUsuario,
                                                     @PathVariable(value = "idColeccionUsuario") int idColeccionUsuario) throws SQLException {
        DTOColeccionUsuario dtoColeccionUsuario = coleccionService.obtenerColeccionUsuario(idColeccionUsuario);
        Map<String, Object> response = new HashMap<>();
        if (dtoColeccionUsuario.getId() != null && dtoColeccionUsuario.getId() > 0) {
            response.put("coleccionUsuario", dtoColeccionUsuario);
            response.put("message", "Coleccion usuario obtenida correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            response.put("message", "No existe en la base de datos una coleccion usuario con el id " + idColeccionUsuario);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario1}/amigos/{idUsuario2}/colecciones/{idColeccionUsuario}", produces = "application/json")
    public ResponseEntity<?> obtenerColeccionUsuarioAmigo(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                          @PathVariable(value = "idUsuario2") int idUsuario2,
                                                          @PathVariable(value = "idColeccionUsuario") int idColeccionUsuario) throws SQLException {
        DTOColeccionUsuario dtoColeccionUsuario = coleccionService.obtenerColeccionUsuario(idColeccionUsuario);
        Map<String, Object> response = new HashMap<>();
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        Boolean reaccion = utilService.existeReaccion(idUsuario1, idColeccionUsuario);

        if (existeAmistad) {
            if (dtoColeccionUsuario.getId() != null && dtoColeccionUsuario.getId() > 0) {
                if (reaccion) {
                    dtoColeccionUsuario.setReaccion(reaccion);
                }
                response.put("coleccionUsuario", dtoColeccionUsuario);
                response.put("message", "Coleccion usuario obtenida correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
            } else {
                response.put("message", "No existe en la base de datos una coleccion usuario con el id " + idColeccionUsuario);
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }


        } else {
            response.put("message", "No puedes consultar una coleccion de un usuario que no es tu amigo");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }


    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/colecciones/{idColeccion}/detalles", produces = "application/json")
    public ResponseEntity<?> obtenerDetallesColeccion(@PathVariable(value = "idColeccion") int idColeccion) throws SQLException {
        List<DTODetalleColeccion> listaDetallesColeccion = null;
        Map<String, Object> response = new HashMap<>();
        Boolean existeColeccion = utilService.existeColeccion(idColeccion);
        if (existeColeccion) {
            listaDetallesColeccion = coleccionService.obtenerDetallesColeccion(idColeccion);
            if (listaDetallesColeccion.size() > 0) {
                response.put("detallesColeccion", listaDetallesColeccion);
                response.put("message", "Lista detalles coleccion obtenida correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } else {
                response.put("message", "Coleccion sin detalles");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }
        } else {
            response.put("message", "Ingrese un id de coleccion existente para obtener sus detalles");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/colecciones/{idColeccionUsuario}/detalles", produces = "application/json")
    public ResponseEntity<?> obtenerDetallesColeccionUsuario(@PathVariable(value = "idUsuario") int idUsuario,
                                                             @PathVariable(value = "idColeccionUsuario") int idColeccionUsuario) throws SQLException {
        List<DTODetalleColeccionUsuario> listaDetallesColeccionUsuario = null;
        Map<String, Object> response = new HashMap<>();
        Boolean existeColeccionUsuario = utilService.existeColeccionUsuarioParaTraerDetalle(idUsuario, idColeccionUsuario);
        if (existeColeccionUsuario) {
            listaDetallesColeccionUsuario = coleccionService.obtenerDetallesColeccionUsuario(idColeccionUsuario);
            if (listaDetallesColeccionUsuario.size() > 0) {
                response.put("detallesColeccionUsuario", listaDetallesColeccionUsuario);
                response.put("message", "Lista detalles coleccion usuario obtenida correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } else {
                response.put("message", "Coleccion usuario sin detalles");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
            }
        } else {
            response.put("message", "Ingrese un id de usuario y de coleccion existentes para obtener sus detalles");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario1}/colecciones/{idUsuario2}/detalles/{idColeccionUsuario}", produces = "application/json")
    public ResponseEntity<?> obtenerDetallesColeccionUsuarioAmigo(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                                  @PathVariable(value = "idUsuario2") int idUsuario2,
                                                                  @PathVariable(value = "idColeccionUsuario") int idColeccionUsuario) throws SQLException {
        List<DTODetalleColeccionUsuario> listaDetallesColeccionUsuario = null;
        Map<String, Object> response = new HashMap<>();
        Boolean existeColeccionUsuario = utilService.existeColeccionUsuarioParaTraerDetalle(idUsuario2, idColeccionUsuario);
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        if (existeColeccionUsuario) {
            if (existeAmistad) {
                listaDetallesColeccionUsuario = coleccionService.obtenerDetallesColeccionUsuario(idColeccionUsuario);
                if (listaDetallesColeccionUsuario.size() > 0) {
                    response.put("detallesColeccionUsuarioAmigo", listaDetallesColeccionUsuario);
                    response.put("message", "Lista detalles coleccion usuario obtenida correctamente");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                } else {
                    response.put("message", "Coleccion usuario sin detalles");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
                }
            } else {
                response.put("message", "No puedes ver los detalles de coleccion de un usuario que no eres amigo");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "Ingrese un id de usuario y de coleccion existentes para obtener sus detalles");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @PostMapping(path = "/usuarios/{idUsuario}/colecciones/{idColeccion}", produces = "application/json")
    public ResponseEntity<?> agregarColeccionUsuario(@PathVariable(value = "idUsuario") int idUsuario,
                                                     @PathVariable(value = "idColeccion") int idColeccion) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario);
        Boolean existeColeccion = utilService.existeColeccion(idColeccion);
        Boolean existeColeccionUsuario = utilService.existeColeccionUsuario(idUsuario, idColeccion);
        DTOColeccionUsuario dtoColeccionUsuario = null;
        DTOColeccion dtoColeccion = null;
        if (dtoUsuario.getId() != null && dtoUsuario.getId() > 0) {
            if (existeColeccion) {
                if (existeColeccionUsuario) {
                    response.put("message", "Ya posees esta coleccion, intenta seleccionar otra");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                } else {
                    try {
                        dtoColeccionUsuario = coleccionService.agregarColeccionUsuario(idUsuario, idColeccion);

                    } catch (Exception e) {
                        response.put("message", "Error al agregar coleccion usuario");
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    }
                }
            } else {
                response.put("message", "No existe coleccion con id " + idColeccion + " en la bd");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "Usuario con id " + idUsuario + " no existe en la bd");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        response.put("coleccionUsuario", dtoColeccionUsuario);
        response.put("message", "Usuario " + dtoUsuario.getUsername() +
                " agregó la coleccion " + dtoColeccionUsuario.getNombre() + " a sus colecciones");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario}/colecciones", produces = "application/json")
    public ResponseEntity<?> verMisColecciones(@PathVariable(value = "idUsuario") int idUsuario) throws SQLException {
        List<DTOColeccionUsuario> misColecciones = null;
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario);
        if (dtoUsuario.getId() != null && dtoUsuario.getId() > 0) {
            misColecciones = coleccionService.verMisColecciones(idUsuario);
            if (misColecciones.size() < 1) {
                response.put("colecciones_usuario", misColecciones);
                response.put("message", "El usuario con id " + idUsuario + " no tiene colecciones agregadas a su lista");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "El usuario con id " + idUsuario + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        response.put("colecciones_usuario", misColecciones);
        response.put("message", "Colecciones obtenidas orrectamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario1}/colecciones/amigos/{idUsuario2}", produces = "application/json")
    public ResponseEntity<?> verColeccionesAmigos(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                  @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        List<DTOColeccionUsuario> coleccionesAmigo = null;
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario = usuarioService.obtenerUsuario(idUsuario1);
        DTOUsuario dtoUsuario2 = usuarioService.obtenerUsuario(idUsuario2);
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        if (idUsuario1 == idUsuario2) {
            response.put("message", "Debe ingresar usuarios distintos y que ambos sean amigos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (dtoUsuario.getId() != null && dtoUsuario.getId() > 0) {
            if (dtoUsuario2.getId() != null && dtoUsuario2.getId() > 0) {
                if (existeAmistad) {
                    coleccionesAmigo = coleccionService.verColeccionesAmigo(idUsuario2);
                    if (coleccionesAmigo.size() < 1) {
                        response.put("coleccionesAmigo", coleccionesAmigo);
                        response.put("message", "Tu amigo con id " + idUsuario2 + " no tiene colecciones agregadas a su lista");
                        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                    }
                } else {
                    response.put("message", "Solo puedes ver colecciones de tus amigos, envia una solicitud al usuario " + idUsuario2 + " para ver sus colecciones");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }
            } else {
                response.put("message", "El usuario con id " + idUsuario2 + " no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "El usuario con id " + idUsuario1 + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        response.put("coleccionesAmigo", coleccionesAmigo);
        response.put("message", "Colecciones de amigo " + dtoUsuario2.getUsername() + " obtenidas orrectamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    /* Categorias */


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/colecciones/categorias", produces = "application/json")
    public ResponseEntity<?> obtenerCategorias() throws SQLException {
        List<DTOCategoria> listaCategorias = categoriaSevice.obtenerCategorias();
        Map<String, Object> response = new HashMap<>();
        if (listaCategorias.size() > 0) {
            response.put("categorias", listaCategorias);
            response.put("message", "Categorias obtenidas correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            response.put("message", "No se encontraron caegorias");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }
    }


    @Secured({"ROLE_USER"})
    @GetMapping(path = "/colecciones/categorias/subCategorias", produces = "application/json")
    public ResponseEntity<?> obtenerSubCategorias(
            @RequestParam(value = "$filter", required = false) String filter) throws SQLException {
        List<DTOSubCategoria> listaSubCategorias = null;
        Map<String, Object> response = new HashMap<>();
        String idCategoria = StringUtils.EMPTY;
        if (filter != null && !filter.equals("") && !filter.equals("()")) {
            idCategoria = utils.valorDesdeFiltro(filter, "idCategoria");
        }
        listaSubCategorias = categoriaSevice.obtenerSubCategorias(idCategoria);
        if (listaSubCategorias.size() > 0) {
            response.put("sub_categorias", listaSubCategorias);
            response.put("message", "Sub categorias obtenidas correctamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK); //
        } else {
            response.put("message", "No se encontraron sub categorias");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
        }


    }


    @Secured({"ROLE_USER"})
    @PostMapping(path = "/{idUsuario1}/amigos/{idUsuario2}/colecciones/{idColeccion}/reaccion", produces = "application/json")
    public ResponseEntity<?> agregarQuitarReaccionColeccionUsuario(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                                   @PathVariable(value = "idUsuario2") int idUsuario2,
                                                                   @PathVariable(value = "idColeccion") int idColeccion
    ) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOUsuario dtoUsuario1 = usuarioService.obtenerPerfil(idUsuario1); // yo
        DTOUsuario dtoUsuario2 = usuarioService.obtenerPerfil(idUsuario2); // amigo
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);
        Boolean existeReaccion = utilService.existeReaccion(idUsuario1, idColeccion);
        if (existeAmistad) {
            if (existeReaccion) {
                usuarioService.eliminarReaccionAColeccion(idUsuario1, idColeccion);
                response.put("message", "Usuario " + dtoUsuario1.getUsername() + " ha eliminado la reaccion a la coleccion id " + idColeccion +
                        " del usuario " + dtoUsuario2.getUsername());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } else {
                usuarioService.reaccionarAColeccion(idUsuario1, idColeccion);
                response.put("message", "Usuario " + dtoUsuario1.getUsername() + " ha reaccionado a la coleccion id " + idColeccion +
                        " del usuario " + dtoUsuario2.getUsername());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "No se puede reaccionar a una coleccion de un usuario que no es amigo");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

    }

    @Secured({"ROLE_USER"})
    @PostMapping(path = "/coleccionUsuario/comentarios", consumes = "application/json")
    public ResponseEntity<?> comentarColeccion(@RequestBody DTOComentario dtoComentario) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOComentario dtoComentarioResp = null;
        if (dtoComentario.getIdUsuario() > 0 && dtoComentario.getIdUsuario2() > 0) {
            if(dtoComentario.getComentario()!=null && dtoComentario.getComentario().length()>0){
                coleccionService.agregarComentarioColeccionUsuarioAmigo(
                        dtoComentario.getIdColeccionUsuario(), dtoComentario.getIdUsuario(), dtoComentario.getComentario());
            }else{
                response.put("message", "Comentario debe tener caracteres");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }


        }
        response.put("message", "Comentario agregado por el usuario id " + dtoComentario.getIdUsuario() + " a la coleccion usuario "
                + dtoComentario.getIdColeccionUsuario() + " del usuario id " + dtoComentario.getIdUsuario2());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/coleccionUsuario/{idColeccionUsuario}/comentarios", produces = "application/json")
    public ResponseEntity<?> obtenerComentariosColeccionUsuario(@PathVariable("idColeccionUsuario") int idColeccionUsuario) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        List<DTOComentario> comentariosColeccionUsuario = null;
        if(idColeccionUsuario>0){
            comentariosColeccionUsuario = coleccionService.obtenerListaComentariosColeccionUsuario(idColeccionUsuario);
            if(comentariosColeccionUsuario.size() == 0){
                response.put("comentariosColeccionUsuario", comentariosColeccionUsuario);
                response.put("message", "La coleccion usuario " + idColeccionUsuario + " no tiene comentarios");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }else{
                response.put("comentariosColeccionUsuario", comentariosColeccionUsuario);
                response.put("message", "Comentarios coleccion usuario " + idColeccionUsuario + " obtenidos correctamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        }else{
            response.put("message", "La coleccion usuario no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

    }

    @Secured({"ROLE_USER"})
    @PostMapping(path = "/usuarios/mensajes", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> enviarMensaje(@RequestBody DTOMensajePrivado dtoMensajePrivado) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        DTOMensajePrivado dtoMensajePrivadoResp = null;
        System.out.println("en facade");
        System.out.println(dtoMensajePrivado.getIdUsuarioEmisor());
        System.out.println(dtoMensajePrivado.getIdUsuarioReceptor());
        System.out.println(dtoMensajePrivado.getMensaje());
        Boolean existeAmistad = utilService.existeAmistad(dtoMensajePrivado.getIdUsuarioEmisor(),dtoMensajePrivado.getIdUsuarioReceptor());

        if(!existeAmistad){
            response.put("message", "Error al enviar mensaje, usuario " + dtoMensajePrivado.getIdUsuarioEmisor() +
                    "no está en tú lista de amigos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

        if (dtoMensajePrivado.getIdUsuarioEmisor() != null && dtoMensajePrivado.getIdUsuarioEmisor() > 0) {
            if (dtoMensajePrivado.getIdUsuarioReceptor() != null && dtoMensajePrivado.getIdUsuarioReceptor() > 0) {
                if (dtoMensajePrivado.getMensaje() != null && !dtoMensajePrivado.getMensaje().equals("")) {
                    dtoMensajePrivadoResp = usuarioService.enviarMensaje(dtoMensajePrivado);
                    response.put("mensajePrivado", dtoMensajePrivadoResp);
                    response.put("message", "Mensaje de usuario " + dtoMensajePrivadoResp.getIdUsuarioEmisor() +
                            " fue enviado correctamente al usuario " + dtoMensajePrivadoResp.getIdUsuarioReceptor());
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                } else {
                    response.put("message", "El mensaje no puede estar vacio");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }
            } else {
                response.put("message", "Ingrese un id de receptor valido ");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            }
        } else {
            response.put("message", "Ingrese un id de emisor valido ");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
    }

    @Secured({"ROLE_USER"})
    @GetMapping(path = "/usuarios/{idUsuario1}/mensajes/{idUsuario2}", produces = "application/json")
    public ResponseEntity<?> obtenerConversacion(@PathVariable(value = "idUsuario1") int idUsuario1,
                                                 @PathVariable(value = "idUsuario2") int idUsuario2) throws SQLException {
        Map<String, Object> response = new HashMap<>();
        Boolean existeAmistad = utilService.existeAmistad(idUsuario1, idUsuario2);

        if(!existeAmistad){
            response.put("message", "Error al buscar conversación, ingrese usuarios amigos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        if (idUsuario1 > 0 && idUsuario2 > 0 && idUsuario1 != idUsuario2) {
                List <DTOMensajePrivado> dtoMensajePrivadoList =
                        usuarioService.obtenerConversacion(idUsuario1, idUsuario2);
                if (dtoMensajePrivadoList.size() == 0) {
                    response.put("mensajePrivado", dtoMensajePrivadoList);
                    response.put("message", "No hay mensajes entre estos 2 usuarios : " + idUsuario1 +
                            " y " + idUsuario2);
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }
                if (dtoMensajePrivadoList.size() > 0) {
                    response.put("mensajePrivado", dtoMensajePrivadoList);
                    response.put("message", "Conversación obtenida correctamente entre los usuarios id  : " + idUsuario1 +
                            " y " + idUsuario2);
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }else{
                    response.put("mensajePrivado", dtoMensajePrivadoList);
                    response.put("message", "Error al obtener la lista de mensajes");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
                }
        } else {
            response.put("message", "Usuarios no son validos para consultar conversacion");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }



    }
}



