package com.epsilon.metadata.image.distributed.app;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.epsilon.metadata.image.distributed.core.ImageMetadataExtractor;

@Component
public class ImageMetadataExtractorApp {

	private static final Log log = LogFactory.getLog(ImageMetadataExtractorApp.class);

	public ImageMetadataExtractorApp() {
		super();
	}

	public void run(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/application-context.xml", ImageMetadataExtractorApp.class);
		log.info("------------------------------------------");
		log.info("Image Metada-Extractor Core Running ......");
		log.info("------------------------------------------");
		context.registerShutdownHook();
		
		ImageMetadataExtractor imageMetadataExtractor = context.getBean(ImageMetadataExtractor.class);
		log.info(new StringBuilder("File Path: ").append(args[1]).toString());
		
		// call the logic that allows to print all the Image Metadata.
		imageMetadataExtractor.printJPEGImageMetadataInfo(new File(args[1]));
	}
}