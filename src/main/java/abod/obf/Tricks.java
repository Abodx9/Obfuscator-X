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

    // Method to encode the Java Strings using array
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

    // A method to encode a JS string with Unicode escapes and position-based shifts
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

    // Method to encode the C Strings
    public static String encode_C(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length() + 1;
        sb.append("wchar_t x[" + Integer.valueOf(len)).append("] = {");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick = decpo + i + 1;
            if (decpo > 0xffff) {
                i++;
            }
            String hex = Integer.toHexString(trick);
            sb.append("0x");
            for (int j = 0; j < hex.length(); j++) {
                sb.append("0");
            }
            sb.append(hex).append(", ");
        }
        sb.append("0x0" + Integer.toHexString(len)).append("};");
        sb.append("for (unsigned int FAxwgxESAZJA = 0, CsjhcAxwgZJA = 0; FAxwgxESAZJA < " + Integer.valueOf(len)
                + " ; FAxwgxESAZJA++){CsjhcAxwgZJA = x[FAxwgxESAZJA];CsjhcAxwgZJA --;CsjhcAxwgZJA -= FAxwgxESAZJA;x[FAxwgxESAZJA] = CsjhcAxwgZJA;}");
        return sb.toString();
    }

    // Method that encodes a string into C# Unicode escapes with simple index-based
    // obfuscation.
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
