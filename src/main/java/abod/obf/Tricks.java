package abod.obf;

public class Tricks {
     // Method to encode the python Strings
    public static String encode_Py(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        sb.append("[setattr(__import__(\"sys\").modules[__name__], \"_sys\", __import__(\"sys\").modules[__name__]),setattr(_sys, \"x\", [");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick =decpo-1;
            String hex = Integer.toHexString(~trick);
            sb.append("0x");
            sb.append(hex).append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]),setattr(_sys, \"PyxGgdGhfKfgED\", []),[PyxGgdGhfKfgED.append(-abs(x[PyxGgUdGhfKfgEDx])) for PyxGgUdGhfKfgEDx in range("+Integer.valueOf(len)+")],setattr(_sys, \"x\", ''.join(chr(a & 0xFFFF) for a in PyxGgdGhfKfgED))]");

        return sb.toString();
    }


    //  Method to encode the Java Strings using array
    public static String encode_Ja(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        sb.append("String x = \"\";").append("int AsjbyrfXhcAxGJsdwgZxJA[] = {");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick =decpo + i + 1;
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
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("};");
        sb.append("for (int AsjbhcAxdwgZxJA = 0, AsjbyrfXhcAxdwgZxJA = 0; AsjbhcAxdwgZxJA < "+Integer.valueOf(len)+ " ;AsjbhcAxdwgZxJA++){AsjbyrfXhcAxdwgZxJA = AsjbyrfXhcAxGJsdwgZxJA[AsjbhcAxdwgZxJA];AsjbyrfXhcAxdwgZxJA --;AsjbyrfXhcAxdwgZxJA -= AsjbhcAxdwgZxJA;x = x + (char)(AsjbyrfXhcAxdwgZxJA & 0xFFFF);}");
        return sb.toString();
    }


    //  A method to encode a JS string with Unicode escapes and position-based shifts
    public static String encode_Js(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        sb.append("var x = \"");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick =decpo + i + 1;
            String hex = Integer.toHexString(trick);
            sb.append("\\u");
            for (int j = 0; j < hex.length(); j++) {
                sb.append("0");
            }
            sb.append(hex);
        }
        sb.append("\";");
        sb.append("for (var XcbjbyrfBDcxsdwgZxJA = 0, XcbjbyrfBDcxsdxwgZxJA = 0; XcbjbyrfBDcxsdwgZxJA < "+Integer.valueOf(len)+ ";XcbjbyrfBDcxsdwgZxJA++){XcbjbyrfBDcxsdxwgZxJA = x.charCodeAt(XcbjbyrfBDcxsdwgZxJA);XcbjbyrfBDcxsdxwgZxJA --;XcbjbyrfBDcxsdxwgZxJA -=XcbjbyrfBDcxsdwgZxJA;x = x.substr(0, XcbjbyrfBDcxsdwgZxJA) + String.fromCharCode(XcbjbyrfBDcxsdxwgZxJA & 0xFFFF) + x.substr(XcbjbyrfBDcxsdwgZxJA + 1);}");
        return sb.toString();
    }


    //  Method to encode the C Strings
    public static String encode_C(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length()+1;
        sb.append("wchar_t x["+Integer.valueOf(len)).append("] = {");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            int trick =decpo + i + 1;
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
        sb.append("0x0"+Integer.toHexString(len)).append("};");
        sb.append("for (unsigned int FAxwgxESAZJA = 0, CsjhcAxwgZJA = 0; FAxwgxESAZJA < "+Integer.valueOf(len)+ " ; FAxwgxESAZJA++){CsjhcAxwgZJA = x[FAxwgxESAZJA];CsjhcAxwgZJA --;CsjhcAxwgZJA -= FAxwgxESAZJA;x[FAxwgxESAZJA] = CsjhcAxwgZJA;}");
        return sb.toString();
    }


    
// Method that encodes a string into C# Unicode escapes with simple index-based obfuscation.
    public static String encode_Cs(String code) {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        sb.append("String x = \"");
        for (int i = 0; i < code.length(); i++) {
            int decpo = code.codePointAt(i);
            // I just used a simple subtraction here lol cause I have not in all above
            int trick =decpo - i;
            String hex = Integer.toHexString(trick);
            sb.append("\\u");
            for (int j = 0; j < hex.length(); j++) {
                sb.append("0");
            }
            sb.append(hex);
        }
        sb.append("\";");
        sb.append("for (int CsHsXvsYjShWfA = 0, CsHsXvXsxYjShWfA = 0; CsHsXvsYjShWfA < "+Integer.valueOf(len)+ "; CsHsXvsYjShWfA++){CsHsXvXsxYjShWfA = x[CsHsXvsYjShWfA];CsHsXvXsxYjShWfA += CsHsXvsYjShWfA;x = x.Substring(0, CsHsXvsYjShWfA) + (char)(CsHsXvXsxYjShWfA & 0xFFFF) +x.Substring(CsHsXvsYjShWfA + 1);}");
        return sb.toString();
    }
}
