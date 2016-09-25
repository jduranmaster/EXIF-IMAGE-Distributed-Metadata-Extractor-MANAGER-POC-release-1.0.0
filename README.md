# EXIF-IMAGE-Distributed-Metadata-Extractor-MANAGER-POC-release-1.0.0

EXIF-IMAGE-Distributed-Metadata-Extractor-MANAGER-POC-release-1.0.0

![Title](./exec-tests/x-logo-poc.jpg)

This POC allows to read EXIF data from IMAGE files supporting the follwing file extensions: JPEG, BMP, PNG, TIFF, RAW, GIF.
The POC also allows to build an interactive map that displays the images asociated to the geotags displayed on it.

The project builds a Spring-Boot application that can invoked using the following input parameters:

1. Read/Extract the EXIF Metadata from an image file:

java -jar exif-image-distributed-metadata-extractor-manager-v1.0.0-1.0.0-SNAPSHOT.jar 1 "absoulte/relative-path-to-the-image-file"

2. Build the interactive map according the geotag info extracted from the images:

java -jar exif-image-distributed-metadata-extractor-manager-v1.0.0-1.0.0-SNAPSHOT.jar 2 "absoulte/relative-path-to-the-images" "absoulte/relative-path-to-the-generated-HTML-file"