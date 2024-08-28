package baheanet.schoolApi.controllers;

import baheanet.schoolApi.domain.aluno.Aluno;
import baheanet.schoolApi.domain.aluno.DadosCadastroAluno;
import baheanet.schoolApi.domain.aluno.DadosDetalhamentoAluno;
import baheanet.schoolApi.domain.professor.DadosDetalhamentoProfessor;
import baheanet.schoolApi.domain.service.TurmaService;
import baheanet.schoolApi.domain.turma.DadosAtualizacaoTurma;
import baheanet.schoolApi.domain.turma.DadosCadastroTurma;
import baheanet.schoolApi.domain.turma.DadosDetalhamentoTurma;
import baheanet.schoolApi.domain.turma.Turma;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService  turmaService;


    @Transactional
    @PostMapping
    public ResponseEntity<DadosDetalhamentoTurma> cadastrarTurma(@RequestBody @Valid DadosCadastroTurma dados, UriComponentsBuilder uriBuilder ) {
       Turma turma= turmaService.cadastrarTurma(dados);
        var uri = uriBuilder.path("/turma/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTurma(turma));
    }


    @Transactional
    @PutMapping
    public ResponseEntity<DadosDetalhamentoTurma> atualizarTurma(@RequestBody @Valid DadosAtualizacaoTurma dados) {
        Turma turma= turmaService.atualizarTurma(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTurma(turma));
    }


    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTurma>> listarTurmas(Pageable pageable) {
        Page<DadosDetalhamentoTurma> page =turmaService.listarAlunos(pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{nome}")
    public ResponseEntity<DadosDetalhamentoTurma> buscarAlunoPeloNome(@PathVariable String nome) {
        var turma=  turmaService.buscarTurmaPorNome(nome);
        return ResponseEntity.ok(new DadosDetalhamentoTurma(turma));
    }



    @Transactional
    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletarTurma(@PathVariable String nome) {
        turmaService.desativarTurma(nome);
        return ResponseEntity.noContent().build();
    }



}
