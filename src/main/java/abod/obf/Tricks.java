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
}
