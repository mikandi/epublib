package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import nl.siegmann.epublib.epub.PackageDocumentBase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A Date used by the book's metadata.
 * 
 * Examples: creation-date, modification-date, etc
 * 
 * @author paul
 * 
 */
public class Date implements Serializable, Parcelable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7533866830395120136L;

	public enum Event
	{
		PUBLICATION("publication"), MODIFICATION("modification"), CREATION("creation");

		private final String value;

		Event(String v)
		{
			value = v;
		}

		public static Event fromValue(String v)
		{
			for (Event c : Event.values())
			{
				if (c.value.equals(v))
				{
					return c;
				}
			}
			return null;
		}

		public String toString()
		{
			return value;
		}
	};

	private Event event;
	private String dateString;

	public Date(java.util.Date date)
	{
		this(date, (Event) null);
	}

	public Date(String dateString)
	{
		this(dateString, (Event) null);
	}

	public Date(java.util.Date date, Event event)
	{
		this((new SimpleDateFormat(PackageDocumentBase.dateFormat)).format(date), event);
	}

	public Date(String dateString, Event event)
	{
		this.dateString = dateString;
		this.event = event;
	}

	public Date(java.util.Date date, String event)
	{
		this((new SimpleDateFormat(PackageDocumentBase.dateFormat)).format(date), event);
	}

	public Date(String dateString, String event)
	{
		this(checkDate(dateString), Event.fromValue(event));
		this.dateString = dateString;
	}

	/**
	 * @param source
	 */
	public Date(Parcel source)
	{
		if (source.readByte() == 1) event = Event.fromValue(source.readString());
		if (source.readByte() == 1) dateString = source.readString();
	}

	private static String checkDate(String dateString)
	{
		if (dateString == null)
		{
			throw new IllegalArgumentException("Cannot create a date from a blank string");
		}
		return dateString;
	}

	public String getValue()
	{
		return dateString;
	}

	public Event getEvent()
	{
		return event;
	}

	public void setEvent(Event event)
	{
		this.event = event;
	}

	public String toString()
	{
		if (event == null)
		{
			return dateString;
		}
		return "" + event + ":" + dateString;
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
		dest.writeByte((byte)(event != null ? 1 : 0));
		if (event != null) dest.writeString(event.value);
		dest.writeByte((byte)(dateString != null ? 1 : 0));
		if (dateString != null) dest.writeString(dateString);
	}

	public static final Parcelable.Creator<Date> CREATOR = new Parcelable.Creator<Date>()
	{
		@Override
		public Date createFromParcel(Parcel source)
		{
			return new Date(source);
		}

		@Override
		public Date[] newArray(int size)
		{
			return new Date[size];
		}
	};
}
