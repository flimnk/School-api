package baheanet.schoolApi.controllers;

import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.aluno.DadosDetalhamentoAluno;
import baheanet.schoolApi.domain.professor.*;
import baheanet.schoolApi.domain.service.ProfessorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Transactional
    @PostMapping()
    ResponseEntity<DadosDetalhamentoProfessor> cadastrarProfessor(@RequestBody @Valid DadosCadastroProfessor dados, UriComponentsBuilder uriBuilder){
      var professor= professorService.cadastrarProfessor(dados);
        var uri = uriBuilder.path("/professor/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoProfessor(professor));


    }
    @Transactional
    @PutMapping()
    ResponseEntity<DadosDetalhamentoProfessor> atualzarProfessor(@RequestBody @Valid DadosAtualizaProfessor dados){
        var professor= professorService.atualizarProfessor(dados);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));


    }

    @Transactional
    @DeleteMapping("/{cpf}")
    ResponseEntity deletarProfessor(@PathVariable  String cpf){
       professorService.deletarProfessor(cpf);
        return ResponseEntity.ok().build();

    }


    @GetMapping("/{nome}")
    public ResponseEntity<DadosDetalhamentoProfessor> buscarAlunoPeloNome(@PathVariable String nome) {
        var professor=  professorService.buscarProfessorPorNome(nome);
        return ResponseEntity.ok(new DadosDetalhamentoProfessor(professor));
    }



    @Transactional
    @GetMapping()
    ResponseEntity<Page<DadosDetalhamentoProfessor>> listarProfessores(Pageable pageable){
       Page<DadosDetalhamentoProfessor> page = professorService.listarProfessores(pageable);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/nomes")
    ResponseEntity<List<DadosNomesProfessor>> listarNomeProfessores(){
        var professores  = professorService.listarNomeProfessores();
        return ResponseEntity.ok(professores.stream().map(DadosNomesProfessor::new).toList());

    }



}
