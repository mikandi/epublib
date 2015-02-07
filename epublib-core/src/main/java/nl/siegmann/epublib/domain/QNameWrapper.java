/**
 * 
 */
package nl.siegmann.epublib.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author rekaszeru
 *
 */
public class QNameWrapper implements Parcelable
{
    /**
     * <p>Namespace URI of this <code>QName</code>.</p>
     */
    private final String namespaceURI;

    /**
     * <p>local part of this <code>QName</code>.</p>
     */
    private final String localPart;

    /**
     * <p>prefix of this <code>QName</code>.</p>
     */
    private String prefix;

	/**
	 * @param namespaceURI
	 * @param localPart
	 * @param prefix
	 */
	public QNameWrapper(String namespaceURI, String localPart, String prefix)
	{
		super();
		this.namespaceURI = namespaceURI;
		this.localPart = localPart;
		this.prefix = prefix;
	}

	/**
	 * @param source
	 */
	public QNameWrapper(Parcel source)
	{
		namespaceURI = ((source.readByte() == 1) ? source.readString() : null);
		localPart = ((source.readByte() == 1) ? source.readString() : null);
		prefix = ((source.readByte() == 1) ? source.readString() : null);
	}
	
	/**
	 * @return the namespaceURI
	 */
	public String getNamespaceURI()
	{
		return namespaceURI;
	}
	
	/**
	 * @return the localPart
	 */
	public String getLocalPart()
	{
		return localPart;
	}
	
	/**
	 * @return the prefix
	 */
	public String getPrefix()
	{
		return prefix;
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
		dest.writeByte((byte)(namespaceURI != null ? 1 : 0));
		if (namespaceURI != null) dest.writeString(namespaceURI);
		dest.writeByte((byte)(localPart != null ? 1 : 0));
		if (localPart != null) dest.writeString(localPart);
		dest.writeByte((byte)(prefix != null ? 1 : 0));
		if (prefix != null) dest.writeString(prefix);
	}

	public static final Parcelable.Creator<QNameWrapper> CREATOR = new Parcelable.Creator<QNameWrapper>()
	{
		@Override
		public QNameWrapper createFromParcel(Parcel source)
		{
			return new QNameWrapper(source);
		}

		@Override
		public QNameWrapper[] newArray(int size)
		{
			return new QNameWrapper[size];
		}
	};
}
