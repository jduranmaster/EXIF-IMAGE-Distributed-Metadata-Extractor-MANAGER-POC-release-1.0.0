package com.epsilon.metadata.image.distributed.core.model;

import java.io.File;
import java.io.Serializable;

import com.drew.lang.GeoLocation;

/*
 * @author jduranmaster@gmail.com / 
 * @version 1.0
 */
public class PhotoLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	public final GeoLocation location;
	public final File file;

	public PhotoLocation(final GeoLocation location, final File file) {
		this.location = location;
		this.file = file;
	}
}