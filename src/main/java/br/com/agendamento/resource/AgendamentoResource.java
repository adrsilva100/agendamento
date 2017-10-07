package br.com.agendamento.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendamento.model.dto.AgendamentoDTO;
import br.com.agendamento.service.AgendamentoService;

@RestController
public class AgendamentoResource {

	private static final Logger logger = LoggerFactory.getLogger(AgendamentoResource.class);

	@Autowired
	private AgendamentoService agendamentoService;

	@RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> agendarTransferencia (@Valid @RequestBody AgendamentoDTO agendamentoDTO, 
			HttpServletRequest request, HttpServletResponse response){

		logger.info("Agendamento recebido: " + agendamentoDTO.toString());
		try {
			agendamentoService.realizarAgendamento(agendamentoDTO);
			
		} catch (UnsupportedOperationException  | IllegalArgumentException e) {
			logger.info("Erro ao realizar o agendamento: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}catch (Exception e) {
			logger.info("Erro ao realizar o agendamento: " + e.getMessage());
			return new ResponseEntity<String>("ERRO AO SALVAR AGENDAMENTO", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Agendamento salvo com sucesso");
		return new ResponseEntity<String>("AGENDAMENTO SALVO COM SUCESSO", HttpStatus.OK);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ResponseEntity<List<AgendamentoDTO>> visualizarAgendamentos(HttpServletRequest request, HttpServletResponse response){
		List<AgendamentoDTO> agendamentos = agendamentoService.findAll();
		logger.info("Agendamentos encontrados: " + agendamentos.size());
		
		return new ResponseEntity<List<AgendamentoDTO>>(agendamentos, HttpStatus.OK);
	}
}
