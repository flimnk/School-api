package baheanet.schoolApi.controllers;

import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.aluno.DadosAtualizaAluno;
import baheanet.schoolApi.domain.aluno.DadosCadastroAluno;
import baheanet.schoolApi.domain.aluno.DadosDetalhamentoAluno;
import baheanet.schoolApi.domain.service.AlunoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Transactional
    @PostMapping
    public ResponseEntity<DadosDetalhamentoAluno> cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder ) {
        Aluno aluno = alunoService.cadastrarAluno(dados);
        var uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoAluno>> listarAlunos(Pageable pageable) {
        Page<DadosDetalhamentoAluno> page = alunoService.listarAlunos(pageable);
        return ResponseEntity.ok(page);
    }

 

    @Transactional
    @PutMapping()
        public ResponseEntity<DadosDetalhamentoAluno> atualizarAluno( @RequestBody @Valid DadosAtualizaAluno dados) {
            var aluno = alunoService.atualizarAluno(dados);
            return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @Transactional
    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletarAluno(@PathVariable String matricula) {
        alunoService.desativarAluno(matricula);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{nome}")
    public ResponseEntity<DadosDetalhamentoAluno> buscarAlunoPeloNome(@PathVariable String nome) {
        System.out.println(nome);
      var aluno =  alunoService.buscarAlunoPorNome(nome);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }






}
