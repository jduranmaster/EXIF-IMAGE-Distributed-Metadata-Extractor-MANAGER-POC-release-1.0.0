package com.epsilon.metadata.image.distributed.app;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.epsilon.metadata.image.distributed.core.ImageMetadataExtractor;

@Component
public class ImageMetadataExtractorGeoTagMapGeneratorApp {

	private static final Log log = LogFactory.getLog(ImageMetadataExtractorGeoTagMapGeneratorApp.class);

	final String[] acceptedExtensions = new String[] { ".jpg", ".jpeg" };
	
	public ImageMetadataExtractorGeoTagMapGeneratorApp() {
		super();
	}

	public void run(String[] args) throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/application-context.xml", ImageMetadataExtractorGeoTagMapGeneratorApp.class);
		log.info("----------------------------------------------------------");
		log.info("Image Metada-Extractor-Geo-Tag-Map-Gen Core Running ......");
		log.info("----------------------------------------------------------");
		context.registerShutdownHook();
		
		ImageMetadataExtractor imageMetadataExtractor = context.getBean(ImageMetadataExtractor.class);
		
		// call the logic that allows to print all the Image Metadata.
		imageMetadataExtractor.buildGeoTagMapFromImageMetadataAndExportToConsole(args[1], acceptedExtensions);
		
		log.info("----------------------------------------------------------");
		log.info("----------------------------------------------------------");
		log.info("----------------------------------------------------------");
		log.info("----------------------------------------------------------");
		log.info("----------------------------------------------------------");
		
		// call the logic that allows to generate the map with all the geo-tags.
		try{
			imageMetadataExtractor.buildGeoTagMapFromImageMetadataAndExportToHTMLFile(args[1], args[2], acceptedExtensions);
		}catch (java.lang.Exception e){
			imageMetadataExtractor.buildGeoTagMapFromImageMetadataAndExportToHTMLFile(args[1], null, acceptedExtensions);
		}
	}
}