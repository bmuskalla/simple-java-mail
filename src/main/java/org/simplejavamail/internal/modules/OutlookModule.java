package org.simplejavamail.internal.modules;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailPopulatingBuilder;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.InputStream;

public interface OutlookModule {
	Email outlookMsgToEmail(@Nonnull final File msgFile);
	Email outlookMsgToEmail(@Nonnull final String msgData);
	EmailPopulatingBuilder outlookMsgToEmailBuilder(@Nonnull final InputStream msgInputStream);
}