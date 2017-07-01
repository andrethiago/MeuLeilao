package br.mp.mpf.meuleilao.infra.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "br.mp.mpf.meuleilao")
@Import(value = {PersistenceConfig.class/*, SecurityConfig.class*/})
public class MainConfig {

}
