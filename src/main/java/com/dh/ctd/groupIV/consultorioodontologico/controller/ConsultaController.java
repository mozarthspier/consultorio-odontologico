package com.dh.ctd.groupIV.consultorioodontologico.controller;

import com.dh.ctd.groupIV.consultorioodontologico.model.Consulta;
import com.dh.ctd.groupIV.consultorioodontologico.service.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService dentistaService;

    @PostMapping
    public Consulta cadastraConsulta(@RequestBody Consulta consulta) {
        return dentistaService.cadastrar(consulta);
    }

    @PatchMapping
    public Consulta alteraConsulta(@RequestBody Consulta consulta) {
        return dentistaService.alterar(consulta);
    }

    @GetMapping
    public ResponseEntity consultaConsulta(@RequestParam(value = "id", required = false)Long id) {
        if(id != null) {
            Optional<Consulta> optionalConsulta = dentistaService.consultaConsultaPorId(id);
            if(optionalConsulta.isEmpty()
            ) {
                return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity(optionalConsulta.get(),HttpStatus.OK);
            }
        } else {
            return new ResponseEntity("Lista consultas", HttpStatus.OK);
        }
    }
}

