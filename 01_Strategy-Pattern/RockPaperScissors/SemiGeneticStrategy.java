import java.util.*;

/**
 * Created by sogoagain on 2017. 3. 16..
 *
 * SemiGeneticStrategy은 제네틱 알고리즘이라고 하긴 힘들지만 제네틱 알고리즘에서 영감을 얻었다.
 * SemiGeneticStrategy은 1세대에 1개의 염색체만이 존재하고, 1개의 염색체는 3개의 유전자(패)를 가진다.
 * 진화를 통해 자식 염색체를 생성하는데 부모로부터 이긴 패들만을 받고 나머지 패는 랜덤으로 생성한다.
 * SemiGeneticStrategy은 LastResultBasedStrategy에 대해서 좋은 결과를 보여주었다.
 * 그 이유로 LastResultBasedStrategy는 게임에서 진 경우 다음 패가 하나로 정해지기 때문이다.
 * 이 특성을 이용해 SemiGeneticStrategy에서 이긴 패만을 취하고 진화하다보면 LastResultBasedStrategy를 이길 수 있는 3개 패의 나열이 생성된다.
 * 따라서 SemiGeneticStrategy전략이 LastResultBasedStrategy보다 우위를 선점하게 되는 것이다.
 */
public class SemiGeneticStrategy implements PlayingStrategy {
    private static final int NUMBER_OF_GENES = 3;
    private Chromosome generation = new Chromosome();
    private int handIndex = 0;

    private class Chromosome {
        private class Gene {
            private HandType hand;
            private int characteristic;

            private Gene(int handIndex) {
                hand = HandType.valueOf(handIndex);
                characteristic = 0;
            }
        }

        private List<Gene> genes = new ArrayList<>();
        private Random random = new Random();

        private Chromosome() {
            for (int i = 0; i < NUMBER_OF_GENES; i++) {
                genes.add(new Gene(random.nextInt(3)));
            }
        }

        private void evolve() {
            for(ListIterator<Gene> genesIterator = genes.listIterator(); genesIterator.hasNext();) {
                Gene gene = genesIterator.next();
                if(gene.characteristic != 1) {
                    genesIterator.remove();
                    genesIterator.add(new Gene(random.nextInt(3)));
                }
            }
        }

        private HandType getHand(int handIndex) {
            return genes.get(handIndex).hand;
        }

        private void setCharacteristic(int handIndex, int value) {
            genes.get(handIndex).characteristic = value;
        }
    }

    @Override
    public HandType nextHand() {
        return generation.getHand(handIndex);
    }

    @Override
    public void recordHistory(ResultType currResult) {
        generation.setCharacteristic(handIndex, currResult.ordinal() - 1);
        handIndex++;

        if (handIndex == NUMBER_OF_GENES) {
            generation.evolve();
            handIndex = 0;
        }
    }

    @Override
    public void initializeByPlayer() {
    }
}
