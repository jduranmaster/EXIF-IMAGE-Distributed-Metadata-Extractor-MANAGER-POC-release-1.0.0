package com.epsilon.metadata.image.distributed.core;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.epsilon.metadata.image.distributed.core.model.PhotoLocation;

/*
 * @author jduranmaster / 
 * @version 1.0
 */
@Component
@PropertySource(value="classpath:application.properties")
public class ImageMetadataExtractorGeoTagMapBuilder implements Serializable {

	private static final long serialVersionUID = -3811083311401351527L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ImageMetadataExtractorGeoTagMapBuilder.class);

	// final String[] acceptedExtensions = new String[] { ".jpg", ".jpeg" };

	@Value("${exif.default.input.images.directory}")
	private String defaultInputImagesDirectory;
	
	@Value("${exif.default.output.htmllocationfile}")
	private String defaulOutputtHtmlFile;
	
	public Collection<PhotoLocation> buildMapFromMetadataExtractionAndExportToConsole(String filePath,
			final String[] acceptedExtensions) throws ImageProcessingException, IOException {
		File path = new File(filePath);

		// process subdirectories recursively? Not needed.
		final File[] files = path.listFiles(new FileFilter() {
			public boolean accept(final File file) {
				if (file.isDirectory())
					return false;
				for (String extension : acceptedExtensions) {
					if (file.getName().toLowerCase().endsWith(extension))
						return true;
				}
				return false;
			}
		});

		Collection<PhotoLocation> photoLocations = new ArrayList<PhotoLocation>();
		for (File file : files) {
			// Read all metadata from the image
			Metadata metadata = ImageMetadataReader.readMetadata(file);
			// See whether it has GPS data
			Collection<GpsDirectory> gpsDirectories = metadata
					.getDirectoriesOfType(GpsDirectory.class);
			if (gpsDirectories == null)
				continue;
			for (GpsDirectory gpsDirectory : gpsDirectories) {
				// Try to read out the location, making sure it's non-zero
				GeoLocation geoLocation = gpsDirectory.getGeoLocation();
				if (geoLocation != null && !geoLocation.isZero()) {
					// Add to our collection for use below
					photoLocations.add(new PhotoLocation(geoLocation, file));
					break;
				}
			}
		}

		// Write output to the console.
		// You can pipe this to a file if you like, or alternatively modify the
		// output stream here to be a file or network stream.
		PrintStream ps = new PrintStream(System.out);

		writeHtml(ps, photoLocations);

		// Make sure we flush the stream before exiting. If you use a different
		// type of stream, you may need to close it here instead.
		ps.flush();
		
		return photoLocations;
	}

	public Collection<PhotoLocation> buildMapFromMetadataExtractionAndExportToFile(String directoryPath,
			String fileName, final String[] acceptedExtensions) throws ImageProcessingException, IOException {
		File path = null;
		if(directoryPath == null) {
			path = new File(defaultInputImagesDirectory);
		}else{
			path = new File(directoryPath);
		}
			
		// process subdirectories recursively? Not needed.
		final File[] files = path.listFiles(new FileFilter() {
			public boolean accept(final File file) {
				if (file.isDirectory())
					return false;
				for (String extension : acceptedExtensions) {
					if (file.getName().toLowerCase().endsWith(extension))
						return true;
				}
				return false;
			}
		});

		Collection<PhotoLocation> photoLocations = new ArrayList<PhotoLocation>();
		for (File file : files) {
			// Read all metadata from the image
			Metadata metadata = ImageMetadataReader.readMetadata(file);
			// See whether it has GPS data
			Collection<GpsDirectory> gpsDirectories = metadata
					.getDirectoriesOfType(GpsDirectory.class);
			if (gpsDirectories == null)
				continue;
			for (GpsDirectory gpsDirectory : gpsDirectories) {
				// Try to read out the location, making sure it's non-zero
				GeoLocation geoLocation = gpsDirectory.getGeoLocation();
				if (geoLocation != null && !geoLocation.isZero()) {
					// Add to our collection for use below
					photoLocations.add(new PhotoLocation(geoLocation, file));
					break;
				}
			}
		}

		// Write output to the console.
		// You can pipe this to a file if you like, or alternatively modify the
		// output stream here to be a file or network stream.
		PrintStream ps = null;
		if(fileName == null) {
			ps = new PrintStream(new FileOutputStream(new File(defaulOutputtHtmlFile)));
		}else {
			ps = new PrintStream(new FileOutputStream(new File(fileName)));
		}

		writeHtml(ps, photoLocations);

		// Make sure we flush the stream before exiting. If you use a different
		// type of stream, you may need to close it here instead.
		ps.flush();
		
		return photoLocations;
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}

	private static void writeHtml(PrintStream ps,
			Iterable<PhotoLocation> photoLocations) {
		ps.println("<!DOCTYPE html>");
		ps.println("<html>");
		ps.println("<head>");
		ps.println("<meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />");
		ps.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>");
		ps.println("<style>html,body{height:100%;margin:0;padding:0;}#map_canvas{height:100%;}</style>");
		ps.println("<script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?sensor=false\"></script>");
		ps.println("<script type=\"text/javascript\">");
		ps.println("function initialise() {");
		ps.println("    var options = { zoom:2, mapTypeId:google.maps.MapTypeId.ROADMAP, center:new google.maps.LatLng(0.0, 0.0)};");
		ps.println("    var map = new google.maps.Map(document.getElementById('map_canvas'), options);");
		ps.println("    var marker;");

		for (PhotoLocation photoLocation : photoLocations) {
			final String fullPath = photoLocation.file.getAbsoluteFile()
					.toString().trim().replace("\\", "\\\\");

			ps.println("    marker = new google.maps.Marker({");
			ps.println("        position:new google.maps.LatLng("
					+ photoLocation.location + "),");
			ps.println("        map:map,");
			ps.println("        title:\"" + fullPath + "\"});");
			ps.println("    google.maps.event.addListener(marker, 'click', function() { document.location = \""
					+ fullPath + "\"; });");
		}

		ps.println("}");
		ps.println("</script>");
		ps.println("</head>");
		ps.println("<body onload=\"initialise()\">");
		ps.println("<div id=\"map_canvas\"></div>");
		ps.println("</body>");
		ps.println("</html>");
	}
}