package com.gravity9.jsonpatch;

public class JsonPathParser {

	private static final String ARRAY_ELEMENT_REGEX = "(?<=\\.)(\\d+)";

	public static String tmfStringToJsonPath(String path) throws JsonPatchException {
		if (path.startsWith("$")) {
			return path;
		} else if (path.contains("?")) {
			throw new JsonPatchException("Invalid path, `?` are not allowed in JsonPointer expressions.");
		} else if (path.contains("//")) {
			throw new JsonPatchException("Invalid path, `//` is not allowed in JsonPointer expressions.");
		}

		return "$" + path.replace('/', '.')
			.replace("~1", "/") // / must be escaped in JsonPointer using ~1
			.replace("~0", "~") // ~ must be escaped in JsonPointer using ~0
			.replaceAll(ARRAY_ELEMENT_REGEX, "[$1]");
	}
}