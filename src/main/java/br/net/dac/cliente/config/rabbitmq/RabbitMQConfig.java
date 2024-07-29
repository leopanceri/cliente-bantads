package br.net.dac.cliente.config.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.net.dac.cliente.consumer.ClienteTransfer;

@Configuration
public class RabbitMQConfig {
	/* 
	@Bean
    Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFacctory,
                               Jackson2JsonMessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFacctory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}
    */
    @Bean
    DefaultClassMapper classMapper() {
    	DefaultClassMapper classMapper = new DefaultClassMapper();
    	Map<String, Class<?>> idClassMapping = new HashMap();
    	classMapper.setTrustedPackages("*");
    	idClassMapping.put("br.net.dac.saga.models.ClienteTransfer", ClienteTransfer.class);
    	
    	classMapper.setIdClassMapping(idClassMapping);
    	
    	return classMapper;
    }
    
    @Bean
    MessageConverter jsonConverter() {
    	Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    	converter.setClassMapper(classMapper());
    	return converter;
    }
}
