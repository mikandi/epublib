package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * MediaType is used to tell the type of content a resource is.
 * 
 * Examples of mediatypes are image/gif, text/css and application/xhtml+xml
 * 
 * All allowed mediaTypes are maintained bye the MediaTypeService.
 * 
 * @see nl.siegmann.epublib.service.MediatypeService
 * 
 * @author paul
 * 
 */
public class MediaType implements Serializable, Parcelable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7256091153727506788L;
	private String name;
	private String defaultExtension;
	private Collection<String> extensions;

	public MediaType(String name, String defaultExtension)
	{
		this(name, defaultExtension, new String[] { defaultExtension });
	}

	public MediaType(String name, String defaultExtension, String[] extensions)
	{
		this(name, defaultExtension, Arrays.asList(extensions));
	}

	public int hashCode()
	{
		if (name == null)
		{
			return 0;
		}
		return name.hashCode();
	}

	public MediaType(String name, String defaultExtension, Collection<String> extensions)
	{
		super();
		this.name = name;
		this.defaultExtension = defaultExtension;
		this.extensions = extensions;
	}

	/**
	 * @param source
	 */
	public MediaType(Parcel source)
	{
		if (source.readByte() == 1) name = source.readString();
		if (source.readByte() == 1) defaultExtension = source.readString();
		if (source.readByte() == 1) source.readStringList((List<String>) extensions);
	}

	public String getName()
	{
		return name;
	}

	public String getDefaultExtension()
	{
		return defaultExtension;
	}

	public Collection<String> getExtensions()
	{
		return extensions;
	}

	public boolean equals(Object otherMediaType)
	{
		if (!(otherMediaType instanceof MediaType))
		{
			return false;
		}
		return name.equals(((MediaType) otherMediaType).getName());
	}

	public String toString()
	{
		return name;
	}

	/// Parcelable
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeByte((byte)(name == null ? 1 : 0));
		if (name != null) dest.writeString(name);
		dest.writeByte((byte)(defaultExtension == null ? 1 : 0));
		if (defaultExtension != null) dest.writeString(defaultExtension);
		dest.writeByte((byte)(extensions == null ? 1 : 0));
		if (extensions != null) dest.writeStringList((List<String>) extensions);
	}

	public static final Parcelable.Creator<MediaType> CREATOR = new Parcelable.Creator<MediaType>()
	{
		@Override
		public MediaType createFromParcel(Parcel source)
		{
			return new MediaType(source);
		}

		@Override
		public MediaType[] newArray(int size)
		{
			return new MediaType[size];
		}
	};
}
