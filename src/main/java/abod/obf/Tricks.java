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
}
