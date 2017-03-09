package pkgPokerBLL;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import pkgPokerEnum.eCardNo;
import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;



public class Hand {

	private UUID HandID;
	private boolean bIsScored;
	private HandScore HS;
	private ArrayList<Card> CardsInHand = new ArrayList<Card>();
	
	public Hand()
	{
		HandID = UUID.randomUUID();
		
	}
	
	public void AddCardToHand(Card c)
	{
		CardsInHand.add(c);
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}
	
	public HandScore getHandScore()
	{
		return HS;
	}
	
	public Hand EvaluateHand()
	{
		Hand h = Hand.EvaluateHand(this);
		return h;
	}
	
	private static Hand EvaluateHand(Hand h)  {

		Collections.sort(h.getCardsInHand());
		
		//	Another way to sort
		//	Collections.sort(h.getCardsInHand(), Card.CardRank);
		
		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pkgPokerBLL.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pkgPokerBLL.Hand.class;
				cArg[1] = pkgPokerBLL.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
 			}

			h.bIsScored = true;
			h.HS = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}
	
	
	
	
	
	
	

	public static boolean isHandRoyalFlush(Hand h, HandScore hs){
		
		boolean isHandRoyalFlush = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (Hand.isHandStraightFlush(h, hs)
			&& h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE 
			&& h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank() == eRank.TEN){
			isHandRoyalFlush = true;
			hs.setHandStrength(eHandStrength.RoyalFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
		}
		return isHandRoyalFlush;
	}
	

	public static boolean isHandStraightFlush(Hand h, HandScore hs)
	{
		boolean isHandStraightFlush = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (Hand.isHandFlush(h, hs)
			&& Hand.isHandStraight(h, hs)){
			isHandStraightFlush = true;
			hs.setHandStrength(eHandStrength.StraightFlush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
		}
		return isHandStraightFlush;
	}	
	

	public static boolean isHandFourOfAKind(Hand h, HandScore hs)
	{
		boolean isHandFourOfAKind = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == 
				(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank())){
			isHandFourOfAKind = true;
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank()){
			isHandFourOfAKind = true;
			hs.setHandStrength(eHandStrength.FourOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		}
		
		return isHandFourOfAKind;
	}	
	
	public static boolean isHandFullHouse(Hand h, HandScore hs) {

		boolean isFullHouse = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNo.FifthCard.getCardNo()).geteRank())) {
			isFullHouse = true;
			hs.setHandStrength(eHandStrength.FullHouse);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		}

		return isFullHouse;

	}
	
	
	public static boolean isHandFlush(Hand h, HandScore hs)
	{
		boolean isHandFlush = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == 
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteSuit())
					&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == 
						h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteSuit())
						&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == 
							h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteSuit())
							&& (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteSuit() == 
								h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteSuit())){
			isHandFlush = true;
			hs.setHandStrength(eHandStrength.Flush);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
		}
		return isHandFlush;
	}		
	
	
	public static boolean isHandStraight(Hand h, HandScore hs){
		
		boolean isHandStraight = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == eRank.ACE)
			&& (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == eRank.FIVE)
			&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == eRank.FOUR)
			&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == eRank.THREE)
			&& (h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank() == eRank.TWO)){
			isHandStraight = true;
			hs.setHandStrength(eHandStrength.Straight);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
		}else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() + 
				h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr())
					==
					(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank().getiRankNbr() +
							h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr())
					&& ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank().getiRankNbr() + 
							(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr()))
							== (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr() * 2))){
			isHandStraight = true;
			hs.setHandStrength(eHandStrength.Straight);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
		}
		return isHandStraight;
	}	
	
	
	public static boolean isHandThreeOfAKind(Hand h, HandScore hs)
	{
		boolean isHandThreeOfAKind = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.ThirdCard.getCardNo()).geteRank()){
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FourthCard.getCardNo()).geteRank()){
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNo.FifthCard.getCardNo()).geteRank()){
			isHandThreeOfAKind = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		}

		return isHandThreeOfAKind;
	}		
	
	
	public static boolean isAcesAndEights(Hand h, HandScore hs)
	{
		boolean isAcesAndEights = false;
		if (Hand.isHandTwoPair(h, hs)){
			if (hs.getHiHand() == eRank.ACE && 
					hs.getLoHand() == eRank.EIGHT){
				isAcesAndEights = true;
			}
		}
		
		return isAcesAndEights;
	}	

	public static boolean isHandTwoPair(Hand h, HandScore hs)
	{
		
		boolean isHandTwoPair = false;
		
		ArrayList<Card> kickers = new ArrayList<Card>();
		if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank().getiRankNbr() ==
					 h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr())){
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() ==
					 h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr())){
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		} else if ((h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank())
				&& (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank().getiRankNbr() ==
					 h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank().getiRankNbr())){
			isHandTwoPair = true;
			hs.setHandStrength(eHandStrength.TwoPair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
		} 
		return isHandTwoPair;
	}	
	
	
	public static boolean isHandPair(Hand h, HandScore hs)
	{
		boolean isHandPair = false;
		ArrayList<Card> kickers = new ArrayList<Card>();
		if (h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank()){
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if (h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank()){
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		} else if (h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank()){
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		}else if (h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank() == 
				h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()).geteRank()){
			isHandPair = true;
			hs.setHandStrength(eHandStrength.Pair);
			hs.setHiHand(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()).geteRank());
			hs.setLoHand(null);
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
			hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		}
		return isHandPair;
	}	

	
	public static boolean isHandHighCard(Hand h, HandScore hs)
	{
		boolean isHandHighCard = true;
		hs.setHandStrength(eHandStrength.HighCard);
		hs.setHiHand(h.getCardsInHand().get(eCardNo.FirstCard.getCardNo()).geteRank());
		hs.setLoHand(null);
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.SecondCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.ThirdCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.FourthCard.getCardNo()));
		hs.getKickers().add(h.getCardsInHand().get(eCardNo.FifthCard.getCardNo()));
		return isHandHighCard;
		
	}	
	


	
	
}
