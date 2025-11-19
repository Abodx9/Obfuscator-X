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
            "]),setattr(_sys, \"PyxGgdGhfKfgED\", []),[PyxGgdGhfKfgED.append(-abs(x[PyxGgUdGhfKfgEDx])) for PyxGgUdGhfKfgEDx in range(" +
            Integer.valueOf(len) +
            ")],setattr(_sys, \"x\", ''.join(chr(a & 0xFFFF) for a in PyxGgdGhfKfgED))]");

        return sb.toString();
    }

    // Updated: Generates self-decoding Java string via keyless rotation + XOR with
    // non-ASCII identifiers.
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

    // Updated: Produces JS string obfuscated with rotation, XOR masking, and
    // Unicode identifiers.
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

    // Updated: Encodes a string into self-decoding C wchar_t array using keyless
    // XOR + bit rotation obfuscation.
    public static String encode_C(String code) {
        int encodeCount = 0;
        for (int i = 0; i < code.length();) {
            int cp = code.codePointAt(i);
            encodeCount++;
            i += Character.charCount(cp);
        }
        int arrLen = encodeCount + 1; // +1 for null terminator

        StringBuilder sb = new StringBuilder();
        sb.append("wchar_t x[").append(arrLen).append("] = {");

        int idx = 0;
        for (int i = 0; i < code.length();) {
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

    // Updated: Obfuscates string into self-decoding C# literal using position-based
    // XOR and bit rotation.
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

    // Obfuscates string into self-decoding Ruby using keyless rotation (6–14), XOR
    // masking, and Unicode identifiers.
    public static String encode_Ru(String code) {
        if (code == null || code.isEmpty()) {
            return "x = \"\"";
        }

        // Validate: no surrogates, no non-BMP
        for (int i = 0; i < code.length();) {
            int cp = code.codePointAt(i);
            if (cp > 0xFFFF || (cp >= 0xD800 && cp <= 0xDFFF)) {
                throw new IllegalArgumentException("Ruby encoder requires BMP-only (no surrogates/emojis): U+" +
                    Integer.toHexString(cp).toUpperCase());
            }
            i += Character.charCount(cp);
        }

        int len = code.length();
        StringBuilder sb = new StringBuilder();
        sb.append("x = \"");

        for (int i = 0; i < len; i++) {
            char c = code.charAt(i);
            int c16 = c & 0xFFFF;
            int mask = (i + 1) ^ len;
            int rot = ((i + 1) % 9) + 6;

            int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
            int enc = rotated ^ mask;

            // Ensure enc is NOT in surrogate range [0xD800, 0xDFFF]
            if (enc >= 0xD800 && enc <= 0xDFFF) {
                // Shift out of surrogate range (e.g., wrap or offset)
                enc = 0xE000 + ((enc - 0xD800) % 0x1000);
            }

            sb.append("\\u").append(String.format("%04X", enc & 0xFFFF));
        }
        sb.append("\";\n");

        sb.append("چ = x.length\n");
        sb.append("x = x.codepoints.map.with_index { |ﮬ, ک|\n");
        sb.append("  ڈ = (ک + 1) ^ چ\n");
        sb.append("  ڒ = ((ک + 1) % 9) + 6\n");
        sb.append("  ژ = ﮬ ^ ڈ\n");
        sb.append("  val = ((ژ >> ڒ) | (ژ << (16 - ڒ))) & 0xFFFF\n");
        sb.append("  val\n");
        sb.append("}.pack('U*').force_encoding('UTF-8')");

        return sb.toString();
    }

    // Simple Go encoder using position-based addition

    public static String encode_Go(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("var x string;");
        sb.append("arr := []int{");

        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick = decpo + (i * 2);
            if (decpo > 0xffff) {
                i++;
            }
            sb.append(String.format("0x%X, ", trick));
        }
        sb.setLength(sb.length() - 2);
        sb.append("};");

        sb.append("for i, v := range arr {");
        sb.append("v -= i * 2;");
        sb.append("x += string(rune(v));");
        sb.append("}");

        return sb.toString();
    }

    // Simple Dart encoder using position-based subtraction

    public static String encode_Da(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("var x = '';");
        sb.append("var arr = [");

        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick = decpo + (i - 4);
            if (decpo > 0xffff) {
                i++;
            }
            sb.append(String.format("0x%X, ", trick));
        }

        sb.setLength(sb.length() - 2);
        sb.append("];");

        sb.append("for (var i = 0; i < arr.length; i++) {");
        sb.append("var v = arr[i];");
        sb.append("v -= i - 4;");
        sb.append("x += String.fromCharCode(v);");
        sb.append("}");

        return sb.toString();
    }

    // Obfuscates string into self-decoding C++ using C++-unique rotation
    // ((i+1)*5%12+4), XOR masking.

    // Thannks to Qwen Ai for helping with this one!, cause I hate C++ :)
    public static String encode_Cpp(String code) {
        if (code == null || code.isEmpty()) {
            return "std::string x;";
        }

        int len = code.length();
        StringBuilder sb = new StringBuilder();
        //        sb.append("#include <string>\n");   Only import when decoding
        //        sb.append("#include <cstdint>\n");
        sb.append("std::string _x;\n");
        sb.append("const uint16_t _a[]={");

        // Encode each char (BMP assumed)
        for (int i = 0; i < len; i++) {
            char c = code.charAt(i);
            int c16 = c & 0xFFFF;
            int mask = (i + 1) ^ len;
            int rot = ((i + 1) * 5) % 12 + 4;

            int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
            int enc = rotated ^ mask;

            sb.append("0x").append(String.format("%X", enc)).append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append("};\n");

        sb.append("for(size_t _i=0;_i<sizeof(_a)/sizeof(_a[0]);++_i){\n");
        sb.append("  uint16_t _m=static_cast<uint16_t>((_i+1)^(")
            .append(len)
            .append("));\n");
        sb.append("  uint16_t _r=static_cast<uint16_t>(((_i+1)*5)%12+4);\n");
        sb.append("  uint16_t _v=_a[_i]^_m;\n");
        sb.append("  _v=static_cast<uint16_t>(((_v>>_r)|(_v<<(16-_r)))&0xFFFF);\n");
        sb.append("  _x+=static_cast<char>(_v);\n");
        sb.append("}");

        return sb.toString();
    }


    // Obfuscates string into self-decoding Rust using Rust-unique rotation ((i+1)*13%11+5), XOR masking, and ASCII-only obfuscated identifiers. again thaanks to Qwen AI
    public static String encode_Rust(String code) {
        if (code == null || code.isEmpty()) {
            return "let mut x = String::new();";
        }

        int len = code.length();
        StringBuilder sb = new StringBuilder();
        sb.append("let mut _x = String::new();\n");
        sb.append("let _a: [u16; ").append(len).append("] = [");

        for (int i = 0; i < len; i++) {
            char c = code.charAt(i);
            int c16 = c & 0xFFFF;
            int mask = (i + 1) ^ len;
            int rot = ((i + 1) * 13) % 11 + 5;

            int rotated = ((c16 << rot) | (c16 >>> (16 - rot))) & 0xFFFF;
            int enc = rotated ^ mask;

            sb.append("0x").append(String.format("%X", enc)).append("u16, ");
        }
        sb.setLength(sb.length() - 2); // remove trailing ", "
        sb.append("];\n");

        // Decoding — ASCII-only, idiomatic Rust
        sb.append("for (_i, &_v) in _a.iter().enumerate() {\n");
        sb.append("    let _m = ((_i + 1) ^ ").append(len).append(") as u16;\n");
        sb.append("    let _r = (((_i + 1) * 13) % 11 + 5) as u32;\n");
        sb.append("    let mut _d = _v ^ _m;\n");
        sb.append("    _d = (_d >> _r) | (_d << (16 - _r));\n");
        sb.append("    _x.push(std::char::from_u32(_d as u32).unwrap_or('?'));\n");
        sb.append("}");

        return sb.toString();
    }

}
