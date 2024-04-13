package librarysystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

public class Util {
	public static final Color DARK_BLUE = Color.BLUE.darker();
	public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
	public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19); //dark green
	public static final Color LINK_AVAILABLE = DARK_BLUE;
	public static final Color LINK_NOT_AVAILABLE = Color.gray;
	//rgb(18, 75, 14)
	
	public static Font makeSmallFont(Font f) {
        return new Font(f.getName(), f.getStyle(), (f.getSize()-2));
    }
	
	public static void adjustLabelFont(JLabel label, Color color, boolean bigger) {
		if(bigger) {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()+2));
			label.setFont(f);
		} else {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()-2));
			label.setFont(f);
		}
		label.setForeground(color);
		
	}
	/** Sorts a list of numeric strings in natural number order */
	public static List<String> numericSort(List<String> list) {
		Collections.sort(list, new NumericSortComparator());
		return list;
	}
	
	static class NumericSortComparator implements Comparator<String>{
		@Override
		public int compare(String s, String t) {
			if(!isNumeric(s) || !isNumeric(t)) 
				throw new IllegalArgumentException("Input list has non-numeric characters");
			int sInt = Integer.parseInt(s);
			int tInt = Integer.parseInt(t);
			if(sInt < tInt) return -1;
			else if(sInt == tInt) return 0;
			else return 1;
		}
	}
	
	public static boolean isNumeric(String s) {
		if(s==null) return false;
		try {
			Integer.parseInt(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}
	
	 public static boolean isValidISBN10(String isbn) {
	        isbn = isbn.replaceAll("[^0-9X]", "");
	        if (isbn.length() != 10) return false;
	        
	        int sum = 0;
	        for (int i = 0; i < 9; i++) {
	            int digit = isbn.charAt(i) - '0';
	            if (digit < 0 || digit > 9) return false;
	            sum += digit * (i + 1);
	        }
	        
	        if (isbn.charAt(9) == 'X') {
	            sum += 10 * 10;
	        } else {
	            int digit = isbn.charAt(9) - '0';
	            if (digit < 0 || digit > 9) return false;
	            sum += digit * 10;
	        }
	        
	        return sum % 11 == 0;
	    }
	    
	    // Function to validate ISBN-13
	    public static boolean isValidISBN13(String isbn) {
	        isbn = isbn.replaceAll("[^0-9]", "");
	        if (isbn.length() != 13) return false;
	        
	        int sum = 0;
	        for (int i = 0; i < 12; i++) {
	            int digit = isbn.charAt(i) - '0';
	            if (digit < 0 || digit > 9) return false;
	            sum += (i % 2 == 0) ? digit : digit * 3;
	        }
	        
	        int checkDigit = isbn.charAt(12) - '0';
	        return (10 - (sum % 10)) % 10 == checkDigit;
	    }
	    
	    public static boolean isValidISBN(String s) {
	    	return isValidISBN10(s) || isValidISBN13(s);
	    }
	    public static boolean isValidPhoneNumber(String phoneNumber) {
	        // Regular expression for a basic telephone number
	        String regex = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$";
	        
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(phoneNumber);
	        
	        return matcher.matches();
	    }
	    
	    public static int getNextKey(Map<String, ?> map) {
	        if (map.isEmpty()) {
	            return 1; // If the map is empty, return 1
	        } else {
	            int maxKey = Integer.MIN_VALUE;
	            for (String key : map.keySet()) {
	                try {
	                    int parsedKey = Integer.parseInt(key); // Parse the key to integer
	                    maxKey = Math.max(maxKey, parsedKey); // Find the maximum key
	                } catch (NumberFormatException e) {
	                	maxKey=1;
	                    // Ignore non-integer keys
	                }
	            }
	            return maxKey == Integer.MIN_VALUE ? 1 : maxKey + 1; // Increment the maximum key and return
	        }
	    }
	    public static boolean isValidZipCode(String zipCode) {
	        // Regular expression for US zip codes (5-digit or 5-digit followed by optional 4-digit extension)
	        String regex = "^\\d{5}(?:[-\\s]\\d{4})?$";
	        
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(zipCode);
	        
	        return matcher.matches();
	    }
	    public static boolean isValidState(String state) {
	        // Regular expression for US state names or their abbreviations
	        String regex = "^(?i)AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY$";
	        
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(state);
	        
	        return matcher.matches();
	    }
	    
	    public static String getConcatnatedFieldMessages(HashMap<String, String> map) {
	        StringBuilder concatenatedValues = new StringBuilder();
	        for (String value : map.values()) {
	            concatenatedValues.append(value).append("\n");
	        }
	        System.out.println(concatenatedValues.toString());
	        return concatenatedValues.toString();
	    }

	public static void main(String[] args) {
		isValidZipCode("52557");
	}
}
