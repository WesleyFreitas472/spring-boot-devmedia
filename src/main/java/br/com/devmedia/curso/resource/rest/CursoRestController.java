package br.com.devmedia.curso.resource.rest;


import br.com.devmedia.curso.domain.Curso;
import br.com.devmedia.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        value = "/cursos",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class CursoRestController {

    @Autowired
    private CursoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listar() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Curso curso) {
        service.save(curso);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id`")
                .buildAndExpand(curso.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
