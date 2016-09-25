package com.epsilon.metadata.image.distributed.core;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.epsilon.metadata.image.distributed.core.model.PhotoLocation;

/*
 * @author jduranmaster / 
 * @version 1.0
 */
@Service
public class ImageMetadataExtractor implements IImageMetadataExtractor, Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageMetadataExtractor.class);
	
	@Autowired
    private ImageMetadataExtractorBuilder imageMetadataExtractorBuilder;
	
	@Autowired
	private ImageMetadataExtractorGeoTagMapBuilder imageMetadataExtractorGeoTagMapBuilder;
	
	/**
	 *	printGenericImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	@Override
	public boolean printGenericImageMetadataInfo(File file) {
		Metadata metadata = null;
		
		try {
			metadata = imageMetadataExtractorBuilder.getGenericImageMetadaReaderBuilder(file).build();
			printMetadata(metadata);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *	printJPEGImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	@Override
	public boolean printJPEGImageMetadataInfo(File file) {
		Metadata metadata = null;
		
		try {
			metadata = imageMetadataExtractorBuilder.getJpegImageMetadaReaderBuilder(file).build();
			printMetadata(metadata);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *	printBMPImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	@Override
	public boolean printBMPImageMetadataInfo(File file) {
		Metadata metadata = null;
		
		try {
			metadata = imageMetadataExtractorBuilder.getBMPImageMetadaReaderBuilder(file).build();
			printMetadata(metadata);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *	printPNGImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	@Override
	public boolean printPNGImageMetadataInfo(File file) {
		Metadata metadata = null;
		
		try {
			metadata = imageMetadataExtractorBuilder.getPngImageMetadaReaderBuilder(file).build();
			printMetadata(metadata);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *	printGIFImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	@Override
	public boolean printGIFImageMetadataInfo(File file) {
		Metadata metadata = null;
		
		try {
			metadata = imageMetadataExtractorBuilder.getGifImageMetadaReaderBuilder(file).build();
			printMetadata(metadata);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 *	printTIFFImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	@Override
	public boolean printTIFFImageMetadataInfo(File file) {
		Metadata metadata = null;
		
		try {
			metadata = imageMetadataExtractorBuilder.getTiffImageMetadaReaderBuilder(file).build();
			printMetadata(metadata);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 *	buildGeoTagMapFromImageMetadataAndExportToConsole():	method used to build the geotag-map based on the 
	 *															geo-location-data extracted from the image metadata. 
	 *															Output -> System.out. 
	 *	@param		filePath			a String object containning the absolute/relative path to a directory
	 *									containing a SET of images.
	 *	@param		acceptedExtensions	a String array containing a SET of valid extension files that will be
	 *									processed.
	 *	@return		a complete SET containing all the geo-location-data of all the images belonging to the 
	 *				
	 * */
	@Override
	public Collection<PhotoLocation> buildGeoTagMapFromImageMetadataAndExportToConsole(String filePath, String[] acceptedExtensions) {
		Collection<PhotoLocation> photoLocationSet = null;
		
		try {
			photoLocationSet = imageMetadataExtractorGeoTagMapBuilder.
					buildMapFromMetadataExtractionAndExportToConsole(filePath, acceptedExtensions);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return photoLocationSet;
	}

	/**
	 *	buildGeoTagMapFromImageMetadataAndExportToHTMLFile():	method used to build the geotag-map based on the 
	 *															geo-location-data extracted from the image metadata. 
	 *															Output -> FILE. 
	 *	@param		filePath			a String object containning the absolute/relative path to a directory
	 *									containing a SET of images.
	 *	@param		acceptedExtensions	a String array containing a SET of valid extension files that will be
	 *									processed.
	 *	@return		a complete SET containing all the geo-location-data of all the images belonging to the 
	 *				
	 * */
	@Override
	public Collection<PhotoLocation> buildGeoTagMapFromImageMetadataAndExportToHTMLFile(String filePath, String fileName, String[] acceptedExtensions) {
		Collection<PhotoLocation> photoLocationSet = null;
		
		try {
			photoLocationSet = imageMetadataExtractorGeoTagMapBuilder.
					buildMapFromMetadataExtractionAndExportToFile(filePath, fileName,acceptedExtensions);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return photoLocationSet;
	}
	
	/**
	 * 	printMetadata(): 	auxiliary method used to print the Metadata stored in the header of the Image Files.
	 * 	
	 * 	@param		metadata	a Metadata object obtained using the specific builder related to the specific 
	 * 							image file format.
	 * */
	private void printMetadata(Metadata metadata){
		LOGGER.info("-------------------------------------");

        // Iterate over the data and print to log. A Metadata object 
		// contains multiple Directory objects.
        for (Directory directory : metadata.getDirectories()) {
            // Each Directory stores values in Tag objects.
            for (Tag tag : directory.getTags()) {
                System.out.println(tag);
            }
            // Each Directory may also contain error messages.
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                	LOGGER.error("ERROR: " + error);
                }
            }
        }
        LOGGER.info("-------------------------------------");
	}

	public static Logger getLogger() {
		return LOGGER;
	}
}