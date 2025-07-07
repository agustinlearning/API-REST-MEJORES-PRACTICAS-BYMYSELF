package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    MedicoRepository repository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos){
        repository.save(new Medico(datos));

    }
    @GetMapping
    public ResponseEntity<Page<DatosListaMedicos>> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion){
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaMedicos::new);

        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = repository.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetallesMedico(medico));
    }

    // para hacer una eliminacion fisica del medico
//    @Transactional
//    @DeleteMapping("/{id}")
//    public void eliminar(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    //para hacer eliminacion logica del medico
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.elimiar();

        return ResponseEntity.noContent().build();
    }
}
