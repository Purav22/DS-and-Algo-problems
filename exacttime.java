import java.util.HashMap;

public class exacttime {
	public static void main(String[] args) {

		HashMap<String, String> map = new HashMap<>();
		map.put("0", "1");
		map.put("1", "2");
		map.put("2", "3");
		map.put("3", "4");
		map.put("4", "5");
		map.put("5", "6");
		map.put("6", "7");
		map.put("7", "8");
		map.put("8", "9");
		map.put("9", "A");
		map.put("A", "B");
		map.put("B", "C");
		map.put("C", "D");
		map.put("D", "E");
		map.put("E", "F");
		map.put("F", "G");
		map.put("G", "H");
		map.put("H", "I");
		map.put("I", "J");
		map.put("J", "K");
		map.put("K", "L");
		map.put("L", "M");
		map.put("M", "N");
		map.put("N", "O");
		map.put("O", "P");
		map.put("P", "Q");
		map.put("Q", "R");
		map.put("R", "S");
		map.put("S", "T");
		map.put("T", "U");
		map.put("U", "V");
		map.put("V", "W");
		map.put("W", "X");
		map.put("X", "Y");
		map.put("Y", "Z");

        String id = "BZZ";
       
		String lastChar = Character.toString(id.charAt(2));
        String corValue = "0";
        if(map.containsKey(lastChar)){
            corValue = map.get(lastChar);
        }
		
		String last2Char = Character.toString(id.charAt(1));
        String cor2Value = "0";
        if(map.containsKey(last2Char)){
            cor2Value = map.get(last2Char);
        }
		
		String last3Char = Character.toString(id.charAt(0));
        String cor3Value = "0";
        if(map.containsKey(last3Char)){
            cor3Value = map.get(last3Char);
        }

		if (corValue.equals("0")) {

			
			id = id.substring(0, 2) + corValue;
			if(cor2Value.equals("0")) {
				id = id.substring(0,1)+ cor2Value + id.substring(2);
			   id = cor3Value + id.substring(1);
            }
            else{
                id = id.substring(0,1)+ cor2Value + id.substring(2);
            }
            
		}
		else {

			id = id.substring(0, 2) + corValue;

        }
    
		System.out.println(id);

	}

}