package am.jsl.dolarek.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Rozszerzone File Filter do filtrowania plików obrazów.
 */
public class ImageFileFilter implements FileFilter {

	private static final String[] extensions;

	static {
		extensions = new String[] { "JPEG", "jpeg", "JPG", "jpg", "PNG", "png",
				"GIF", "gif"};
	}
	private static final String description = "Image files";

	public boolean accept(File f) {
		String name = f.getName().toLowerCase();
		for (int i = extensions.length - 1; i >= 0; i--) {
			if (name.endsWith(extensions[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidImageExtension(String extension) {
		String name = extension.toLowerCase();
		if (!TextUtils.hasText(name)) {
			return false;
		}

		for (int i = extensions.length - 1; i >= 0; i--) {
			if (name.equals(extensions[i])) {
				return true;
			}
		}
		return false;
	}

	public String getDescription() {
		return description;
	}
}
