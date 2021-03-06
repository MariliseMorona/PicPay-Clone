package br.com.dio.picpayclone.resource;

import br.com.dio.picpayclone.dto.TransacaoDTO;
import br.com.dio.picpayclone.service.ITransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
//abstract não pode ser instacia, pode apenas ser extendida
//classe generica T, recebe qualquer classe
public class TransacaoResource extends ResourceBase<TransacaoDTO> {

    @Autowired
    private ITransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoDTO> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO,
                                               UriComponentsBuilder uriBuilder) {
        TransacaoDTO transacaoRetornoDTO = transacaoService.processar(transacaoDTO);
        String path = "/transacoes/{codigo}";
        return responderItemCriadoComURI(transacaoRetornoDTO, uriBuilder, path, transacaoRetornoDTO.getCodigo());
    }

    @GetMapping
    public ResponseEntity<Page<TransacaoDTO>> listar(@PageableDefault(page = 0, size = 20) Pageable paginacao,
                                                     @RequestParam String login) {
        Page<TransacaoDTO> transacoes = transacaoService.listar(paginacao, login);
        return responderListaDeItensPaginada(transacoes);
    }

}
