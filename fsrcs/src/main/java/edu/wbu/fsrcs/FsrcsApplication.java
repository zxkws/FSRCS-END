package edu.wbu.fsrcs;

import edu.wbu.fsrcs.utils.AesEncryptUtil;
import edu.wbu.fsrcs.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan(value = "edu.wbu.fsrcs.dao")
@SpringBootApplication
public class FsrcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsrcsApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}
