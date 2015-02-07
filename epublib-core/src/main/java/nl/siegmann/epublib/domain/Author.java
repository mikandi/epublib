package nl.siegmann.epublib.domain;

import java.io.Serializable;

import nl.siegmann.epublib.util.StringUtil;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents one of the authors of the book
 * 
 * @author paul
 * 
 */
public class Author implements Serializable, Parcelable
{

	private static final long serialVersionUID = 6663408501416574200L;

	private String firstname;
	private String lastname;
	private Relator relator = Relator.AUTHOR;

	public Author(String singleName)
	{
		this("", singleName);
	}

	public Author(String firstname, String lastname)
	{
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Author(final Parcel source)
	{
		if (source.readByte() == 1) firstname = source.readString();
		if (source.readByte() == 1) lastname = source.readString();
		if (source.readByte() == 1) relator = Relator.byCode(source.readString());
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String toString()
	{
		return lastname + ", " + firstname;
	}

	public int hashCode()
	{
		return StringUtil.hashCode(firstname, lastname);
	}

	public boolean equals(Object authorObject)
	{
		if (!(authorObject instanceof Author))
		{
			return false;
		}
		Author other = (Author) authorObject;
		return StringUtil.equals(firstname, other.firstname) && StringUtil.equals(lastname, other.lastname);
	}

	public Relator setRole(String code)
	{
		Relator result = Relator.byCode(code);
		if (result == null)
		{
			result = Relator.AUTHOR;
		}
		this.relator = result;
		return result;
	}

	public Relator getRelator()
	{
		return relator;
	}

	public void setRelator(Relator relator)
	{
		this.relator = relator;
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
		dest.writeByte((byte)(firstname != null ? 1 : 0));
		if (firstname != null) dest.writeString(firstname);
		dest.writeByte((byte)(lastname != null ? 1 : 0));
		if (lastname != null) dest.writeString(lastname);
		dest.writeByte((byte)(relator != null ? 1 : 0));
		if (relator != null) dest.writeString(relator.getCode());
	}

	public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>()
	{
		@Override
		public Author createFromParcel(Parcel source)
		{
			return new Author(source);
		}
		
		@Override
		public Author[] newArray(int size)
		{
			return new Author[size];
		}
	};
}
