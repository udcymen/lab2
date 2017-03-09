package pkgPokerEnum;

public enum eCardNo {

	FirstCard(0), SecondCard(1), ThirdCard(2), FourthCard(3), FifthCard(4);
	
	
	private int CardNo;
	
	private eCardNo(final int CardNo){
		this.CardNo = CardNo;
		}

	
	public int getCardNo(){
		return CardNo;
	}
	
}
