package com.epsilon.metadata.image.distributed.core;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.bmp.BmpMetadataReader;
import com.drew.imaging.gif.GifMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.png.PngProcessingException;
import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.metadata.Metadata;

/*
 * @author jduranmaster / 
 * @version 1.0
 */
@Component
public class ImageMetadataExtractorBuilder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 	getGenericImageMetadaReaderBuilder():	auxiliary method that allows to get an instance of 
	 * 											GenericImageMetadaReaderBuilder.
	 * 	
	 * 	@param		file 				the file which Metadata will be processed.
	 * 	@return							an instance of GenericImageMetadaReaderBuilder object.
	 * */
	public GenericImageMetadaReaderBuilder getGenericImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
		return new GenericImageMetadaReaderBuilder(file);
	}
	
	/**
	 * 	getJpegImageMetadaReaderBuilder():		auxiliary method that allows to get an instance of 
	 * 											JpegImageMetadaReaderBuilder.
	 * 	
	 * 	@param		file 				the file which Metadata will be processed.
	 * 	@return							an instance of JpegImageMetadaReaderBuilder object.
	 * */
	public JpegImageMetadaReaderBuilder getJpegImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
		return new JpegImageMetadaReaderBuilder(file);
	}
	
	/**
	 * 	getBMPImageMetadaReaderBuilder():		auxiliary method that allows to get an instance of 
	 * 											BMPImageMetadaReaderBuilder.
	 * 	
	 * 	@param		file 				the file which Metadata will be processed.
	 * 	@return							an instance of BMPImageMetadaReaderBuilder object.
	 * */
	public BMPImageMetadaReaderBuilder getBMPImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
		return new BMPImageMetadaReaderBuilder(file);
	}
	
	/**
	 * 	getTiffImageMetadaReaderBuilder():		auxiliary method that allows to get an instance of 
	 * 											TiffImageMetadaReaderBuilder.
	 * 	
	 * 	@param		file 				the file which Metadata will be processed.
	 * 	@return							an instance of TiffImageMetadaReaderBuilder object.
	 * */
	public TiffImageMetadaReaderBuilder getTiffImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
		return new TiffImageMetadaReaderBuilder(file);
	}
	
	/**
	 * 	getPngImageMetadaReaderBuilder():		auxiliary method that allows to get an instance of 
	 * 											PngImageMetadaReaderBuilder.
	 * 	
	 * 	@param		file 				the file which Metadata will be processed.
	 * 	@return							an instance of PngImageMetadaReaderBuilder object.
	 * */
	public PngImageMetadaReaderBuilder getPngImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
		return new PngImageMetadaReaderBuilder(file);
	}
	
	/**
	 * 	getGifImageMetadaReaderBuilder():		auxiliary method that allows to get an instance of 
	 * 											GifImageMetadaReaderBuilder.
	 * 	
	 * 	@param		file 				the file which Metadata will be processed.
	 * 	@return							an instance of GifImageMetadaReaderBuilder object.
	 * */
	public GifImageMetadaReaderBuilder getGifImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
		return new GifImageMetadaReaderBuilder(file);
	}
	
	// Inner classes used to build the different types of Metadata related to the specific types of
	// images that are going to be processed.
	
	/*
	 * @author jduranmaster /
	 * 
	 * @version 1.0
	 */
	public static class GenericImageMetadaReaderBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Metadata metadata;

		public GenericImageMetadaReaderBuilder(File file) throws ImageProcessingException, IOException {
			metadata = ImageMetadataReader.readMetadata(file);
		}

		public Metadata build() {
			return metadata;
		}
	}

	/*
	 * @author jduranmaster /
	 * 
	 * @version 1.0
	 */
	public static class JpegImageMetadaReaderBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Metadata metadata;

		public JpegImageMetadaReaderBuilder(File file) throws JpegProcessingException, IOException {
			 metadata = JpegMetadataReader.readMetadata(file);
		}
		
		public Metadata build() {
			return metadata;
		}
	}

	/*
	 * @author jduranmaster /
	 * 
	 * @version 1.0
	 */
	public static class BMPImageMetadaReaderBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Metadata metadata;

		public BMPImageMetadaReaderBuilder(File file) throws IOException {
			metadata = BmpMetadataReader.readMetadata(file);
		}
		
		public Metadata build() {
			return metadata;
		}
	}

	/*
	 * @author jduranmaster /
	 * 
	 * @version 1.0
	 */
	public static class TiffImageMetadaReaderBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Metadata metadata;

		public TiffImageMetadaReaderBuilder(File file) throws TiffProcessingException, IOException {
			metadata = TiffMetadataReader.readMetadata(file);
		}
		
		public Metadata build() {
			return metadata;
		}
	}

	/*
	 * @author jduranmaster /
	 * 
	 * @version 1.0
	 */
	public static class PngImageMetadaReaderBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Metadata metadata;

		public PngImageMetadaReaderBuilder(File file) throws PngProcessingException, IOException {
			metadata = PngMetadataReader.readMetadata(file);
		}
		
		public Metadata build() {
			return metadata;
		}
	}

	/*
	 * @author jduranmaster /
	 * 
	 * @version 1.0
	 */
	public static class GifImageMetadaReaderBuilder implements Serializable {

		private static final long serialVersionUID = 1L;
		private Metadata metadata;

		public GifImageMetadaReaderBuilder(File file) throws IOException {
			metadata = GifMetadataReader.readMetadata(file);
		}
		
		public Metadata build() {
			return metadata;
		}
	}
}