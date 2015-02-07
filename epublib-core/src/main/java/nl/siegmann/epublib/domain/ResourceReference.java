package nl.siegmann.epublib.domain;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class ResourceReference implements Serializable, Parcelable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596967243557743048L;
	protected Resource resource;

	public ResourceReference(Resource resource)
	{
		this.resource = resource;
	}

	/**
	 * @param source
	 */
	public ResourceReference(Parcel source)
	{
		if (source.readByte() == 1) resource = source.readParcelable(Resource.class.getClassLoader());
	}

	public Resource getResource()
	{
		return resource;
	}

	/**
	 * Besides setting the resource it also sets the fragmentId to null.
	 * 
	 * @param resource
	 */
	public void setResource(Resource resource)
	{
		this.resource = resource;
	}

	/**
	 * The id of the reference referred to.
	 * 
	 * null of the reference is null or has a null id itself.
	 * 
	 * @return The id of the reference referred to.
	 */
	public String getResourceId()
	{
		if (resource != null)
		{
			return resource.getId();
		}
		return null;
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
		dest.writeByte((byte)(resource != null ? 1 : 0));
		if (resource != null) dest.writeParcelable(resource, 0);
	}

	public static final Parcelable.Creator<ResourceReference> CREATOR = new Parcelable.Creator<ResourceReference>()
	{
		@Override
		public ResourceReference createFromParcel(Parcel source)
		{
			return new ResourceReference(source);
		}

		@Override
		public ResourceReference[] newArray(int size)
		{
			return new ResourceReference[size];
		}
	};
}
