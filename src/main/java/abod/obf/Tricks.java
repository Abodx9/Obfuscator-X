package abod.obf;

public class Tricks {
    // Method to encode the python Strings
    public static String encode_Py(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        sb.append(
                "[setattr(__import__(\"sys\").modules[__name__], \"_sys\", __import__(\"sys\").modules[__name__]),setattr(_sys, \"x\", [");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick = decpo - 1;
            String hex = Integer.toHexString(~trick);
            sb.append("0x");
            sb.append(hex).append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(
                "]),setattr(_sys, \"PyxGgdGhfKfgED\", []),[PyxGgdGhfKfgED.append(-abs(x[PyxGgUdGhfKfgEDx])) for PyxGgUdGhfKfgEDx in range("
                        + Integer.valueOf(len)
                        + ")],setattr(_sys, \"x\", ''.join(chr(a & 0xFFFF) for a in PyxGgdGhfKfgED))]");

        return sb.toString();
    }

    // Updated: Generates self-decoding Java string via keyless rotation + XOR with non-ASCII identifiers.
    public static String encode_Ja(String code) {
        int len = code.length();
        StringBuilder sb = new StringBuilder();
        sb.append("String x = \"\"; int څ[] = {");

        for (int i = 0; i < len; i++) {
            char c = code.charAt(i); // safe for BMP
            int c16 = c & 0xFFFF;
            int mask = (i + 1) ^ len;
            int rot = (i + 1) % 15 + 1;

            int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
            int enc = rotated ^ mask;

            sb.append("0x").append(Integer.toHexString(enc)).append(", ");
        }

        sb.setLength(sb.length() - 2);
        sb.append("};");

        sb.append("int ﷸ = څ.length;");
        sb.append("for (int ﷹ = 0; ﷹ < ﷸ; ﷹ++) {");
        sb.append("  int ﷵ = (ﷹ + 1) ^ ﷸ;");
        sb.append("  int چ = (ﷹ + 1) % 15 + 1;");
        sb.append("  int क = څ[ﷹ] ^ ﷵ;");
        sb.append("  int ڈ = ((क >>> چ) | (क << (16 - چ))) & 0xFFFF;");
        sb.append("  x += (char) ڈ;");
        sb.append("}");

        return sb.toString();
    }

    // Updated: Produces JS string obfuscated with rotation, XOR masking, and Unicode identifiers.
    public static String encode_Js(String code) {
    int len = code.length();
    StringBuilder sb = new StringBuilder();
    sb.append("var x = \"");

    for (int i = 0; i < len; i++) {
        char c = code.charAt(i);
        int c16 = c & 0xFFFF;
        int mask = (i + 1) ^ len;
        int rot = ((i + 1) % 13) + 3;

        int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
        int enc = rotated ^ mask;
        String hex = String.format("%04X", enc & 0xFFFF);
        sb.append("\\u").append(hex);
    }
    sb.append("\";");
    sb.append("var چ = x.length;");
    sb.append("for (var ک = 0; ک < چ; ک++) {");
    sb.append("  var ڈ = (ک + 1) ^ چ;");
    sb.append("  var ڒ = ((ک + 1) % 13) + 3;");
    sb.append("  var ژ = x.charCodeAt(ک) ^ ڈ;");
    sb.append("  var ڠ = ((ژ >>> ڒ) | (ژ << (16 - ڒ))) & 0xFFFF;");
    sb.append("  x = x.substr(0, ک) + String.fromCharCode(ڠ) + x.substr(ک + 1);");
    sb.append("}");

    return sb.toString();
}

    // Updated: Encodes a string into self-decoding C wchar_t array using keyless XOR + bit rotation (6–14) obfuscation.
   public static String encode_C(String code) {
    int encodeCount = 0;
    for (int i = 0; i < code.length(); ) {
        int cp = code.codePointAt(i);
        encodeCount++;
        i += Character.charCount(cp);
    }
    int arrLen = encodeCount + 1; // +1 for null terminator

    StringBuilder sb = new StringBuilder();
    sb.append("wchar_t x[").append(arrLen).append("] = {");

    int idx = 0;
    for (int i = 0; i < code.length(); ) {
        int cp = code.codePointAt(i);
        int c16 = cp & 0xFFFF;
        int mask = (idx + 1) ^ encodeCount;
        int rot = ((idx + 1) % 9) + 6;
        int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
        int enc = rotated ^ mask;

        sb.append("0x").append(Integer.toHexString(enc)).append(", ");
        idx++;
        i += Character.charCount(cp);
    }
    sb.append("0x").append(Integer.toHexString(encodeCount)).append("};\n");
    sb.append("for (unsigned int ک = 0; ک < ").append(arrLen).append("; ک++) {");
    sb.append("  unsigned int چ = (ک + 1) ^ (").append(arrLen).append(" - 1);");
    sb.append("  unsigned int ڒ = ((ک + 1) % 9) + 6;");
    sb.append("  uint16_t ژ = (uint16_t)x[ک];");
    sb.append("  uint16_t ڠ = ژ ^ چ;");
    sb.append("  ڠ = ((ڠ >> ڒ) | (ڠ << (16 - ڒ)));");
    sb.append("  x[ک] = (ک == ").append(encodeCount).append(") ? 0 : ڠ;");
    sb.append("}");
    
    return sb.toString();
}

    // Updated: Obfuscates string into self-decoding C# literal using position-based XOR and 6–14 bit rotation.
    public static String encode_Cs(String code) {
    int len = code.length();
    StringBuilder sb = new StringBuilder();
    sb.append("String x = \"");

    for (int i = 0; i < len; i++) {
        char c = code.charAt(i);
        int c16 = c & 0xFFFF;
        int mask = (i + 1) ^ len;
        int rot = ((i + 1) % 9) + 6;
        int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
        int enc = rotated ^ mask;
        String hex = String.format("%04X", enc & 0xFFFF);
        sb.append("\\u").append(hex);
    }
    sb.append("\";");
    sb.append("int چ = x.Length;");
    sb.append("for (int ک = 0; ک < چ; ک++) {");
    sb.append("  int ڈ = (ک + 1) ^ چ;");
    sb.append("  int ڒ = ((ک + 1) % 9) + 6;");
    sb.append("  int ژ = x[ک] ^ ڈ;");
    sb.append("  int ڠ = ((ژ >> ڒ) | (ژ << (16 - ڒ))) & 0xFFFF;");
    sb.append("  x = x.Substring(0, ک) + (char)(ڠ & 0xFFFF) + x.Substring(ک + 1);");
    sb.append("}");

    return sb.toString();
}

    // Method to encode Ruby Strings with simple index-based obfuscation

    public static String encode_Ru(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        sb.append("x = \"");
        for (int i = 0; i < len; i++) {
            int decpo = code.codePointAt(i);
            int trick = decpo;
            // I need to get rid of the hex string cause Ruby cannot handle 8 bits.
            String hex = Integer.toHexString(~trick).toUpperCase();
            String hx = hex.substring(4, 8);
            sb.append("\\u");
            sb.append(hx);
        }
        sb.append("\"\n");

        sb.append("x.codepoints.each_with_index do |xCvXdS, xCvXdStF|\n" +
                "xCvXdS = ~xCvXdS\n" +
                "x[xCvXdStF] = [xCvXdS & 0xFFFF].pack('U').force_encoding('UTF-8')\n" +
                "end");
        return sb.toString();
    }
}
