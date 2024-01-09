package dummyFile;

public class InputCheck {

	private String str;

	public InputCheck(String str) {
		if (str == null) {
			//nullの場合は空文字を渡すためにthisキーワードをつけた。
			//isNumeric()でisEmptyを使って空文字チェックする。するとfalseが返り、コンソールに「数値のみ入力してください。」と出る。
			this.str = "";
		}
		/*thisキーワードを指定しないと、java.lang.NullPointerExceptionが起こる。
		 * this指定が無いとコンストラクタの引数が自分自身を代入し27行目の条件式「this.str.length()」がnullになり例外が起こったため、thisキーワードをつけて解決した。
		 * thisキーワードは、ローカル変数とフィールドを区別する。
		 * */
		this.str = replaceFullWidthWithHalfWidth(str); 
		//必要かわからないけど、ECサイト制作で使用した技術が使えると思い、全角を半角に置換しました。
	}

	protected boolean isNumeric() {
		
		if (this.str == "") {
		return false;
		}

		for (int i = 0; i < this.str.length(); i++) {
			char c = this.str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	protected boolean checkSize() {
		//数値の置換の際、空文字だとNumberFormatExceptionが発生したため空文字も同時に制御した。
	    if (!this.str.isEmpty() && this.str.length() <= 10) {
	        try {
	            if (Math.abs(Integer.parseInt(this.str)) <= 1024 * 1024 * 100) {
	                return true;
	            }
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    return false;
	}
	
	private String replaceFullWidthWithHalfWidth(String input) {
        StringBuilder converted = new StringBuilder();
        if (input == null) {
            return null;
        }
        for (char c : input.toCharArray()) {
            if (c >= '０' && c <= '９') {
                converted.append((char) (c - '０' + '0'));
            } else {
                converted.append(c);
            }
        }
        return converted.toString();
    }

}
