public class HaveFun {
    
    static final String CODED_MESSAGE = "=ESYt1WRgkXYkhGdylmQgkHcwFGS";

    public static void main(String[] args) {
        
        System.out.println("Use the reverse() method to get the String you're supposed to decode.\n\n"
                + "HINT: It may or may not be in Base64");
        
        // also maybe call the method below
        reverse(CODED_MESSAGE);

    }
    
    public static void reverse(String input) {
        byte[] strAsByteArr = input.getBytes();
        byte[] result = new byte[strAsByteArr.length];
        
        for (int i = 0; i < strAsByteArr.length; i++) {
            result[i] = strAsByteArr[strAsByteArr.length - i - 1];
        }
        
        System.out.println("\nNormal CODED_MESSAGE: " + new String(result));
    }
    
}
