package math;


import java.util.*;

public class StatisticDistribution {

    /**
     * feature：根据权重生成概率分布
     *
     */

    // prob: serverInfoIndex
    private NavigableMap<Double, Integer> distribution;

    private ArrayList<Integer> weights = new ArrayList<>();

    private HashMap<Integer, List<Integer>> total = new HashMap<>();


    private void updateDistribution() {
        this.distribution = new TreeMap<>();
        int cnt = weights.size();
        int allWeight = 0;
        for (int i = 0; i < weights.size(); ++i) {
            int weight = weights.get(i);
            if (total.get(weight) == null) {
                List<Integer> l = new ArrayList<>();
                total.put(weight, l);
            }
            List<Integer> servers = total.get(weight);
            servers.add(i);
            allWeight += weight;
        }

        double sum = 0.0;
        Set<Map.Entry<Integer, List<Integer>>> entries = total.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry : entries) {
            int key = entry.getKey();
            for(Integer index : entry.getValue()) {
                double delta = (double) key / allWeight;
                sum += delta;
                this.distribution.put(sum, index);
            }
        }
    }


    public int get() {
        double rnd = new Random().nextDouble();
        int index = distribution.ceilingEntry(rnd).getValue();
//        List<Integer> servers = total.get(key);
//        int rndInt = new Random().nextInt(servers.size());
//        return servers.get(rndInt);
        return index;
    }


    public static void main(String[] args) {

        StatisticDistribution sd = new StatisticDistribution();
        HashMap<String, Integer> accumulator;
        String[] servers = {"a", "b"};
        sd.weights.addAll(Arrays.asList(1, 10));
        sd.updateDistribution();
        for(int j = 0 ; j < 10 ; ++ j) {
            accumulator = new HashMap<>();
            for(int i = 0 ; i < 100 ; ++ i) {
                int index = sd.get();
                String server = servers[index];
                accumulator.put(server,accumulator.getOrDefault(server, 0)+1);
            }
            System.out.println(accumulator.toString());

        }
    }

}
