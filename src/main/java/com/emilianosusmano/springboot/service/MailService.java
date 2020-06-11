package com.emilianosusmano.springboot.service;

import com.emilianosusmano.springboot.dto.StatsDto;

public interface MailService {
    public void sendEmailEstadisticas(String to, StatsDto stats);

}
