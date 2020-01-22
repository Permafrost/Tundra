package tundra.support;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.sftp.client.SFTPSession;
import com.wm.app.b2b.server.sftp.client.SFTPSessionManager;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import java.io.ByteArrayInputStream;
import java.util.Calendar;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class sftp

{
	// ---( internal utility methods )---

	final static sftp _instance = new sftp();

	static sftp _newInstance() { return new sftp(); }

	static sftp _cast(Object o) { return (sftp)o; }

	// ---( server methods )---




	public static final void touch (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(touch)>> ---
		// @sigtype java 3.5
		// [i] field:0:required $sftp.session.key
		// [i] field:0:required $file
		// [i] field:0:optional $file.create? {"true","false"}
		// [i] field:0:optional $file.updated
		IDataCursor cursor = pipeline.getCursor();
		
		try {
			String sessionKey = IDataHelper.get(cursor,  "$sftp.session.key", String.class);
			String file = IDataHelper.get(cursor,  "$file", String.class);
			boolean create = IDataHelper.getOrDefault(cursor, "$file.create?", Boolean.class, true);
			Calendar updated = IDataHelper.get(cursor,  "$file.updated", Calendar.class);
			
			touch(sessionKey, file, create, updated);
		} catch(SftpException ex) {
			ExceptionHelper.raise(ex);
		} finally {
			cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Optionally creates a file on an SFTP server if it does not exist, and updates its last modified datetime to a
	 * specified datetime.
	 * 
	 * @param sessionKey        The SFTP session key to use.
	 * @param file              The file to be touched.
	 * @param createMissing     Whether to create the file if it does not already exist.
	 * @param updated           The datetime to set the file's modified datetime to.
	 * @throws SftpException    If an SFTP error occurs.
	 */
	public static void touch(String sessionKey, String file, boolean createMissing, Calendar updated) throws SftpException {
	    touch(sessionKey, file, createMissing, updated == null ? System.currentTimeMillis() : updated.getTimeInMillis());
	}
	
	/**
	 * Optionally creates a file on an SFTP server if it does not exist, and updates its last modified datetime to a
	 * specified datetime.
	 *
	 * @param sessionKey        The SFTP session key to use.
	 * @param file              The file to be touched.
	 * @param createMissing     Whether to create the file if it does not already exist.
	 * @param updated           The datetime to set the file's modified datetime to.
	 * @throws SftpException    If an SFTP error occurs.
	 */
	public static void touch(String sessionKey, String file, boolean createMissing, long updated) throws SftpException {
	    SFTPSession session = SFTPSessionManager.getInstance().getSession(sessionKey);
	
	    if (session != null && !session.isExpired() && session.startSession()) {
	        try {
	            touch(session.getSftpChannel(), file, createMissing, updated);
	        } finally {
	            session.endSession();
	        }
	    }
	}
	
	/**
	 * Optionally creates a file on an SFTP server if it does not exist, and updates its last modified datetime to a
	 * specified datetime.
	 *
	 * @param channel           The SFTP channel to use.
	 * @param file              The file to be touched.
	 * @param createMissing     Whether to create the file if it does not already exist.
	 * @param updated           The datetime to set the file's modified datetime to.
	 * @throws SftpException    If an SFTP error occurs.
	 */
	public static void touch(ChannelSftp channel, String file, boolean createMissing, long updated) throws SftpException {
	    if (createMissing && !exists(channel, file)) {
	        channel.put(new ByteArrayInputStream("".getBytes()), file);
	    }
	    int mtime = (int)(updated / 1000L); // convert milliseconds to seconds
	    channel.setMtime(file, mtime);
	}
	
	/**
	 * Returns true if the given file exists on the SFTP server.
	 * 
	 * @param channel           The SFTP channel to use.
	 * @param file              The file to check existence of.
	 * @return                  True if the file exists.
	 * @throws SftpException    If an SFTP error occurs.
	 */
	public static boolean exists(ChannelSftp channel, String file) throws SftpException {
	    boolean exists = false;
	    try {
	        SftpATTRS attributes = channel.lstat(file);
	        exists = attributes != null;
	    } catch(SftpException ex) {
	        if (ex.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
	            exists = false;
	        } else {
	            throw ex;
	        }
	    }
	    return exists;
	}
	// --- <<IS-END-SHARED>> ---
}

