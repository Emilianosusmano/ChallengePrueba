package com.emilianosusmano.springboot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.emilianosusmano.springboot.dto.StatsDto;
import com.emilianosusmano.springboot.service.MailService;

@SuppressWarnings("deprecation")
@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private Environment env;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @Async
    @Override
    public void sendEmailEstadisticas(String to, StatsDto stats) {
	        mailSender.send(crearMensajeEstadistica(to, stats));
    }
    
	private MimeMessagePreparator crearMensajeEstadistica(String to, StatsDto stats) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setTo(to);
				helper.setCc(env.getProperty("spring.mail.username"));
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("canthumanos", stats.getContadorDnaHumano());
				model.put("cantmutantes", stats.getContadorDnaMutante());
				model.put("ratio", stats.getRatio());
				model.put("mail", to);
				helper.setSubject(env.getProperty("mail.subject.estadistica"));
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						env.getProperty("vm.email.estadisticas"), "UTF-8", model);
				helper.setText(text, true);
			}
		};
		return preparator;
	}

}
