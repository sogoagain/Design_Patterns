import java.util.*;

/**
 * Created by sogoesagain on 2017. 3. 16..
 *
 * GeneticStrategy는 제네틱(유전) 알고리즘을 이용한 전략이다.
 * 새로운 전략을 구상하다가 예전에 인터넷을 통해 한 고등학생이 블리자드의 카드게임인 하스스톤의 카드 구성을 제네틱 알고리즘을 이용해 연구한 자료를 본 기억이 떠올랐다.
 * 또한 화학분야 석사 과정을 밟고있는 친구가 자신의 랩실에서 제네틱 알고리즘을 이용해 원자의 위치를 시뮬레이션 하는 방법에 관한 연구를 진행한 적이 있다며 말해주었다.
 * 언젠가 한번 제네틱 알고리즘을 이용해 문제해결을 하고 싶었는데 작지만 이번 기회에 구현해 보았다.
 *
 * 아래의 두 자료를 중점적으로 참조하여 작성하였다.
 * 1. http://untitledtblog.tistory.com/110
 * 2. http://www.aistudy.com/biology/genetic/operator_moon.htm#_bookmark_1a40c20
 *
 * GeneticStrategy을 구현하는데 제네틱 알고리즘에서 선택한 세부 사항은 다음과 같다.
 * 1. 1세대 염색체들은 랜덤 값으로 생성하였다.
 * 2. 적합도 함수는 각 게임 결과에 진 경우 0, 비긴 경우 1, 이긴 경우 3의 값을 할당하여 하나의 염색체의 결과값을 모두 더한값으로 정의하였다.
 * 3. 부모세대를 선택하는 방법으로는 룰렛 휠 선택을 사용하였다.
 * 4. 자식을 생성하기 위한 교차 연산(crossover)로는 1점 교차를 사용하였다.
 * 5. 게임 횟수가 작을때 빠른 진화를 하기 위해 부모 세대 중 가장 적합도가 높은 개체를 자식 세대로 가져오는 Elite selection을 확률 100%로 사용하였다.
 * 6. 변이 연산은 PROBABILITY_OF_MUTATION의 확률로 일어나며 임의의 염색체 하나에 대해 원래 가지고 있는 값을 제외한 나머지 두 값에서 랜덤으로 변이가 일어난다.
 *
 * GeneticStrategy의 결과는 다음과 같다.
 * GeneticStrategy의 성능을 입증하고자 SequentialStrategy 전략을 구현하였다.
 * 제네틱 알고리즘의 경우 상대방이 일정한 패턴으로 게임을 진행할 경우 우수한 성능을 보여주었다.
 * 즉 도달할 목표치가 있고 충분한 진화 횟수(게임진행 횟수)가 주어진다면 게임에서 승리하는 경향을 보여주었다.
 * 그 예가 SequentialStrategy 전략이다.
 * 특히 GeneticStrategy의 염색체를 구성하는 유전자들의 수(NUMBER_OF_GENES)와 SequentialStrategy의 패의 수가 같을 때 최상의 결과를 보여주었다.
 * 그러나, RandomStrategy나 LastResultBasedStrategy와 같이 랜덤한 패를 내는 경우가 있는 전략에 대해서는 좋은 결과를 보여주지 못하였다.
 * 사실 랜덤한 패에 대해서도 유전적 진화를 통해 전략을 파악하여 이기는 것을 원했으나 그러지 못하였다.
 *
 * LastResultBasedStrategy에 강한 전략을 구상하고자 SemiGeneticStrategy 전략을 작성하였다.
 */
public class GeneticStrategy implements PlayingStrategy {
    private static final int NUMBER_OF_GENES = 4;               // 하나의 염색체를 구성하는 유전자들의 수
    private static final int POPULATION = 4;                    // 한 세대를 이루는 염색체의 수
    private static final int PROBABILITY_OF_MUTATION = 5;       // 돌연변이 발생 확률

    private List<Chromosome> generation = new ArrayList<>();    // 한 세대
    private int chromosomeIndex = 0;                            // 현재 경기를 뛰고있는 염색체의 인덱스
    private int handIndex = 0;                                  // 현재 낸 손의 인덱스

    private Random rand = new Random();                         // 난수 생성기

    // 염색체 클래스, 염색체들이 모여 한 세대를 이룬다.
    private class Chromosome {
        private List<Gene> genes = new ArrayList<>();           // 하나의 염색체를 이루는 유전자들
        private int fitness = 0;                                // 염색체의 적합도
        private Random rand = new Random();                     // 난수 발생기

        // 유전자 클래스, 유전자들이 모여 하나의 염색체를 이룬다.
        private class Gene {
            private HandType hand;
            private int characteristic;

            private Gene(int handIndex) {
                hand = HandType.valueOf(handIndex);
                characteristic = 0;
            }
        }

        private Chromosome() {
            // 1세대 염색체 생성 (초기 유전자들은 랜덤으로 생성된다.)
            for (int i = 0; i < NUMBER_OF_GENES; i++) {
                genes.add(new Gene(rand.nextInt(3)));
            }
        }

        // 적합도 계산.
        // 하나의 염색체를 구성하는 각 유전자들이 갖고 있는 특성(characteristic)들을 더한 것이 적합도다.
        private void calculateFitness() {
            fitness = 0;

            for (Gene gene : genes) {
                fitness += gene.characteristic;
            }
        }

        // 적합도를 반환
        private int getFitness() {
            return fitness;
        }

        // index에 해당하는 유전자를 반환
        private Gene getGene(int index) {
            return genes.get(index);
        }

        // index에 해당하는 유전자의 손을 매개변수로 들어온 유전자의 손으로 설정
        private void setHand(int index, Gene newGene) {
            genes.get(index).hand = newGene.hand;
        }

        // index에 해당하는 유전자의 손을 반환함
        private HandType getHand(int index) {
            return genes.get(index).hand;
        }

        // 돌연변이가 발생 하였을 때 돌연변이를 진행하는 함수
        // 돌연변이는 염색체의 한 유전자에서 발생되며 원래 가지고 있던 값을 제외한 나머지 값들 중 하나를 임의로 갖게된다.
        private void mutate(int index) {
            genes.add(index, new Gene((genes.get(index).hand.ordinal() + rand.nextInt(2) + 1) % 3));
        }

        // 염색체를 구성하는 유전자들의 정보를 문자열로 구성하여 반환함
        @Override
        public String toString() {
            String content = "염색체[ ";
            for(Gene gene:genes) {
                content += gene.hand.toString() + " ";
            }
            content += "]";
            return content;
        }

        // 유전자의 형질을 설정함
        private void setCharacteristic(int index, int characteristic) {
            genes.get(index).characteristic = characteristic;
        }


        // 염색체를 이루고 있는 유전자들의 형질 정보를 초기화함
        private Chromosome clearCharacteristic() {
            for (Gene temp : genes) {
                temp.characteristic = 0;
            }
            return this;
        }
    }

    GeneticStrategy() {
        // 1세대 생성
        for (int i = 0; i < POPULATION; i++) {
            Chromosome firstGenerationParent = new Chromosome();
            generation.add(firstGenerationParent);
        }
    }

    // 룰렛 휠 방식을 이용하여 번식 주체가 될 부모를 선정함
    private Chromosome rouletteWheelSelect() {
        int selectedParentIndex = 0;

        // 한 세대를 이루는 염색체들의 적합도 합 계산
        int sumOfFitness = 0;
        for (Chromosome person : generation) {
            sumOfFitness += person.getFitness();
        }

        // 한 세대를 이루는 얌색체들의 적합도가 0이라면 모든 경우에서 진 것
        // 이 경우 랜덤으로 부모를 선택함
        // 그렇지 않은 경우 룰렛 휠 방식으로 부모를 선택함
        if (sumOfFitness == 0) {
            selectedParentIndex = rand.nextInt(generation.size());
        } else {
            int point = rand.nextInt(sumOfFitness);
            int sum = 0;

            for (int i = 0; i < POPULATION; i++) {
                sum += generation.get(i).getFitness();
                if (point < sum) {
                    selectedParentIndex = i;
                    break;
                }
            }
        }

        // 선택된 부모를 염색체 한 세대를 나타내는 generation 리스트에서 삭제함.
        // 자식 하나를 생성하기 위해 부모 2개를 선택하게 되는데, 이미 선택된 부모는 리스트에서 삭제함으로 중복된 부모가 선택되어지는 것을 막음.
        Chromosome selectedParent = generation.get(selectedParentIndex);
        generation.remove(selectedParentIndex);

        // 선택된 부모 반환
        return selectedParent;
    }

    // 선택된 부모에 대하여 교차 연산을 진행해 자식을 생성한다.
    // 교차되는 포인트는 1곳으로 랜덤으로 선택된다. (1점 교차)
    private Chromosome crossover(List<Chromosome> parents) {
        int crossPoint = rand.nextInt(NUMBER_OF_GENES);
        Chromosome child = new Chromosome();

        // 교차연산 진행
        for (int i = 0; i <= crossPoint; i++) {
            child.setHand(i, parents.get(0).getGene(i));
        }
        for (int i = crossPoint + 1; i < NUMBER_OF_GENES; i++) {
            child.setHand(i, parents.get(1).getGene(i));
        }

        return child;
    }

    // 돌연변이 발생를 발생시킨다. 돌연변이는 PROBABILITY_OF_MUTATION의 확률로 발생한다.
    private void occurMutation(Chromosome child) {
        boolean isMutationOccurrs = rand.nextInt(100) < PROBABILITY_OF_MUTATION;
        if (isMutationOccurrs) {
            int occurredIndex = rand.nextInt(NUMBER_OF_GENES);
            child.mutate(occurredIndex);
        }
    }

    // 다음 세대로의 진화
    private List<Chromosome> evolve() {
        List<Chromosome> nextGeneration = new ArrayList<>();

        // 현 세대(부모세대)를 이루고 있는 각 염색체들의 적합도를 계산한다.
        for (Chromosome parent : generation) {
            parent.calculateFitness();
        }

        // 한 세대를 이루는 염색체 수보다 하나 적은 수의 자식을 교배를 통해 생성한다.
        for (int i = 0; i < POPULATION - 1; i++) {
            // 룰렛휠 선택을 통해 부모 2개를 차출한다.
            List<Chromosome> parents = new ArrayList<>();
            parents.add(rouletteWheelSelect());
            parents.add(rouletteWheelSelect());

            // 부모 선택이 완료 되었으면 현세대를 나타내는 generation리스트에 선택된 부모들을 다시 추가한다.
            generation.add(parents.get(0));
            generation.add(parents.get(1));

            // 교차연산을 통해 자식을 생성한다.
            Chromosome child = crossover(parents);
            // 돌연변이를 발생시킨다
            occurMutation(child);
            // 다음 세대를 나타내는 nextGeneration리스트에 최종적으로 탄생한 자식을 추가한다.
            nextGeneration.add(child);
        }

        // 현 세대에서 최고의 적합도를 보여주는 부모를 다음세대로 가져온다. (Elite selection)
        nextGeneration.add(getBestParent().clearCharacteristic());

        // 생성된 다음 세대를 반환한다.
        return nextGeneration;
    }

    // 햔 세대에서 최고의 적합도를 나타내는 Elite 염색체를 찾는다.
    private Chromosome getBestParent() {
        Chromosome bestParent = null;
        int bestFitness = -1;
        for (Chromosome parent : generation) {
            if (parent.getFitness() > bestFitness) {
                bestParent = parent;
                bestFitness = parent.getFitness();
            }
        }
        return bestParent;
    }

    // 다음에 낼 손을 반환한다.
    public HandType nextHand() {
        return generation.get(chromosomeIndex).getHand(handIndex);
    }

    // 경기 결과를 통해 현재 경기를 하고 있는 염색체의 유전자 characteristic을 설정한다.
    // 모든 염색체의 경기가 완료되었을 경우 진화를 통해 다음 세대를 생성한다.
    public void recordHistory(ResultType currResult) {
        int characteristic = 0;
        switch (currResult) {
            case LOST:
                characteristic = 0;
                break;
            case DRAWN:
                characteristic = 1;
                break;
            case WON:
                characteristic = 3;
                break;
        }
        generation.get(chromosomeIndex).setCharacteristic(handIndex, characteristic);

        handIndex++;
        if (handIndex == NUMBER_OF_GENES) {
            handIndex = 0;
            chromosomeIndex++;
            if (chromosomeIndex == POPULATION) {
                chromosomeIndex = 0;
                // 진화한다.
                generation = evolve();
            }
        }
    }

    @Override
    public void initializeByPlayer() {
    }
}
