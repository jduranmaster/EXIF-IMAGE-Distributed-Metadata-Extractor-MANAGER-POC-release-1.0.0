package com.epsilon.metadata.image.distributed.core;

import java.io.File;
import java.util.Collection;

import com.epsilon.metadata.image.distributed.core.model.PhotoLocation;

/*
* @author jduranmaster / 
* @version 1.0
*/
public interface IImageMetadataExtractor {

	/**
	 *	printGenericImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	public boolean printGenericImageMetadataInfo(File file);
	
	/**
	 *	printJPEGImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	public boolean printJPEGImageMetadataInfo(File file);
	
	/**
	 *	printBMPImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	public boolean printBMPImageMetadataInfo(File file);
	
	/**
	 *	printPNGImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	public boolean printPNGImageMetadataInfo(File file);
	
	/**
	 *	printGIFImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	public boolean printGIFImageMetadataInfo(File file);
	
	/**
	 *	printTIFFImageMetadataInfo():	method used to print the Metadata related to the image file.
	 *	
	 *	@param		file			a java.io.File object with all the data related to the image.
	 *	@return						a boolean indicating if the image Metadata could be read.
	 * */
	public boolean printTIFFImageMetadataInfo(File file);
	
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
	public Collection<PhotoLocation> buildGeoTagMapFromImageMetadataAndExportToConsole(String filePath, final String[] acceptedExtensions);
	
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
	public Collection<PhotoLocation> buildGeoTagMapFromImageMetadataAndExportToHTMLFile(String filePath, String fileName, final String[] acceptedExtensions);
}