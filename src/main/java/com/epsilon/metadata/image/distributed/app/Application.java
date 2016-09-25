package com.epsilon.metadata.image.distributed.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.epsilon.metadata.image.distributed")
//@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Log mainApplicationLOGGER = LogFactory.getLog(Application.class);
	
	public static final String APP_NORMAL_MODE = "1";
	public static final String APP_HTML_MAP_MODE = "2";
	
	@Autowired
	private ImageMetadataExtractorApp imageMetadataExtractorApp;
	
	@Autowired
	private ImageMetadataExtractorGeoTagMapGeneratorApp imageMetadataExtractorGeoTagMapGeneratorApp;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public static Log getMainapplicationlogger() {
		return mainApplicationLOGGER;
	}

	@Override
	public void run(String... args) throws Exception {
		if(args.length >= 2){
			if(args[1] != null){
				if((args[0] != null && args.length > 0)){
					if(args[0].equals(APP_NORMAL_MODE)){
						// print image data.
						imageMetadataExtractorApp.run(args);
					}
					if(args[0].equals(APP_HTML_MAP_MODE)){
						// generate image map location.
						imageMetadataExtractorGeoTagMapGeneratorApp.run(args);
					}else {
						// usage error 2
						mainApplicationLOGGER.info("ERROR. Path to Images-Directory not Found!");
						mainApplicationLOGGER.info(new StringBuilder("USAGE: ").append("java -jar /.jar processingOption (1/2) directory-FilePath ").toString());
					}
				}
			}else {
				// usage error 1
				mainApplicationLOGGER.info("ERROR. Path to Image-File not Found!");
				mainApplicationLOGGER.info(new StringBuilder("USAGE: ").append("java -jar /.jar processingOption (1/2) image-FilePath ").toString());
			}
		}else {
			// usage error 1
			mainApplicationLOGGER.info("ERROR. Input parameters not Found!");
			mainApplicationLOGGER.info(new StringBuilder("USAGE: ").append("java -jar /.jar processingOption (1/2) image-FilePath ").toString());
		}
	}
}