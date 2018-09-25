package org.simplejavamail.internal.clisupport.therapijavadoc;

import com.github.therapi.runtimejavadoc.Comment;
import com.github.therapi.runtimejavadoc.CommentElement;
import com.github.therapi.runtimejavadoc.CommentText;
import com.github.therapi.runtimejavadoc.InlineLink;
import com.github.therapi.runtimejavadoc.Link;
import org.bbottema.javareflection.ClassUtils;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.quote;

public final class TherapiJavadocHelper {
	
	private static final String KEY_DELEGATES_TO = "Delegates to";
	private static final Pattern PATTERN_DELEGATES_TO = compile("(?i)" + quote(KEY_DELEGATES_TO));
	
	private TherapiJavadocHelper() {
	}
	
	@Nullable
	public static Method getMethodDelegate(Comment comment) {
		if (comment == null) {
			return null;
		}
		CommentElement lastEleTextSaysDelegatedTo = null;
		for (CommentElement ele : comment.getElements()) {
			if (ele instanceof CommentText) {
				if (PATTERN_DELEGATES_TO.matcher(((CommentText) ele).getValue()).find()) {
					lastEleTextSaysDelegatedTo = ele;
				}
			} else {
				if (ele instanceof InlineLink && lastEleTextSaysDelegatedTo != null) {
					return findMethodForLink(((InlineLink) ele).getLink());
				}
				
				lastEleTextSaysDelegatedTo = null;
			}
		}
		
		return null;
	}
	
	private static Method findMethodForLink(Link link) {
		Class<?> aClass = ClassUtils.locateClass(link.getReferencedClassName(), "org.simplejavamail", null);
		System.out.println(aClass + "." + link.getReferencedMemberName() + "(" + Arrays.toString(link.getParams()) + ")");
		return null;
	}
}