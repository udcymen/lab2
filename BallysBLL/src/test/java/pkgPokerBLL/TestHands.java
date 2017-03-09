package pkgPokerBLL;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgPokerEnum.eHandStrength;
import pkgPokerEnum.eRank;
import pkgPokerEnum.eSuit;

public class TestHands {
	
	@Test
	public void TestRoyalFlush(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.JACK,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.QUEEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.RoyalFlush.getHandStrength(),
					h.getHandScore().getHandStrength().getHandStrength());
			
		assertEquals(eRank.ACE.getiRankNbr(),
					h.getHandScore().getHiHand().getiRankNbr());
			
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestHandStraightFlush(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.StraightFlush.getHandStrength(),
					h.getHandScore().getHandStrength().getHandStrength());
			
		assertEquals(eRank.SIX.getiRankNbr(),
					h.getHandScore().getHiHand().getiRankNbr());
			
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestHandFourOfAKind(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(),
					h.getHandScore().getHandStrength().getHandStrength());
			
		assertEquals(eRank.SIX.getiRankNbr(),
					h.getHandScore().getHiHand().getiRankNbr());
			
		assertEquals(1,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.TWO,
				h.getHandScore().getKickers().get(0).geteRank());
	}
	
	@Test
	public void TestHandFourOfAKind1(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.HEARTS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.FourOfAKind.getHandStrength(),
					h.getHandScore().getHandStrength().getHandStrength());
			
		assertEquals(eRank.SIX.getiRankNbr(),
					h.getHandScore().getHiHand().getiRankNbr());
			
		assertEquals(1,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.EIGHT,
				h.getHandScore().getKickers().get(0).geteRank());
	}

	@Test
	public void TestFullHouse() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	@Test
	public void TestFullHouse1() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.FullHouse.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		assertEquals(0,h.getHandScore().getKickers().size());
	}
	
	
	@Test
	public void TestThreeofAKind() {
		
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.FOUR.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(2,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.THREE,
				h.getHandScore().getKickers().get(0).geteRank());
	}
		
		@Test
		public void TestThreeofAKind1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(2,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.FIVE,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestThreeofAKind2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.ThreeOfAKind.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.SIX.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(2,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.EIGHT,
				h.getHandScore().getKickers().get(0).geteRank());
	}
		@Test
		public void TestHandFlush() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Flush.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(0,h.getHandScore().getKickers().size());
	}
		
		@Test
		public void TestHandStraight() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SEVEN,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.NINE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TEN,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.HEARTS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Straight.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.TEN.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(0,h.getHandScore().getKickers().size());
	}
		
		@Test
		public void TestHandStraight1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.SPADES));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.FOUR,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.HEARTS));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Straight.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(0,h.getHandScore().getKickers().size());
	}
		
		@Test
		public void TestTwoPair() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.FIVE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		assertEquals(1,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.TWO,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestTwoPair1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.FIVE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.TWO.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		assertEquals(1,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.THREE,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestTwoPair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.FIVE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.TWO.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		assertEquals(1,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.FIVE,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestAcesAndEights() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.KING,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.TwoPair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(eRank.EIGHT.getiRankNbr(),
				h.getHandScore().getLoHand().getiRankNbr());
		
		assertEquals(1,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.KING,
				h.getHandScore().getKickers().get(0).geteRank());
		
		assertEquals(Hand.isAcesAndEights(h, h.getHandScore()),
				true);
		}
		
		
		@Test
		public void TestHandPair() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.ACE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(3,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.EIGHT,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestHandPair1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.EIGHT.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(3,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.ACE,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestHandPair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.SIX.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(3,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.ACE,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		@Test
		public void TestHandPair3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.Pair.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.THREE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(3,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.ACE,
				h.getHandScore().getKickers().get(0).geteRank());
		}
		
		
		@Test
		public void TestHandHighHand() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eRank.ACE,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.EIGHT,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.SIX,eSuit.CLUBS));
		h.AddCardToHand(new Card(eRank.TWO,eSuit.DIAMONDS));
		h.AddCardToHand(new Card(eRank.THREE,eSuit.SPADES));		
		h.EvaluateHand();
		
		assertEquals(eHandStrength.HighCard.getHandStrength(),
				h.getHandScore().getHandStrength().getHandStrength());
		
		assertEquals(eRank.ACE.getiRankNbr(),
				h.getHandScore().getHiHand().getiRankNbr());
		
		assertEquals(4,h.getHandScore().getKickers().size());
		
		assertEquals(eRank.EIGHT,
				h.getHandScore().getKickers().get(0).geteRank());
		}
}
